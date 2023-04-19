package com.example.kotlinprojecttwomathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.Locale
import kotlin.random.Random

class gameActivity : AppCompatActivity() {
    lateinit var scoretv : TextView
    lateinit var lifetv : TextView
    lateinit var timetv : TextView
    lateinit var questiontv : TextView
    lateinit var okbtn : Button
    lateinit var nextbtn : Button
    lateinit var answeret: EditText

    lateinit var countDownTimer: CountDownTimer
    private val startTimerInMillis : Long = 10000
    var timeLiftInMillis : Long = startTimerInMillis

    var correctAns : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        scoretv = findViewById(R.id.scoreTV)
        lifetv = findViewById(R.id.lifeTV)
        timetv = findViewById(R.id.timeTV)
        questiontv = findViewById(R.id.questionTV)

        answeret = findViewById(R.id.ansET)

        okbtn = findViewById(R.id.playagainBTN)
        nextbtn = findViewById(R.id.exitBTN)
        scoretv.setText("0")
        lifetv.setText("3")

        gameContinue()

        okbtn.setOnClickListener {
            val userAns = answeret.text
                if(userAns.toString().isNotEmpty()){
                    if(userAns.toString().toInt() == correctAns){
                        var score : Int = scoretv.text.toString().toInt()+1;
                        scoretv.setText(
                            score.toString()
                        )
                        questiontv.setText("Congratulations. Your answer is correct.")
                    }else{
                        var life : Int = lifetv.text.toString().toInt() - 1
                        lifetv.setText(
                            life.toString()
                        )
                        questiontv.setText("Snap! Wrong answer!")
                    }
                }else{
                    Toast.makeText(applicationContext, "Enter a valid answer and try again!", Toast.LENGTH_LONG,).show()
                }
            pauseTimer()
            pauseTimer()
        }
        nextbtn.setOnClickListener {
        pauseTimer()
        resetTimer()


            if(lifetv.text.toString().toInt()==0){
                Toast.makeText(applicationContext, "Game Over", Toast.LENGTH_LONG,).show()
                val intent = Intent(this@gameActivity, resultActivity::class.java)
                intent.putExtra("score", scoretv.text.toString())
                startActivity(intent)
                finish()
            }else{

                gameContinue()
                answeret.setText("")
            }
        }


    }

    fun gameContinue(){
        val num1 = Random.nextInt(0, 10)
        val num2 = Random.nextInt(0, 10)
        correctAns = num1+num2

        val mode = intent.getStringExtra("mode", )
        if(mode=="+"){

            correctAns = num1+num2

            questiontv.setText("$num1 + $num2")
        } else if(mode=="-"){

            correctAns = num1-num2

            questiontv.setText("$num1 - $num2")
        } else if(mode=="*"){

            correctAns = num1*num2

            questiontv.setText("$num1 x $num2")
        }
        startTimer()

    }

    fun startTimer(){
        countDownTimer  = object : CountDownTimer(
            timeLiftInMillis,
            1000,
        ){
            override fun onTick(millisUntilFinished: Long) {
                timeLiftInMillis = millisUntilFinished
                updateText()
            }

            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateText()

                Toast.makeText(applicationContext, "Times up", Toast.LENGTH_LONG,).show()
                questiontv.setText("Times UP!.")

            }
        }.start()
    }

   fun updateText(){
       val remainingTime : Int = (timeLiftInMillis/1000).toInt()
       timetv.setText(
           String.format(Locale.getDefault(),
               "%02d", remainingTime,
           )
       )
    }

    fun pauseTimer(){
        countDownTimer.cancel()
    }

    fun resetTimer(){
        timeLiftInMillis = startTimerInMillis
        updateText()
    }

}