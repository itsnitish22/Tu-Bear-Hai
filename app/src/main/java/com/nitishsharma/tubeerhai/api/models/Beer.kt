package com.nitishsharma.tubeerhai.api.models

data class Beer(
    val id: Int,
    val name: String,
    val first_brewed: String,
    val image_url: String,
    val abv: Double,
    val tagline: String,
    val description: String,
    val food_pairing: ArrayList<String>,
    val brewers_tips: String,
    val ingredients: Ingredients
)

data class Ingredients(
    val malt: ArrayList<Malt>,
    val hops: ArrayList<Hop>,
    val yeast: String
)

data class Hop(
    val name: String,
    val amount: Amount,
)

data class Malt(
    val name: String,
    val amount: Amount
)

data class Amount(
    val value: Double,
    val unit: String
)