package com.rizqirama.uasmobiledua.ui.fragment

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rizqirama.uasmobiledua.R
import com.rizqirama.uasmobiledua.adapter.AdapterFood
import com.rizqirama.uasmobiledua.app.ApiConfig
import com.rizqirama.uasmobiledua.model.Food
import com.rizqirama.uasmobiledua.model.ResponseModel
import com.rizqirama.uasmobiledua.ui.activity.food.AddFoodActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class FoodFragment : Fragment() {
    lateinit var btnAddFood : Button
    lateinit var tvStatus : TextView
    lateinit var rvFood : RecyclerView
    lateinit var pd : ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root = inflater.inflate(R.layout.fragment_food, container,false)
        init(root)
        mainButton()
        return root
    }

    private fun mainButton() {

        btnAddFood.setOnClickListener {
            startActivity(Intent(requireActivity(), AddFoodActivity::class.java))
        }
    }

    private fun loadData() {
        pd.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.getFood().enqueue(object : Callback<ResponseModel>{
            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                pd.visibility = View.GONE
                Log.d(ContentValues.TAG, "onFailure")
            }

            override fun onResponse(
                call: retrofit2.Call<ResponseModel>,
                response: Response<ResponseModel>
            ) {
                pd.visibility = View.GONE
                if (response.isSuccessful) {
                    val res = response.body()!!
                    if (res.success == 1)displayFood(res.foods)
                    else toast(res.message)
                } else toast(response.message())
            }
        })
    }

    private fun displayFood(listFood : ArrayList<Food>) {
        if (listFood.isEmpty()) {
            tvStatus.visibility = View.VISIBLE
        } else {
            tvStatus.visibility = View.GONE
        }

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        rvFood.adapter = AdapterFood(listFood)
        rvFood.layoutManager = layoutManager
    }

    fun toast(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    private fun init(view: View) {
        btnAddFood = view.findViewById(R.id.btn_add_data)
        tvStatus = view.findViewById(R.id.tv_status)
        rvFood = view.findViewById(R.id.rv_food)
        pd = view.findViewById(R.id.pd)
    }

    override fun onResume() {
        loadData()
        super.onResume()
    }
}