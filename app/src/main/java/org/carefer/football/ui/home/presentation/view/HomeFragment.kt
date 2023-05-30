package org.carefer.football.ui.home.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.carefer.football.base.BaseFragment
import org.carefer.football.base.Result
import org.carefer.football.databinding.FragmentHomeBinding
import org.carefer.football.ui.home.presentation.adapter.MatchListAdapter
import org.carefer.football.ui.home.presentation.viewmodel.HomeViewModel


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    var adapter: MatchListAdapter? = null
    private lateinit var _binding: FragmentHomeBinding
    private val binding get() = _binding

    private val viewmodel: HomeViewModel by viewModels<HomeViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        getMatchList()
        initObservation()
        return binding.root

    }

    private fun initObservation() {

        viewmodel.resultResponse.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    showLoadingDialog(false)
                    result.data?.let {
                        adapter = MatchListAdapter(ArrayList())
                        binding?.rvMatchList?.adapter = adapter
                        adapter?.addMatches(it.matches!!)
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

    private fun getMatchList() {
        lifecycleScope.launch {
            viewmodel.getMatchList()
        }
    }
}