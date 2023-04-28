package com.wangning.gdut_cs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String CS_INTRO_XML_FILENAME = "cs_intro.xml";

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private NavFragment navFragment;
    private IntroFragment introFragment;

    private TextView tv_nav_history, tv_nav_teacher, tv_nav_student, tv_nav_activity;

    private List<CSIntro> csIntroList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 将 cs_intro.xml 文件中 CSIntro 解析为 Bean
        csIntroList = csIntroXmlParse(CS_INTRO_XML_FILENAME);
        init();

    }

    /**
     * 初始化界面控件
     */
    private void init() {
        // 获取 fragmentManager
        fragmentManager = getFragmentManager();
        // 通过 findFragmentById() 方法获取 navFragment
        navFragment = (NavFragment) fragmentManager.findFragmentById(R.id.nav_layout);
        // 获取 navFragment 中的 TextView 控件
        tv_nav_history = navFragment.getView().findViewById(R.id.tv_nav_history);
        tv_nav_teacher = navFragment.getView().findViewById(R.id.tv_nav_teacher);
        tv_nav_student = navFragment.getView().findViewById(R.id.tv_nav_student);
        tv_nav_activity = navFragment.getView().findViewById(R.id.tv_nav_activity);

        tv_nav_history.setOnClickListener(this);
        tv_nav_teacher.setOnClickListener(this);
        tv_nav_student.setOnClickListener(this);
        tv_nav_activity.setOnClickListener(this);

        // 设置首次进入界面后，默认需要显示的数据
        switchData(csIntroList.get(0));
    }

    /**
     * 设置点击事件监听器，点击 nav，下方 Fragment 切换至对应内容
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_nav_history:
                switchData(csIntroList.get(0));
                break;
            case R.id.tv_nav_teacher:
                switchData(csIntroList.get(1));
                break;
            case R.id.tv_nav_student:
                switchData(csIntroList.get(2));
                break;
            case R.id.tv_nav_activity:
                switchData(csIntroList.get(3));
                break;
        }
    }

    /**
     * 填充 Activity 下侧的 IntroFragment，并传递数据 CSIntro
     *
     * @param csIntro csIntro
     */
    private void switchData(CSIntro csIntro) {
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();//开启一个事务
        // 通过调用 getInstance() 方法实例化 IntroFragment
        introFragment = new IntroFragment().getInstance(csIntro);
        // 调用 replace() 方法
        fragmentTransaction.replace(R.id.content_layout, introFragment);
        fragmentTransaction.commit();
    }

    /**
     * 利用 dom4j 解析 xml 文件获取 CSIntro 信息
     *
     * @param fileName assets目录下文件的文件名
     * @return ArrayList<CSIntro>
     */
    private ArrayList<CSIntro> csIntroXmlParse(String fileName) {
        SAXReader saxReader = new SAXReader();
        // 利用解析器把 xml 文件加载到内存中,并返回一个文档对象
        Document document = null;

        try {
            document = saxReader.read(getResources().getAssets().open(fileName));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 获取到根标签
        Element rootElement = document.getRootElement();
        // 通过根标签来获取 introduction 标签
        // elements():可以获取调用者所有的子标签.会把这些子标签放到一个集合中返回.
        // elements("标签名"):可以获取调用者所有的指定的子标签,会把这些子标签放到一个集合中并返回
        List<Element> introElements = rootElement.elements("introduction");

        ArrayList<CSIntro> list = new ArrayList<>();

        // 遍历集合,得到每一个 introduction 标签
        for (Element element : introElements) {
            // 获取 title 标签的标签体内容
            Element titleElement = element.element("title");
            String title = titleElement.getText();
            // 获取 img 标签的标签体内容
            Element imgElement = element.element("img");
            int img = Integer.parseInt(imgElement.getText());
            // 获取 content 标签的标签体内容
            Element contentElement = element.element("content");
            String content = contentElement.getText();
            CSIntro csIntro = new CSIntro(title, img, content);
            list.add(csIntro);
        }

        return list;
    }


}