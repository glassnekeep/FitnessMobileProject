package application.workout.fitnessmobileproject.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import application.workout.fitnessmobileproject.R
import application.workout.fitnessmobileproject.databinding.FragmentShareProgressBinding
import application.workout.fitnessmobileproject.viewModels.ShareProgressViewModel
import application.workout.fitnessmobileproject.views.adapters.ProgressAdapter

class ShareProgressFragment : Fragment() {

    private var _binding: FragmentShareProgressBinding? = null

    val binding get() = _binding!!

    private val viewModel: ShareProgressViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShareProgressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressRecyclerView.layoutManager = LinearLayoutManager(this.context)
        //binding.toolbar.registerToolbar.setNavigationIcon(R.drawable.ic_baseline_share_24)
        binding.toolbar.let {
            it.setNavigationIcon(R.drawable.ic_baseline_share_24)
            it.setupWithNavController(findNavController(), AppBarConfiguration(findNavController().graph))
            it.title = "Shared progresses"
        }

        viewModel.sharedProgressList.observe(this) { sharedProgressList ->
            binding.progressRecyclerView.adapter = ProgressAdapter(context ?: activity!!.applicationContext, progressList = sharedProgressList)
        }
    }
}