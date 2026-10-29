package com.arijit.rise

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.arijit.rise.adapters.ViewPagerAdapter
import com.arijit.rise.fragments.HabitsFragment
import com.arijit.rise.fragments.HomeFragment
import com.arijit.rise.fragments.ProfileFragment
import com.arijit.rise.fragments.StatsFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tab_layout)

        val fragmentList = listOf(
            HomeFragment(),
            HabitsFragment(),
            StatsFragment(),
            ProfileFragment()
        )

        val adapter = ViewPagerAdapter(this, fragmentList)
        viewPager.adapter = adapter


        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.setIcon(R.drawable.home)
                    tab.text = "Home"
                }
                1 -> {
                    tab.setIcon(R.drawable.habits)
                    tab.text = "Habits"
                }
                2 -> {
                    tab.setIcon(R.drawable.stats)
                    tab.text = "Stats"
                }
                3 -> {
                    tab.setIcon(R.drawable.user_alt)
                    tab.text = "Profile"
                }
            }
        }.attach()
    }
}