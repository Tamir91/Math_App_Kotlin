package com.example.tamir.math_app_kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/*Created by Tamir Yurovskiy
*  5/11/17
*  */

class MainActivity : AppCompatActivity() {

    lateinit var firstNum: TextView
    lateinit var secondNum: TextView
    lateinit var sign: TextView
    lateinit var buttonAnswer: Button
    val LOWER_VALUE = 5
    val HIGHER_VALUE = 10


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setTextViewValues(LOWER_VALUE, HIGHER_VALUE)
    }

    private fun initViews() {
        firstNum = findViewById(R.id.tv_num1)
        secondNum = findViewById(R.id.tv_num2)
        sign = findViewById(R.id.tv_sign)
        buttonAnswer = findViewById(R.id.bAnswer)
        buttonAnswer.setOnClickListener {
            showAlert()
        }
    }

    private fun showAlert() {

        val auto_answer = getRandomNum(LOWER_VALUE, LOWER_VALUE + HIGHER_VALUE)
        val alertDialog = AlertDialog.Builder(this).create()
        alertDialog!!.setTitle("True or False?")
        alertDialog.setMessage(auto_answer.toString())

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES", { dialogInterface, i ->
            if (auto_answer == getWrightAnswer()) {
                Toast.makeText(applicationContext, "Right", Toast.LENGTH_SHORT).show()
                setTextViewValues(LOWER_VALUE, HIGHER_VALUE)
            }
            else
                Toast.makeText(applicationContext, "Wrong", Toast.LENGTH_SHORT).show()

        })

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", { dialogInterface, i ->
            if (auto_answer != getWrightAnswer()) {
                Toast.makeText(applicationContext, "Right", Toast.LENGTH_SHORT).show()
                setTextViewValues(LOWER_VALUE, HIGHER_VALUE)
            } else
                Toast.makeText(applicationContext, "Wrong", Toast.LENGTH_SHORT).show()
        })

        alertDialog.show()
    }

    private fun getRandomNum(from: Int, to: Int): Int {
        val random = Random()
        return random.nextInt(to - from) + from
    }

    private fun setTextViewValues(from: Int, to: Int) {
        firstNum.setText(getRandomNum(from, to).toString())
        secondNum.setText(getRandomNum(from, to).toString())
    }

    private fun getWrightAnswer(): Int {
        return firstNum.text.toString().toInt() + secondNum.text.toString().toInt()

    }

}
