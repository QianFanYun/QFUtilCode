package com.qianfanyun.utilcode.qfy_permission;

import com.yanzhenjie.permission.RequestExecutor;

/**
 * @author luys
 * @describe 权限申请回调接口
 * @date 2019/2/27
 * @email samluys@foxmail.com
 */
public interface QFPermissionRequestListener {

    /**
     * 允许授予
     */
    void onGranted();

    /**
     * 拒绝操作
     */
    void onDenied();

    /**
     * 权限被拒绝后的说明信息，用于询问用户是否继续授权
     */
    void showDeniedInfo(RequestExecutor executor);

    /**
     * 总是被拒绝，弹窗提示用户去系统设置中授权
     */
    void alwaysDenied();

}
