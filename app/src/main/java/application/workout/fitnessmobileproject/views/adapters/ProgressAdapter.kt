package application.workout.fitnessmobileproject.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import application.workout.fitnessmobileproject.R
import application.workout.fitnessmobileproject.databinding.ItemProgressBinding
import application.workout.fitnessmobileproject.model.models.Progress
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

class ProgressAdapter(
    val context: Context,
    val progressList: List<List<Progress>>): RecyclerView.Adapter<ProgressAdapter.ProgressViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgressAdapter.ProgressViewHolder {
        val binding = ItemProgressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProgressViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProgressAdapter.ProgressViewHolder, position: Int) {
        with(holder) {
            with(progressList[position]) {
                val userInfo = "${this[0].user.firstname} ${this[0].user.lastname}"
                binding.userText.text = userInfo
                binding.totalScore.text = "Total score: ${getScore(this).toString()}"
                val chart = binding.progressChart
                val entries = ArrayList(this.mapIndexed { index, progress ->
                    BarEntry(index.toFloat(), (progress.currentExercise.toFloat() / progress.program.numberOfExercises.toFloat() * 100))
                })
                val dataset = BarDataSet(entries, "Progress %")
                dataset.let {
                    it.color = context.getColor(R.color.white)
                    it.axisDependency = YAxis.AxisDependency.LEFT
                    it.setDrawValues(false)
                }
                val labels: ArrayList<String> = ArrayList(this.map { it.program.name })
                val data = BarData(dataset)
                chart.let {
                    it.legend.isEnabled = false
                    it.data = data
                    it.isClickable = false
                    it.data.barWidth = 0.05f
                    it.xAxis.textColor = context.getColor(R.color.white)
                    it.xAxis.textSize = 12f
                    it.axisLeft.textColor = context.getColor(R.color.white)
                    it.axisLeft.textSize = 12f
                    it.axisRight.textColor = context.getColor(R.color.white)
                    it.axisRight.textSize = 12f
                    it.setNoDataTextColor(R.color.white)
                    it.data.setValueTextColor(R.color.white)
                    it.data.setValueTextSize(8f)
                    it.description.isEnabled = false
                    it.setFitBars(true)
                    it.data.notifyDataChanged()
                    it.invalidate()
                }
                chart.xAxis.let {
                    val formatter = object: com.github.mikephil.charting.formatter.ValueFormatter() {
                        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                            return labels[value.toInt()]
                        }
                    }
                    it.granularity = 1f
                    it.isGranularityEnabled = true
                    it.valueFormatter = formatter
                    it.position = XAxis.XAxisPosition.BOTTOM
                    it.setDrawGridLines(false)
                }
                chart.data.notifyDataChanged()
                chart.invalidate()
            }
        }
    }

    override fun getItemCount(): Int {
        return progressList.size
    }

    private fun getScore(progressList: List<Progress>) : Int {
        return progressList.fold(0) { sum, progress ->
            sum + (progress.program.exercise.fold(0) { sumExercise, exercise ->
                sumExercise + exercise.weight
            } * progress.currentExercise.toFloat() / progress.program.numberOfExercises.toFloat()).toInt()
        }
    }

    inner class ProgressViewHolder(val binding: ItemProgressBinding) : RecyclerView.ViewHolder(binding.root)
}