package br.stone.mobiletraining.samilasantos.chucknorrisfacts.extensions

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface

fun Activity.dialog(
    msg: String,
    positiveButton: String = "Ok",
    negativeButton: String = "Cancel",
    listener: (DialogInterface, Int) -> Unit = { d, _ -> d.dismiss() }
) =
    AlertDialog
        .Builder(this)
        .setCancelable(false)
        .setPositiveButton(positiveButton, listener)
        .setNegativeButton(negativeButton, listener)
        .setMessage(msg)
