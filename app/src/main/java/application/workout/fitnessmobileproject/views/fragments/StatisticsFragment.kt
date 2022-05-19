package application.workout.fitnessmobileproject.views.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import application.workout.fitnessmobileproject.R
import application.workout.fitnessmobileproject.databinding.FragmentStatisticsBinding
import application.workout.fitnessmobileproject.utils.USER
import application.workout.fitnessmobileproject.viewModels.StatisticsViewModel
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter as ValueFormatter1

class StatisticsFragment : Fragment() {

    private var _binding: FragmentStatisticsBinding? = null

    private val binding get() = _binding!!

    private val viewModel: StatisticsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (USER != null) {
            val userInfo = "${USER!!.firstname} ${USER!!.lastname}"
            Log.d("user", "${USER!!.firstname} ${USER!!.lastname}")
            binding.progressImage.userText.text = userInfo
        }
        val chart = binding.progressImage.progressChart
        viewModel.userProgress.observe(this) { progressList ->
            val score = progressList.fold(0) { sum, progress ->
                sum + (progress.program.exercise.fold(0) { sumExercise, exercise ->
                    sumExercise + exercise.weight
                } * progress.currentExercise.toFloat() / progress.program.numberOfExercises.toFloat()).toInt()
            }
            binding.progressImage.totalScore.text = score.toString()
            val entries = ArrayList(progressList.mapIndexed { index, progress ->
                BarEntry(index.toFloat(), (progress.currentExercise.toFloat() / progress.program.numberOfExercises.toFloat() * 100))
            })
            val dataset = BarDataSet(entries, "Progress %")
            dataset.let {
                it.color = resources.getColor(R.color.white)
                it.axisDependency = YAxis.AxisDependency.LEFT
                it.setDrawValues(false)
            }
            val labels: ArrayList<String> = ArrayList(progressList.map { it.program.name })
            val data = BarData(dataset)
            chart.let {
                it.legend.isEnabled = false
                it.data = data
                it.isClickable = false
                it.data.barWidth = 0.05f
                it.xAxis.textColor = resources.getColor(R.color.white)//Color.WHITE
                it.xAxis.textSize = 12f
                it.axisLeft.textColor = resources.getColor(R.color.white)
                it.axisLeft.textSize = 12f
                it.axisRight.textColor = resources.getColor(R.color.white)
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