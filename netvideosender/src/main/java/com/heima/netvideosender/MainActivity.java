package com.heima.netvideosender;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onclick(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("https://vd.yinyuetai.com/hc.yinyuetai.com/uploads/videos/common/89150169A1D6000A78B3AE461606C9FE.mp4"), "video/mp4");
        startActivity(intent);
    }

}
