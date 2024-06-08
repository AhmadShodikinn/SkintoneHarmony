package com.example.skintoneharmony.data.response

import com.google.gson.annotations.SerializedName

data class SkintoneResponse(

	@field:SerializedName("SkintoneResponse")
	val skintoneResponse: List<SkintoneResponseItem>
)

data class SkintoneResponseItem(

	@field:SerializedName("image_url")
	val imageUrl: String,

	@field:SerializedName("shade_id")
	val shadeId: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("source")
	val source: String,

	@field:SerializedName("skintone")
	val skintone: String,

	@field:SerializedName("recommended_brands")
	val recommendedBrands: List<String>
)
