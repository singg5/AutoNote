package com.averis.autonote

class Vehicle(var name: String, fuelCap: Int) {
    var averageFuelBurn: Any? = null
    var price: Any? = null

    fun averageFuelBurnCalc(fuelTanked:Double, kilometers:Double): Double {
        return (fuelTanked/kilometers)*100
    }
    fun averageFuelBurnPriceCalc(fuelTanked:Double, price: Double): Double {
        return fuelTanked*price
    }
    fun travelCostsCalc(averageFuelBurn: Double, kilometers: Double, fuelPrice: Double): Double {
        return (averageFuelBurn*kilometers)/100*fuelPrice
    }
    fun removeTrailingZeros(num: String): String{
        if(!num.contains('.'))
            return num
        return num
            .dropLastWhile { it == '0' }
            .dropLastWhile { it == '.' }
    }
}