package com.averis.autonote

import java.text.DecimalFormat

class Vehicle(var name: String, fuelcap: Int) {
    var lkm: String? = null

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
}