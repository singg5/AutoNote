package com.averis.autonote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import java.math.RoundingMode
import java.text.DecimalFormat


class Calculator : Fragment(R.layout.fragment_calculator), AdapterView.OnItemSelectedListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vehicle = Vehicle("mercedes", 66)
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)
        val calculate: Button = view.findViewById(R.id.calculate)
        val spinner: Spinner = view.findViewById(R.id.spinner)
        val editorOne = view.findViewById<EditText>(R.id.editorOne)
        val editorTwo = view.findViewById<EditText>(R.id.editorTwo)
        val editorThree = view.findViewById<EditText>(R.id.editorThree)
        val result = view.findViewById<TextView>(R.id.result)
        val numberOneString = view.findViewById<TextView>(R.id.num1Str)
        val numberTwoString = view.findViewById<TextView>(R.id.num2Str)

        val error = R.string.error_field_empty

        val kilometersTraveled = getString(R.string.kilometers_traveled)
        val travelCosts = getString(R.string.travel_costs)

        result.text = "- l/100km"

        spinner.adapter = activity?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.picks,
                android.R.layout.simple_spinner_item
            )
        } as SpinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //..
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        editorOne.hint = getString(R.string.distance)
                        numberOneString.text = kilometersTraveled
                        calculate.setOnClickListener {
                            val fuelTanked = editorOne.text.toString()
                            val distance = editorTwo.text.toString()
                            when (fuelTanked.isNotEmpty() || distance.isNotEmpty()) {
                                true -> {
                                    val wynikPaliwa =
                                        vehicle.averageFuelBurn(distance.toDouble(), fuelTanked.toDouble())
                                    val wynikPaliwaStr: String = df.format(wynikPaliwa)
                                    vehicle.lkm = vehicle.removeTrailingZeros(wynikPaliwaStr)
                                    result.text = "${vehicle.lkm} l/100km"
                                }
                                else -> Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    1 -> {
                        val numberOne = editorOne.text.toString()
                        val numberTwo = editorTwo.text.toString()
                        val numberThree = editorThree.text.toString()
                        editorOne.hint = vehicle.lkm
                        when(numberOne.isNotEmpty() || numberTwo.isNotEmpty() || numberThree.isNotEmpty()) {
                            true -> {

                            }
                            else -> Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()
                        }
                        numberOneString.text = getString(R.string.travel_costs)
//                        vehicle.travelCosts(vehicle.lkm.toDouble(), )
                    }
                    2 -> {

                    }
                    3 -> {

                    }
                }
            }
        }



//        calculate.setOnClickListener {
//            val numberOne = distance.text.toString()
//            val numberTwo = fuelTanked.text.toString()
//            val error = R.string.error_field_empty
//            when(numberOne.isNotEmpty() || numberTwo.isNotEmpty()) {
//                true -> {
//                    val wynikPaliwa = vehicle.fuel(numberTwo.toDouble(), numberOne.toDouble())
//                    val wynikPaliwaStr: String = df.format(wynikPaliwa)
//                    vehicle.lkm = vehicle.removeTrailingZeros(wynikPaliwaStr)
//                    result.text = "${vehicle.lkm} l/100km"
//                }
//                else -> Toast.makeText(activity,error ,Toast.LENGTH_SHORT).show()
//            }
//            if (numberOne.isEmpty()) {
//                Toast.makeText(activity,error, Toast.LENGTH_SHORT).show()
//            } else if (numberTwo.isEmpty()){
//                Toast.makeText(activity,error, Toast.LENGTH_SHORT).show()
//            }
//            else {
//                val wynikPaliwa = vehicle.fuel(numberTwo.toDouble(), numberOne.toDouble())
//                val wynikPaliwaStr: String = df.format(wynikPaliwa)
//                vehicle.lkm = vehicle.removeTrailingZeros(wynikPaliwaStr)
//                result.text = "${vehicle.lkm} l/100km"
//
//            }
//        }
        return view
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

//    fun onClick(view: View?) {
//        when(view?.id){
//            R.id.testerButton -> {
//
//            }
//        }
//    }

}