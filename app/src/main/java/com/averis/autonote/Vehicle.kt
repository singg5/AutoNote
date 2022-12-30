package com.averis.autonote

class Vehicle(var name: String, fuelCap: Int) {
    var lkm: String? = null

    fun averageFuelBurn(fuelTanked:Double, kilometers:Double): Double {
        return (fuelTanked/kilometers)*100
    }
    fun travelCosts(lkm: Double, kilometers: Double, fuelPrice: Double): Double {
        return (lkm*kilometers)/100*fuelPrice
    }
    fun removeTrailingZeros(num: String): String{
        if(!num.contains('.'))
            return num
        return num
            .dropLastWhile { it == '0' }
            .dropLastWhile { it == '.' }
    }
}