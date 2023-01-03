/*
 Created by @noriban
*/
package com.averis.autonote

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
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
        val info: TextView = view.findViewById(R.id.info)
        val fuelPrice: TextView = view.findViewById(R.id.fuelPrice)
        val spinner: Spinner = view.findViewById(R.id.spinner)
        val editorOne = view.findViewById<EditText>(R.id.editorOne)
        val editorTwo = view.findViewById<EditText>(R.id.editorTwo)
        val editorThree = view.findViewById<EditText>(R.id.editorThree)
        val result = view.findViewById<TextView>(R.id.result)
        val numberOneString = view.findViewById<TextView>(R.id.num1Str)
        val numberTwoString = view.findViewById<TextView>(R.id.num2Str)
        val numberThreeString = view.findViewById<TextView>(R.id.num3Str)

        val error = R.string.error_field_empty

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
                        editorOne.text = null
                        editorTwo.text = null
                        editorThree.text = null
                        // Names
                        info.text = getString(R.string.text_for_average_fuel)
                        numberOneString.text = getString(R.string.kilometers_traveled)
                        numberTwoString.text = getString(R.string.fuel_tanked)
                        numberThreeString.text = getString(R.string.fuel_price_liters)
                        editorOne.hint = "100.00"
                        editorTwo.hint = getString(R.string.fuel)
                        when(vehicle.price){
                            null -> editorThree.hint = "00.00"
                            else -> {
                                editorThree.setText(vehicle.price.toString())
                            }
                        }
                        fuelPrice.text = null
                        result.text = "- l/100km"

                        calculate.setOnClickListener {
                            val distance = editorOne.text.toString()
                            val fuelTanked = editorTwo.text.toString()
                            val price = editorThree.text.toString()
                            when (fuelTanked.isNotEmpty() && distance.isNotEmpty()) {
                                true -> {
                                    when(price.isNotEmpty()) {
                                        true -> {
                                            vehicle.price = price
                                            val fuelResult = vehicle.averageFuelBurnCalc(fuelTanked.toDouble(), distance.toDouble() )
                                            val fuelResultString: String = df.format(fuelResult)
                                            vehicle.averageFuelBurn = vehicle.removeTrailingZeros(fuelResult.toString())
                                            result.text = "${vehicle.removeTrailingZeros(fuelResultString)} l/100km"

                                            val fuelPriceText = vehicle.averageFuelBurnPriceCalc(fuelTanked.toDouble(), price.toDouble())
                                            val fuelPriceVehicle = df.format(fuelPriceText)
                                            vehicle.price = vehicle.removeTrailingZeros(price)
                                            fuelPrice.text = "${vehicle.removeTrailingZeros(fuelPriceVehicle)} ${getString(R.string.currency_hint)}"
                                        }
                                        else -> {
                                            val fuelResult = vehicle.averageFuelBurnCalc(fuelTanked.toDouble(), distance.toDouble() )
                                            val fuelResultString: String = df.format(fuelResult)
                                            vehicle.averageFuelBurn = vehicle.removeTrailingZeros(fuelResult.toString())
                                            result.text = "${vehicle.removeTrailingZeros(fuelResultString)} l/100km"
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
                        info.text = getString(R.string.text_for_travel_costs)
                        editorOne.text = null
                        editorTwo.text = null
                        editorThree.text = null
                        fuelPrice.text = null
                        numberOneString.text = getString(R.string.average_fuel_burn)

                        when(vehicle.averageFuelBurn){
                            null -> editorOne.hint = "00.00"
                            else -> {
                                editorOne.setText(vehicle.averageFuelBurn.toString())
                            }
                        }

                        numberTwoString.text = getString(R.string.kilometers_traveled)
                        editorTwo.hint = "100.00"

                        numberThreeString.text = getString(R.string.fuel_price_liters)
                        when(vehicle.price){
                            null -> editorThree.hint = "00.00"
                            else -> {
                                editorThree.setText(vehicle.price.toString())
                            }
                        }



                        result.text = "- ${getString(R.string.currency_hint)}"
                        fuelPrice.text = null


                        calculate.setOnClickListener {
                            val averageFuelBurn = editorOne.text.toString()
                            val distance = editorTwo.text.toString()
                            val price = editorThree.text.toString()
                            when(averageFuelBurn.isNotEmpty() && distance.isNotEmpty() && price.isNotEmpty()) {
                                true -> {
                                    val fuelResult =
                                        vehicle.travelCostsCalc(averageFuelBurn.toDouble(), distance.toDouble(), price.toDouble())
                                    val fuelResultString: String = df.format(fuelResult)
                                    vehicle.price = vehicle.removeTrailingZeros(price)
                                    result.text = "${vehicle.removeTrailingZeros(fuelResultString)} ${getString(R.string.currency_hint)}"
                                }
                                else -> Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()
                            }
                            it.hideKeyboard()
                        }
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