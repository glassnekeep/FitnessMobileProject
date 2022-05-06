package application.workout.fitnessmobileproject.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import application.workout.fitnessmobileproject.R
import application.workout.fitnessmobileproject.databinding.FragmentHomeViewPagerBinding
import application.workout.fitnessmobileproject.views.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class HomeViewPagerFragment : Fragment() {

    private var _binding: FragmentHomeViewPagerBinding? = null

    private val binding get() = _binding!!

    private val tabsIconArray = intArrayOf(
        R.drawable.ic_baseline_home_24,
        R.drawable.ic_baseline_access_time_filled_24,
        R.drawable.ic_baseline_equalizer_24,
        R.drawable.ic_baseline_person_24
    )

    private val tabsTextArray = arrayOf<String>(
        "Home", "Programs", "Stats", "Settings"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeViewPagerBinding.inflate(inflater, container, false)
        val fragmentList = arrayListOf<Fragment>(
            HomeFragment(),
            ProgramsFragment(),
            StatisticsFragment(),
            SettingsFragment()
        )
        val adapter = ViewPagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabsTextArray[position]
            tab.icon = ResourcesCompat.getDrawable(context!!.resources, tabsIconArray[position], null)
        }.attach()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
    }
}