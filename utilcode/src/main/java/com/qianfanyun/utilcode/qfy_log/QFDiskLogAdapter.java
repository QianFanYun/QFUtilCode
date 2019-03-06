package com.qianfanyun.utilcode.qfy_log;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.LogAdapter;

/**
 * @author luys
 * @describe This is used to saves log messages to the disk
 * @date 2019/3/5
 * @email samluys@foxmail.com
 */
public class QFDiskLogAdapter implements LogAdapter {
    @NonNull
    private final FormatStrategy formatStrategy;


    public QFDiskLogAdapter() {
        formatStrategy = QFCsvFormatStrategy.newBuilder().build();
    }

    public QFDiskLogAdapter(String path) {
        formatStrategy = QFCsvFormatStrategy.newBuilder().build(path);
    }

    public QFDiskLogAdapter(@NonNull FormatStrategy formatStrategy) {
        this.formatStrategy = QFUtils.checkNotNull(formatStrategy);
    }

    @Override public boolean isLoggable(int priority, @Nullable String tag) {
        return true;
    }

    @Override public void log(int priority, @Nullable String tag, @NonNull String message) {
        formatStrategy.log(priority, tag, message);
    }
}
