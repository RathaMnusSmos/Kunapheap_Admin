package com.rathalove.kunapheapadmin.DataModel.AllProductData

import com.google.gson.annotations.SerializedName


data class Image (

  @SerializedName("image_id"   ) var imageId   : String? = null,
  @SerializedName("image_link" ) var imageLink : String? = null,
  @SerializedName("item_id"    ) var itemId    : String? = null

)