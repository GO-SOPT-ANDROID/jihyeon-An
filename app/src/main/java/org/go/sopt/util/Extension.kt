package org.go.sopt.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar

fun View.makeSnackBar(message: String){
    Snackbar.make(
        this,
        message,
        Snackbar.LENGTH_SHORT
    ).show()
}

fun Context.hideKeyBoard(view : View){
    val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(view.windowToken, 0)
}