package com.example.merchant.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.merchant.R
import com.example.merchant.data.models.Buyers
import com.example.merchant.data.models.Deliveries
import kotlinx.android.synthetic.main.buyerslist.view.*
import kotlinx.android.synthetic.main.deliverylist.view.*
import kotlinx.android.synthetic.main.items_instock_list.view.*


class DeliveryAdapter(val context: Context, val userList: List<Deliveries>): RecyclerView.Adapter<DeliveryAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var item: TextView
        var sender: TextView
        var time: TextView

        init {
            name = itemView.couriername
            item = itemView.itemreceived
            sender = itemView.couriername
            time = itemView.deliverytime
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.deliverylist, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = userList[position].name
        holder.item.text = userList[position].order
        holder.sender.text = userList[position].courier_name
        holder.time.text = userList[position].created_date

    }

    override fun getItemCount(): Int {
        return userList.size
    }
}