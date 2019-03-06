package com.qianfanyun.qfutilcode;

import android.app.Application;
import android.os.Environment;

import com.qianfanyun.utilcode.qfy_log.QFLogUtils;
import com.qianfanyun.utilcode.qfy_permission.QFPermissionConstants;
import com.qianfanyun.utilcode.qfy_permission.QFPermissionManager;
import com.qianfanyun.utilcode.qfy_permission.QFPermissionRequestListener;

import java.io.File;

/**
 * @author luys
 * @describe
 * @date 2019/3/4
 * @email samluys@foxmail.com
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // QFLogUtils.init();
        // 如果需要保存log信息到本地，需要申请存储权限
        QFLogUtils.newBuilder()
                .tag("QF_LOG_TEST")
                .isSaveLocal(true)
                .savePath(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "qfy_log_test")
                .saveTag("ZOE_LUYS")
                .build();
    }

}
