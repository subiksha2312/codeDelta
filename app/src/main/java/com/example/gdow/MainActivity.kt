package com.example.gdow

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.content.SharedPreferences
import android.view.View
import android.widget.Button


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnStart = findViewById(R.id.btnStart) as Button
        btnStart.setOnClickListener{
            openGameView1()
        }


    }
    fun openGameView1(){
        val hsPref = this.getSharedPreferences(getString(R.string.HighScoreKey), Context.MODE_PRIVATE)
        if (hsPref.contains(getString(R.string.HighScore)) == false) {
            with(hsPref.edit()) {
                putInt(getString(R.string.HighScore), 0)
                apply()
            }
        }
        val intent=Intent(this, gameView1::class.java)
        startActivity(intent)
    }

}