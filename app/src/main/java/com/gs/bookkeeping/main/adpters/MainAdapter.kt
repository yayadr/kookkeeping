package com.gs.bookkeeping.main.adpters

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.gs.bookkeeping.R
import com.gs.bookkeeping.main.beans.AccountBean
import com.gs.bookkeeping.utils.CalculationUtil
import com.gs.bookkeeping.utils.LogUtil

/**
 * author : gengsai
 * date : 2020/10/13
 * description : 
 */
class MainAdapter(layoutResId: Int) : BaseQuickAdapter<AccountBean, BaseViewHolder>(layoutResId) ,
    LoadMoreModule {

    var all_money = 0f

    override fun convert(holder: BaseViewHolder, item: AccountBean) {
        holder.setText(R.id.tv_item_main_date,"更新时间：${item.date}")
            .setText(R.id.tv_item_main_name,item.name)
            .setText(R.id.tv_item_main_num,"${item.num}￥")
            .setText(R.id.tv_item_main_des,item.des)
        if (all_money > 0f && item.numF > 0f){
            var percentage = CalculationUtil.percentage(item.numF,all_money)
            holder.setText(R.id.tv_item_main_percentage,"$percentage%")
        }
    }

    fun showPercentage(all_money :Float){
        this.all_money = all_money
        notifyDataSetChanged()
    }

}