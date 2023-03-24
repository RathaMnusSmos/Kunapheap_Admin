package com.rathalove.kunapheapadmin.DataModel.AllProductData

import com.google.gson.annotations.SerializedName


data class AllProductData (

  @SerializedName("product_id"       ) var productId       : String?         = null,
  @SerializedName("product_name"     ) var productName     : String?         = null,
  @SerializedName("product_price"    ) var productPrice    : String?         = null,
  @SerializedName("product_discount" ) var productDiscount : Double?         = null,
  @SerializedName("category_id"      ) var categoryId      : String?         = null,
  @SerializedName("item"             ) var item            : ArrayList<Item> = arrayListOf()

)