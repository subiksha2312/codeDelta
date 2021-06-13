package com.example.gdow

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class GameView2 : AppCompatActivity() {

    var alltimeHighScore:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_view2)

        var conlayout=findViewById(R.id.gameView2Layout) as ConstraintLayout
        conlayout.setBackgroundResource(R.color.DarkRed)

        Log.d("Game2.1","entering gameview2")

        var btnPlayAgain = findViewById(R.id.btnPlayAgain) as Button
        btnPlayAgain.setOnClickListener{
            openMainActivity()
        }
        Log.d("Game 2.2","before displaying the score")
        var text: TextView = findViewById(R.id.Score) as TextView
        //val intent = Intent()
        var score  = intent.getIntExtra("score",0).toString()

        Log.d("Game 2.4",score)

        text.setText("Your score is: $truescore")
        Log.d("Game 2.3","after displaying the score")

        setHighscore()

        var HStext: TextView = findViewById(R.id.Highscore) as TextView
        HStext.setText("High Score is: $alltimeHighScore")

    }

    fun setHighscore(){
        val hsPref = this.getSharedPreferences(getString(R.string.HighScoreKey), Context.MODE_PRIVATE)
        alltimeHighScore = hsPref.getInt(getString(R.string.HighScore),0)
        Log.d("CurrHighScore", alltimeHighScore.toString())

        if (truescore > alltimeHighScore){
            alltimeHighScore = truescore
            with (hsPref.edit()) {
                putInt(getString(R.string.HighScore), alltimeHighScore)
                apply()
                commit()
            }
        }

    }
    fun openMainActivity(){
        truescore = 0
        millisleft=60000

        val intent= Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        truescore = 0
        millisleft=60000
        setHighscore()
    }

}
