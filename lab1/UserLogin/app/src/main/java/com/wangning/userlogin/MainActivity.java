package com.wangning.userlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private EditText et_username, et_password;
    private Button btn_submit;
    private String username, password;
    private List<String> protocols;
    private CheckBox cb_agree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        // 获取界面控件
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_submit = findViewById(R.id.btn_submit);
        cb_agree = findViewById(R.id.cb_agree);

        // 协议列表
        protocols = new ArrayList<>();

        // 设置 btn_submit 点击事件的监听器
        btn_submit.setOnClickListener(this);
        // 设置 cb_agree 选择事件的监听器
        cb_agree.setOnCheckedChangeListener(this);


    }

    /**
     * 获取登录界面输入的数据
     */
    private void getData() {
        username = et_username.getText().toString().trim();
        password = et_password.getText().toString().trim();
    }

    /**
     * Button 点击事件处理
     *
     * @param view view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // btn_submit
            case R.id.btn_submit:
                getData();
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(MainActivity.this, "请输入学号/工号", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Log.i("MainActivity", "登录的用户信息：" + "学号/工号：" + username + ",密码：" + password + ",协议：" + protocols.toString());
                }
                break;
            default:
                Log.e("MainActivity", "Unknown button click event");
        }
    }

    /**
     * CheckBox 选择事件
     *
     * @param compoundButton compoundButton
     * @param isChecked      isChecked
     */
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        String motion = compoundButton.getText().toString();
        if (isChecked) {
            if (!protocols.contains(motion)) {
                // 之前没有选择，则加入
                protocols.add(motion);
            }
        } else {
            if (protocols.contains(motion)) {
                protocols.remove(motion);
            }
        }
    }
}