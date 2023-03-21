package com.rathalove.kunapheapadmin.DataModel.AllItemData

import com.google.gson.annotations.SerializedName


data class Size (

  @SerializedName("size_id"   ) var sizeId   : String? = null,
  @SerializedName("size_name" ) var sizeName : String? = null

)