package com.gs.bookkeeping.utils

import android.content.Context
import android.widget.Toast

/**
 * author : gengsai
 * date : 2020/10/16
 * description : 
 */

object ToastUtil {

    fun show(msg:String , context: Context){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
    }

}