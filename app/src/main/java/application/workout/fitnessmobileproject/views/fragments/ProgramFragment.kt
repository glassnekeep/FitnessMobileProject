package application.workout.fitnessmobileproject.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelStore
import androidx.navigation.NavGraph
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import application.workout.fitnessmobileproject.R
import application.workout.fitnessmobileproject.databinding.FragmentProgramBinding
import application.workout.fitnessmobileproject.databinding.FragmentProgramsBinding
import application.workout.fitnessmobileproject.utils.basicFullResourceRoot
import application.workout.fitnessmobileproject.viewModels.ProgramViewModel
import application.workout.fitnessmobileproject.views.adapters.ExerciseAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_program.*
import kotlinx.android.synthetic.main.register_toolbar.*

class ProgramFragment : Fragment() {

    private var _binding: FragmentProgramBinding? = null

    private val binding get() = _binding!!

    private val arguments: ProgramFragmentArgs by navArgs()

    private val viewModel: ProgramViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initProgram(arguments.programId)
        //(requireActivity() as AppCompatActivity).supportActionBar?.hide()
        //(requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProgramBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding.programName.text = arguments.programId.toString()
        //(requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar.registerToolbar)
        //(requireActivity() as AppCompatActivity).setSupportActionBar(register_toolbar)
        //(requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        val activity = (requireActivity() as AppCompatActivity)
        //activity.supportActionBar?.show()
        //activity.setSupportActionBar(toolbar)
        binding.collapsingLayout.setupWithNavController(binding.toolbar, findNavController(), AppBarConfiguration(findNavController().graph))
        (activity as AppCompatActivity).supportActionBar?.let {
            it.setHomeButtonEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
            it.title = "Program"
        }
        activity.supportActionBar.let { bar ->
            bar?.setHomeButtonEnabled(true)
            bar?.setDisplayHomeAsUpEnabled(true)
        }
        binding.exerciseRecycler.layoutManager = LinearLayoutManager(this.context)
        viewModel.program.observe(this) { program ->
            val time = program.exercise.fold(0) { sum, element ->
                sum + if (element.time != 0) element.time else 30
            }
            binding.exerciseRecycler.adapter = ExerciseAdapter(context ?: getActivity()!!.applicationContext, program.exercise)
            activity.supportActionBar?.title = program.name
            binding.toolbar.title = program.name
            binding.collapsingLayout.title = program.name
            binding.timeOfProgram.text = "${(time / 60)} min."
            binding.numberOfExercises.text = "${program.exercise.count()} exercises"
            Glide
                .with(binding.root.context)
                //.load(program.image)
                .load("${basicFullResourceRoot}${program.image}")
                .placeholder(R.drawable.place_holder)
                .override(672, 280)
                .centerCrop()
                .into(binding.programImage)
        }
        binding.startButton.setOnClickListener {
            findNavController().navigate(R.id.action_programFragment_to_attendingProgramFragment)
        }
    }
}