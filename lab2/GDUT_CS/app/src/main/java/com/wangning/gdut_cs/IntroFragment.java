package com.wangning.gdut_cs;

import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * TODO: bug-会加载两次IntroFragment
 */
public class IntroFragment extends Fragment {

    private ImageView iv_intro;
    private TextView tv_content;

    private View view;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_intro, container, false);

        iv_intro = view.findViewById(R.id.iv_intro);
        tv_content = view.findViewById(R.id.tv_content);
        if (getArguments() != null) {
            CSIntro csIntro = (CSIntro) getArguments().getSerializable("CSIntro");
            // TODO: 获取相应的背景图片，硬编码了……
            String title = csIntro.getTitle();
            if(Objects.equals(title, "光荣历程")) {
                iv_intro.setBackgroundResource(R.drawable.cs_history);
            } else if(Objects.equals(title, "师资力量")) {
                iv_intro.setBackgroundResource(R.drawable.cs_teacher);
            }else if(Objects.equals(title, "莘莘学子")) {
                iv_intro.setBackgroundResource(R.drawable.cs_student);
            }else if(Objects.equals(title, "学院活动")) {
                iv_intro.setBackgroundResource(R.drawable.cs_activity);
            }
            tv_content.setText(csIntro.getContent());
        }
        return view;
    }


    public IntroFragment getInstance(CSIntro csIntro) {
        IntroFragment introFragment = new IntroFragment();
        // 通过 Bundle 对象传递数据可以保证在设备横竖屏切换时传递的数据不丢失
        Bundle bundle = new Bundle();
        // 将需要传递的字符串以键值对的形式传入bundle对象
        bundle.putSerializable("CSIntro", (Serializable) csIntro);
        introFragment.setArguments(bundle);
        return introFragment;
    }

}