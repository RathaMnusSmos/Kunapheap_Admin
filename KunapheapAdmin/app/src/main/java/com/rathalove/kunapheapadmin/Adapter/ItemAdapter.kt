package com.rathalove.kunapheapadmin.Adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.rathalove.kunapheapadmin.ActionListener.OnclickListener
import com.rathalove.kunapheapadmin.DataModel.AllItemData.AllItemData
import com.rathalove.kunapheapadmin.R

import java.util.LinkedList

class ItemAdapter(var context: Context, var listItem: LinkedList<AllItemData>, var listener: OnclickListener):Adapter<ItemAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(view)
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var color = listItem[position].ColorOnSide!!.color!!.colorName.toString()
        var id_item = position + 1
        var name = listItem[position].product!!.productName.toString()
        var size = listItem[position].ColorOnSide!!.size!!.sizeName.toString()
        var price = listItem[position].product!!.productPrice.toString()
        var amount = listItem[position].itemAmount.toString()
        holder.id_txt.text = id_item.toString()
        holder.name_item.text = name
        holder.color_item.setBackgroundColor(Color.parseColor(color))
        holder.size.text = size
        holder.price.text = price
        holder.amount.text = amount

        holder.item.setOnClickListener{
            listener.noItemClick(position, listItem[position])
        }


    }
    override fun getItemCount(): Int {
        return listItem.size
    }




    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var item: LinearLayout = itemView.findViewById(R.id.item_row)
        var id_txt: TextView = itemView.findViewById(R.id.id_txt)
        var name_item: TextView = itemView.findViewById(R.id.name_txt)
        var color_item: TextView = itemView.findViewById(R.id.color)
        var size: TextView = itemView.findViewById(R.id.size_txt)
        var price: TextView = itemView.findViewById(R.id.price_txt)
        var amount: TextView = itemView.findViewById(R.id.amount_txt)
    }


}