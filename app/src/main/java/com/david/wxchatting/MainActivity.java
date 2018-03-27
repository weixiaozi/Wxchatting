package com.david.wxchatting;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.david.wxchatting.ChattingBean.OTHER_TXT;
import static com.david.wxchatting.ChattingBean.OWN_TXT;

public class MainActivity extends AppCompatActivity {
    private List<ChattingBean> mData = new ArrayList<>();
    private List<ChattingBean> mDataNew = new ArrayList<>();
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ChattingRootView chatrootview = findViewById(R.id.chatrootview);
        RecyclerView recycleChatting = findViewById(R.id.recycle_chatting);
        for (int i = 0; i < 30; i++) {
            mData.add(new ChattingBean(i % 3 == 0 ? OWN_TXT : OTHER_TXT, "mycontent" + i, "2018-3-" + i, "name" + i));
        }
        for (int i = 0; i < 18; i++) {
            mDataNew.add(new ChattingBean(i % 2 == 0 ? OWN_TXT : OTHER_TXT, "mycontentnew" + i, "2019-4-" + i, "nameNew" + i));

        }
        final ChattingAdapter chattingAdapter = new ChattingAdapter(this, mData);

        recycleChatting.setAdapter(chattingAdapter);
        recycleChatting.setItemAnimator(null);
        chatrootview.setAdatperLoading(chattingAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycleChatting.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setStackFromEnd(true);
        chatrootview.setOnLoadingLisening(new ChattingRootView.OnLoadingListening() {
            @Override
            public void startLoading() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mData.remove(0);
                        mData.addAll(0, mDataNew);
                        chattingAdapter.notifyItemRangeInserted(0, mDataNew.size() - 1);
                        chatrootview.loadFinish();
                    }
                }, 2000);
            }
        });
    }
}
