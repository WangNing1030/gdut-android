package com.wangning.shoppingmall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Goods> goodsList = new ArrayList<>();
    private RecyclerView recyclerView;

    // 商品名称与价格数据集合
    private String[] names = {"桌子", "苹果", "蛋糕", "线衣", "猕猴桃", "围巾", "香蕉", "西瓜"};
    private String[] prices = {"1800元", "10元/kg", "300元", "350元", "10元/kg",
            "280元", "5元/kg", "15元/kg"};
    // 图片数据集合
    private int[] images = {R.drawable.table, R.drawable.apple, R.drawable.cake,
            R.drawable.wireclothes, R.drawable.kiwifruit, R.drawable.scarf, R.drawable.banana, R.drawable.watermelon};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化 Goods 数据
        initGoods(8, names, prices, images);
        recyclerView = findViewById(R.id.recyclerview);
        // 设置 LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 设置 Adapter，实现数据与视图之间的转换
        recyclerView.setAdapter(new GoodsAdapter(goodsList));
        // 给垂直列表 RecyclerView 添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    /**
     * 初始化 Goods 数据
     *
     * @param goodsCount 商品总数
     * @param names      商品名数组
     * @param prices     商品价格数组
     * @param images     商品图片数组
     */
    private void initGoods(int goodsCount, String[] names, String[] prices, int[] images) {
        for (int i = 0; i < goodsCount; i++) {
            Goods goods = new Goods(names[i], prices[i], images[i]);
            goodsList.add(goods);
        }
    }
}