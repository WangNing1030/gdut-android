package com.wangning.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private Button btn_normal_ac;
    private Button btn_dialog_ac;

    /**
     * Activity 被回收之前一定会被调用，保存临时数据
     *
     * @param outState bundle
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String tempData = "Something you just typed";
        outState.putString("data_temp", tempData);
    }

    /**
     * Button 点击事件处理，
     * 根据 btn_id 跳转相应的 Activity
     *
     * @param view view
     */
    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_normal_ac:
                intent = new Intent(MainActivity.this, NormalActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_dialog_ac:
                intent = new Intent(MainActivity.this, DialogActivity.class);
                startActivity(intent);
                break;
        }
    }

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

        Log.i(TAG, "onCreate");
        // savedInstanceState 带有之前保存的全部数据（onSaveInstanceState()）
        if (savedInstanceState != null) {
            String dataTemp = savedInstanceState.getString("data_temp");
            Log.i(TAG, "onCreate: " + dataTemp);
        }

        btn_normal_ac = findViewById(R.id.btn_normal_ac);
        btn_dialog_ac = findViewById(R.id.btn_dialog_ac);

        btn_normal_ac.setOnClickListener(this);
        btn_dialog_ac.setOnClickListener(this);

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
     * 当前 Activity 被其他 Activity 覆盖或屏幕锁屏时调用，
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
        Log.i(TAG, "call onDestroy");
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