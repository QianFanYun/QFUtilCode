package com.qianfanyun.utilcode.qfy_permission;

import android.app.Activity;
import android.content.Context;
import android.os.Build;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;

import java.util.List;

/**
 * @author luys
 * @describe 权限管理 基于AndPermission https://github.com/yanzhenjie/AndPermission
 * @date 2019/2/27
 * @email samluys@foxmail.com
 */
public final class QFPermissionManager {

    public final static int REQUEST_CODE_SETTING = 1;



    /**
     * 申请权限
     * @param context 上下文
     * @param listener 结果回调
     * @param permissionGroup 权限或者权限组
     */
    public static void requestPermission (final Context context, final QFPermissionRequestListener listener, final String... permissionGroup) {

        // Android 6.0以上运行时权限申请
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // 检查是否有权限，有就直接return
            if (hasPermissions(context, permissionGroup)) {
                listener.onGranted();
                return;
            }

            // 没有权限则申请权限
            AndPermission.with(context)
                    .runtime()
                    .permission(permissionGroup)
                    // 当用户不给权限时优先执行
                    .rationale(new Rationale<List<String>>() {
                        @Override
                        public void showRationale(Context context, List<String> data, final RequestExecutor executor) {
                            // 当用户拒绝之后，下一次请求此权限的时候，作出说明，以便用户判断是否需要授权
                            listener.showDeniedInfo(executor);
                        }
                    })
                    .onGranted(new Action<List<String>>() {
                        @Override
                        public void onAction(List<String> data) {
                            listener.onGranted();
                        }
                    })
                    .onDenied(new Action<List<String>>() {
                        @Override
                        public void onAction(List<String> data) {

                            if(AndPermission.hasAlwaysDeniedPermission(context,permissionGroup)) {
                                // 用户勾选了"不再提示"，并拒绝
                                listener.alwaysDenied();
                            } else {
                                listener.onDenied();
                            }
                        }
                    })
                    .start();
        } else {
            listener.onGranted();
        }
    }

    /**
     * 判断是否有这个权限或权限组
     * @param context
     * @param permissionGroup
     * @return
     */
    public static boolean hasPermissions (final Context context, final String... permissionGroup) {
        return AndPermission.hasPermissions(context, permissionGroup);
    }


    /**
     * 提示用户去系统设置中授权
     *
     * @param activity
     */
    public static void goSetting(Activity activity) {

        AndPermission.with(activity)
                .runtime()
                .setting()
                .start(REQUEST_CODE_SETTING);
    }
}
