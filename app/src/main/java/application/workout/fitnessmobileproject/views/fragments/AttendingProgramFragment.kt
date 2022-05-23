package application.workout.fitnessmobileproject.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import application.workout.fitnessmobileproject.R
import application.workout.fitnessmobileproject.databinding.FragmentAttendingProgramBinding
import application.workout.fitnessmobileproject.utils.basicFullResourceRoot
import application.workout.fitnessmobileproject.viewModels.ProgramViewModel
import com.bumptech.glide.Glide
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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
        binding.toolbar.setupWithNavController(findNavController(), AppBarConfiguration(findNavController().graph))
        binding.toolbar.title = "Attending program"
        viewModel.currentExercise.observe(this) { exercise ->
            Glide
                .with(binding.root.context)
                .load("$basicFullResourceRoot${exercise.image}")
                .placeholder(R.drawable.place_holder)
                .override(600, 600)
                .centerCrop()
                .into(binding.exerciseImage)
            binding.exerciseName.text = exercise.name
            val time = "X${exercise.time}"
            binding.exerciseInfo.text = time
        }
        viewModel.isLastExercise.observe(this) { isLast ->
            binding.nextExercise.setOnClickListener {
                if (isLast) {
                    Toast.makeText(activity, "Finished ${viewModel.program.value?.name}", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_attendingProgramFragment_to_homeViewPagerFragment)
                    viewModel.finishProgram()
                } else {
                    findNavController().navigate(R.id.action_attendingProgramFragment_to_restFragment)
                    viewModel.increaseCurrent()
                }
            }
            binding.nextExercise.text = if (isLast) "Finish" else "Next exercise"
        }
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(R.id.action_attendingProgramFragment_to_homeViewPagerFragment)
            viewModel.finishProgram()
        }
        callback.isEnabled = true
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_attendingProgramFragment_to_homeViewPagerFragment)
            viewModel.finishProgram()
        }
    }
}