package application.workout.fitnessmobileproject.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import application.workout.fitnessmobileproject.R
import application.workout.fitnessmobileproject.databinding.ItemExerciseBinding
import application.workout.fitnessmobileproject.model.models.Exercise
import application.workout.fitnessmobileproject.utils.basicFullResourceRoot
import com.bumptech.glide.Glide

class ExerciseAdapter(
    val context: Context,
    private val exerciseList: List<Exercise>): RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val binding = ItemExerciseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExerciseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        with(holder) {
            with(exerciseList[position]) {
                binding.exerciseName.text = this.name
                binding.numberOfApproaches.text = this.numberOfApproaches.toString()
                Glide
                    .with(binding.root.context)
                    //.load(this.image)
                    .load("${basicFullResourceRoot}${this.image}")
                    .placeholder(R.drawable.place_holder)
                    .override(180, 180)
                    .centerCrop()
                    .into(binding.exerciseImage)
            }
        }
        holder.binding
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }

    inner class ExerciseViewHolder(val binding: ItemExerciseBinding): RecyclerView.ViewHolder(binding.root)
}