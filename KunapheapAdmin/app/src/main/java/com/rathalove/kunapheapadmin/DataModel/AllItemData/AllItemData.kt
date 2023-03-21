package com.rathalove.kunapheapadmin.DataModel.AllItemData

import com.google.gson.annotations.SerializedName


data class AllItemData (

  @SerializedName("item_id"               ) var itemId             : String?      = null,
  @SerializedName("product_id"            ) var productId          : String?      = null,
  @SerializedName("item_amount"           ) var itemAmount         : Int?         = null,
  @SerializedName("item_created_date"     ) var itemCreatedDate    : String?      = null,
  @SerializedName("item_last_modify_date" ) var itemLastModifyDate : String?      = null,
  @SerializedName("color_id"              ) var colorId            : String?      = null,
  @SerializedName("size_id"               ) var sizeId             : String?      = null,
  @SerializedName("product"               ) var product            : Product?     = Product(),
  @SerializedName("ColorOnSide"           ) var ColorOnSide        : ColorOnSide? = ColorOnSide()

)