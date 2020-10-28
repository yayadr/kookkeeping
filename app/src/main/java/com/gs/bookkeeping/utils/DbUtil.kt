package com.gs.bookkeeping.utils

import android.content.Context
import com.gs.bookkeeping.db.DaoMaster
import com.gs.bookkeeping.db.DaoSession

/**
 * author : gengsai
 * date : 2020/10/13
 * description : 
 */
object DbUtil {


    fun initAccountDb(mContext: Context): DaoSession {

        val helper = DaoMaster.DevOpenHelper(mContext, "AccountInfo.db", null)
        val daoMaster = DaoMaster(helper.writableDb)
        val daoSession = daoMaster.newSession()
        return daoSession
    }
    fun initInfoDb(mContext: Context): DaoSession {

        val helper = DaoMaster.DevOpenHelper(mContext, "BookKeepingInfo.db", null)
        val daoMaster = DaoMaster(helper.writableDb)
        val daoSession = daoMaster.newSession()
        return daoSession
    }

}