package com.gs.bookkeeping

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.gs.bookkeeping.account.setting.ui.SettingActivity
import com.gs.bookkeeping.base.BaseActivity
import com.gs.bookkeeping.base.CodeConstants
import com.gs.bookkeeping.databinding.ActivityMainBinding
import com.gs.bookkeeping.db.AccountBeanDao
import com.gs.bookkeeping.main.adpters.MainAdapter
import com.gs.bookkeeping.main.beans.AccountBean
import com.gs.bookkeeping.main.ui.AccountInfoActivity
import com.gs.bookkeeping.main.ui.AddAccountActivity
import com.gs.bookkeeping.utils.DbUtil
import com.gs.bookkeeping.utils.ToastUtil
import java.lang.Exception

class MainActivity : BaseActivity() {

    private lateinit var mContext: Context
    private lateinit var mBinding: ActivityMainBinding

    private lateinit var mainAdapter: MainAdapter
    private lateinit var mLayoutManager: LinearLayoutManager

    private var accountList:MutableList<AccountBean> = ArrayList()

    lateinit var accountBeanDao:AccountBeanDao

    private var numAll:Float = 0f
    private var numMonth:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mBinding.root
        setContentView(view)
        mContext = this
        initDb()
        initViews()
        initData()
    }

    fun initDb(){
        var daoSession = DbUtil.initAccountDb(mContext)
        accountBeanDao = daoSession.accountBeanDao

    }

    fun initViews() {
        mainAdapter = MainAdapter(R.layout.item_main)
        mLayoutManager = LinearLayoutManager(mContext)
        mBinding.rvMain.layoutManager = mLayoutManager
        mBinding.rvMain.adapter = mainAdapter

        mainAdapter.setOnItemClickListener(
            OnItemClickListener { adapter, view, position ->
                var intent = Intent(mContext,AccountInfoActivity::class.java)
                intent.putExtra("cId",mainAdapter.data.get(position).cId)
                intent.putExtra("name",mainAdapter.data.get(position).name)
                startActivityForResult(intent,CodeConstants.MAIN_CODE)
            }
        )
        mBinding.fbMainAdd.setOnClickListener(this)
        mBinding.ivMainSetting.setOnClickListener(this)
    }

    fun initData() {
        try {
            accountList = accountBeanDao.queryBuilder()
                .orderDesc((AccountBeanDao.Properties.NumF) )
                .list()
            mainAdapter.data = accountList
            mainAdapter.notifyDataSetChanged()
        }catch (e:Exception){
            ToastUtil.show("获取数据错误",this)
        }

        numAll = 0f
        for (i in 0..(accountList.size-1)){
            numAll = numAll + accountList[i].num.toFloat()
        }
        mainAdapter.showPercentage(numAll)
        mBinding.tvMainTopAllMoney.text = numAll.toInt().toString()

    }



    override fun OnClickView(v: View?) {
        if (v == mBinding.fbMainAdd){
            var intent = Intent(mContext,AddAccountActivity::class.java)
            startActivityForResult(intent,CodeConstants.MAIN_CODE)
        }else if (v == mBinding.ivMainSetting){
            var intent = Intent(mContext,SettingActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CodeConstants.MAIN_CODE){
            initDb()
            initData()
        }
    }
}