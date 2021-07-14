package com.example.nds

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MyTradeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_trade)

        val pagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        val pager = findViewById<ViewPager>(R.id.pager)
        pager.adapter = pagerAdapter
        val tabLayout = findViewById<TabLayout>(R.id.tabs)
        tabLayout.setupWithViewPager(pager)
        tabLayout.setTabTextColors(Color.parseColor("#CCCCCC"), Color.parseColor("#627EA4")) // 기본색, 내가 탭한 색
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#627EA4")) // 밑줄색
    }

    private class SectionsPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            when (position) {
                0 -> return MyTradeBuyFragment() // 구매내역
                1 -> return MyTradeSellFragment() // 판매내역
            }
            return getItem(position)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when (position) {
                0 -> return "구매내역"
                1 -> return "판매내역"
            }
            return null
        }

        override fun getCount(): Int {
            return 2
        }
    }
}