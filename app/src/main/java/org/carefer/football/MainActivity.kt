package org.carefer.football

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import org.carefer.football.base.BaseActivity


@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}