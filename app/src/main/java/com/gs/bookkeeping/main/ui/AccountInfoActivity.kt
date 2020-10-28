package com.gs.bookkeeping.main.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.gs.bookkeeping.R
import com.gs.bookkeeping.base.BaseActivity
import com.gs.bookkeeping.base.CodeConstants
import com.gs.bookkeeping.databinding.ActivityAccountInfoBinding
import com.gs.bookkeeping.db.AccountBeanDao
import com.gs.bookkeeping.main.adpters.MainAdapter
import com.gs.bookkeeping.main.beans.AccountBean
import com.gs.bookkeeping.utils.DbUtil

/**
 * author : gengsai
 * date : 2020/10/19
 * description : 账户明显
 */
class AccountInfoActivity :BaseActivity(){

    private lateinit var mBinding: ActivityAccountInfoBinding
    private lateinit var mContext: Context

    private lateinit var mAdapter:MainAdapter
    private lateinit var mLm:LinearLayoutManager

    private lateinit var accountBeanDao: AccountBeanDao
    private lateinit var accountBeanDao_info: AccountBeanDao
    private lateinit var name:String
    private var accountList:MutableList<AccountBean> = ArrayList()
    private var accountInfoList:MutableList<AccountBean> = ArrayList()
    private lateinit var accountBean: AccountBean


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAccountInfoBinding.inflate(layoutInflater)
        var view = mBinding.root
        setContentView(view)
        mContext = this
        name = intent.getStringExtra("name")
        initViews()
        initDb()
    }

    fun initViews() {

        mAdapter = MainAdapter(R.layout.item_main)
        mLm = LinearLayoutManager(mContext)
        mBinding.rvInfo.layoutManager = mLm
        mBinding.rvInfo.adapter = mAdapter

        mBinding.ivInfoBack.setOnClickListener(this)
        mBinding.tvInfoDelete.setOnClickListener(this)
        mBinding.tvInfoChange.setOnClickListener(this)

    }

    fun initDb(){
        var daoSession = DbUtil.initAccountDb(mContext)
        accountBeanDao = daoSession.accountBeanDao

        var daoSession_info = DbUtil.initInfoDb(mContext)
        accountBeanDao_info = daoSession_info.accountBeanDao

        accountList = accountBeanDao.queryBuilder()
            .where(AccountBeanDao.Properties.Name.eq(name))
            .orderDesc(AccountBeanDao.Properties.Id)
            .list()

        accountInfoList = accountBeanDao_info.queryBuilder()
            .where(AccountBeanDao.Properties.Name.eq(name))
            .orderDesc(AccountBeanDao.Properties.Id)
            .list()

        accountBean = accountList[0]

        mBinding.tvInfoAccount.text = name+":"
        mBinding.tvInfoNum.text = accountBean.num

        mAdapter.data = accountInfoList
        mAdapter.notifyDataSetChanged()



    }

    override fun OnClickView(v: View?) {
        if (v == mBinding.ivInfoBack){
            finish()
        }else if (v == mBinding.tvInfoDelete){
            accountBeanDao.deleteByKey(accountBean.id)
            accountBeanDao_info.queryBuilder()
                .where(AccountBeanDao.Properties.Name.eq(accountBean.name))
                .buildDelete()
                .executeDeleteWithoutDetachingEntities()
            finish()
        }else if (v == mBinding.tvInfoChange){
            var intent = Intent(mContext,ChangeInfoActivity::class.java)
            intent.putExtra("id",accountBean.id)
            startActivityForResult(intent,CodeConstants.INFO_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CodeConstants.INFO_CODE){
            initDb()
        }
    }
}