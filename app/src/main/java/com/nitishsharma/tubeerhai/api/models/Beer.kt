package com.nitishsharma.tubeerhai.api.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
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
) : Parcelable

@Parcelize
data class Ingredients(
    val malt: ArrayList<Malt>,
    val hops: ArrayList<Hop>,
    val yeast: String
) : Parcelable

@Parcelize
data class Hop(
    val name: String,
    val amount: Amount,
) : Parcelable

@Parcelize
data class Malt(
    val name: String,
    val amount: Amount
) : Parcelable

@Parcelize
data class Amount(
    val value: Double,
    val unit: String
) : Parcelable