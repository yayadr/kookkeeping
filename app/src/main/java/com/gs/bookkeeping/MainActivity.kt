package com.gs.bookkeeping

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentTransaction
import com.gs.bookkeeping.base.BaseActivity
import com.gs.bookkeeping.databinding.ActivityMainBinding
import com.gs.bookkeeping.home.AccountFragment
import com.gs.bookkeeping.main.adpters.MainAdapter
import com.iammert.library.readablebottombar.ReadableBottomBar

class MainActivity : BaseActivity() {

    private lateinit var mContext: Context
    private lateinit var mBinding: ActivityMainBinding
    private val homeFrag:AccountFragment by lazy {
        AccountFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mBinding.root
        setContentView(view)
        mContext = this


        mBinding.rbbTabMain.setOnItemSelectListener( object : ReadableBottomBar.ItemSelectListener{
            override fun onItemSelected(index: Int) {
                var ft = supportFragmentManager.beginTransaction()
                when(index){
                    0 -> {
                        if (!homeFrag.isAdded){
                            ft.add(R.id.flTab,homeFrag)
                        }else{
                            ft.show(homeFrag)
                        }
                    }
                    1 -> {

                    }
                }
                ft.commit()
            }
        })
    }



    override fun OnClickView(v: View?) {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}