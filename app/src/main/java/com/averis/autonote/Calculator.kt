package com.averis.autonote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.math.RoundingMode
import java.text.DecimalFormat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [Calculator.newInstance] factory method to
 * create an instance of this fragment.
 */
class Calculator : Fragment(R.layout.fragment_calculator) {
    fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return kotlin.math.round(this * multiplier) / multiplier
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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vehicle = Vehicle("mercedes", 66)
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)
        val calculate: Button = view.findViewById(R.id.calculate)
//        val spinner = findViewById(R.id.spinner) // for feature use
        val distance = view.findViewById<EditText>(R.id.distance)
        val fuelTanked = view.findViewById<EditText>(R.id.fuelTanked)
        val result = view.findViewById<TextView>(R.id.result)

        result.text = "- l/100km"


        calculate.setOnClickListener {
            var numberOne = distance.text.toString()
            var numberTwo = fuelTanked.text.toString()
            if (numberOne.isEmpty()) {
                println("Nie ma liczby przejechanych kilometrów")
            } else if (numberTwo.isEmpty()){
                println("Nie ma liczby zatankowane paliwa")
            }
            else {
                val wynikPaliwa = vehicle.fuel(numberTwo.toDouble(), numberOne.toDouble())
//                val wynikPaliwa: Double = fuel(ld.toDouble(), lp.toDouble()) // 4.99
                val wynikPaliwaStr: String = df.format(wynikPaliwa)
                val wynikPaliwaStrTwo: String = roundToTwo(wynikPaliwa)
                vehicle.lkm = vehicle.removeTrailingZeros(wynikPaliwaStr)
                result.text = "${vehicle.lkm} l/100km"

            }
        }
        return view
    }
//    fun onClick(view: View?) {
//        when(view?.id){
//            R.id.testerButton -> {
//
//            }
//        }
//    }

}