package com.rizqirama.uasmobiledua.ui.activity.food

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog
import com.rizqirama.uasmobiledua.R
import com.rizqirama.uasmobiledua.app.ApiConfig
import com.rizqirama.uasmobiledua.model.Food
import com.rizqirama.uasmobiledua.model.ResponseModel
import kotlinx.android.synthetic.main.activity_add_food.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddFoodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food)
        btn_save.setOnClickListener {
            if (edt_name.text.toString().isEmpty()) {
                edt_name.error = "Kolom nama tidak boleh kosong"
                edt_name.requestFocus()
            } else if (edt_type.text.toString().isEmpty()) {
                edt_type.error = "Kolom jenis tidak boleh kosong"
                edt_type.requestFocus()
            } else if (edt_origin.text.toString().isEmpty()) {
                edt_origin.error = "Kolom asal tidak boleh kosong"
                edt_origin.requestFocus()
            } else {
                val food = Food()
                food.foodName = edt_name.text.toString()
                food.typeOfFood = edt_type.text.toString()
                food.originOfFood = edt_origin.text.toString()
                createFood(food)
            }
        }
    }

    private fun createFood(food: Food) {
        val loading = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
        loading.setTitleText("Loading......").show()

        ApiConfig.instanceRetrofit.createFood(food).enqueue(object : Callback<ResponseModel> {
            override fun onFailure(call: retrofit2.Call<ResponseModel>, t: Throwable) {
                loading.dismiss()
                Log.d(ContentValues.TAG, "onFailure")
            }

            override fun onResponse(
                call: retrofit2.Call<ResponseModel>,
                response: Response<ResponseModel>
            ){
                loading.dismiss()
                if (response.isSuccessful) {
                    val res = response.body()!!
                    if (res.success == 1) {
                        toast("Data berhasil disimpan")
                        onBackPressed()
                    } else toast(res.message)
                } else toast(response.message())
            }
        })
    }

    private fun toast(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }
}