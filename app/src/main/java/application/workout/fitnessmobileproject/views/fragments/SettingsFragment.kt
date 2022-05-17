package application.workout.fitnessmobileproject.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import application.workout.fitnessmobileproject.R
import application.workout.fitnessmobileproject.databinding.FragmentSettingsBinding
import application.workout.fitnessmobileproject.viewModels.SettingsViewModel

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null

    private val binding get() = _binding!!

    private val viewModel: SettingsViewModel by viewModels<SettingsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.settings.observe(this.viewLifecycleOwner) { settings ->
            val text = "${settings.user.firstname} ${settings.user.lastname}"
            binding.firstnameLastnameTextView.text = text
        }
        binding.shareProgress.setOnClickListener {
            findNavController().navigate(R.id.action_homeViewPagerFragment_to_shareProgressFragment)
        }
    }

}