package com.wangning.shoppingmall;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.ViewHolder> {

    private List<Goods> goodsList;

    public GoodsAdapter(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    /**
     * 创建 ViewHolder 实例：加载 goods_item 布局，创建一个 ViewHolder 实例，
     * 并把加载出来的布局传入到构造函数当中，最后将 ViewHolder 实例返回
     *
     * @param parent   parent
     * @param viewType viewType
     * @return viewHolder(view)
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.goods_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * 对 RecyclerView 子项的数据进行赋值，在每个子项被滚动到屏幕内时执行
     *
     * @param holder   holder
     * @param position position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Goods goods = goodsList.get(position);
        holder.image.setImageResource(goods.getImageId());
        holder.name.setText(goods.getName());
        holder.price.setText(goods.getPrice());
    }

    /**
     * 一共的子项条数
     *
     * @return goodsList.size()
     */
    @Override
    public int getItemCount() {
        return goodsList.size();
    }

    /**
     * ViewHolder 复用条目对象
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView price;

        public ViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.iv);
            name = view.findViewById(R.id.name);
            price = view.findViewById(R.id.price);
        }
    }
}
