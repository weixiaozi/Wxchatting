package com.david.wxchatting;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.david.wxchatting.databinding.ItemChattingLoadingBinding;
import com.david.wxchatting.databinding.ItemChattingOtherBinding;
import com.david.wxchatting.databinding.ItemChattingOwnBinding;

import java.util.List;

import static com.david.wxchatting.ChattingBean.LOADING;
import static com.david.wxchatting.ChattingBean.OTHER_TXT;
import static com.david.wxchatting.ChattingBean.OWN_TXT;

/**
 * Created by dell on 2017/10/27.
 */

public class ChattingAdapter extends BaseRvAdapter implements ChattingRootView.NotifyAdapterLoading {
    private List<ChattingBean> datas;
    private ItemChattingLoadingBinding loadingBinding;

    public ChattingAdapter(Context mContext, List datas) {
        super(mContext);
        this.datas = datas;
    }

    @Override
    protected MyViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        if (viewType == LOADING) {
            return bind(R.layout.item_chatting_loading, parent);
        } else if (viewType == OTHER_TXT) {
            return bind(R.layout.item_chatting_other, parent);
        } else if (viewType == OWN_TXT) {
            return bind(R.layout.item_chatting_own, parent);
        }
        return null;
    }

    @Override
    protected void onBindHolder(MyViewHolder holder, int position) {
        ViewDataBinding mBinding = holder.mBinding;
        ChattingBean chattingBean = datas.get(position);
        if (mBinding instanceof ItemChattingLoadingBinding) {
            loadingBinding = (ItemChattingLoadingBinding) mBinding;
        } else if (mBinding instanceof ItemChattingOtherBinding) {
            ItemChattingOtherBinding otherTxtBinding = (ItemChattingOtherBinding) mBinding;
            otherTxtBinding.tvTime.setText(chattingBean.getTime());
            otherTxtBinding.tvName.setText(chattingBean.getName());
            otherTxtBinding.tvContent.setText(chattingBean.getContent());
        } else if (mBinding instanceof ItemChattingOwnBinding) {
            ItemChattingOwnBinding ownTxtBinding = (ItemChattingOwnBinding) mBinding;
            ownTxtBinding.tvTime.setText(chattingBean.getTime());
            ownTxtBinding.tvName.setText(chattingBean.getName());
            ownTxtBinding.tvContent.setText(chattingBean.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (datas != null) {
            return datas.get(position).getType();
        }
        return super.getItemViewType(position);
    }

    public void LoadingIsStart(boolean isStart) {
        if (loadingBinding != null) {
            if (isStart)
                loadingBinding.imgLoading.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            else
                loadingBinding.imgLoading.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));
        }
    }

    @Override
    public void addLoading() {
        if (datas.size() > 1) {
            ChattingBean chattingBean = datas.get(0);
            if (chattingBean.getType() != LOADING) {
                datas.add(0, new ChattingBean(LOADING, null, null, null));
                notifyItemInserted(0);
            }
        }
    }

    @Override
    public void removeLoading() {
        if (datas.size() > 1) {
            ChattingBean chattingBean = datas.get(0);
            if (chattingBean.getType() == LOADING) {
                datas.remove(0);
                notifyItemRemoved(0);
            }
        }
    }


    public void isAddLoading(boolean isAdd) {
        if (isAdd) {
            if (datas.size() > 1) {
                ChattingBean chattingBean = datas.get(0);
                if (chattingBean.getType() != LOADING) {
                    datas.add(0, new ChattingBean(LOADING, null, null, null));
                    notifyItemInserted(0);
                }
            }
        } else {
            if (datas.size() > 1) {
                ChattingBean chattingBean = datas.get(0);
                if (chattingBean.getType() == LOADING) {
                    datas.remove(0);
                    notifyItemRemoved(0);
                }
            }

        }
    }
}
