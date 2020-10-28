package com.gs.bookkeeping.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * author : gengsai
 * date : 2020/10/20
 * description : 
 */
object DateUtil {

    /**
     * 获取现在时间（精确到日）
     */
    fun getDayDate(): String {
        var nowDate = System.currentTimeMillis()
        var sdf = SimpleDateFormat("yyyy-MM-dd")
        var nowDateStr = sdf.format(Date(nowDate))
        return nowDateStr
    }

    /**
     * 获取现在时间（精确到月）
     */
    fun getMonthDate(): String {
        var nowDate = System.currentTimeMillis()
        var sdf = SimpleDateFormat("yyyy-MM")
        var nowDateStr = sdf.format(Date(nowDate))
        return nowDateStr
    }
    fun getMonthDate(dateStr:String): String {
        var nowDateStr = dateStr.subSequence(0,7)
        return nowDateStr.toString()
    }

    /**
     * 时间戳转换时间（精确到日）
     */
    fun changeDate(date: Long): String {
        var sdf = SimpleDateFormat("yyyy-MM-dd")
        var nowDateStr = sdf.format(Date(date))
        return nowDateStr
    }

    /**
     * 是否是今天
     */
    fun isDay(date: Long):Boolean{
        if (changeDate(date).equals(getDayDate())){
            return true
        }
        return false
    }

    fun isDay(date: String):Boolean{
        if (date.equals(getDayDate())){
            return true
        }
        return false
    }

    /**
     * 是否是本月
     */
    fun isMonth(date: Long):Boolean{
        if (getMonthDate(changeDate(date)).equals(getMonthDate())){
            return true
        }
        return false
    }

    fun isMonth(date: String):Boolean{
        if (getMonthDate(date).equals(getMonthDate())){
            return true
        }
        return false
    }

}