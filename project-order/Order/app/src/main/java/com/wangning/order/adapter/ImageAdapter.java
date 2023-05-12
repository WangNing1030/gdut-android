package com.wangning.order.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;


import com.wangning.order.bean.AdBannerBean;
import com.wangning.order.viewholder.ImageHolder;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

/**
 * 自定义 Banner
 */
public class ImageAdapter extends BannerAdapter<AdBannerBean, ImageHolder> {
    /**
     * 设置数据，也可以调用 banner 提供的方法,或者自己在 adapter 中实现
     *
     * @param mDatas mDatas
     */
    public ImageAdapter(List<AdBannerBean> mDatas) {
        super(mDatas);
    }

    /**
     * 创建 ViewHolder，可以用 viewType 这个字段来区分不同的 ViewHolder
     *
     * @param parent   viewGroup
     * @param viewType viewType
     * @return ImageHolder
     */
    @Override
    public ImageHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new ImageHolder(imageView);
    }

    @Override
    public void onBindView(ImageHolder holder, AdBannerBean data, int position, int size) {
        holder.imageView.setImageResource(data.imageRes);
    }
}
