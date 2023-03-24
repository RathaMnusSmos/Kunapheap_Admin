package com.rathalove.kunapheapadmin.DataModel.AllProductData

import com.google.gson.annotations.SerializedName


data class Item (

  @SerializedName("item_id"               ) var itemId             : String?          = null,
  @SerializedName("product_id"            ) var productId          : String?          = null,
  @SerializedName("item_amount"           ) var itemAmount         : Int?             = null,
  @SerializedName("item_created_date"     ) var itemCreatedDate    : String?          = null,
  @SerializedName("item_last_modify_date" ) var itemLastModifyDate : String?          = null,
  @SerializedName("color_id"              ) var colorId            : String?          = null,
  @SerializedName("size_id"               ) var sizeId             : String?          = null,
  @SerializedName("ColorOnSide"           ) var ColorOnSide        : ColorOnSide?     = ColorOnSide(),
  @SerializedName("image"                 ) var image              : ArrayList<Image> = arrayListOf()

)