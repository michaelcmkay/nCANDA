package edu.ucsd.ncanda;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

public class WebActivity extends Activity {

    private WebView mWebview ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String url = "https://secure.projopts.org/ncanda_ema1/subjects/token.php?token=" + FirebaseInstanceId.getInstance().getToken();

        mWebview  = new WebView(this);
        mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript
        mWebview.setWebViewClient(new WebViewClient());
        mWebview .loadUrl(url);
        setContentView(mWebview );

        Log.d("WEB_ACTIVITY", FirebaseInstanceId.getInstance().getToken());
    }
}
