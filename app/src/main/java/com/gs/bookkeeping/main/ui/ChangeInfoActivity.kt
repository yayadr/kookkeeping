package com.gs.bookkeeping.main.ui

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.view.View
import com.gs.bookkeeping.base.BaseActivity
import com.gs.bookkeeping.databinding.ActivityAddAccountBinding
import com.gs.bookkeeping.db.AccountBeanDao
import com.gs.bookkeeping.db.DaoMaster
import com.gs.bookkeeping.main.beans.AccountBean
import com.gs.bookkeeping.utils.DateUtil
import com.gs.bookkeeping.utils.DbUtil
import com.gs.bookkeeping.utils.EdtUtil
import com.gs.bookkeeping.utils.ToastUtil

/**
 * author : gengsai
 * date : 2020/10/16
 * description : 修改信息
 */
class ChangeInfoActivity :BaseActivity() {

    private lateinit var mBinding: ActivityAddAccountBinding
    private lateinit var mContext: Context

    private lateinit var accountBeanDao: AccountBeanDao
    private lateinit var accountBeanDao_info: AccountBeanDao

    private lateinit var accountBean: AccountBean
    private var id:Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAddAccountBinding.inflate(layoutInflater)
        var view = mBinding.root
        setContentView(view)
        mContext = this
        id = intent.getLongExtra("id",0)
        initViews()
        initDb()
    }

    fun initViews(){
        mBinding.title.tvTitle.text = "修改信息"
        mBinding.tvAddAdd.text = "修改"

        mBinding.title.ivTitleBack.setOnClickListener(this)
        mBinding.tvAddAdd.setOnClickListener(this)
    }

    fun initDb(){
        val daoSession = DbUtil.initAccountDb(mContext)
        accountBeanDao = daoSession.accountBeanDao

        val daoSession_info  = DbUtil.initInfoDb(mContext)
        accountBeanDao_info = daoSession_info.accountBeanDao

        accountBean = accountBeanDao.queryBuilder()
            .where((AccountBeanDao.Properties.Id).eq(id))
            .build()
            .unique()

        mBinding.edtAddAccount.text = EdtUtil.set(accountBean.name)
        mBinding.edtAddMoney.text = EdtUtil.set(accountBean.num)
        mBinding.edtAddDes.text = EdtUtil.set(accountBean.des)

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
            changeInfo()
            finish()
        }
    }

    fun changeInfo(){
        var name = mBinding.edtAddAccount.text.toString()
        var num = mBinding.edtAddMoney.text.toString()
        var des = mBinding.edtAddDes.text.toString()
        if (TextUtils.isEmpty(des)){
            des = ""
        }

        accountBean.name = name
        accountBean.num = num
        accountBean.numF = num.toFloat()
        accountBean.date = DateUtil.getDayDate()
        accountBean.des = des
        accountBeanDao.update(accountBean)

        var acc_new = AccountBean()
        acc_new.name = name
        acc_new.num = num
        acc_new.numF = num.toFloat()
        acc_new.date = DateUtil.getDayDate()
        acc_new.des = des
        accountBeanDao_info.insertOrReplace(acc_new)


    }

}