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
import application.workout.fitnessmobileproject.utils.USER
import kotlinx.coroutines.*

class SplashFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        (activity?.application as FitnessApplication).setCredentials("ivan", "ivan")
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) { (activity?.application as FitnessApplication).validateUser() }
            delay(1000L)
            if(USER != null) {
                findNavController().navigate(R.id.action_splashFragment_to_homeViewPagerFragment)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
}