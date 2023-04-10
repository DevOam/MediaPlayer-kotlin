package com.example.mediaplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer

class MainActivity(millisInFuture: Long) : AppCompatActivity() {
    lateinit var timer:CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        timer=object : CountDownTimer(2000,1000) {
            override fun onTick(p0: Long) {
                TODO("Not yet implemented")
            }

            override fun onFinish() {
                TODO("Not yet implemented")
                gotosoundActivity()
            }

        }.start()
    }

    private fun gotosoundActivity() {
        startActivity(Intent(this,soundActivity::class.java))
    }
}