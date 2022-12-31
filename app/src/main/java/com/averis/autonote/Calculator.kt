package com.averis.autonote

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.material.internal.ViewUtils.hideKeyboard
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
        val info: TextView = view.findViewById(R.id.info)
        val fuelPrice: TextView = view.findViewById(R.id.fuelPrice)
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
                        info.text = getString(R.string.text_for_average_fuel)

                        calculate.setOnClickListener {
                            val distance = editorOne.text.toString()
                            val fuelTanked = editorTwo.text.toString()
                            val price = editorThree.text.toString()
                            editorOne.text = null
                            editorTwo.text = null
                            editorThree.text = null


                            when (fuelTanked.isNotEmpty() || distance.isNotEmpty()) {
                                true -> {

                                    when(price.isNotEmpty()) {
                                        true -> {
                                            val fuelResult = vehicle.averageFuelBurnCalc(fuelTanked.toDouble(), distance.toDouble() )
                                            val fuelResultStr: String = df.format(fuelResult)
                                            val fuelPriceText = vehicle.averageFuelBurnPriceCalc(fuelTanked.toDouble(), price.toDouble())
                                            val fuelPriceVehicle = df.format(fuelPriceText)
                                            vehicle.averageFuelBurn = vehicle.removeTrailingZeros(fuelResultStr)
                                            result.text = "${vehicle.averageFuelBurn} l/100km"
                                            fuelPrice.text = "${vehicle.removeTrailingZeros(fuelPriceVehicle)} ${getString(R.string.currency_hint)}"
//                                            Toast.makeText(activity, "TODO: Make averagefuelburn function with price", Toast.LENGTH_SHORT).show()
                                    }
                                        else -> {
                                            val wynikPaliwa =
                                                vehicle.averageFuelBurnCalc(distance.toDouble(), fuelTanked.toDouble())
                                            val wynikPaliwaStr: String = df.format(wynikPaliwa)
                                            vehicle.averageFuelBurn = vehicle.removeTrailingZeros(wynikPaliwaStr)
                                            result.text = "${vehicle.averageFuelBurn} l/100km"
                                        }
                                    }

                                }
                                else -> {
                                    Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()
                                }
                            }
                            it.hideKeyboard()
                        }
                    }
                    1 -> {
                        val numberOne = editorOne.text.toString()
                        val numberTwo = editorTwo.text.toString()
                        val numberThree = editorThree.text.toString()
                        editorOne.text = null
                        editorTwo.text = null
                        editorThree.text = null
                        editorThree.hint = "${getString(R.string.travel_costs)} (${getString(R.string.optional)})"
                        numberOneString.text = getString(R.string.travel_costs)

                        calculate.setOnClickListener {
                            when(numberOne.isNotEmpty() || numberTwo.isNotEmpty() || numberThree.isNotEmpty()) {
                                true -> {

                                }
                                else -> Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()
                            }
                        }
                        when(vehicle.averageFuelBurn){
                            null -> editorOne.hint = "00.00"
                            else -> editorOne.hint = vehicle.averageFuelBurn
                        }


//                        vehicle.travelCosts(vehicle.lkm.toDouble(), )
                    }
                    2 -> {
                        Toast.makeText(activity, "This feature is not implemented yet", Toast.LENGTH_SHORT).show()
                    }
                    3 -> {
                        Toast.makeText(activity, "This feature is not implemented yet", Toast.LENGTH_SHORT).show()
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
    fun View.hideKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
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