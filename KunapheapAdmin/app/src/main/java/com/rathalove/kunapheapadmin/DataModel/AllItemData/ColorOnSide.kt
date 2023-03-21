package com.rathalove.kunapheapadmin.DataModel.AllItemData

import com.google.gson.annotations.SerializedName


data class ColorOnSide (

  @SerializedName("color_id" ) var colorId : String? = null,
  @SerializedName("size_id"  ) var sizeId  : String? = null,
  @SerializedName("size"     ) var size    : Size?   = Size(),
  @SerializedName("color"    ) var color   : Color?  = Color()

)