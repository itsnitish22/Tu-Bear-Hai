package com.nitishsharma.tubeerhai.utility

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment

object Utility {
    fun Context.shareOnWhatsapp(sharedMsg: String) {
        val whatsappIntent = Intent(Intent.ACTION_SEND)
        whatsappIntent.type = "text/plain"
        whatsappIntent.setPackage("com.whatsapp")
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, sharedMsg)
        try {
            this.startActivity(whatsappIntent)
        } catch (ex: ActivityNotFoundException) {
            this.toast("Install whatsapp first")
        }
    }

    fun Fragment.shareOnWhatsapp(sharedMsg: String) {
        requireContext().shareOnWhatsapp(sharedMsg)
    }

    fun Context.toast(message: String) {
        Toast.makeText(
            this, message,
            if (message.length <= 25) Toast.LENGTH_SHORT else Toast.LENGTH_LONG
        ).show()
    }

    fun Fragment.toast(msg: String) {
        requireContext().toast(msg)
    }
}