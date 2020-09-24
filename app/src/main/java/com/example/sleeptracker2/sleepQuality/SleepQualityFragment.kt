package com.example.sleeptracker2.sleepQuality

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
import com.example.sleeptracker2.databinding.FragmentSleepQualityFragmentBinding


class SleepQualityFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentSleepQualityFragmentBinding>(
            inflater,
            R.layout.fragment_sleep_quality_fragment,
            container,
            false
        )
        val application = requireNotNull(this.activity).application
        val arguments = SleepQualityFragmentArgs.fromBundle(requireArguments())

        val dataSource = SleepDatabase.getInstance(application).sleepDao
        val viewModelFactory = SleepQualityViewModelFactory(arguments.key, dataSource)

        // Get a reference to the ViewModel associated with this fragment.
        val sleepQualityViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(SleepQualityViewModel::class.java)

        sleepQualityViewModel.navigateToSleepTracker.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController()
                    .navigate(SleepQualityFragmentDirections.actionSleepQualityFragmentToSleepTrackerFragment())
                sleepQualityViewModel.doneNavigating()
            }
        })
        binding.sleepQualityViewModel = sleepQualityViewModel
        return binding.root
    }
}
