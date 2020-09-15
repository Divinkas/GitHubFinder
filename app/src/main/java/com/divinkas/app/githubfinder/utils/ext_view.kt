package com.divinkas.app.githubfinder.utils

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.SystemClock
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

fun View.disable() {
    this.isEnabled = false
}

fun View.enable() {
    this.isEnabled = true
}

fun View.isVisible() = this.visibility == View.VISIBLE
fun View.isNotVisible() = this.visibility != View.VISIBLE
fun View.visible() {
    if (isNotVisible()) visibility = View.VISIBLE
}

fun View.isInvisible() = this.visibility == View.INVISIBLE
fun View.isNotInvisible() = this.visibility != View.INVISIBLE
fun View.invisible() {
    if (isNotInvisible()) this.visibility = View.INVISIBLE
}

fun View.isGone() = this.visibility == View.GONE
fun View.isNotGone() = this.visibility != View.GONE
fun View.gone() {
    if (isNotGone()) this.visibility = View.GONE
}

fun clearFocusAndHideKeyboard(activity: Activity) {
    val view = activity.currentFocus
    if (view != null) {
        view.clearFocus()
        val manager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun focusEditText(editText: EditText) {
    fun createMotionEvent(action: Int): MotionEvent {
        return MotionEvent.obtain(
            SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), action, 0f, 0f, 0
        )
    }

    Handler().postDelayed({
        editText.dispatchTouchEvent(createMotionEvent(MotionEvent.ACTION_DOWN))
        editText.dispatchTouchEvent(createMotionEvent(MotionEvent.ACTION_UP))
    }, 100)
}

fun gone(vararg views: View) {
    views.forEach { it.gone() }
}

fun visible(vararg views: View) {
    views.forEach { it.visible() }
}