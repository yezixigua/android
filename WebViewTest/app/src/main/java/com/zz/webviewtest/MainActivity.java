package com.zz.webviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
  *
  * @package:        com.zz.webviewtest
  * @className:      MainActivity
  * @description:     java类作用描述
  * @author:         liuzhuang
  * @createDate:     2020/1/6 0006
  * @updateUser:     更新者：
  * @updateDate:     2020/1/6 0006
  * @updateRemark:   更新说明：
  * @version:        1.0
 */

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView =findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl("https://www.baidu.com");

    }
}
