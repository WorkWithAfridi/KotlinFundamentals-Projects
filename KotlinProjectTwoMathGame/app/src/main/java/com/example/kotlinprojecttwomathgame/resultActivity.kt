package com.example.kotlinprojecttwomathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class resultActivity : AppCompatActivity() {
    lateinit var playAgainBtn : Button
    lateinit var exitBtn : Button
    lateinit var  finResTv  : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        playAgainBtn = findViewById(R.id.playagainBTN)
        exitBtn = findViewById(R.id.exitBTN)
        finResTv = findViewById(R.id.finalResultTV)

        val score = intent.getStringExtra("score", )

        finResTv.setText("Your Score is : $score")

        playAgainBtn.setOnClickListener {
            startActivity(
                Intent(this@resultActivity, MainActivity::class.java)
            )
            finish()
        }

        exitBtn.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_MAIN
            )
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}