package com.gs.bookkeeping.main.adpters

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.gs.bookkeeping.R
import com.gs.bookkeeping.main.beans.AccountBean
import com.gs.bookkeeping.main.beans.InfoBean

/**
 * author : gengsai
 * date : 2020/10/13
 * description : 
 */
class MainAdapter(layoutResId: Int) : BaseQuickAdapter<AccountBean, BaseViewHolder>(layoutResId) ,
    LoadMoreModule {

    override fun convert(holder: BaseViewHolder, item: AccountBean) {
        holder.setText(R.id.tv_item_main_date,item.date)
            .setText(R.id.tv_item_main_name,item.name)
            .setText(R.id.tv_item_main_num,item.num)
            .setText(R.id.tv_item_main_des,item.des)
    }

}