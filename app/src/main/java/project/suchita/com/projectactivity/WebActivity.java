package project.suchita.com.projectactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebActivity extends AppCompatActivity implements View.OnClickListener{

    WebView wvResult;
    ProgressBar pvLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);
        wvResult = (WebView)findViewById(R.id.wvResult);
        pvLoad=(ProgressBar)findViewById(R.id.pbLoad);


        wvResult.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pvLoad.setVisibility(View.INVISIBLE);
            }
        });
        wvResult.loadUrl("https://www.google.com");

        WebSettings webSettings = wvResult.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setJavaScriptEnabled(true);
        wvResult.setWebChromeClient(new WebChromeClient());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.btnBrowser):
        }
    }
}
