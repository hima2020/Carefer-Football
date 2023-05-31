package org.carefer.football.ui.favorites.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.carefer.football.base.BaseFragment
import org.carefer.football.databinding.FragmentFavoritesBinding
import org.carefer.football.ui.home.presentation.adapter.MatchListAdapter
import org.carefer.football.ui.home.presentation.viewmodel.HomeViewModel


@AndroidEntryPoint
class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>() {

    private lateinit var _binding: FragmentFavoritesBinding
    private val binding get() = _binding
    private val viewModel: HomeViewModel by activityViewModels<HomeViewModel>()
    private var adapter: MatchListAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        initRecyclerView()
        initObservation()
        return binding.root

    }

    private fun initRecyclerView() {
        binding?.rvMatchList?.layoutManager = LinearLayoutManager(requireContext())
        adapter = MatchListAdapter(ArrayList())
        adapter?.onItemClick = {
            viewModel.addToFav(it)
        }
        binding?.rvMatchList?.adapter = adapter
    }

    private fun initObservation() {
        viewModel.favorites.observe(viewLifecycleOwner) {
            lifecycleScope.launch(Dispatchers.Main)
            {
                if (it != null) {
                    adapter?.addMatches(it)
                }

            }


        }
    }

    override fun onStart() {
        super.onStart()
        getFavouritesMatchList()
    }

    private fun getFavouritesMatchList() {
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.getAllMatches()
        }
    }
}