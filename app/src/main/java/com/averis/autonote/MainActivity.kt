package com.averis.autonote

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.round
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var spinner: Spinner
    lateinit var num1: EditText
    lateinit var num2: EditText
    lateinit var wynik: TextView
    lateinit var oblicz: Button

    fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return round(this * multiplier) / multiplier
    }
    private fun roundToOne(numInDouble: Double): String {
        return "%.1f".format(numInDouble)
    }
    private fun roundToTwo(numInDouble: Double): String {
        return "%.2f".format(numInDouble)
    }
    private fun roundToZero(numInDouble: Double): String {
        return "%.0f".format(numInDouble)
    }
    fun fuel(fuelTanked:Double, kilometers:Double): Double {
        return (fuelTanked/kilometers)*100
    }
    fun removeTrailingZeros(num: String): String{
        if(!num.contains('.'))
            return num
        return num
            .dropLastWhile { it == '0' }
            .dropLastWhile { it == '.' }
    }
    // TODO: Opisać funkcje
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner = findViewById(R.id.spinner)
        num1 = findViewById(R.id.num1)
        num2 = findViewById(R.id.num2)
        oblicz = findViewById(R.id.obliczButt)
        wynik = findViewById(R.id.wynik)

        val df = DecimalFormat("#.###")

        var arrayAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.picks,
            android.R.layout.simple_spinner_item
        )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = arrayAdapter
        // TODO: Dokumentacja
        // przy kliknięciu oblicza paliwo, jednak gdy nie ma
        // danych nic nadaje że jest błąd w konsoli
        oblicz.setOnClickListener {
            var lp = num1.text.toString()
            var ld = num2.text.toString()
            if (lp.isEmpty()) {
                println("Nie ma liczby przejechanych kilometrów")
            } else if (ld.isEmpty()){
                println("Nie ma liczby zatankowane paliwa")
            }
            else {
                val wynikPaliwa: Double = fuel(ld.toDouble(), lp.toDouble()) // 4.99
                val wynikPaliwaStr: String = df.format(wynikPaliwa)
                val wynikPaliwaStrTwo: String = roundToTwo(wynikPaliwa)
                val wynikPaliwaStrRound: String = (round(wynikPaliwa * 100) / 100).toString()
                println(wynikPaliwaStrRound)
                if(wynikPaliwaStr.contains(".0")) {
                    wynik.text = "${removeTrailingZeros(wynikPaliwaStr)} l/100km"
                } else {
                    wynik.text = "${wynikPaliwaStr} l/100km"
                }
            }

        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}


