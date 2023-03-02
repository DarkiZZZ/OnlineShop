package ru.msokolov.onlineshop.ui

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackBar(parentView: View, message: String){
    Snackbar.make(parentView, message, Snackbar.LENGTH_SHORT).show()
}