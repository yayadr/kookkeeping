package com.gs.bookkeeping.utils

import com.github.yuweiguocn.library.greendao.MigrationHelper
import com.gs.bookkeeping.main.beans.AccountBean
import org.greenrobot.greendao.AbstractDao
import org.greenrobot.greendao.database.Database

/**
 * author : gengsai
 * date : 2020/11/10
 * description : 
 */
object StringHelper {
    /**
     * 傻瓜式手写，复杂
     */
    fun updateDb():String{
        var dbStr = "CREATE TABLE " + "IF NOT EXISTS" + "\"ACCOUNT_BEAN\" (" +  //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," +  // 0: id
                "\"C_ID\" TEXT," +  // 1: cId
                "\"NAME\" TEXT," +  // 2: name
                "\"NUM\" TEXT," +  // 3: num
                "\"DATE\" TEXT," +  // 4: date
                "\"NUM_F\" REAL," +  // 5: numF
                "\"DES\" TEXT);"

        return dbStr
    }

    /**
     * 自己封装，未完成逻辑
     */
    fun updateDb(vararg strs: String):String{
        var dbStr = "CREATE TABLE " + "IF NOT EXISTS" + "\"ACCOUNT_BEAN\" (" +  //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," +  // 0: id
                "\"C_ID\" TEXT," +  // 1: cId
                "\"NAME\" TEXT," +  // 2: name
                "\"NUM\" TEXT," +  // 3: num
                "\"DATE\" TEXT," +  // 4: date
                "\"NUM_F\" REAL," +  // 5: numF
                "\"DES\" TEXT);"

        for (str in strs){

        }

        return ""

    }

    /**
     * 第三方封装
     */
    fun updateDb(db: Database,daoClasses: Class<out AbstractDao<*, *>?>? ){
        MigrationHelper.migrate(db,daoClasses)
    }
}