package com.nitishsharma.tubeerhai.main.bottomsheet

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.bold
import androidx.core.text.underline
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nitishsharma.tubeerhai.api.models.Beer
import com.nitishsharma.tubeerhai.databinding.BottomsheetDetailedBinding

class DetailedBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: BottomsheetDetailedBinding
    private lateinit var beer: Beer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = BottomsheetDetailedBinding.inflate(inflater, container, false).also {
        binding = it
        beer = arguments?.getParcelable("SELECTED_BEER")!!
    }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (view?.parent as View).setBackgroundColor(Color.TRANSPARENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(beer)
    }

    private fun initViews(currentBeer: Beer) {
        binding.apply {
            com.bumptech.glide.Glide.with(requireActivity()).load(currentBeer.image_url)
                .into(includedBeerLv.liquorImage)
            includedBeerLv.beerName.text = currentBeer.name
            includedBeerLv.beerVolume.text = currentBeer.abv.toString() + "%"
            descriptionBeerName.text = currentBeer.name
            descriptionBeerBrewedDate.text =
                SpannableStringBuilder().bold { append("First Brewed On:   ") }
                    .append(currentBeer.first_brewed)
            descriptionBeerVolume.text =
                SpannableStringBuilder().bold { append("Alcohol Concentration:   ") }
                    .append(currentBeer.abv.toString()).append("%")
            descriptionBeerTagline.text = currentBeer.tagline
            descriptionBeerDescription.text =
                SpannableStringBuilder().bold {
                    underline { append("Product Description:") }.append(
                        "   "
                    )
                }.append(currentBeer.description)

            var food_pairing = "\n"
            for (i in currentBeer.food_pairing) {
                food_pairing = "$food_pairing • $i \n"
            }
            descriptionBeerFoodPairing.text =
                SpannableStringBuilder().bold {
                    underline { append("Best When Paired With:") }.append(
                        "   "
                    )
                }.append(food_pairing)
            descriptionBeerBrewingTips.text =
                SpannableStringBuilder().bold { underline { append("Some Brewing Tips:") }.append("   ") }
                    .append(currentBeer.brewers_tips)
            var malt = "\n"
            var hops = "\n"
            var yeast = currentBeer.ingredients.yeast
            for (i in currentBeer.ingredients.malt) {
                malt = "$malt • ${i.name} - ${i.amount.value} ${i.amount.unit} \n"
            }
            for (i in currentBeer.ingredients.hops) {
                hops = "$hops • ${i.name} - ${i.amount.value} ${i.amount.unit} \n"
            }
            binding.descriptionBeerIngredients.text =
                SpannableStringBuilder().bold { underline { append("Ingredients:\n") }.append("\nMalt:") }
                    .append(malt).bold { append("\nHops:") }.append(hops)
                    .bold { append("\nYeast:   ") }.append(yeast)
        }
    }

    companion object {
        const val ARGS = "SELECTED_BEER"

        fun newInstance(beer: Beer): DetailedBottomSheet {
            val fragment = DetailedBottomSheet()
            val args = Bundle().apply {
                putParcelable(ARGS, beer)
            }
            fragment.arguments = args
            return fragment
        }
    }

}