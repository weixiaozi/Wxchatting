package com.david.wxchatting;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dell on 2017/10/27.
 */

public abstract class BaseRvAdapter<T extends ViewDataBinding> extends RecyclerView.Adapter {

    private Context mContext;

    public BaseRvAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindHolder((MyViewHolder) holder, position);
    }

    protected abstract MyViewHolder onCreateHolder(ViewGroup parent, int viewType);

    protected abstract void onBindHolder(MyViewHolder holder, int position);

    protected MyViewHolder bind(@LayoutRes int id, ViewGroup root) {
        View view = LayoutInflater.from(mContext).inflate(id, root, false);
        T mBinding = DataBindingUtil.bind(view);
        return new MyViewHolder(view, mBinding);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public T mBinding;

        public MyViewHolder(View itemView, T mBinding) {
            super(itemView);
            this.mBinding = mBinding;
        }
    }

}
