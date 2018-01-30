package com.demo.verify;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.verify.http.HttpCallback;
import com.demo.verify.http.OkHttpUtils;
import com.demo.verify.http.ResultDesc;
import com.demo.verify.utils.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditBaseUrl, mEditInterface, mEditParams;
    private RadioButton mGet, mPost;
    private Button mButton;
    private ImageView mTips;
    private TextView mJsonContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        mEditBaseUrl = (EditText) findViewById(R.id.ed_baseurl);
        mEditInterface = (EditText) findViewById(R.id.ed_interface);
        mEditParams = (EditText) findViewById(R.id.ed_params);

        mGet = (RadioButton) findViewById(R.id.rb_get);
        mPost = (RadioButton) findViewById(R.id.rb_post);

        mTips = (ImageView) findViewById(R.id.img_params_tips);
        mJsonContent = (TextView) findViewById(R.id.text_json_content);

        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mGet.setOnClickListener(this);
        mPost.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_get:
                mTips.setImageResource(R.drawable.get);
                break;
            case R.id.rb_post:
                mTips.setImageResource(R.drawable.post);
                break;
            case R.id.button:
                if (isCheck()) {
                    if (mGet.isChecked()) {
                        getContent();
                    } else {
                        postContent();
                    }
                }
                break;
        }
    }

    private boolean isCheck() {
        if (StringUtil.isEmpty(mEditBaseUrl.getText().toString())) {
            Toast.makeText(getApplicationContext(), "BaseUrl不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (StringUtil.isEmpty(mEditInterface.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Interface不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!mGet.isChecked() && !mPost.isChecked()) {
            Toast.makeText(getApplicationContext(), "请选择请求方式Get/Post", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (mPost.isChecked() && StringUtil.isEmpty(mEditParams.getText().toString())) {
            Toast.makeText(getApplicationContext(), "请输入具体参数", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void getContent() {
        if (!StringUtil.isEmpty(mEditBaseUrl.getText().toString())) {
            MainApplication.BaseUrl = mEditBaseUrl.getText().toString();
        }

        if (!StringUtil.isEmpty(mEditInterface.getText().toString())) {
            MainApplication.BaseInterface = mEditInterface.getText().toString();
        }

        MainApplication.completeUrl = MainApplication.BaseUrl + MainApplication.BaseInterface;

        if (!StringUtil.isEmpty(mEditParams.getText().toString())) {
            String content = mEditParams.getText().toString().trim();
            String[] arryString = content.split("&");
            HashMap<String, String> params = new HashMap<String, String>();

            for (int i = 0; i < arryString.length; i++) {
                String[] itemString = arryString[i].split("=");
                params.put(itemString[0], itemString[1]);
            }

            MainApplication.completeUrl = MainApplication.BaseUrl + MainApplication.BaseInterface;

            OkHttpUtils.getAsyn(MainApplication.completeUrl, params, new HttpCallback() {

                @Override
                public void onSuccess(ResultDesc resultDesc) {
                    super.onSuccess(resultDesc);
                    String responseStr = new String(resultDesc.getResult());
                    mJsonContent.setText(responseStr);
                    mJsonContent.setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.holo_green_light));
                }

                @Override
                public void onFailure(int code, String message) {
                    super.onFailure(code, message);
                    mJsonContent.setText(message);
                    mJsonContent.setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.holo_red_light));
                }
            });
        } else {

            OkHttpUtils.getAsyn(MainApplication.completeUrl, new HttpCallback() {

                @Override
                public void onSuccess(ResultDesc resultDesc) {
                    super.onSuccess(resultDesc);
                    String responseStr = new String(resultDesc.getResult());
                    mJsonContent.setText(responseStr);
                    mJsonContent.setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.holo_green_light));
                }

                @Override
                public void onFailure(int code, String message) {
                    super.onFailure(code, message);
                    mJsonContent.setText(message);
                    mJsonContent.setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.holo_red_light));
                }
            });
        }

    }

    private void postContent() {
        if (!StringUtil.isEmpty(mEditBaseUrl.getText().toString())) {
            MainApplication.BaseUrl = mEditBaseUrl.getText().toString();
        }

        if (!StringUtil.isEmpty(mEditInterface.getText().toString())) {
            MainApplication.BaseInterface = mEditInterface.getText().toString();
        }

        MainApplication.completeUrl = MainApplication.BaseUrl + MainApplication.BaseInterface;

        if (!StringUtil.isEmpty(mEditParams.getText().toString())) {
            String content = mEditParams.getText().toString().trim();
            String newContent = content.substring(1, content.length() - 1);//移除大括号

            String[] arryString = newContent.split(",");
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

            HashMap<String, String> params = new HashMap<String, String>();

            for (int i = 0; i < arryString.length; i++) {
                String[] itemString = arryString[i].split(":");
                params.put(itemString[0].replace("\"", ""), itemString[1].replace("\"", ""));//移除双引号
            }

            OkHttpUtils.postAsyn(MainApplication.completeUrl, params, new HttpCallback() {

                @Override
                public void onSuccess(ResultDesc resultDesc) {
                    super.onSuccess(resultDesc);
                    String responseStr = new String(resultDesc.getResult());
                    mJsonContent.setText(responseStr);
                    mJsonContent.setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.holo_green_light));
                }

                @Override
                public void onFailure(int code, String message) {
                    super.onFailure(code, message);
                    mJsonContent.setText(message);
                    mJsonContent.setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.holo_red_light));
                }
            });
        } else {
            OkHttpUtils.getAsyn(MainApplication.completeUrl, new HttpCallback() {

                @Override
                public void onSuccess(ResultDesc resultDesc) {
                    super.onSuccess(resultDesc);
                    String responseStr = new String(resultDesc.getResult());
                    mJsonContent.setText(responseStr);
                    mJsonContent.setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.holo_green_light));
                }

                @Override
                public void onFailure(int code, String message) {
                    super.onFailure(code, message);
                    mJsonContent.setText(message);
                    mJsonContent.setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.holo_red_light));
                }
            });
        }
    }


}
