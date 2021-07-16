package com.example.nds

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.nds.databinding.ErrandResBinding
import com.google.android.material.tabs.TabLayout

class MyErrandActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_errand)

        val pagerAdapter: MyErrandActivity.SectionsPagerAdapter = MyErrandActivity.SectionsPagerAdapter(supportFragmentManager)
        val pager = findViewById<ViewPager>(R.id.pager)
        pager.adapter = pagerAdapter

        val tabLayout = findViewById<TabLayout>(R.id.tabs)
        tabLayout.setupWithViewPager(pager)
        tabLayout.setTabTextColors(Color.parseColor("#CCCCCC"), Color.parseColor("#627EA4")) // 탭의 기본색, 내가 탭한 색
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#627EA4")) // 탭의 밑줄색
    }

    private class SectionsPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            when (position) {
                0 -> return MyErrandReqFragment() // 내가 요청한 심부름
                1 -> return MyErrandResFragment() // 내가 수행한 심부름
            }
            return getItem(position)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when (position) {
                0 -> return "내가 요청한 심부름"
                1 -> return "내가 수행한 심부름"
            }
            return null
        }

        override fun getCount(): Int {
            return 2
        }
    }
}