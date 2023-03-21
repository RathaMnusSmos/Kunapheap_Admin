package com.rathalove.kunapheapadmin.DataModel.AllItemData

import com.google.gson.annotations.SerializedName


data class Product (

  @SerializedName("product_id"       ) var productId       : String? = null,
  @SerializedName("product_name"     ) var productName     : String? = null,
  @SerializedName("product_price"    ) var productPrice    : String? = null,
  @SerializedName("product_discount" ) var productDiscount : Double? = null,
  @SerializedName("category_id"      ) var categoryId      : String? = null

)