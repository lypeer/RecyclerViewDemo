package com.lypeer.recyclerviewdemo;

import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by yang on 2015/8/8.
 */
public class SideBarView extends RelativeLayout implements View.OnTouchListener {

    /**
     * 是否已经加载过一次，他如果已经加载过一次，就为true
     */
    private boolean mLoadOnce = false;

    /**
     * 按下去的是否的x坐标
     */
    private float mXDown;

    /**
     * 按下去的时候的Y坐标
     */
    private float mYDown;

    /**
     * sidebar的宽度
     */
    private int mWidth = -280;

    /**
     * 屏幕的宽度
     */
    private int mScreenWidth;
    /**
     * context对象
     */
    private Context mContext;

    /**
     * 侧边栏的对象
     */
    private View mSideBar;

    /**
     * 主要视图的对象
     */
    private View mMainView;

    /**
     * 置顶的按钮
     */
    private Button mTOTheTop;

    /**
     * 删除列表项的按钮
     */
    private Button mDelete;

    /**
     * sidebar的信息包
     */
    private RelativeLayout.LayoutParams mSideBarParams;

    /**
     * mainview的信息包
     */
    private RelativeLayout.LayoutParams mMainViewParams;

    public SideBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    /**
     * 初始化的方法，在里面对控件进行初始化，并且动态添加滑动拖出的部分
     */
    private void init() {
        mSideBar = LayoutInflater.from(mContext).inflate(R.layout.sidebar, null);
        mTOTheTop = (Button) mSideBar.findViewById(R.id.to_the_top);
        mDelete = (Button) mSideBar.findViewById(R.id.delete);
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mScreenWidth = wm.getDefaultDisplay().getWidth();

        addView(mSideBar, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            Log.e("onMeasure", "onMeasure");
            //使侧滑栏出现在界面的右边，并且隐藏
            mSideBarParams = (RelativeLayout.LayoutParams) mSideBar.getLayoutParams();

            mSideBarParams.addRule(ALIGN_PARENT_RIGHT);
            mSideBar.setLayoutParams(mSideBarParams);

            //对列表项进行初始化，并且让其在侧滑栏的左边
            mMainView = getChildAt(1);
            mMainViewParams = (RelativeLayout.LayoutParams) mMainView.getLayoutParams();
            mMainViewParams.addRule(LEFT_OF, R.id.sidebar);
//          /  mMainViewParams.width = mScreenWidth;
            mMainView.setLayoutParams(mMainViewParams);

            mMainView.setOnTouchListener(this);

            mLoadOnce = true;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.e("onLayout" , "onLayout");
        super.onLayout(changed, l, t, r, b);
        if(changed){
            mSideBarParams.rightMargin = mWidth;
            mSideBar.setLayoutParams(mSideBarParams);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float mDistance;
        mMainView.setClickable(false);
        mMainView.setFocusable(false);
        mMainView.setFocusableInTouchMode(false);
        mMainView.setPressed(false);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mXDown = event.getRawX();
                mYDown = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(event.getRawY() - mYDown) < 20) {
                    mDistance = event.getRawX() - mXDown;
                    if (mDistance > 0) {
                        mSideBarParams.rightMargin = (int) ( - mDistance);
                        mSideBar.setLayoutParams(mSideBarParams);
                        return true;
                    } else {
//                    mSideBarParams.rightMargin = 0;
//                    mSideBar.setLayoutParams(mSideBarParams);
                        mSideBarParams.rightMargin = (int) ( - mDistance);
                        mSideBar.setLayoutParams(mSideBarParams);
//                    mMainViewParams.leftMargin = (int)mDistance;
//                    mMainView.setLayoutParams(mMainViewParams);
                        Log.e("margin", mMainView.getWidth() + "");
                        return true;
                    }
                }

                break;
            case MotionEvent.ACTION_UP :
                return true;
        }
        return false;
    }

    class HideSideBarTask extends AsyncTask<Void, Integer, Integer> {

        @Override
        protected Integer doInBackground(Void... params) {

            return null;
        }
    }

    /**
     * 使线程休眠指定时间
     *
     * @param time 休眠的时间，以毫秒为单位
     */
    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
