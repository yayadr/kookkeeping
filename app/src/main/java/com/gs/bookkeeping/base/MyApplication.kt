package com.gs.bookkeeping.base

import android.app.Application
import android.content.Context
import com.github.yuweiguocn.library.greendao.MigrationHelper
import com.gs.bookkeeping.db.AccountBeanDao
import com.gs.bookkeeping.db.DaoMaster.DevOpenHelper
import com.gs.bookkeeping.db.InfoBeanDao
import com.gs.bookkeeping.utils.StringHelper
import org.greenrobot.greendao.database.Database


/**
 * author : gengsai
 * date : 2020/10/13
 * description : 
 */
class MyApplication :Application() {

    lateinit var context:Context

    override fun onCreate() {
        super.onCreate()
        context = this
//        UpDateDb()
    }

    /**
     * 升级数据库，修改字段，防止删除数据库重新创建
     */
    fun UpDateDb(){
        // regular SQLite database
        // regular SQLite database
        val helper: DevOpenHelper = object : DevOpenHelper(this, "notes-db") {

            override fun onUpgrade(db: Database, oldVersion: Int, newVersion: Int) {
                var currentVersion = 0
                if (oldVersion == 1001) {
                    //修改表结构信息
                    db.execSQL(

                        StringHelper.updateDb(
                            "_id",
                            " C_ID",
                            " NAME ",
                            "NUM",
                            " DATE ",
                            " NUM_F ",
                            " DES "
                        )
                    )
                    currentVersion = newVersion
                }
                if (oldVersion == 1002) {
                    //修改表结构信息
                    StringHelper.updateDb(db,AccountBeanDao::class.java)
                    currentVersion = newVersion
                }
                if (oldVersion == 1003) {
                    //删除重建
                    AccountBeanDao.createTable(db, true)
                    InfoBeanDao.createTable(db, true)
                    currentVersion = newVersion
                }
                if (currentVersion != newVersion) {
                    super.onUpgrade(db, oldVersion, newVersion)
                }
            }
        }

    }
}