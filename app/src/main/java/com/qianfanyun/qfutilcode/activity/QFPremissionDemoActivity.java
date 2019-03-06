package com.qianfanyun.qfutilcode.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.qianfanyun.qfutilcode.R;
import com.qianfanyun.utilcode.qfy_permission.QFPermissionConstants;
import com.qianfanyun.utilcode.qfy_permission.QFPermissionManager;
import com.qianfanyun.utilcode.qfy_permission.QFPermissionRequestListener;


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
                qfy_requestPermission(QFPermissionConstants.READ_PHONE_STATE);
                break;
            case R.id.qfy_permission_group:
                qfy_requestPermission(QFPermissionConstants.Group.LOCATION);
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
                return "去往设置页面授权";
            }
        },permission);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case QFPermissionManager.REQUEST_CODE_SETTING:
                if (QFPermissionManager.hasPermissions(this, QFPermissionConstants.READ_PHONE_STATE)) {
                    // 有对应的权限
                    Toast.makeText(QFPremissionDemoActivity.this, "onActivityResult:授权成功", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(QFPremissionDemoActivity.this, "onActivityResult:授权失败", Toast.LENGTH_LONG).show();
                }

                break;
        }
    }
}
