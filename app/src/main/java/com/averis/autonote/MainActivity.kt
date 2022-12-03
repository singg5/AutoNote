package com.averis.autonote

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {
    lateinit var num1: EditText
    lateinit var num2: EditText
    lateinit var num3: EditText
//    lateinit var num1Str: String
//    lateinit var num2Str: String
//    lateinit var num3Str: String
    lateinit var wynik: TextView
    lateinit var oblicz: Button

    private fun roundToNum(numInDouble: Double): String {
        return "%.2f".format(numInDouble)
    }
    fun fuel(fuelTanked:Double, kilometers:Double): Double {
        return (fuelTanked/kilometers)*100
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        num1 = findViewById(R.id.num1)
        num2 = findViewById(R.id.num2)
        num3 = findViewById(R.id.num3)
        oblicz = findViewById(R.id.obliczButt)
        wynik = findViewById(R.id.wynik)


        // przy kliknięciu oblicza paliwo, jednak gdy nie ma
        // danych nic nadaje że jest błąd w konsoli
        oblicz.setOnClickListener {
            var lp = num1.text.toString()
            var ld = num2.text.toString()
//            var lt = num3.text.toString()
            if (lp.isEmpty()) {
                println("error")
            } else if (ld.isEmpty()){
                println("error")
            }
//            else if (lt.isEmpty()) {
//                println("error")
//            }
            else {
                var wynikpaliwa: Double = fuel(ld.toDouble(), lp.toDouble())
                wynik.text = "${roundToNum(wynikpaliwa)} l/100km"
            }

        }
    }
}


