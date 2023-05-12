package com.wangning.order.bean;

import com.wangning.order.R;

import java.util.ArrayList;
import java.util.List;

public class AdBannerBean {
    public Integer imageRes;

    public AdBannerBean(Integer imageRes) {
        this.imageRes = imageRes;
    }

    /**
     * 获取广告 Banner 列表
     *
     * @return List<AdBannerBean>
     */
    public static List<AdBannerBean> getTestData() {
        List<AdBannerBean> list = new ArrayList<>();
        list.add(new AdBannerBean(R.drawable.banner_1));
        list.add(new AdBannerBean(R.drawable.banner_2));
        list.add(new AdBannerBean(R.drawable.banner_3));
        return list;
    }

}
