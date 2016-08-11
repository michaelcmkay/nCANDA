package edu.ucsd.ncanda;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.iid.FirebaseInstanceId;

public class WebActivity extends Activity {

    private WebView mWebview ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocalBroadcastManager.getInstance(this).registerReceiver(tokenReceiver,
                new IntentFilter("tokenReceiver"));
        setWebView();
    }
    private void setWebView(){
        String token = FirebaseInstanceId.getInstance().getToken();
        if(token == null) {
            Toast.makeText(this, "Please wait while your token is being generated!", Toast.LENGTH_LONG).show();
            Log.d("WEB_ACTIVITY", "was null");
            return;
        }
        String url = "https://secure.projopts.org/ncanda_ema1/subjects/token.php?token=" + token;

        mWebview  = new WebView(this);
        mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript
        mWebview.setWebViewClient(new WebViewClient());
        mWebview.loadUrl(url);
        setContentView(mWebview );

        Log.d("WEB_ACTIVITY", token);
    }

    BroadcastReceiver tokenReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String token = intent.getStringExtra("token");
            if(token != null)
                setWebView();


        }
    };
}
