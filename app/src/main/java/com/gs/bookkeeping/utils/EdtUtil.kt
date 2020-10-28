package com.gs.bookkeeping.utils

import android.text.Editable
import android.widget.EditText

/**
 * author : gengsai
 * date : 2020/10/20
 * description : 
 */
object EdtUtil {
    fun set(string: String):Editable{
        var editable = Editable.Factory.getInstance().newEditable(string)
        return editable
    }
}