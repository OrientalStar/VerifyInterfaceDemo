package com.demo.verify.http;

import android.graphics.Bitmap;

/**
 * Created by ponos.peng
 * 2017/8/11.
 * Description:自定义请求回调类
 */

public class HttpCallback {
    /**
     * @param resultDesc 返回数据
     * @Description 请求成功时回调
     */
    public void onSuccess(ResultDesc resultDesc) {
    }

    /**
     * @param code    状态码
     * @param message 状态消息
     * @Description 请求失败时回调
     */
    public void onFailure(int code, String message) {

    }

    /**
     * @param currentTotalLen 进度
     * @param totalLen        总量
     * @Description 上传或下载时进度回调
     */
    public void onProgress(long currentTotalLen, long totalLen) {
    }

    /**
     * @param bitmap 图片bitmap
     * @Description 显示图片成功回调
     */
    public void onBitmapSuccess(Bitmap bitmap) {
    }
}
