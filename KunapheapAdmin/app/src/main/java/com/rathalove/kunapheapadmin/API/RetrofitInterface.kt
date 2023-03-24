package com.rathalove.kunapheapadmin.API

import com.rathalove.kunapheapadmin.DataModel.AllItemData.AllItemData
import com.rathalove.kunapheapadmin.DataModel.AllProductData.AllProductData
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {

    @GET("/item/allItems")
    fun getItemData(): Call<ArrayList<AllItemData>>

    @GET("/product/getAllProduct")
    fun getProductData(): Call<ArrayList<AllProductData>>

}