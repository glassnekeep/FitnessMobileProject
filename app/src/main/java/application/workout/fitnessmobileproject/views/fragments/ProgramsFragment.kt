package application.workout.fitnessmobileproject.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import application.workout.fitnessmobileproject.R
import application.workout.fitnessmobileproject.databinding.FragmentProgramsBinding
import application.workout.fitnessmobileproject.viewModels.ProgramsViewModel
import application.workout.fitnessmobileproject.views.adapters.ProgramAdapter

class ProgramsFragment : Fragment() {

    private var _binding: FragmentProgramsBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ProgramsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProgramsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO Разобраться есть ли разница если тут вместо activity использовать this.context
        binding.programsRecyclerView.layoutManager = LinearLayoutManager(this.context)
        viewModel.programs.observe(this) { programs ->
            binding.programsRecyclerView.adapter = ProgramAdapter(context ?: activity!!.applicationContext, programs)
        }
    }
}