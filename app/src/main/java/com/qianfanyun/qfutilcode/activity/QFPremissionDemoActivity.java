package com.qianfanyun.qfutilcode.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.qianfanyun.qfutilcode.R;
import com.qianfanyun.utilcode.qfy_permission.QFPermissionManager;
import com.qianfanyun.utilcode.qfy_permission.QFPermissionRequestListener;
import com.yanzhenjie.permission.runtime.Permission;


public class QFPremissionDemoActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qfpremission_demo);

        findViewById(R.id.qfy_contacts).setOnClickListener(this);
        findViewById(R.id.qfy_permission_group).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qfy_contacts:
                qfy_requestPermission(Permission.READ_PHONE_STATE);
                break;
            case R.id.qfy_permission_group:
                qfy_requestPermission(Permission.Group.LOCATION);
                break;
        }
    }


    private void qfy_requestPermission(String... permission) {

        QFPermissionManager.requestPermission(this, new QFPermissionRequestListener() {
            @Override
            public void onGranted() {

                Toast.makeText(QFPremissionDemoActivity.this, "授权成功", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onDenied() {
                Toast.makeText(QFPremissionDemoActivity.this, "拒绝授权", Toast.LENGTH_LONG).show();
            }

            @Override
            public String showPermissionRational() {
                return "为了更好的体验APP，需要获取手机相关信息";
            }

            @Override
            public String alwaysDeniedMessage() {
                return getString(R.string.permission_read_phone) + getString(R.string.app_name) + getString(R.string.permission_read_phone2) + getString(R.string.app_name);
            }

        },permission);
    }
}
