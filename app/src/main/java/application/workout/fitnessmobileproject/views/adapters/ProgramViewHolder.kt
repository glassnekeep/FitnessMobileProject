package application.workout.fitnessmobileproject.views.adapters

import android.view.View
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import application.workout.fitnessmobileproject.model.models.Program

class ProgramViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bingProgram(program: Program) {
        val name = program.name
    }
}