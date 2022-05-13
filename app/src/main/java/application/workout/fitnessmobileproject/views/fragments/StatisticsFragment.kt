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
        val chart = binding.progressImage.progressChart
        viewModel.userProgress.observe(this) { progressList ->
            Log.d("progress", "$progressList")
            val entries = ArrayList(progressList.mapIndexed { index, progress ->
                BarEntry(index.toFloat(), (progress.currentExercise.toFloat() / progress.program.numberOfExercises.toFloat() * 100))
            })
            /*Log.d("entry size", "${entries.size}")
            Log.d("entries", "$entries")*/
            val dataset = BarDataSet(entries, "Progress %")
            dataset.let {
                it.color = resources.getColor(R.color.white)
                it.axisDependency = YAxis.AxisDependency.LEFT
            }
            val labels: ArrayList<String> = ArrayList(progressList.map { it.program.name })
            val data = BarData(dataset)
            //data.barWidth = 0.2f
            chart.let {
                it.legend.isEnabled = false
                it.data = data
                it.data.barWidth = 0.1f
                it.xAxis.textColor = Color.WHITE
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