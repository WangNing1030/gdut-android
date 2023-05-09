package com.wangning.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class WebViewActivity extends AppCompatActivity {

    public static final String REQUEST_VIEW_KEY = "displayView";

    private Button btnDialog;

    public static void actionStart(Context context, int requestViewCode) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(REQUEST_VIEW_KEY, requestViewCode);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        final WebView webView = findViewById(R.id.web_view);
        // 当需要从一个网页跳转至另一个网页时，目标网页仍然在当前 WebView 展示，而不是打开系统浏览器
        webView.setWebViewClient(new WebViewClient());

        int requestViewCode = getIntent().getIntExtra(REQUEST_VIEW_KEY, MainActivity.REQUEST_VIEW_HTML_CODE);
        if (requestViewCode == MainActivity.REQUEST_VIEW_HTML_CODE) {
            // WebView 展示 HTML 代码网页
            displayHTML(webView);
        }
        if (requestViewCode == MainActivity.REQUEST_VIEW_JS_CODE) {
            // WebView 展示 JavaScript 代码网页
            displayJS(webView);
        }
    }

    /**
     * WebView 展示 HTML 代码网页
     *
     * @param webView webView
     */
    private void displayHTML(WebView webView) {
        // 创建一个字符串构建器，将要显示的HTML内容放置在该构建器中
        StringBuilder sb = new StringBuilder();
        sb.append("<div>WebView展示HTML代码网页：</div>");
        sb.append("<ul>");
        sb.append("<li>baseUrl：指定当前页使用的基本URL</li>");
        sb.append("<li>data：要显示的字符串数据</li>");
        sb.append("<li>mimeType：要显示内容的MIME类型</li>");
        sb.append("<li>encoding：指定数据的编码方式</li>");
        sb.append("<li>historyUrl：进入该页前显示页的URL</li>");
        sb.append("</ul>");
        // 加载 HTML 代码，设置 utf-8 避免中文乱码
        webView.loadDataWithBaseURL(null, sb.toString(), "text/html", "utf-8", null);
    }

    /**
     * WebView 展示 JavaScript 代码网页
     *
     * @param webView webView
     */
    private void displayJS(WebView webView) {
        btnDialog = findViewById(R.id.btn_dialog);
        btnDialog.setVisibility(View.VISIBLE);
        btnDialog.setOnClickListener(view -> {
            // 让 WebView 支持 JavaScript 脚本
            webView.getSettings().setJavaScriptEnabled(true);
            // 显示网页中通过 JavaScript 代码弹出的提示框
            webView.setWebChromeClient(new WebChromeClient());
            webView.loadUrl("file:///android_asset/alert.html");
        });

    }

}