package com.gs.bookkeeping.utils

import java.text.NumberFormat

/**
 * author : gengsai
 * date : 2020/11/11
 * description : 计算
 */
object CalculationUtil {
    /**
     * 计算百分比 默认不保留小数
     * @param diliverNum 被除数
     * @param queryMailNum 除数
     * @return 返回字符串
     */
    fun percentageStr(diliverNum: Double, queryMailNum: Double): String? {
        return percentageStr(diliverNum, queryMailNum, 0)
    }

    /**
     * 计算百分比
     * @param diliverNum 被除数
     * @param queryMailNum 除数
     * @param radixPoint 保留小数点位数
     * @return
     */
    fun percentageStr(diliverNum: Double, queryMailNum: Double, radixPoint: Int): String? {
        // 创建一个数值格式化对象
        val numberFormat: NumberFormat = NumberFormat.getInstance()
        // 设置精确到小数点后radixPoint位
        numberFormat.setMaximumFractionDigits(radixPoint)
        return numberFormat.format(diliverNum / queryMailNum * 100)
    }

    fun percentage(diliverNum: Double, queryMailNum: Double): Double {
        return percentage(diliverNum, queryMailNum, 0)
    }

    fun percentage(diliverNum: Float, queryMailNum: Float): Double {
        return percentage(diliverNum.toDouble(), queryMailNum.toDouble(), 0)
    }

    fun percentage(diliverNum: Double, queryMailNum: Double, radixPoint: Int): Double {
        // 创建一个数值格式化对象
        val numberFormat: NumberFormat = NumberFormat.getInstance()
        // 设置精确到小数点后radixPoint位
        numberFormat.setMaximumFractionDigits(radixPoint)
        val result: String = numberFormat.format(diliverNum / queryMailNum * 100)
        return result.toDouble()
    }
}