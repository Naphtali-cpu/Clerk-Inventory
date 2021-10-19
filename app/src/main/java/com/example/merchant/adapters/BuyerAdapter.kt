package com.example.merchant.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.merchant.R
import com.example.merchant.models.Buyers
import com.example.merchant.models.MyDataItem
import kotlinx.android.synthetic.main.buyerslist.view.*
import kotlinx.android.synthetic.main.items_instock_list.view.*
import kotlinx.android.synthetic.main.items_instock_list.view.date

class BuyerAdapter(val context: Context, val userList: List<Buyers>): RecyclerView.Adapter<BuyerAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var address: TextView
        var date: TextView

        init {
            name = itemView.buyername
            address = itemView.location
            date = itemView.date
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.buyerslist, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = userList[position].name
        holder.address.text = userList[position].address
        holder.date.text = userList[position].created_date

    }

    override fun getItemCount(): Int {
        return userList.size
    }
}