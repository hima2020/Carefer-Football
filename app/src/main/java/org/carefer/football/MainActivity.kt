package org.carefer.football

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.carefer.football.base.BaseActivity
import org.carefer.football.ui.home.presentation.viewmodel.HomeViewModel


@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val viewModel: HomeViewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}