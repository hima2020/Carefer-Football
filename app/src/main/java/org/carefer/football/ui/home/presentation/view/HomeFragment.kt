package org.carefer.football.ui.home.presentation.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.threetenabp.AndroidThreeTen.init
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericFastAdapter
import com.mikepenz.fastadapter.adapters.GenericItemAdapter
import com.mikepenz.fastadapter.adapters.ModelAdapter
import com.mikepenz.fastadapter.ui.items.ProgressItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.carefer.football.base.BaseFragment
import org.carefer.football.base.Result
import org.carefer.football.databinding.FragmentHomeBinding
import org.carefer.football.ui.home.data.model.DateModel
import org.carefer.football.ui.home.data.model.ItemModel
import org.carefer.football.ui.home.data.model.MatchModel
import org.carefer.football.ui.home.presentation.adapter.MatchListAdapter
import org.carefer.football.ui.home.presentation.items.DateItem
import org.carefer.football.ui.home.presentation.items.MatchItem
import org.carefer.football.ui.home.presentation.viewmodel.HomeViewModel
import org.carefer.football.utils.Utils
import org.carefer.football.views.EndlessScrollListener
import org.carefer.football.views.extentions.getDate
import org.carefer.football.views.extentions.mapTeamToUiModel
import java.util.*


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    var adapter: MatchListAdapter? = null
    private lateinit var _binding: FragmentHomeBinding
    private val binding get() = _binding

    private val viewModel: HomeViewModel by activityViewModels<HomeViewModel>()
    private var loadToTop = false
    private lateinit var fastAdapter: GenericFastAdapter
    private var footerAdapter = GenericItemAdapter()
    private var headerAdapter = GenericItemAdapter()
    private lateinit var endlessScroll: EndlessScrollListener

    private val itemAdapter = ModelAdapter { item: ItemModel ->
        when (item) {
            is MatchModel -> MatchItem(item, requireContext())
            is DateModel -> DateItem(item)
            else -> throw IllegalArgumentException()
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        initViews()
        init(activity?.application)

        setupRecyclerView()
        initObservation()

        getMatchList(Utils.getDates(Date(), true))

        return binding.root

    }

    private fun initViews() {
        adapter = MatchListAdapter(ArrayList())
        binding?.rvMatchList?.adapter = adapter
    }


    private fun setupRecyclerView() {

        binding?.rvMatchList?.layoutManager = LinearLayoutManager(requireContext())
        fastAdapter = FastAdapter.with(listOf(headerAdapter, itemAdapter, footerAdapter))

        endlessScroll = object : EndlessScrollListener() {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onLoadMore(fromTop: Boolean) {
                loadToTop = fromTop
                val dateArg: Pair<String, String>

                if (!loadToTop) {
                    footerAdapter.clear()
                    footerAdapter.add(ProgressItem())
                    dateArg = Utils.getDates(itemAdapter.models.last().shortDate, false)
                } else {
                    headerAdapter.clear()
                    headerAdapter.add(ProgressItem())
                    dateArg = Utils.getDates(itemAdapter.models.first().shortDate, true)
                }
                getMatchList(dateArg)
            }
        }
        binding?.rvMatchList?.adapter = fastAdapter
        binding?.rvMatchList.addOnScrollListener(endlessScroll)
    }

    private fun initObservation() {


        viewModel.resultResponse.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    var items: MutableList<ItemModel> = mutableListOf()

                    showLoadingDialog(false)

                    result.data?.let {
                        it.matches?.flatMap { it ->
                            listOf(
                                //change api model to ui model
                                MatchModel(
                                    it?.homeTeam?.mapTeamToUiModel()!!,
                                    it?.score?.fullTime?.home,
                                    it.awayTeam?.mapTeamToUiModel()!!,
                                    it.score?.fullTime?.away,
                                    it.utcDate!!,
                                    it.status!!,
                                    it.matchday.toString(),
                                    it.homeTeam?.crest,
                                    it.awayTeam?.crest
                                )
                            )

                        }?.groupBy { match ->
                            {
                                match.shortDate.date
                            }
                        }
                            //type(it): List<Map<Int, List<MatchModel>>>
                            .also { list ->
                                lifecycleScope.launch(Dispatchers.Main) {
                                    updateTeams(list)
                                }
                            }



                        endlessScroll.enable()
                        it?.matches?.toMutableList()?.sortBy { it?.utcDate }
                        if (loadToTop) {
                            headerAdapter.clear()
                            itemAdapter.add(0, items)
                        } else {
                            footerAdapter.clear()
                            itemAdapter.add(items)
                        }


//                        if (it.matches?.size != 0) {
//
//                            adapter?.addMatches(listOf(lastDateString))
//                            adapter?.addMatches(it.matches!!)
//                            endlessScroll.enable()
//                        }
                    }
                }
                Result.Status.LOADING -> {
                    showLoadingDialog(true)
                }
                Result.Status.ERROR -> {
                    showLoadingDialog(false)
                }
            }
        }
    }

    private suspend fun updateTeams(list: Map<() -> Int, List<MatchModel>>?) {

        var items: MutableList<ItemModel> = mutableListOf()
        var lastDate = ""


        list?.forEach { date ->

            //add DateModel on start of the each list
            if (date.value.first().utcDate.getDate() != lastDate) {
                lastDate = date.value.first().utcDate.getDate()
                items.add(DateModel(date.value.first().utcDate))
            }

            items.addAll(date.value)

        }
        //update recyclerView with the new list
        withContext(Dispatchers.Main) {
            updateRecyclerView(items)
        }

    }

    private fun updateRecyclerView(items: MutableList<ItemModel>) {

        endlessScroll.enable()
        items.sortBy { it.shortDate }
        if (loadToTop) {
            headerAdapter.clear()
            itemAdapter.add(0, items)
        } else {
            footerAdapter.clear()
            itemAdapter.add(items)
        }

    }

    private fun getMatchList(dateArgs: Pair<String, String>) {
        lifecycleScope.launch {
            endlessScroll.disable()
            viewModel.getMatchList(dateArgs)
        }
    }
}