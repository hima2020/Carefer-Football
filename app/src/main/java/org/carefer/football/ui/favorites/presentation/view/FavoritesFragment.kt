package org.carefer.football.ui.favorites.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import org.carefer.football.base.BaseFragment
import org.carefer.football.databinding.FragmentFavoritesBinding


@AndroidEntryPoint
class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>() {

    private lateinit var _binding: FragmentFavoritesBinding
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root

    }
}