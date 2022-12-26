package com.averis.autonote

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

 class DashboardActivity : AppCompatActivity(R.layout.fragment_dashboard) {
     lateinit var result: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        result = findViewById(R.id.result)
    }
}