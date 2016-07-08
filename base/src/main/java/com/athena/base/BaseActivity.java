package com.athena.base;

import android.app.Activity;
import android.os.Bundle;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Pierce on 2016/7/9.
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
