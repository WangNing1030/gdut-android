package com.wangning.pickpeach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PeachActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "PeachActivity";

    private Button btn_one, btn_two, btn_three, btn_four, btn_five,btn_six,btn_exit;
    private int count=0;//桃子个数
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peach);
        init();
    }
    private void init() {
        btn_one = findViewById(R.id.btn_one);
        btn_two = findViewById(R.id.btn_two);
        btn_three = findViewById(R.id.btn_three);
        btn_four = findViewById(R.id.btn_four);
        btn_five = findViewById(R.id.btn_five);
        btn_six = findViewById(R.id.btn_six);
        btn_exit = findViewById(R.id.btn_exit);
        btn_one.setOnClickListener(this);
        btn_two.setOnClickListener(this);
        btn_three.setOnClickListener(this);
        btn_four.setOnClickListener(this);
        btn_five.setOnClickListener(this);
        btn_six.setOnClickListener(this);
        btn_exit.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_one:    //第一个桃子的点击事件
                info(btn_one);
                break;
            case R.id.btn_two:    //第二个桃子的点击事件
                info(btn_two);
                break;
            case R.id.btn_three:  //第三个桃子的点击事件
                info(btn_three);
                break;
            case R.id.btn_four:   //第四个桃子的点击事件
                info(btn_four);
                break;
            case R.id.btn_five:   //第五个桃子的点击事件
                info(btn_five);
                break;
            case R.id.btn_six:    //第六个桃子的点击事件
                info(btn_six);
                break;
            case R.id.btn_exit:   //“退出桃园”按钮的点击事件
                returnData();
                break;
        }
    }
    /**
     * 按钮的点击事件处理
     */
    private void info(Button btn){
        count++; //桃子个数加1
        btn.setVisibility(View.INVISIBLE);
        Toast.makeText(PeachActivity.this,"摘到"+count+"个桃子",
                Toast.LENGTH_SHORT).show();
    }

    /**
     * 将数据回传到上个界面
     */
    private void returnData(){
        Intent intent = new Intent();
        intent.putExtra("count",count);
        // 携带数据进行回传
        setResult(1,intent);
        PeachActivity.this.finish();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0){
            returnData(); //调用数据回传方法
        }
        return false;
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