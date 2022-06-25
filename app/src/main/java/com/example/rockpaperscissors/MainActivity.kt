package com.example.rockpaperscissors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rockpaperscissors.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnSelectObject.setOnClickListener { onSelectObjectBtnClickListener() }
    }

    private fun onSelectObjectBtnClickListener() {
        val bottomSheet = ObjectSelectorModalBottomSheet()
        bottomSheet.show(supportFragmentManager, ObjectSelectorModalBottomSheet.TAG)
    }
}