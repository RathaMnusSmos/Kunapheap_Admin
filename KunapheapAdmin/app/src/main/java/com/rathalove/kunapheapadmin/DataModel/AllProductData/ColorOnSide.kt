package com.rathalove.kunapheapadmin.DataModel.AllProductData

import com.google.gson.annotations.SerializedName


data class ColorOnSide (

  @SerializedName("color_id" ) var colorId : String? = null,
  @SerializedName("size_id"  ) var sizeId  : String? = null,
  @SerializedName("color"    ) var color   : Color?  = Color(),
  @SerializedName("size"     ) var size    : Size?   = Size()

)