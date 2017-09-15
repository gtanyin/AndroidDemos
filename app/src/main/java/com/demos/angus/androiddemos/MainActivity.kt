package com.demos.angus.androiddemos

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.demos.angus.androiddemos.Base.BaseAcitvity
import com.demos.angus.androiddemos.Model.Person
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : BaseAcitvity() {

    private lateinit var people: ArrayList<Person>


    override fun getLayoutId(): Int = R.layout.activity_main;

    override fun initView() {
        people = ArrayList()
        people.add(Person("taylor", R.mipmap.avatar0))
        people.add(Person("taylor1", R.mipmap.avatar1))
        people.add(Person("taylor2", R.mipmap.avatar2))
        people.add(Person("taylor3", R.mipmap.avatar3))
        people.add(Person("taylor4", R.mipmap.avatar4))
        people.add(Person("taylor5", R.mipmap.avatar5))

        recyclerview.layoutManager = LinearLayoutManager(mContext)

        val baseQuickAdapter = object : BaseQuickAdapter<Person, BaseViewHolder>(R.layout.item_avatar, people) {
            override fun convert(helper: BaseViewHolder, item: Person) {
                //遇到网络图片或者大图可能出现oom的情况，可以使用Glide加载
                Glide.with(mContext).load(item.avater).into(helper.getView<View>(R.id.avater) as ImageView)

                helper.setText(R.id.name, item.name)
                        .addOnClickListener(R.id.avater)//对子控件添加监听
                        .addOnClickListener(R.id.name)

            }
        }

        //添加动画
        baseQuickAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN)


        //对单条item点击
        baseQuickAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
            showToast("U r click at " + position)
        }

        //对子控件的点击事件
        baseQuickAdapter.onItemChildClickListener = BaseQuickAdapter.OnItemChildClickListener { _, view, position ->
            when (view.id) {
                R.id.avater -> showToast("U r click at avatar at " + position)
                R.id.name -> showToast("U r click at name at " + position)
            }
        }

        recyclerview.adapter = baseQuickAdapter

    }


}
