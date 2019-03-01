package com.qianfanyun.utilcode.qfy_permission;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;

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
                            showRationalDialog(context, listener, executor);
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
                                // 用户勾选了"不再提示"，并拒绝，这个时候可以提示用户去设置里允许
                                showSettingDialog(context, listener);
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
     * 用户拒绝后，下次请求弹框说明，申请权限的原因，并引导用户去收取
     *
     * @param context
     * @param listener
     */
    private static void showRationalDialog(final Context context, QFPermissionRequestListener listener, final RequestExecutor executor) {
        new AlertDialog.Builder(context)
                .setCancelable(false)
                .setTitle("权限申请提醒")
                .setMessage(listener.showPermissionRational())
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        executor.execute();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        executor.cancel();
                        dialog.dismiss();
                    }
                }).show();
    }

    /**
     * 引导用户去设置页面授权
     * @param context
     * @param listener
     */
    private static void showSettingDialog(final Context context, QFPermissionRequestListener listener) {
        new AlertDialog.Builder(context)
                .setCancelable(false)
                .setMessage(listener.alwaysDeniedMessage())
                .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        QFPermissionManager.goSetting((Activity) context);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
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
