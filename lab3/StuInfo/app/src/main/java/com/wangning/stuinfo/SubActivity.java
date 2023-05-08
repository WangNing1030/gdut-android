package com.wangning.stuinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class SubActivity extends AppCompatActivity {

    private List<Student> studentList = new ArrayList<>();
    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        // 获取 studentList
        studentList = initData();
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            finish();
        });
        recyclerView = findViewById(R.id.recyclerview);
        // 设置 LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 设置 Adapter，实现数据与视图之间的转换
        recyclerView.setAdapter(new StudentAdapter(studentList));
        // 给垂直列表 RecyclerView 添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    /**
     * 从 db_student 的 student 表中查找所有数据
     * @return studentList
     */
    private List<Student> initData() {
        return LitePal.findAll(Student.class);
    }
}