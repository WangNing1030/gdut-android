package com.wangning.directory;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private EditText mEtName, mEtPhone;
    private TextView mTvShow;
    private Button mBtnAdd, mBtnQuery, mBtnUpdate, mBtnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化界面控件
        initView();
    }

    /**
     * 点击事件处理
     *
     * @param view view
     */
    @Override
    public void onClick(View view) {
        String name, phone;
        ContactInfo contact;
        switch (view.getId()) {
            case R.id.btn_add: // 添加数据：添加单条数据
                clearTvShow(mTvShow);
                name = mEtName.getText().toString();
                phone = mEtPhone.getText().toString();

                /*
                3.SQL : INSERT INTO contact_info (name, phone) VALUES (name,phone);
                 */
                contact = new ContactInfo(name, phone);
                contact.save();

                /*
                2.SharedPreferences : 添加数据
                 */
//                SharedPreferencesUtils.saveContactInfo(this, new ContactInfo(name, phone));

                /*
                1.文件 : 添加数据到 data.txt
                 */
//                FileUtils.saveContactInfo(this, name, phone);

                Toast.makeText(this, "信息已添加", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_query: // 查询数据：查询全部数据

                List<ContactInfo> contactList;

                /*
                3.SQL : SELECT * FROM contact_info;
                 */
                contactList = LitePal.findAll(ContactInfo.class);

                /*
                2.SharedPreferences : 查询数据
                 */
//                contactList = SharedPreferencesUtils.getContactInfo(this);

                /*
                1.文件 : 从 data.txt 中获取数据
                 */
//                contactList = FileUtils.getContactInfo(this);

                if (contactList == null || contactList.size() == 0) {
                    clearTvShow(mTvShow);
                    Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
                } else {
                    mTvShow.setText("Info");
                    for (ContactInfo contactInfo : contactList) {
                        mTvShow.append("\n" + "Name : " + contactInfo.getName() +
                                ", Tel : " + contactInfo.getPhone());
                    }
                }


                break;
            case R.id.btn_update: // 修改数据：根据 name 修改 phone
                clearTvShow(mTvShow);
                name = mEtName.getText().toString();
                phone = mEtPhone.getText().toString();

                contact = new ContactInfo();
                contact.setPhone(phone);

                /*
                3.SQL : UPDATE contact_info SET phone = phone WHERE name = name;
                 */
                contact.updateAll("name = ?", name);

                /*
                2.SharedPreferences : 修改数据
                 */
//                contact.setName(name);
//                SharedPreferencesUtils.updateContactInfo(this, contact);

                /*
                1.文件 : 修改数据，根据 name 修改 phone
                 */
//                contact.setName(name);
//                FileUtils.updateContactInfo(this, contact);

                Toast.makeText(this, "信息已修改", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_delete: // 删除数据：删除所有数据
                clearTvShow(mTvShow);
                /*
                3.SQL : DELETE FROM contact_info;
                 */
                LitePal.deleteAll(ContactInfo.class);

                /*
                2.SharedPreferences : 删除数据
                 */
//                SharedPreferencesUtils.deleteAll(this);

                /*
                1.文件 : 删除数据，删除所有数据（删除 data.txt）
                 */
//                FileUtils.deleteAll(this);

                Toast.makeText(this, "信息已删除", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 清空联系人显示框 mTvShow
     *
     * @param mTvShow textView
     */
    private void clearTvShow(TextView mTvShow) {
        mTvShow.setText("");
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        mEtName = findViewById(R.id.et_name);
        mEtPhone = findViewById(R.id.et_phone);
        mTvShow = findViewById(R.id.tv_show);
        mBtnAdd = findViewById(R.id.btn_add);
        mBtnQuery = findViewById(R.id.btn_query);
        mBtnUpdate = findViewById(R.id.btn_update);
        mBtnDelete = findViewById(R.id.btn_delete);
        mBtnAdd.setOnClickListener(this);
        mBtnQuery.setOnClickListener(this);
        mBtnUpdate.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
    }
}