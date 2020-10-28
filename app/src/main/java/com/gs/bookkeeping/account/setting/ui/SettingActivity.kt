package com.gs.bookkeeping.account.setting.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import com.gs.bookkeeping.base.BaseActivity
import com.gs.bookkeeping.databinding.ActivitySettingBinding

/**
 * author : gengsai
 * date : 2020/10/21
 * description : 设置
 */
class SettingActivity :BaseActivity() {

    private lateinit var mBinding: ActivitySettingBinding
    private lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySettingBinding.inflate(layoutInflater)
        var view = mBinding.root
        setContentView(view)
        mContext = this
        initViews()
    }

    fun initViews(){
        mBinding.title.tvTitle.text = "设置"
        mBinding.title.ivTitleBack.setOnClickListener(this)
    }
    override fun OnClickView(v: View?) {
        if (v == mBinding.title.ivTitleBack){
            finish()
        }
    }
}