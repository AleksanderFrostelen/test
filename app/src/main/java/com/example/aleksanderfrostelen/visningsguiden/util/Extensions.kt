package com.example.aleksanderfrostelen.visningsguiden.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import java.io.ByteArrayOutputStream
import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import kotlin.math.roundToInt
import kotlin.reflect.KClass

/**
 * Created by alex on 2018-02-21.
 */

val swedishSymbols = DecimalFormatSymbols(Locale("sv","SE"))
val decimalFormat = DecimalFormat("###,###,###.##", swedishSymbols)
val percentageFormat = DecimalFormat("+0.0;-#")

const val charSet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
val random = SecureRandom()

fun Activity.presentActivity(targetClass: KClass<*>, bundle: Bundle? = null, animIn : Int = -1, animOut : Int = -1, forResult : Boolean = false) {
    val intent = Intent(this, targetClass.java)
    bundle?.let {
        intent.putExtras(it)
    }

    if (forResult) {
        startActivityForResult(intent, 1)
    } else {
        startActivity(intent)
    }

    if (animIn > 0) {
        this.overridePendingTransition(animIn, animOut)
    }
}

fun Fragment.dismissKeyboard() {
    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    activity?.currentFocus?.windowToken?.let {
        imm.hideSoftInputFromWindow(it, 0)
    }
}

fun Fragment.showKeyboard(editText: EditText) {
    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    editText.requestFocus()
    imm.showSoftInput(editText, 0)
}

fun Activity.dismissKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    currentFocus?.windowToken?.let {
        imm.hideSoftInputFromWindow(it, 0)
    }
}

fun Int.toFormattedNumber() : String {
    return decimalFormat.format(this)
}

fun Double.toFormattedNumber() : String {
    return decimalFormat.format(this)
}

fun Double.toFormattedDouble() : String {
    return decimalFormat.format(this)
}

fun Double.toPercentageDouble() : String {
    return percentageFormat.format(this)
}

fun Double.toRoundedInt() : Int {
    return this.roundToInt()
}

fun String.Companion.randomString(len: Int): String {
    val sb = StringBuilder(len)
    for (i in 0 until len)
        sb.append(charSet[random.nextInt(charSet.length)])
    return sb.toString()
}

fun String.SHA1(): String {
    var md: MessageDigest? = null
    try {
        md = MessageDigest.getInstance("SHA-1")
        val textBytes = this.toByteArray(charset("UTF-8"))
        md!!.update(textBytes, 0, textBytes.size)
        val sha1hash = md.digest()
        return sha1hash.convertToHex()
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    } catch (e: UnsupportedEncodingException) {
        e.printStackTrace()
    }

    return ""
}

fun ByteArray.convertToHex() : String {
    var result = ""

    this.forEach {
        result += (String.format("%02X", it))
    }

    return result
}

fun Bitmap.toByteArray() : ByteArray{
    val bos = ByteArrayOutputStream()
    compress(android.graphics.Bitmap.CompressFormat.PNG, 100, bos)

    val data = bos.toByteArray()
    try {
        bos.close()
    } catch (e : Exception) {
        e.printStackTrace()
    }
    return data
}

fun String.isValidEmailAddress(): Boolean {
    return matches(Regex("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+\$"))
}

fun String.capitalizeWords(): String {
    if (isNullOrBlank()) return ""

    val words = split(" ")

    return words.joinToString(" ") {
        it.capitalize()
    }.trimEnd()
}

