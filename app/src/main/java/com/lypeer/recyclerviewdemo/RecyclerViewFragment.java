package com.lypeer.recyclerviewdemo;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by yang on 2015/8/7.
 */
public class RecyclerViewFragment extends Fragment {
    /**
     * 依着的activity的context
     */
    private Context mContext;

    /**
     * fragment的视图对象
     */
    private View fragmentView;

    /**
     * 显示的recyclerView
     */
    private RecyclerView mRecyclerView;

    /**
     * 增加卡片
     */
    private Button mAddCard;

    /**
     * 移除卡片
     */
    private Button mRemoveCard;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            fragmentView = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        }

        init(fragmentView);
        return fragmentView;
    }

    /**
     * 对界面进行初始化的函数
     *
     * @param v 总视图的对象
     */
    private void init(View v) {

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i + "");
        }
        final RecyclerViewAdapter adapter = new RecyclerViewAdapter(mContext, list);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        mAddCard = (Button) v.findViewById(R.id.add);
        mRemoveCard = (Button) v.findViewById(R.id.delete);

        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                adapter.addCard(position + 1);
            }

            @Override
            public void onItemLongCick(View v, int position) {
                adapter.removeCard(position);
            }
        });

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        mAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addCard(1);
            }
        });

        mRemoveCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.removeCard(1);
            }
        });
    }


}
