package application.workout.fitnessmobileproject.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import application.workout.fitnessmobileproject.R
import application.workout.fitnessmobileproject.databinding.FragmentLoginBinding
import application.workout.fitnessmobileproject.viewModels.LoginViewModel
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Using ViewBinding here
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*viewModel.user.observe(this) { User ->
            binding.usernameEditText.setText(User.username)
            binding.passwordEditTextText.setText(User.phoneNumber)
        }*/

        binding.loginButton.setOnClickListener {
            lifecycleScope.launch {
                if (viewModel.doLogin(binding.usernameEditText.text.toString(), binding.passwordEditTextText.text.toString())) {
                    findNavController().navigate(R.id.action_loginFragment_to_homeViewPagerFragment)
                } else {
                    Toast.makeText(activity, "Invalid login", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}