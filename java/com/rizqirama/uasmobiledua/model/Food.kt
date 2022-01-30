package com.rizqirama.uasmobiledua.model

import com.google.gson.Gson

class Food {
    var id = 0
    var foodName = ""
    var typeOfFood = ""
    var originOfFood = ""

    constructor()
    constructor(name: String, type: String, origin: String) {
        this.foodName = name
        this.typeOfFood = type
        this.originOfFood = origin
    }

    fun toJson(): String{
        return Gson().toJson(this, Food::class.java)
    }

    fun fromJson(json: String): Food{
        if (json.isEmpty()) return Food()
        return Gson().fromJson(json, Food::class.java)
    }
}