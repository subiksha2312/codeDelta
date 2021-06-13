package com.example.gdow

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.content.SharedPreferences
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    var alltimeHighScore:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hsPref = this.getSharedPreferences(getString(R.string.HighScoreKey), Context.MODE_PRIVATE)
        if (hsPref.contains(getString(R.string.HighScore)) == false) {
            with(hsPref.edit()) {
                putInt(getString(R.string.HighScore), 0)
                apply()
                commit()
            }
        }
        Log.d("MainActivity", "Entering Main Activity")
        alltimeHighScore = hsPref.getInt(getString(R.string.HighScore),0)

        var mainhighscore=findViewById(R.id.mainhighscore) as TextView
        mainhighscore.setText("All time Highscore is: $alltimeHighScore")

        var btnStart = findViewById(R.id.btnStart) as Button
        btnStart.setOnClickListener{
            openGameView1()
        }


    }

    override fun onResume() {
        super.onResume()
        Log.d("Resume", "Resuming Main activity")

        val hsPref = this.getSharedPreferences(getString(R.string.HighScoreKey), Context.MODE_PRIVATE)

        alltimeHighScore = hsPref.getInt(getString(R.string.HighScore),0)

        var mainhighscore=findViewById(R.id.mainhighscore) as TextView
        mainhighscore.setText("All time Highscore is: $alltimeHighScore")

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("alltimehighscore",alltimeHighScore)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        var hs=findViewById(R.id.mainhighscore) as TextView
        var highscore=savedInstanceState.getInt("alltimehighscore")
        hs.setText("All time Highscore is: $highscore")
    }


    fun openGameView1(){
        val intent=Intent(this, gameView1::class.java)
        startActivity(intent)
    }

}