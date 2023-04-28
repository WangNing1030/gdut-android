package com.wangning.datatransmission;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn_main_submit;
    private TextView tv_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        // 获取页面控件
        btn_main_submit = findViewById(R.id.btn_main_submit);
        tv_data = findViewById(R.id.tv_data);

        btn_main_submit.setOnClickListener(view -> {
            // 跳转至 SubActivity，当活动销毁时返回数据
            Intent intent = new Intent(MainActivity.this, SubActivity.class);
            startActivityForResult(intent, 1);
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 接收回传数据，封装后在 tv_data 展示
        if (requestCode == 1 && resultCode == 2) {
            Bundle bundle = data.getExtras();
            String username = bundle.getString("账号");
            String password = bundle.getString("密码");
            String protocols = bundle.getString("协议");
            String userInfo = "用户信息：\n" + "学号/工号：" + username + "\n密码：" + password + "\n协议：" + protocols;
            tv_data.setText(userInfo);
            Log.i("MainActivity",userInfo);
        }
    }
}