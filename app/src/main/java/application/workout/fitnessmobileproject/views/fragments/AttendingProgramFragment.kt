package application.workout.fitnessmobileproject.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
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
        /**Тут согласно документации будет получено в подписчике сразу значение livedata,
         * поэтому клик листенер дефолтный ниже объявлять не нужно, как я понмиаю
         * Данное поведение требует дополнительной проверки при тестировании
         */
        viewModel.currentExercise.observe(this) { exercise ->
            Glide
                .with(binding.root.context)
                .load(exercise.image)
                .placeholder(R.drawable.place_holder)
                .override(600, 600)
                .centerCrop()
                .into(binding.exerciseImage)
            binding.exerciseName.text = exercise.name
            binding.exerciseInfo.text = exercise.time.toString()
            /*if (program.exercise.size == viewModel.current + 1) {
                binding.nextExercise.setOnClickListener {
                    findNavController().navigate(R.id.action_attendingProgramFragment_to_programFragment)
                }
                binding.nextExercise.text = "Finish"
            } else {
                binding.nextExercise.setOnClickListener {
                    findNavController().navigate(R.id.action_attendingProgramFragment_to_restFragment)
                    viewModel.current++
                }
                binding.nextExercise.text = "Next exercise"
            }*/
        }
        viewModel.isLastExercise.observe(this) { isLast ->
            binding.nextExercise.setOnClickListener {
                viewModel.increaseCurrent()
                if (isLast) {
                    Toast.makeText(activity, "Finished program", Toast.LENGTH_SHORT).show()
                    viewModel.finishProgram()
                    findNavController().navigate(R.id.action_attendingProgramFragment_to_homeViewPagerFragment)
                } else {
                    findNavController().navigate(R.id.action_attendingProgramFragment_to_restFragment)
                }
            }
            binding.nextExercise.text = if (isLast) "Finish" else "Next exercise"
        }
    }

    override fun onResume() {
        super.onResume()
    }
}