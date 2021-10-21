package com.example.merchant.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.merchant.R
import com.example.merchant.api.ApiInterface
import com.example.merchant.api.ServiceBuilder
import com.example.merchant.data.models.MyDataItem
import com.example.merchant.navigationbaractivities.Dashboard
import com.example.merchant.update.UpdateProduct
import kotlinx.android.synthetic.main.items_instock_list.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyAdapter(val context: Context, val userList: List<MyDataItem>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var sortno: TextView
        var date: TextView
        var buttondelete: ImageButton
        var buttonupdate: ImageButton
        init {
            name = itemView.item
            sortno = itemView.brand
            date = itemView.date
            buttondelete = itemView.delete
            buttonupdate = itemView.update
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
        holder.buttondelete.setOnClickListener {
            val deleteproduct = ServiceBuilder.buildService(ApiInterface::class.java)
            val requestCall = deleteproduct.deleteStock(userList[position].id)

            requestCall.enqueue(object: Callback<Unit> {

                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.isSuccessful) {

                    } else {
//                        Toast.makeText(this@MyAdapter, "Failed to Delete", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.d("MyAdapter", "onFailure:" + t.message)
                }
            })
        }
        holder.buttonupdate.setOnClickListener { v ->
            val context = v.context
            val intent = Intent(context, UpdateProduct::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }
    }



    override fun getItemCount(): Int {
        return userList.size
    }
}