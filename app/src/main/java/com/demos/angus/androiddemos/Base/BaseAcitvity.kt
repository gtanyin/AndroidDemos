package com.demos.angus.androiddemos.Base

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import butterknife.ButterKnife

/**
 * Created by angus on 2017/9/15.
 */
abstract class BaseAcitvity : AppCompatActivity() {

    lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        ButterKnife.bind(this)
        mContext = this
        initView()
    }

    abstract fun getLayoutId(): Int
    abstract fun initView()
    fun showToast(toast: String) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
    }
}