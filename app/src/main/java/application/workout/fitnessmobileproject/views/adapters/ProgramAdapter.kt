package application.workout.fitnessmobileproject.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import application.workout.fitnessmobileproject.R
import application.workout.fitnessmobileproject.databinding.ItemProgramBinding
import application.workout.fitnessmobileproject.model.models.Program
import application.workout.fitnessmobileproject.utils.basicFullResourceRoot
import application.workout.fitnessmobileproject.views.fragments.HomeViewPagerFragmentDirections
import application.workout.fitnessmobileproject.views.fragments.ProgramsFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ProgramAdapter(
    val context: Context,
    val programList: List<Program>): RecyclerView.Adapter<ProgramAdapter.ProgramViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramViewHolder {
        val binding = ItemProgramBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProgramViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProgramViewHolder, position: Int) {
        with(holder) {
            with(programList[position]) {
                binding.programName.text = this.name
                binding.imageProgram.layout(0,0,0,0)
                Glide
                    .with(binding.root.context)
                    //.load(this.image)
                    .load("${basicFullResourceRoot}${this.image}")
                    .placeholder(R.drawable.place_holder)
                    .apply(RequestOptions().override(672, 280))
                    .centerCrop()
                    //.override(331, 136)
                    .into(binding.imageProgram)
                //Glide.with(binding.root.context).load(this.image).into(binding.imageProgram)
                binding.root.setOnClickListener {
                    val action = HomeViewPagerFragmentDirections.actionHomeViewPagerFragmentToProgramFragment(this.id)
                    it.findNavController().navigate(action)
                }
            }
            /*itemView.setOnClickListener {
                val action = HomeViewPagerFragmentDirections.actionHomeViewPagerFragmentToProgramFragment()
                it.findNavController().navigate(R.id.action_homeViewPagerFragment_to_programFragment)
            }*/
        }
        holder.binding
    }

    override fun getItemCount(): Int {
        return programList.size
    }

    inner class ProgramViewHolder(val binding: ItemProgramBinding) : RecyclerView.ViewHolder(binding.root)
}