package application.workout.fitnessmobileproject.views.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import application.workout.fitnessmobileproject.R
import application.workout.fitnessmobileproject.databinding.FragmentProfileBinding
import application.workout.fitnessmobileproject.databinding.FragmentProgramsBinding
import application.workout.fitnessmobileproject.utils.FitnessApplication
import application.workout.fitnessmobileproject.utils.USER

class ProfileFragment : Fragment() {

    private var _binding : FragmentProfileBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.let {
            it.setupWithNavController(findNavController(), AppBarConfiguration(findNavController().graph))
            it.title = "Profile"
            it.setOnMenuItemClickListener { menu ->
                when (menu.itemId) {
                    R.id.exit -> {
                        if (activity != null) {
                            Toast.makeText(activity!!.applicationContext, "Logged out", Toast.LENGTH_SHORT).show()
                            (activity!!.application as FitnessApplication).setCredentials("", "")
                        }
                        findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
                        USER = null
                        true
                    }
                    else -> false
                }
            }
        }
        binding.toolbar.setOnMenuItemClickListener { menu ->
            when (menu.itemId) {
                R.id.exit -> {
                    if (activity != null) {
                        Toast.makeText(activity!!.applicationContext, "Logged out", Toast.LENGTH_SHORT).show()
                        (activity!!.application as FitnessApplication).setCredentials("", "")
                    }
                    findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
                    USER = null
                    true
                }
                else -> false
            }
        }
    }

    /*private fun showMenu(view: View, menuRes: Int) {
        val popup = PopupMenu(context ?: activity!!.applicationContext, view)
        popup.menuInflater.inflate(menuRes, popup.menu)
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.exit -> {
                    if (activity != null) {
                        Toast.makeText(activity!!.applicationContext, "Logged out", Toast.LENGTH_SHORT).show()
                        (activity!!.application as FitnessApplication).setCredentials("", "")
                    }
                    findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
                    USER = null
                    true
                }
                else -> false
            }
        }
        popup.show()
    }*/
}