package com.example.cryptocoinapp.common.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.core.content.ContextCompat.startActivity


class Handler {
    fun onClickMethodReference(url: String,view:View) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        view.context.startActivity(browserIntent)
    }
}