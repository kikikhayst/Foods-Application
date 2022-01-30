package com.rizqirama.uasmobiledua.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rizqirama.uasmobiledua.R
import com.rizqirama.uasmobiledua.model.Food

class AdapterFood (var data: ArrayList<Food>): RecyclerView.Adapter<AdapterFood.Holder>(){
    class Holder(view: View): RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvType = view.findViewById<TextView>(R.id.tv_type)
        val tvOrigin = view.findViewById<TextView>(R.id.tv_origin)
        val layout = view.findViewById<LinearLayout>(R.id.layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view : View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val a = data[position]

        holder.tvName.text = a.foodName
        holder.tvType.text = a.typeOfFood
        holder.tvOrigin.text = a.originOfFood
        holder.layout.setOnClickListener{

        }
    }
}