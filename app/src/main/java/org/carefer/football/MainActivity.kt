package org.carefer.football

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import org.carefer.football.base.BaseActivity
import org.carefer.football.databinding.ActivityMainBinding
import org.carefer.football.ui.home.presentation.viewmodel.HomeViewModel


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: HomeViewModel by viewModels<HomeViewModel>()
    private var binding: ActivityMainBinding? = null
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        initNavigation()
        initActions()

    }


    private fun initNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.home_host_fragment) as NavHostFragment
         navController = navHostFragment.navController


    }

    private fun initActions() {
        binding?.bottomBar?.onItemSelected = {

            when (it) {
                0 -> {
                    // navController.popBackStack()
                    navController.navigate(R.id.homeFragment)

                }
                1 -> {
                    // navController.popBackStack()
                    navController.navigate(R.id.favouritesFragment)
                }
            }
        }
    }
}