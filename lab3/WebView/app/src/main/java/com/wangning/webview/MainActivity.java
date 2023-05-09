package com.wangning.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_VIEW_HTML_CODE = 1;
    public static final int REQUEST_VIEW_JS_CODE = 2;

    private Button btnHTML, btnJS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btnHTML = findViewById(R.id.btn_html);
        btnJS = findViewById(R.id.btn_js);
        btnHTML.setOnClickListener(this);
        btnJS.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_html:
                WebViewActivity.actionStart(this, REQUEST_VIEW_HTML_CODE);
                break;
            case R.id.btn_js:
                WebViewActivity.actionStart(this, REQUEST_VIEW_JS_CODE);
                break;
            default:
                break;
        }
    }
}