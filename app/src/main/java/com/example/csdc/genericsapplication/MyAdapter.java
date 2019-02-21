package com.example.csdc.genericsapplication;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by csdc on 2019/2/19.
 */

public abstract class MyAdapter<T extends ListDataModel> extends BaseAdapter { //规定数据类型

    private List<T> mTList;
    private int resLayout;

    public MyAdapter(List<T> students, int resLayout) {
        this.mTList = students;
        this.resLayout = resLayout;
    }

    @Override
    public int getCount() {
        return mTList != null ? mTList.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return mTList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.bind(parent.getContext(),convertView,parent,resLayout,position);
        bindView(holder,getItem(holder.position)); //holder + 数据 //由子类实现并传递
        return holder.getItemView();
    }

    public static class ViewHolder{
        private SparseArray<View> mViews;//所有的view的缓存
        private Context mContext;
        private View item; //每个item
        private int position;

        public ViewHolder(Context context,ViewGroup parent,int layoutRes){
            this.mViews = new SparseArray<>();
            this.mContext = context;
            View conVertView = LayoutInflater.from(mContext).inflate(layoutRes,parent,false);
            conVertView.setTag(this);
            this.item = conVertView; //每一个ItemView
        }

        //创建ViewHolder的过程并绑定View的过程
        public static ViewHolder bind(Context context,View convertView,ViewGroup parent,int layoutRes,int position){
            ViewHolder viewHolder;
            if(convertView == null){
                viewHolder = new ViewHolder(context,parent,layoutRes);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
                viewHolder.item = convertView;
            }
            viewHolder.position = position; //数据源位置
            return viewHolder;
        }

        public View getItemView(){
            return item;
        }

        public <K extends View> K getView(int id){// 获得每一个itemview的控件的id
            K k = (K) mViews.get(id);
            if(k == null){
                k = item.findViewById(id);
                mViews.put(id,k);
            }
            return k;
        }

        public ViewHolder setText(int id,String title){ //链式编程
            View view = getView(id); //获得每隔ItemView的控件View
            if(view instanceof TextView){
                ((TextView) view).setText(title);
            }
            return this;
        }
    }

    public abstract void bindView(ViewHolder viewHolder,T obj); //子类实现数据的
}
