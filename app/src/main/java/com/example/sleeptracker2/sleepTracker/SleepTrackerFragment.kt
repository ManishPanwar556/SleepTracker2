package com.example.sleeptracker2.sleepTracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sleeptracker2.R
import com.example.sleeptracker2.database.SleepDatabase
import com.example.sleeptracker2.databinding.FragmentSleepTrackerFragmentBinding


class SleepTrackerFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentSleepTrackerFragmentBinding>(
            inflater,
            R.layout.fragment_sleep_tracker_fragment,
            container,
            false
        )

        val application= requireNotNull(this.activity).application
        val  dataSource=SleepDatabase.getInstance(application).sleepDao

        val viewModelFactory= SleepTrackerViewModelFactory(dataSource,application)

        val viewModel=ViewModelProvider(this,viewModelFactory).get(SleepViewModel::class.java)
        viewModel.navigateToSleepQuality.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.findNavController().navigate(SleepTrackerFragmentDirections.actionSleepTrackerFragmentToSleepQualityFragment(it.sleepId))
                viewModel.doneNavigating()
            }
        })
        binding.sleepViewModel=viewModel
        binding.setLifecycleOwner(this)
        return binding.root
    }


}