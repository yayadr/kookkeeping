package com.gs.bookkeeping.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.gs.bookkeeping.R
import com.gs.bookkeeping.account.setting.ui.SettingActivity
import com.gs.bookkeeping.base.BaseFragment
import com.gs.bookkeeping.base.CodeConstants
import com.gs.bookkeeping.databinding.ActivityMainBinding
import com.gs.bookkeeping.databinding.FragmentAccountBinding
import com.gs.bookkeeping.db.AccountBeanDao
import com.gs.bookkeeping.main.adpters.MainAdapter
import com.gs.bookkeeping.main.beans.AccountBean
import com.gs.bookkeeping.main.ui.AccountInfoActivity
import com.gs.bookkeeping.main.ui.AddAccountActivity
import com.gs.bookkeeping.utils.DbUtil
import com.gs.bookkeeping.utils.ToastUtil
import java.lang.Exception

/**
 * author : gengsai
 * date : 2021/9/27
 * description : 
 */
class AccountFragment :BaseFragment(){
    private lateinit var mContext: Context
    private lateinit var mBinding: FragmentAccountBinding

    private lateinit var mainAdapter: MainAdapter
    private lateinit var mLayoutManager: LinearLayoutManager

    private var accountList: MutableList<AccountBean> = ArrayList()
    private var positiveList: MutableList<AccountBean> = ArrayList()
    private var negativeList: MutableList<AccountBean> = ArrayList()

    lateinit var accountBeanDao: AccountBeanDao

    private var numAll: Float = 0f
    private var numPositive: Float = 0f
    private var numNegative: Float = 0f

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mContext = requireContext()
        mBinding = FragmentAccountBinding.inflate(inflater)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initDb()
        initViews()
        initData()
    }
    fun initDb() {
        var daoSession = DbUtil.initAccountDb(mContext)
        accountBeanDao = daoSession.accountBeanDao

    }

    fun initViews() {
        mainAdapter = MainAdapter(R.layout.item_main)
        mLayoutManager = LinearLayoutManager(mContext)
        mBinding.rvMain.layoutManager = mLayoutManager
        mBinding.rvMain.adapter = mainAdapter

        mainAdapter.setOnItemClickListener()
             { _, _, position ->
                var intent = Intent(mContext, AccountInfoActivity::class.java)
                intent.putExtra("cId", mainAdapter.data.get(position).cId)
                intent.putExtra("name", mainAdapter.data.get(position).name)
                startActivityForResult(intent, CodeConstants.MAIN_CODE)
            }

        mBinding.fbMainAdd.setOnClickListener{
            var intent = Intent(mContext, AddAccountActivity::class.java)
            startActivityForResult(intent, CodeConstants.MAIN_CODE)
        }
        mBinding.ivMainSetting.setOnClickListener{
            var intent = Intent(mContext, SettingActivity::class.java)
            startActivity(intent)
        }
    }

    fun initData() {
        try {
            accountList = accountBeanDao.queryBuilder()
                .orderDesc((AccountBeanDao.Properties.NumF))
                .list()
            mainAdapter.data = accountList
            mainAdapter.notifyDataSetChanged()
        } catch (e: Exception) {
            ToastUtil.show("获取数据错误", mContext)
        }

        numAll = 0f
        numPositive = 0f
        numNegative = 0f

        for (i in 0..(accountList.size - 1)) {
            numAll = numAll + accountList[i].num.toFloat()
            if (accountList[i].numF > 0) {
                positiveList.add(accountList[i])
            } else if (accountList[i].numF < 0) {
                negativeList.add(accountList[i])
            }
        }

        for (i in 0..(positiveList.size - 1)) {
            numPositive = numPositive + positiveList[i].numF
        }

        for (i in 0..(negativeList.size - 1)) {
            numNegative = numNegative + negativeList[i].numF
        }

        mainAdapter.showPercentage(numPositive)
        mBinding.tvMainTopAllMoney.text = numAll.toInt().toString()
        mBinding.tvMainTopPositiveMoney.text = numPositive.toInt().toString()
        mBinding.tvMainTopNegativeMoney.text = numNegative.toInt().toString()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CodeConstants.MAIN_CODE) {
            initDb()
            initData()
        }
    }
}