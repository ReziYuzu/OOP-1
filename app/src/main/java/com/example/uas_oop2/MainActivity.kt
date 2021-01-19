package com.example.uas_oop2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_evi.setOnClickListener {
            startActivity(Intent(this,EviActivity::class.java))
        }
        btn_sholeh.setOnClickListener {//pindah activity
            startActivity(Intent(this,SholehActivity::class.java))
        }
    }
}