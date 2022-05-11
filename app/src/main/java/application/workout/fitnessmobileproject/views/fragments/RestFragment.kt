package application.workout.fitnessmobileproject.views.fragments

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import application.workout.fitnessmobileproject.R
import application.workout.fitnessmobileproject.databinding.FragmentRestBinding
import application.workout.fitnessmobileproject.utils.SETTINGS
import application.workout.fitnessmobileproject.viewModels.ProgramViewModel
import kotlinx.coroutines.*
import java.lang.Runnable

class RestFragment : Fragment() {

    private var _binding: FragmentRestBinding? = null

    private val binding get() = _binding!!

    val viewModel: ProgramViewModel by activityViewModels()

    //private var maxProgress = 0

    private var currentProgress = SETTINGS?.restTime ?: 10//100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.customProgress.max = SETTINGS?.restTime ?: 100
        setText()
    }

    override fun onResume() {
        super.onResume()
        val job = settingProgress()
        binding.nextButton.setOnClickListener {
            job.cancel()
            findNavController().navigate(R.id.action_restFragment_to_attendingProgramFragment)
        }
    }

    /*private fun setProgress() {
        val thread = Thread(Runnable {
            while (currentProgress <= 100) {
                try {
                    currentProgress += 10
                    Thread.sleep(1000)
                } catch (exception: InterruptedException) {
                    Toast.makeText(context, exception.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })
        thread.start()
    }*/

    private fun settingProgress(): Job {
        return MainScope().launch {
            while (currentProgress >= 0) {
                try {
                    currentProgress -= 1
                    delay(1000)
                    setText()
                } catch (exception: Exception) {
                    //Toast.makeText(context, exception.message.toString(), Toast.LENGTH_SHORT).show()
                    Log.d("exception while cancelling coroutine", "message = ${exception.message}")
                }
            }
            //viewModel.current++
            if (isActive) findNavController().navigate(R.id.action_restFragment_to_attendingProgramFragment)
        }
    }

    private fun setText() {
        binding.currentProgress.text = currentProgress.toString()
        binding.customProgress.progress = currentProgress
    }
}