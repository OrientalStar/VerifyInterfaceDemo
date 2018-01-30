package com.demo.verify.http;


import com.demo.verify.utils.StringUtil;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ponos.peng
 * 2017/8/11.
 * Description:
 */

public class DataAnalysis {
    private static String reason;

    public enum JSON_TYPE {
        /**
         * JSONObject
         */
        JSON_OBJECT,
        /**
         * JSONArray
         */
        JSON_ARRAY,
        /**
         * 不是JSON格式的字符串
         */
        JSON_ERROR
    }

    /**
     * @param result 返回数据
     * @Description 获取result数据格式
     */
    private static JSON_TYPE getJSONType(String result) {
        if (StringUtil.isEmpty(result)) {
            return JSON_TYPE.JSON_ERROR;
        }

        final char[] strChar = result.substring(0, 1).toCharArray();
        final char firstChar = strChar[0];

        if (firstChar == '{') {
            return JSON_TYPE.JSON_OBJECT;
        } else if (firstChar == '[') {
            return JSON_TYPE.JSON_ARRAY;
        } else {
            return JSON_TYPE.JSON_ERROR;
        }
    }

    /**
     * @param result 请求返回字符串
     * @Description 返回数据解析
     */
    public static ResultDesc getReturnData(String result) {
        ResultDesc resultDesc = null;

        if (StringUtil.isEmpty(result)) {
            //返回数据为空
            resultDesc = dataRestructuring("no data");
            return resultDesc;
        }

        try {
            //这里需要适配自己的接口可以释放出来调整
            JSONObject jsonObject = new JSONObject(result);
            //返回码
//            int code = jsonObject.getInt("CODE");
//            int code = jsonObject.getInt("code");
            //返回说明
//            if (jsonObject.getString("MSG") != null){
//                 reason = jsonObject.getString("MSG");
//            }

//            JSONArray retailList = jsonObject.getJSONArray("data");
//            String reason = jsonObject.getString("reason");
            //返回数据
//            String resultData = jsonObject.getString("result");
            String resultData = result;

//            resultDesc = dataRestructuring(code, reason, resultData);
            resultDesc = dataRestructuring( resultData);
        } catch (JSONException e) {
//            resultDesc = dataRestructuring(-1, UIUtils.getString(R.string.back_abnormal_results), "");
            resultDesc = dataRestructuring( "JSONException");
        }

        return resultDesc;
    }

    /**
     * @param result     返回数据
     * @Description 数据重组
     */
    private static ResultDesc dataRestructuring( String result) {
        ResultDesc resultDesc = new ResultDesc( result);
        return resultDesc;
    }
}
