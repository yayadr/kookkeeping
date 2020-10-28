package com.gs.bookkeeping.account.login.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import com.gs.bookkeeping.base.BaseActivity
import com.gs.bookkeeping.databinding.ActivityLoginBinding

/**
 * author : gengsai
 * date : 2020/10/20
 * description : 
 */
class LoginActivity :BaseActivity(){

    private lateinit var mBinding: ActivityLoginBinding
    private lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        var view = mBinding.root
        setContentView(view)
        mContext = this


    }

    override fun OnClickView(v: View?) {

    }
}