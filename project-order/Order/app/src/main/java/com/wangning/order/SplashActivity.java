package com.wangning.order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.wangning.order.activity.ShopActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }

    /**
     * 启动欢迎界面展示 3 秒后跳转至 ShopActivity
     */
    private void init() {
        // 利用 Timer 让此界面延迟 3 秒后跳转，timer 中有一个线程，这个线程不断执行 task
        Timer timer = new Timer();
        // TimerTask 实现 Runnable 接口，TimerTask 类表示一个在指定时间内执行的 task
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, ShopActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        };
        timer.schedule(task, 3000);
    }
}