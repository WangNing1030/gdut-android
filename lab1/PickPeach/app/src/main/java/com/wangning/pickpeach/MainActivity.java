package com.wangning.pickpeach;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button btn_peach;
    private TextView tv_count;
    private int totalCount = 0;

    /**
     * Activity 第一次被创建时调用，
     * 完成初始化 - 加载布局、绑定事件等
     *
     * @param savedInstanceState bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        btn_peach = findViewById(R.id.btn_peach);
        tv_count = findViewById(R.id.tv_count);
        btn_peach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PeachActivity.class);
                // 开启一个Activity，当开启的Activity销毁时，会从销毁的Activity中返回数据
                startActivityForResult(intent, 1);
            }
        });
    }

    /**
     * 接收回传数据
     *
     * @param requestCode 请求码
     * @param resultCode  回传码
     * @param data        回传数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1) {
            int count = data.getIntExtra("count", 0); //获取回传的数据
            totalCount = totalCount + count;
            tv_count.setText("摘到" + totalCount + "个");
        }
    }

    /**
     * Activity 由不可见变为可见时调用
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    /**
     * Activity 获取焦点时调用（准备好和用户进行交互时），
     * 此时 Activity 位于返回栈的栈顶，处于运行状态
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    /**
     * 当前 Activity 被其他 Activity 覆盖或屏幕锁屏时调用，M
     * 通常会在这个方法中将一些消耗 CPU 的资源释放掉，以及保存一些关键数据，
     * 但这个方法的执行速度一定要快，不然会影响到新的栈顶活动的使用
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    /**
     * Activity 完全不可见时调用，
     * 如果启动的新活动是一个对话框式的活动，那么 onPause() 会得到执行，而 onStop() 并不会执行
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    /**
     * Activity 被销毁前调用
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    /**
     * Activity 从停止状态到再次启动时调用
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: ");
    }
}