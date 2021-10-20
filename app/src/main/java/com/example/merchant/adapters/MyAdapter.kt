package com.example.merchant.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.merchant.R
import com.example.merchant.data.models.MyDataItem
import kotlinx.android.synthetic.main.items_instock_list.view.*

class MyAdapter(val context: Context, val userList: List<MyDataItem>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var sortno: TextView
        var date: TextView

        init {
            name = itemView.item
            sortno = itemView.brand
            date = itemView.date
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.items_instock_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = userList[position].name
        holder.sortno.text = userList[position].sortno
        holder.date.text = userList[position].created_date

    }

    override fun getItemCount(): Int {
        return userList.size
    }
}