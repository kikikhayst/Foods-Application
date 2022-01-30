package com.rizqirama.uasmobiledua.model

class ResponseModel {
    var success = 0
    lateinit var message : String
    var food = Food()

    var foods : ArrayList<Food> = ArrayList()
}