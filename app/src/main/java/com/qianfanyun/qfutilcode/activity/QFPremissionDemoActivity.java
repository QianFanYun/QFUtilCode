package com.qianfanyun.qfutilcode.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.qianfanyun.qfutilcode.R;
import com.qianfanyun.utilcode.qfy_permission.QFPermissionManager;
import com.qianfanyun.utilcode.qfy_permission.QFPermissionRequestListener;
import com.yanzhenjie.permission.RequestExecutor;
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
                qfy_requestPermission(Permission.READ_CONTACTS, Permission.WRITE_CONTACTS);
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
            public void showDeniedInfo(final RequestExecutor executor) {
                new AlertDialog.Builder(QFPremissionDemoActivity.this)
                        .setCancelable(false)
                        .setTitle("权限申请提醒")
                        .setMessage("这里需要联系人权限")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                executor.execute();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                executor.cancel();
                            }
                        }).show();
            }

            @Override
            public void alwaysDenied() {
                new AlertDialog.Builder(QFPremissionDemoActivity.this)
                        .setCancelable(false)
                        .setMessage("前往设置里开启权限？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                QFPermissionManager.goSetting(QFPremissionDemoActivity.this);

                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            }
        },permission);
    }
}
