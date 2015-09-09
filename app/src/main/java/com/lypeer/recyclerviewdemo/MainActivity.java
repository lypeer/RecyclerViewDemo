package com.lypeer.recyclerviewdemo;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment getTargetFragment() {
        return new RecyclerViewFragment();
    }


}
