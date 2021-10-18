package com.example.merchant.addition

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.merchant.viewmodels.AddItemViewModel
import com.example.merchant.dashboardclass.ItemInStock
import com.example.merchant.R
import com.example.merchant.models.MyDataItem
import com.example.merchant.models.UserResponse
import kotlinx.android.synthetic.main.activity_add_items_in_stock.*

class AddItemsInStock : AppCompatActivity() {
    lateinit var viewModel: AddItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_items_in_stock)

        initViewModel()
        buttonCreate.setOnClickListener {
            createStock()
        }

        val main = findViewById(R.id.back) as ImageView

        main.setOnClickListener{
            val intent = Intent(this, ItemInStock::class.java)
            startActivity(intent)
        }

    }

    private fun createStock() {
        val stock  = MyDataItem(0, name.text.toString(), slug.text.toString(), sortno.text.toString(), "")
        viewModel.createNewStock(stock)

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(AddItemViewModel::class.java)
        viewModel.getCreateNewStockObserver().observe(this, Observer <UserResponse?>{

            if(it  == null) {
                Toast.makeText(this@AddItemsInStock, "Failed to add Stock", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@AddItemsInStock, "Added Stock Successfully", Toast.LENGTH_LONG).show()
            }
        })
    }
}