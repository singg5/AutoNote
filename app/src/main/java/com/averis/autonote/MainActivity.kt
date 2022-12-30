package com.averis.autonote

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.averis.autonote.databinding.ActivityMainBinding
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*
import kotlin.math.round


class MainActivity : AppCompatActivity(R.layout.activity_main), AdapterView.OnItemSelectedListener {

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

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Dashboard())

        binding.bottomNavigationView.setOnItemSelectedListener {
                    when(it.itemId) {
                        R.id.dashboard -> replaceFragment(Dashboard())
                        R.id.note -> replaceFragment(Note())
                        R.id.calculator -> replaceFragment(Calculator())
                        else -> {
                        }
                    }
                        true
        }






        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN


//        // TODO: Dokumentacja
//        // przy kliknięciu oblicza paliwo, jednak gdy nie ma
//        // danych nic nadaje że jest błąd w konsoli
//        oblicz.setOnClickListener {
//            var numberOne = number1.text.toString()
//            var numberTwo = number2.text.toString()
//            if (numberOne.isEmpty()) {
//                println("Nie ma liczby przejechanych kilometrów")
//            } else if (numberTwo.isEmpty()){
//                println("Nie ma liczby zatankowane paliwa")
//            }
//            else {
//                val wynikPaliwa = vehicle.fuel(numberTwo.toDouble(), numberOne.toDouble())
////                val wynikPaliwa: Double = fuel(ld.toDouble(), lp.toDouble()) // 4.99
//                val wynikPaliwaStr: String = df.format(wynikPaliwa)
//                val wynikPaliwaStrTwo: String = roundToTwo(wynikPaliwa)
////                if(wynikPaliwaStr.contains(".0")) {
////                    wynik.text = "${removeTrailingZeros(wynikPaliwaStr)} l/100km"
////                } else {
////                    wynik.text = "${wynikPaliwaStr} l/100km"
////                }
//                vehicle.lkm = vehicle.removeTrailingZeros(wynikPaliwaStr)
//                result.text = "${vehicle.lkm} l/100km"
//
//            }
//        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}


