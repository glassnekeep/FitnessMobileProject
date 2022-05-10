package application.workout.fitnessmobileproject.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import application.workout.fitnessmobileproject.R
import application.workout.fitnessmobileproject.databinding.FragmentAttendingProgramBinding
import application.workout.fitnessmobileproject.viewModels.ProgramViewModel
import com.bumptech.glide.Glide

class AttendingProgramFragment : Fragment() {

    private var _binding: FragmentAttendingProgramBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ProgramViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAttendingProgramBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.program.observe(this) { program ->
            if (viewModel.current == 0) {
                Glide
                    .with(binding.root.context)
                    .load(program.exercise[viewModel.current].image)
                    .placeholder(R.drawable.place_holder)
                    .override(600, 600)
                    .centerCrop()
                    .into(binding.exerciseImage)
                binding.exerciseName.text = program.exercise[viewModel.current].name
                binding.exerciseInfo.text = program.exercise[viewModel.current].time.toString()

            }
        }

    }

    override fun onResume() {
        super.onResume()
    }
}