package ru.msokolov.onlineshop.ui

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackBar(parentView: View, message: String) {
    Snackbar.make(parentView, message, Snackbar.LENGTH_SHORT).show()
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Fragment.writeToSharedPrefs(value: String, key: String) {
    val sharedPrefs = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
    with(sharedPrefs.edit()) {
        putString(key, value)
        apply()
    }
}

fun Fragment.readFromSharedPrefs(key: String): String? {
    val sharedPrefs = activity?.getPreferences(Context.MODE_PRIVATE) ?: return null
    return sharedPrefs.getString(key, null)
}

private fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}