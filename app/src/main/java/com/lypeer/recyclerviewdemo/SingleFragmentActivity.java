package com.lypeer.recyclerviewdemo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by yang on 2015/8/6.
 */
public abstract class SingleFragmentActivity extends FragmentActivity {

    /**
     *必须实现的抽象方法，得到要往activity中attach的fragment
     * @return 目标fragment
     */
    protected abstract Fragment getTargetFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment == null){
            fragment = getTargetFragment();
            fm.beginTransaction().add(R.id.fragment_container , fragment).commit();
        }
    }
}
