package com.lypeer.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by yang on 2015/8/7.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    /**
     * 建立实例的activity的context
     */
    private Context mContext;

    /**
     * 填充view的数据
     */
    private ArrayList<String> mData;

    /**
     * 瀑布流的高度参数
     */
    private ArrayList<Integer> mHeight;

    /**
     * 点击的监听器的对象
     */
    private OnItemClickListener mOnItemClickListener;

    /**
     * 这是adapter的构造方法
     *
     * @param context 首先要传进来activity的context，用来使用LayoutInflater
     * @param data    填充每一个item的数据
     */
    public RecyclerViewAdapter(Context context, ArrayList<String> data) {
        mContext = context;
        mData = data;

        mHeight = new ArrayList<>();
        for (int i = 0; i < mData.size(); i++) {
            mHeight.add(200 + (int) (Math.random() * 300));
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(
                R.layout.recyclerview_item, parent, false
        ));

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
//        //这是动态设置高度瀑布流的代码
//        ViewGroup.LayoutParams lp = holder.mTextView.getLayoutParams();
//        lp.height = mHeight.get(position);
//        holder.mTextView.setLayoutParams(lp);
        holder.mTextView.setText(mData.get(
                position
        ));

        if (mOnItemClickListener != null) {
            holder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.mTextView, pos);
                }
            });

            holder.mTextView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongCick(holder.mTextView, pos);
                    return false;
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * viewholder类
     */
    class MyViewHolder extends RecyclerView.ViewHolder {

        /**
         * 装数据的TextView
         */
        TextView mTextView;

        /**
         * MyViewHolder的构造方法
         *
         * @param v item的对象
         */
        public MyViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.item_text);
        }

    }

    /**
     * 添加一张卡片的方法，注意提示刷新界面用的是notifyItemInserted方法
     */
    public void addCard(int position) {
        mData.add(position, "insert");
        mHeight.add(position, 200 + (int) (Math.random() * 300));
        notifyItemInserted(position);
    }

    /**
     * 移除一张卡片的方法
     */
    public void removeCard(int position) {
        mData.remove(position);
        mHeight.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * 点击事件的接口
     */
    public interface OnItemClickListener {

        /**
         * 短点击
         *
         * @param v        被点击的对象
         * @param position 被点击的view的位置
         */
        void onItemClick(View v, int position);

        /**
         * 长按
         *
         * @param v        被点击的对象
         * @param position 被点击的view的位置
         */
        void onItemLongCick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
