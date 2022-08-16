package com.task.channelapp.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.task.channelapp.databinding.ActivityMainBinding
import com.task.channelapp.utils.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, IMain>() {

    override val viewModel: IMain by viewModels<MainVM>()
    override fun getViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}