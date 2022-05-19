package application.workout.fitnessmobileproject.views.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import application.workout.fitnessmobileproject.R
import application.workout.fitnessmobileproject.databinding.CustomDialogBinding
import application.workout.fitnessmobileproject.databinding.FragmentShareProgressBinding
import application.workout.fitnessmobileproject.viewModels.ShareProgressViewModel
import application.workout.fitnessmobileproject.views.adapters.ProgressAdapter
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ShareProgressFragment : Fragment() {

    private var _binding: FragmentShareProgressBinding? = null

    val binding get() = _binding!!

    private val viewModel: ShareProgressViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShareProgressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressRecyclerView.layoutManager = LinearLayoutManager(this.context)
        //binding.toolbar.registerToolbar.setNavigationIcon(R.drawable.ic_baseline_share_24)
        binding.toolbar.let {
            it.setupWithNavController(findNavController(), AppBarConfiguration(findNavController().graph))
            it.title = "Shared progresses"
            it.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.share -> {
                        val alertDialog = AlertDialog.Builder(context)
                        //val dialogBinding = CustomDialogBinding.inflate(layoutInflater)
                        val customView = LayoutInflater.from(context).inflate(R.layout.custom_dialog, null)
                        val enterUsernameLayout = customView.findViewById<TextInputLayout>(R.id.enter_user_id_layout)
                        val submitButton = customView.findViewById<MaterialButton>(R.id.submit_button)
                        val customDialog = alertDialog.setView(/*dialogBinding.root*/customView).create()
                        customDialog.show()
                        /*dialogBinding.*/submitButton.setOnClickListener {
                            val nowDateTime = LocalDateTime.now()
                            val formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm:ss")
                            val formatted = nowDateTime.format(formatter)
                            viewModel.shareProgress(userId = /*dialogBinding.*/enterUsernameLayout.editText?.text.toString().toInt() ?: 0, formatted)
                            customDialog.cancel()
                        }
                        /*val customDialog = alertDialog.create()
                        customDialog.show()*/
                        true
                    }
                    else -> { false }
                }
            }
        }

        viewModel.sharedProgressList.observe(this) { sharedProgressList ->
            binding.progressRecyclerView.adapter = ProgressAdapter(context ?: activity!!.applicationContext, progressList = sharedProgressList)
        }

        viewModel.message.observe(this) { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}