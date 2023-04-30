package com.wangning.orderedbroadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class AbortActivity extends AppCompatActivity {

    /**
     * 有序广播被拦截后终止，跳转至新界面，点击 btn_return 返回主界面但有序广播已终止不会继续广播
     *
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abort);
        Button btn_return = findViewById(R.id.btn_return);
        btn_return.setOnClickListener(view -> {
            finish();
        });
    }
}