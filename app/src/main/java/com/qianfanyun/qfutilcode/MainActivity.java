package com.qianfanyun.qfutilcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qianfanyun.qfutilcode.activity.QFPremissionDemoActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.qfy_permission).setOnClickListener(this);
        findViewById(R.id.qfy_log).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qfy_permission:

                startActivity(new Intent(this, QFPremissionDemoActivity.class));
                break;
        }

    }
}
