package com.example.taxopark.utils.text

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan

object TextViewStyleUtils {

    fun bold(phone: String, text: String): SpannableString {
        val spannableText = SpannableString(text)

// Find the start and end points of the phone number in the text
        val phoneStart = 0
        val phoneEnd = phone.length

// Set the phone number portion of the text to bold
        spannableText.setSpan(
            StyleSpan(Typeface.BOLD),
            phoneStart,
            phoneEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannableText
    }
}