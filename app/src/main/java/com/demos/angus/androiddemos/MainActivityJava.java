package com.demos.angus.androiddemos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.demos.angus.androiddemos.Base.BaseAcitvity;
import com.demos.angus.androiddemos.Model.Person;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by angus on 2017/9/15.
 */

public class MainActivityJava extends BaseAcitvity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    private List<Person> people;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

        people = new ArrayList<>();
        people.add(new Person("taylor", R.mipmap.avatar0));
        people.add(new Person("taylor1", R.mipmap.avatar1));
        people.add(new Person("taylor2", R.mipmap.avatar2));
        people.add(new Person("taylor3", R.mipmap.avatar3));
        people.add(new Person("taylor4", R.mipmap.avatar4));
        people.add(new Person("taylor5", R.mipmap.avatar5));


        if (recyclerView == null) {
            recyclerView =  findViewById(R.id.recyclerview);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));


        BaseQuickAdapter baseQuickAdapter = new BaseQuickAdapter<Person, BaseViewHolder>(R.layout.item_avatar, people) {
            @Override
            protected void convert(BaseViewHolder helper, Person item) {
                //遇到网络图片或者大图可能出现oom的情况，可以使用Glide加载
                Glide.with(mContext).load(item.getAvater()).into((ImageView) helper.getView(R.id.avater));

                helper.setText(R.id.name, item.getName())
                        .addOnClickListener(R.id.avater)//对子控件添加监听
                        .addOnClickListener(R.id.name);

            }
        };


        //添加动画
        baseQuickAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);

        //对单条item点击
        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                showToast("U r click at " + position);
            }
        });

        //对子控件的点击事件
        baseQuickAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.avater:
                        showToast("U r click at avatar at " + position);
                        break;
                    case R.id.name:
                        showToast("U r click at name at " + position);
                        break;
                }
            }
        });

        recyclerView.setAdapter(baseQuickAdapter);
    }
}
