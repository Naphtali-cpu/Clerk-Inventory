package com.example.merchant.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.merchant.api.ApiInterface
import com.example.merchant.api.RetroInstance
import com.example.merchant.models.MyDataItem
import com.example.merchant.models.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddItemViewModel: ViewModel() {
    lateinit var createNewStockLiveData: MutableLiveData<UserResponse?>
    init {
        createNewStockLiveData = MutableLiveData()
    }

    fun getCreateNewStockObserver(): MutableLiveData<UserResponse?> {
        return createNewStockLiveData
    }


    fun createNewStock(stock: MyDataItem) {
        val retroService  = RetroInstance.getRetroInstance().create(ApiInterface::class.java)
        val call = retroService.createStock(stock)
        call.enqueue(object: Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                createNewStockLiveData.postValue(null)
            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if(response.isSuccessful) {
                    createNewStockLiveData.postValue(response.body())
                } else {
                    createNewStockLiveData.postValue(null)
                }
            }
        })
    }


}