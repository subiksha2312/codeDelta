package com.example.gdow

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.*
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.*


var truescore:Int=0
var millisleft:Long=60000

class gameView1 : AppCompatActivity() {
    var objDOW = GuessDOW();
    var nOptions = IntArray(4)
    lateinit var timerText:TextView
    lateinit var gameTimer: CountDownTimer



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        var saveddate=((findViewById(R.id.textView2) as TextView).text).toString()
        var savedOp1= ((findViewById(R.id.Option1) as TextView).text).toString()
        var savedOp2= ((findViewById(R.id.Option2) as TextView).text).toString()
        var savedOp3= ((findViewById(R.id.Option3) as TextView).text).toString()
        var savedOp4= ((findViewById(R.id.Option4) as TextView).text).toString()
        var conlayout= findViewById(R.id.gameView1Layout) as ConstraintLayout
        var condraw = conlayout.background as ColorDrawable
        var currlayoutcolor = condraw.color

        Log.d("currentlayoutcolor", currlayoutcolor.toString())

        Log.d("saveddate", saveddate)
        Log.d("savedOp1", savedOp1)
        Log.d("savedOp2", savedOp2)
        Log.d("savedOp3", savedOp3)
        Log.d("savedOp4", savedOp4)

        outState.putString("saveddate",saveddate)
        outState.putString("savedOp1",savedOp1)
        outState.putString("savedOp2",savedOp2)
        outState.putString("savedOp3",savedOp3)
        outState.putString("savedOp4",savedOp4)
        outState.putLong("timeremaining", millisleft)
        outState.putInt("currlayoutcolor",currlayoutcolor)




    }

    fun startTimer(timeleft:Long){
        gameTimer=object: CountDownTimer(timeleft,1000){
            override fun onTick(millisUntilFinished: Long) {
                millisleft = millisUntilFinished
                Log.d("StartTime", "Starting Timer...")
                UpdateTimer()
            }

            override fun onFinish() {
                openGameView2(truescore)
            }
        }.start()

    }



    fun UpdateTimer() {
        var min = (millisleft/1000)/60
        var sec = (millisleft/1000) % 60
        Log.d("mins",min.toString())
        Log.d("sec", sec.toString())

        var formattedTime = String.format(Locale.getDefault(), "%02d:%02d", min, sec)
        timerText.setText(formattedTime)

    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        var tvDate = findViewById(R.id.textView2) as TextView
        var radio1 = findViewById(R.id.Option1) as RadioButton
        var radio2 = findViewById(R.id.Option2) as RadioButton
        var radio3 = findViewById(R.id.Option3) as RadioButton
        var radio4 = findViewById(R.id.Option4) as RadioButton
        var conlayout = findViewById(R.id.gameView1Layout) as ConstraintLayout
        Log.d("livescore", truescore.toString())
        var livescore= findViewById(R.id.livescore) as TextView


        millisleft = savedInstanceState.getLong("timeremaining")
        UpdateTimer()

        tvDate.setText(savedInstanceState.getString("saveddate"))
        radio1.setText(savedInstanceState.getString("savedOp1"))
        radio2.setText(savedInstanceState.getString("savedOp2"))
        radio3.setText(savedInstanceState.getString("savedOp3"))
        radio4.setText(savedInstanceState.getString("savedOp4"))
        livescore.setText("Your Score is: $truescore")
        conlayout.setBackgroundColor(savedInstanceState.getInt("currlayoutcolor"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_view1)

        var conlayout=findViewById(R.id.gameView1Layout) as ConstraintLayout
        conlayout.setBackgroundResource(R.color.purple_700)

        var btnCheck = findViewById(R.id.btnCheck) as Button
        btnCheck.setOnClickListener {
            onClickCheckAnswer()
        }

        timerText = findViewById(R.id.timer) as TextView

        Log.d("Millis Left", millisleft.toString())

        startTimer(millisleft)

        val radio1 = findViewById(R.id.Option1) as RadioButton
        val radio2 = findViewById(R.id.Option2) as RadioButton
        val radio3 = findViewById(R.id.Option3) as RadioButton
        val radio4 = findViewById(R.id.Option4) as RadioButton

        radio1.setOnClickListener {
            onRadioButtonClick()
        }
        radio2.setOnClickListener {
            onRadioButtonClick()
        }
        radio3.setOnClickListener {
            onRadioButtonClick()
        }
        radio4.setOnClickListener {
            onRadioButtonClick()
        }

        GenDateandDayOptions()

    }


    fun GenDateandDayOptions() {
        objDOW.generateDt()
        val DisplayDate: String = objDOW.DecoratedDate()
        var text: TextView = findViewById(R.id.textView2) as TextView
        text.setText(DisplayDate)

        val WeekArray = arrayOf<String>(
            "Sunday",
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday"
        )
        nOptions = objDOW.RandomDows()

        val radio1 = findViewById(R.id.Option1) as RadioButton
        val radio2 = findViewById(R.id.Option2) as RadioButton
        val radio3 = findViewById(R.id.Option3) as RadioButton
        val radio4 = findViewById(R.id.Option4) as RadioButton

        radio1.setText(WeekArray[nOptions[0]])
        radio2.setText(WeekArray[nOptions[1]])
        radio3.setText(WeekArray[nOptions[2]])
        radio4.setText(WeekArray[nOptions[3]])

    }

    fun onRadioButtonClick() {
        val v = (getSystemService(Context.VIBRATOR_SERVICE) as Vibrator)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500,
                VibrationEffect.DEFAULT_AMPLITUDE))
        }
        else {
            v.vibrate(500)
        }
    }

    fun openGameView2(currscore: Int) {
        val intent = Intent(this, GameView2::class.java)
        intent.putExtra("score",currscore)
        startActivity(intent)
    }



    fun onClickCheckAnswer() {
        var conlayout=findViewById(R.id.gameView1Layout) as ConstraintLayout

        var btnCheck = findViewById(R.id.btnCheck) as Button
        // Get the checked radio button id from radio group
        var radioGroup = findViewById(R.id.RadioGroup) as RadioGroup
        var id: Int = radioGroup.checkedRadioButtonId
        if (id != -1) { // If any radio button checked from radio group
            // Get the instance of radio button using id
            val radio: RadioButton = findViewById(id)
            val userOption:String = radio.text as String
            if (objDOW.checkanswer(userOption)) {
                truescore=truescore+5
                conlayout.setBackgroundResource(R.color.DarkGreen)


            }
            else {
                truescore= truescore-2
                millisleft -=5000
                gameTimer.cancel()
                startTimer(millisleft)
                conlayout.setBackgroundResource(R.color.DarkRed)


            }
            var livescore=(findViewById(R.id.livescore) as TextView)

            livescore.setText("Your Score is: $truescore")
            GenDateandDayOptions()
            radioGroup.clearCheck()


        }
    }

}












