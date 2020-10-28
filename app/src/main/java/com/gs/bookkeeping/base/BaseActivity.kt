package com.gs.bookkeeping.base

import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * author : gengsai
 * date : 2020/10/13
 * description : 
 */
abstract class BaseActivity :AppCompatActivity(), View.OnClickListener{

    var lastTime :Long = 0

    override fun onClick(v: View?) {
        if (!fastOnClick()){
            OnClickView(v)
        }

    }
    private fun fastOnClick():Boolean{
        if (System.currentTimeMillis() - lastTime < 1000){
            return true
        }
        lastTime = System.currentTimeMillis()
        return false
    }

    protected abstract fun OnClickView(v: View?)
}