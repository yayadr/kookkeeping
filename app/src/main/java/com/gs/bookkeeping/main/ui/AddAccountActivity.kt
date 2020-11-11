package com.gs.bookkeeping.main.ui

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.gs.bookkeeping.base.BaseActivity
import com.gs.bookkeeping.databinding.ActivityAddAccountBinding
import com.gs.bookkeeping.db.AccountBeanDao
import com.gs.bookkeeping.main.beans.AccountBean
import com.gs.bookkeeping.utils.DateUtil
import com.gs.bookkeeping.utils.DbUtil
import com.gs.bookkeeping.utils.ToastUtil

/**
 * author : gengsai
 * date : 2020/10/16
 * description : 添加账户
 */
class AddAccountActivity :BaseActivity() {

    private lateinit var mBinding: ActivityAddAccountBinding
    private lateinit var mContext: Context

    lateinit var accountBeanDao: AccountBeanDao
    lateinit var accountBeanDao_info: AccountBeanDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAddAccountBinding.inflate(layoutInflater)
        var view = mBinding.root
        setContentView(view)
        mContext = this
        initViews()
        initDb()
    }

    fun initViews(){
        mBinding.title.tvTitle.text = "添加账户"
        mBinding.title.ivTitleBack.setOnClickListener(this)
        mBinding.tvAddAdd.setOnClickListener(this)
    }

    fun initDb(){
        val daoSession = DbUtil.initAccountDb(mContext)
        accountBeanDao = daoSession.accountBeanDao

        val daoSession_info  = DbUtil.initInfoDb(mContext)
        accountBeanDao_info = daoSession_info.accountBeanDao
    }

    override fun OnClickView(v: View?) {
        if (v == mBinding.title.ivTitleBack){
            finish()
        }else if (v == mBinding.tvAddAdd){
            if (TextUtils.isEmpty(mBinding.edtAddAccount.text.toString())
                || TextUtils.isEmpty(mBinding.edtAddMoney.text.toString())){
                ToastUtil.show("不可为空",mContext)
                return
            }
            keepInfo()
            finish()
        }
    }

    fun keepInfo(){
        var name = mBinding.edtAddAccount.text.toString()
        var num = mBinding.edtAddMoney.text.toString()
        var des = mBinding.edtAddDes.text.toString()
        if (TextUtils.isEmpty(des)){
            des = ""
        }

        var acc = AccountBean()
        acc.cId = name + System.currentTimeMillis()
        acc.name = name
        acc.num = num
        acc.numF = num.toFloat()
        acc.date = DateUtil.getDayDate()
        acc.des = des

        accountBeanDao.insertOrReplace(acc)
        accountBeanDao_info.insertOrReplace(acc)
    }

}