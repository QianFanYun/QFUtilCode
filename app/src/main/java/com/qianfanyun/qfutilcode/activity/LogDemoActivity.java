package com.qianfanyun.qfutilcode.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qianfanyun.qfutilcode.R;
import com.qianfanyun.utilcode.qfy_log.QFLogUtils;
import com.qianfanyun.utilcode.qfy_permission.QFPermissionConstants;
import com.qianfanyun.utilcode.qfy_permission.QFPermissionManager;
import com.qianfanyun.utilcode.qfy_permission.QFPermissionRequestListener;

public class LogDemoActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_demo);


        findViewById(R.id.btn_noTag).setOnClickListener(this);
        findViewById(R.id.btn_tag).setOnClickListener(this);
        findViewById(R.id.btn_json).setOnClickListener(this);
        findViewById(R.id.btn_xml).setOnClickListener(this);

        QFPermissionManager.requestPermission(this, new QFPermissionRequestListener() {
            @Override
            public void onGranted() {

            }

            @Override
            public void onDenied() {

            }

            @Override
            public String showPermissionRational() {
                return "保存Log信息到本地需要获取手机的存储权限";
            }

            @Override
            public String alwaysDeniedMessage() {
                return "去设置界面授权";
            }
        }, QFPermissionConstants.WRITE_EXTERNAL_STORAGE);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_noTag:
                QFLogUtils.v("verbose");
                QFLogUtils.d("debug");
                QFLogUtils.i("info");
                QFLogUtils.w("warn");
                QFLogUtils.e("error");
                break;
            case R.id.btn_tag:
                QFLogUtils.v("LUYS","verbose");
                QFLogUtils.d("LUYS","debug");
                QFLogUtils.i("LUYS","info");
                QFLogUtils.w("LUYS","warn");
                QFLogUtils.e("LUYS","error");
                break;
            case R.id.btn_json:
                String json = "{\"data\":{\"next\":0,\"group\":{\"id_in_category\":1003,\"name_in_category\":\"公司\",\"direct\":\"https://xxxx\"}},\"ret\":0,\"text\":\"\",\"hasaffair\":0,\"affair\":{\"title\":\"\",\"desc\":\"\",\"link\":\"\",\"rank\":99,\"level\":-1,\"levelname\":\"\",\"leveldesc\":\"\",\"exp\":-1,\"gold\":\"-1\",\"cash\":\"-1\"}}";
                QFLogUtils.json(json);
                break;
            case R.id.btn_xml:
                String xmlDate = "<province id=\"01\" name=\"北京\">  \n" +
                        "    <city id=\"0101\" name=\"北京\">  \n" +
                        "      <county id=\"010101\" name=\"北京\" weatherCode=\"101010100\"/>  \n" +
                        "      <county id=\"010102\" name=\"海淀\" weatherCode=\"101010200\"/>  \n" +
                        "      <county id=\"010103\" name=\"朝阳\" weatherCode=\"101010300\"/>  \n" +
                        "      <county id=\"010110\" name=\"石景山\" weatherCode=\"101011000\"/>  \n" +
                        "    </city>  \n" +
                        "  </province>  ";
                QFLogUtils.xml(xmlDate);
                break;
        }

    }
}
