package com.pourkazemi.mahdi.maktab_hw_13_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.pourkazemi.mahdi.maktab_hw_13_2.adapter.RecycleAdapter
import com.pourkazemi.mahdi.maktab_hw_13_2.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MyViewModel by viewModels()
    @Inject
    lateinit var adapter: RecycleAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //viewModel.getFlickerList()
        //binding.lifecycleOwner = this
        //binding.viewModel = viewModel
        binding.rc.adapter = adapter

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.pictureListState.collect{
                    adapter.submitList(it)
                }
            }
        }
    }
}
