### [QFLogUtils.java](https://github.com/QianFanYun/QFUtilCode/blob/143f3a2b44463837bc23dd0ec64fda992044aac4/utilcode/src/main/java/com/qianfanyun/utilcode/qfy_log/QFLogUtils.java)  -> [demo](https://github.com/QianFanYun/QFUtilCode/blob/143f3a2b44463837bc23dd0ec64fda992044aac4/app/src/main/java/com/qianfanyun/qfutilcode/activity/LogDemoActivity.java)
#### 初始化
Application的onCreate方法里添加如下代码
```
QFLogUtils.newBuilder()
                .tag("")// 配置全局TAG 默认为“QF_LOG”
                .isSaveLocal(true)// 是否开启保存Log信息到本地
                .savePath("")// 设置保存路径
                .saveTag("ZOE_LUYS") // 保存指定的TAG信息
                .build();
```

### [QFPermissionManager](https://github.com/QianFanYun/QFUtilCode/blob/5448b93325360b34e0a6feeba7fae89059ef29b8/utilcode/src/main/java/com/qianfanyun/utilcode/qfy_permission/QFPermissionManager.java) -> [Demo](https://github.com/QianFanYun/QFUtilCode/blob/5448b93325360b34e0a6feeba7fae89059ef29b8/app/src/main/java/com/qianfanyun/qfutilcode/activity/QFPremissionDemoActivity.java)

方法 | 说明
---|---
onGranted | 权限授权成功
onDenied | 权限授权失败
showPermissionRational | 用户第一次拒绝后，下次请求可以提供一个提示信息，返回具体信息内容
alwaysDeniedMessage | 用户总是拒绝（勾选了“不再提示”），引导用户去设置页授权，返回具体信息内容
