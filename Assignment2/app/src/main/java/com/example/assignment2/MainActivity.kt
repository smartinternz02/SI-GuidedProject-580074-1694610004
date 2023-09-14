package com.example.assignment2

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import java.util.*

class MainActivity : ComponentActivity() {
    private lateinit var resultTextView: TextView
    private lateinit var rollButton: Button
    private lateinit var diceImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultTextView = findViewById(R.id.resultTextView)
        rollButton = findViewById(R.id.rollButton)
        rollButton.setOnClickListener { rollDice() }
        diceImageView = findViewById(R.id.diceImageView)
    }

     private fun rollDice() {
        val random = Random()
        val randomNumber = random.nextInt(6)+1
        val drawableResource = when (randomNumber) {
            1 -> R.drawable.dice1
            2 -> R.drawable.dice2
            3 -> R.drawable.dice3
            4 -> R.drawable.dice4
            5 -> R.drawable.dice5
            else -> R.drawable.dice6
        }
         diceImageView.setImageResource(drawableResource)
        // Update the screen with the dice roll
        val resultText = "You Rolled a $randomNumber"
        resultTextView.text = resultText
    }
}