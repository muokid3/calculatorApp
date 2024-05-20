package com.dm.berxley.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dm.berxley.calculator.ui.theme.CalculatorTheme
import java.lang.ArithmeticException


class MainActivity : ComponentActivity() {

    private var tvInput: TextView? = null
    private var lastDecimal: Boolean = false
    private var lastNumeric: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit (view: View){
        tvInput?.append((view as Button).text)
        lastNumeric = true
        lastDecimal = false
    }

    fun onClear (view: View){
        tvInput?.text = ""
    }

    fun onDecimal (view: View){
        if (lastNumeric && !lastDecimal){
            tvInput?.append(".")
            lastDecimal = true
            lastNumeric = false
        }
    }

    fun onOperator(view: View){
        tvInput?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDecimal = false
            }
        }
    }

    fun isOperatorAdded(string: String) : Boolean{
        return if (string.startsWith("-")) {
            false
        }else {
            string.contains("+")
                    || string.contains("-")
                    || string.contains("*")
                    || string.contains("/")
        }
    }

    fun onEqual(view: View){
        if (lastNumeric){
            var tvValue = tvInput?.text.toString()
            var prefix = ""

            if (tvValue.startsWith("-")){
                prefix = "-"
                tvValue = tvValue.substring(1)
            }

            try {
                if (tvValue.contains("-")){
                    val split = tvValue.split("-")
                    var one = split[0]
                    val two = split[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = (one.toDouble() - two.toDouble()).toString()
                }else if (tvValue.contains("+")){
                    val split = tvValue.split("+")
                    var one = split[0]
                    val two = split[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = (one.toDouble() + two.toDouble()).toString()
                }else if (tvValue.contains("*")){
                    val split = tvValue.split("*")
                    var one = split[0]
                    val two = split[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = (one.toDouble() * two.toDouble()).toString()
                }else if (tvValue.contains("/")){
                    val split = tvValue.split("/")
                    var one = split[0]
                    val two = split[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = (one.toDouble() / two.toDouble()).toString()
                }
            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }
}


