package application.workout.fitnessmobileproject.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import application.workout.fitnessmobileproject.R
import application.workout.fitnessmobileproject.databinding.FragmentRegisterBinding
import application.workout.fitnessmobileproject.viewModels.RegisterViewModel
import com.google.android.material.textfield.TextInputLayout

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null

    private val binding get() = _binding!!

    private val viewModel: RegisterViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.username.observe(this) { username ->
            binding.loginEditText.editText?.setText(username)
        }
        viewModel.password.observe(this) { password ->
            binding.passwordEditText.editText?.setText(password)
            binding.passwordConfirmEditText.editText?.setText(password)
        }
        viewModel.email.observe(this) { email ->
            binding.emailEditText.editText?.setText(email)
        }
        binding.nextButton.setOnClickListener {
            /*if (binding.loginEditText.editText?.text.isNullOrEmpty() &&
                    !binding.passwordEditText.editText?.text.isNullOrEmpty() &&
                    !binding.passwordConfirmEditText.editText?.text.isNullOrEmpty() &&
                    !binding.emailEditText.editText?.text.isNullOrEmpty()) {
                if (binding)
            } else {
                Toast.makeText(activity, "Не все поля заполнены!", Toast.LENGTH_SHORT).show()
            }*/
            if (binding.loginEditText.editText?.text.isNullOrEmpty()) {
                binding.loginEditText.error = "Enter login!"
                return@setOnClickListener
            }
            if (binding.passwordEditText.editText?.text.isNullOrEmpty()) {
                binding.loginEditText.error = "Enter password!"
                return@setOnClickListener
            }
            if (binding.passwordConfirmEditText.editText?.text.isNullOrEmpty()) {
                binding.loginEditText.error = "Confirm password!"
                return@setOnClickListener
            }
            if (binding.emailEditText.editText?.text.isNullOrEmpty()) {
                binding.loginEditText.error = "Enter email!"
                return@setOnClickListener
            }
            if (binding.passwordConfirmEditText.editText?.text.toString() != binding.passwordEditText.editText?.text.toString()) {
                binding.passwordConfirmEditText.error = "Passwords don't match!"
                return@setOnClickListener
            }
            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(binding.emailEditText.editText?.text.toString()).matches()) {
                binding.emailEditText.error = "Enter correct email!"
                return@setOnClickListener
            }
            viewModel.setMainInfo(
                username = binding.loginEditText.editText?.text.toString(),
                password = binding.passwordEditText.editText?.text.toString(),
                email = binding.emailEditText.editText?.text.toString()
            )
            findNavController().navigate(R.id.action_registerFragment_to_registerParametersFragment)
        }
        val listOfFields = listOf(binding.loginEditText, binding.passwordEditText, binding.passwordEditText, binding.emailEditText)
        listOfFields.forEach { textInputLayout ->
            textInputLayout.editText?.doOnTextChanged { text, start, before, count ->
                if (!text.isNullOrEmpty()) {
                    textInputLayout.error = null
                }
            }
        }
    }
}