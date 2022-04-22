package application.workout.fitnessmobileproject.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import application.workout.fitnessmobileproject.R
import application.workout.fitnessmobileproject.utils.FitnessApplication
import kotlinx.coroutines.*

class SplashFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) { (activity?.application as FitnessApplication).validateUser() }
            delay(3000)
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
}