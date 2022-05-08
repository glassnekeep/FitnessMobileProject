package application.workout.fitnessmobileproject.views.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import application.workout.fitnessmobileproject.R
import application.workout.fitnessmobileproject.databinding.FragmentRegisterBinding
import application.workout.fitnessmobileproject.databinding.FragmentRegisterParametersBinding
import application.workout.fitnessmobileproject.utils.exceptions.EmptyFieldException
import application.workout.fitnessmobileproject.viewModels.RegisterViewModel

class RegisterParametersFragment : Fragment() {

    private var _binding: FragmentRegisterParametersBinding? = null

    private val binding get() = _binding!!

    private val viewModel: RegisterViewModel by activityViewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterParametersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.root.setupWithNavController(findNavController(), AppBarConfiguration(findNavController().graph))
        binding.registerButton.setOnClickListener {
            if (binding.firstNameEditText.editText?.text.isNullOrEmpty()) {
                binding.firstNameEditText.error = "Enter name!"
                return@setOnClickListener
            }
            if (binding.secondNameEditText.editText?.text.isNullOrEmpty()) {
                binding.secondNameEditText.error = "Enter surname!"
                return@setOnClickListener
            }
            if (binding.phoneNumberEditText.editText?.text.isNullOrEmpty()) {
                binding.phoneNumberEditText.error = "Confirm phone number!"
                return@setOnClickListener
            }
            if (binding.sexEditText.editText?.text.isNullOrEmpty()) {
                binding.sexEditText.error = "Enter sex!"
                return@setOnClickListener
            }
            if (binding.weightEditText.editText?.text.isNullOrEmpty()) {
                binding.weightEditText.error = "Enter weight!"
                return@setOnClickListener
            }
            if (binding.heightEditText.editText?.text.isNullOrEmpty()) {
                binding.heightEditText.error = "Enter height!"
                return@setOnClickListener
            }
            if (!android.util.Patterns.PHONE.matcher(binding.phoneNumberEditText.editText?.text.toString()).matches()) {
                binding.phoneNumberEditText.error = "Enter correct phone number!"
                return@setOnClickListener
            }
            viewModel.setAdditionalInfo(
                name = binding.firstNameEditText.editText?.text.toString(),
                surname = binding.secondNameEditText.editText?.text.toString(),
                phoneNumber = binding.phoneNumberEditText.editText?.text.toString(),
                sex = binding.sexEditText.editText?.text.toString(),
                weight = binding.weightEditText.editText?.text.toString().toInt(),
                height = binding.heightEditText.editText?.text.toString().toInt()
            )
            try {
                viewModel.registerUser()
            } catch (exception: Exception) {
                when(exception) {
                    is EmptyFieldException -> { Toast.makeText(activity, exception.message, Toast.LENGTH_SHORT).show() }
                    else -> {
                        Toast.makeText(activity, "Something went wrong", Toast.LENGTH_SHORT).show()
                        Log.d("exception", "Register exception of ${exception.message}")
                    }
                }
                return@setOnClickListener
            }
            findNavController().navigate(R.id.action_registerParametersFragment_to_loginFragment)
        }
        val listOfFields = listOf(
            binding.firstNameEditText,
            binding.secondNameEditText,
            binding.phoneNumberEditText,
            binding.sexEditText,
            binding.weightEditText,
            binding.heightEditText)
        listOfFields.forEach { textInputLayout ->
            textInputLayout.editText?.doOnTextChanged { text, start, before, count ->
                if (!text.isNullOrEmpty()) {
                    textInputLayout.error = null
                }
            }
        }
    }
}