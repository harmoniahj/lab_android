package com.example.nds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nds.adapter.MyLikeAdapter
import com.example.nds.databinding.ActivityMyLikeBinding
import com.example.nds.service.MyLikeService

class MyInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyLikeBinding
    private lateinit var adapter: MyLikeAdapter
    private lateinit var myLikeService: MyLikeService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_info)
    }
}