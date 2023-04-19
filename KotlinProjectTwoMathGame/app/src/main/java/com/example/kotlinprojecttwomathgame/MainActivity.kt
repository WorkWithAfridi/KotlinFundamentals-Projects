package com.example.kotlinprojecttwomathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var  addBtn : Button
    lateinit var subBtn : Button
    lateinit var mulBtn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addBtn = findViewById(R.id.addBtn)
        mulBtn = findViewById(R.id.mulBtn)
        subBtn = findViewById(R.id.subBtn)

        addBtn.setOnClickListener {
            val intent:Intent =
                Intent(
                    this@MainActivity, gameActivity::class.java,
                )
            intent.putExtra("mode" , "+")
            startActivity(
                intent
            )
        }

        mulBtn.setOnClickListener {
            val intent:Intent =
                Intent(
                    this@MainActivity, gameActivity::class.java,
                )
            intent.putExtra("mode" , "*")
            startActivity(
                intent
            )
        }

        subBtn.setOnClickListener {
            val intent:Intent =
                Intent(
                    this@MainActivity, gameActivity::class.java,
                )
            intent.putExtra("mode" , "-")
            startActivity(
                intent
            )
        }
    }
}