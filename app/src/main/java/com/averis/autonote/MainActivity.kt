package com.averis.autonote

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var spinner: Spinner
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
    private fun roundTo(numInDouble: Double): String {
        return "%.0f".format(numInDouble)
    }
    fun fuel(fuelTanked:Double, kilometers:Double): Double {
        return (fuelTanked/kilometers)*100
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner = findViewById(R.id.spinner)
        num1 = findViewById(R.id.num1)
        num2 = findViewById(R.id.num2)
//        num3 = findViewById(R.id.num3)
        oblicz = findViewById(R.id.obliczButt)
        wynik = findViewById(R.id.wynik)

        var arrayAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.picks,
            android.R.layout.simple_spinner_item
        )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = arrayAdapter

        // przy kliknięciu oblicza paliwo, jednak gdy nie ma
        // danych nic nadaje że jest błąd w konsoli
        oblicz.setOnClickListener {
            var lp = num1.text.toString()
            var ld = num2.text.toString()
//            var lt = num3.text.toString()
            if (lp.isEmpty()) {
                println("Nie ma liczby przejechanych kilometrów")
            } else if (ld.isEmpty()){
                println("Nie ma liczby zatankowane paliwa")
            }
//            else if (lt.isEmpty()) {
//                println("error")
//            }
            else {
                if(lp.toDouble()%ld.toDouble() == 0.0) {
                    var wynikpaliwa: Double = fuel(ld.toDouble(), lp.toDouble())
                    wynik.text = "${roundTo(wynikpaliwa)} l/100km"
                } else {
                    var wynikpaliwa: Double = fuel(ld.toDouble(), lp.toDouble())
                    wynik.text = "${roundToNum(wynikpaliwa)} l/100km"
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


