package com.winfo.mypractise.ui;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.winfo.mypractise.R;
import com.winfo.mypractise.baseclass.BaseActivity;

public class DataStorageActivity extends BaseActivity {

    private static final String TAG = "SharedPreferencesTest";
    /**
     * 保存数据SharedPreferences文件的名字
     */
    private final String PREFS_NAME = "MyPrefsFile";

    private EditText user, passWord;//输入框
    private TextView getText;//显示区域
    private SharedPreferences sharedPreferences;

    @Override
    protected int initLayout() {
        return R.layout.activity_data_storage;
    }


    @Override
    protected void initView() {
        user = findViewById(R.id.edit_user);
        passWord = findView(R.id.edit_password);
        getText = findView(R.id.out_content);
        //保存，清空，获取账号、密码，移除密码
        findView(R.id.out_save).setOnClickListener(this);
        findView(R.id.out_black).setOnClickListener(this);
        findView(R.id.get_user_password).setOnClickListener(this);
        findView(R.id.get_remove).setOnClickListener(this);

    }

    /**
     * 保存用户信息
     */
    private void saveUserInfo() {
        SharedPreferences userInfo = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.OnSharedPreferenceChangeListener changeListener = (preferences, key) -> {
            //preferences被 编辑的SharedPreferences实例
            //该SharedPreferences中被编辑的条目所对应的key
        };
        //userInfo注册监听事件
        userInfo.registerOnSharedPreferenceChangeListener(changeListener);
        SharedPreferences.Editor editor = userInfo.edit();//获取Editor
        //得到Editor后，写入需要保存的数据
        editor.putString("username", "一只猫的涵养");
        editor.putInt("age", 20);
        editor.apply();//提交修改
        Log.i(TAG, "保存用户信息成功");
    }

    /**
     *
     * getSharedPreferences(Sting name,int mode)----------
     * mode参数：
     *   MODE_PRIVATE（只能被自己的应用程序访问）-----
     *   MODE_WORLD_READABLE（除了自己访问外还可以被其它应该程序读取）-----
     *   MODE_WORLD_WRITEABLE（除了自己访问外还可以被其它应该程序读取和写入）-----
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.out_save:
                //Edit的内容
                String sp_user = user.getText().toString();
                String sp_psd = passWord.getText().toString();

                if ("".equals(sp_user) && "".equals(sp_psd)) {
                    showToast("账号或密码不能为空！");
                } else {
                    sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editorSava = sharedPreferences.edit();
                    editorSava.putString("user", sp_user).putString("passWord", sp_psd);
                    editorSava.apply();
                    showToast("保存成功");
                    Log.e(TAG, "user:" + sp_user + "----passWord:" + sp_psd);
                }
                break;
            case R.id.out_black:
                SharedPreferences.Editor editorClear = sharedPreferences.edit();
                editorClear.clear().apply();
                showToast("清理完成");
                break;
            case R.id.get_user_password:
                String userId = sharedPreferences.getString("user", "");
                String status = sharedPreferences.getString("passWord", "");
                String str1 = "账号:" + userId + "\n" + "密码:" + status;
                getText.setText(str1);
                break;
            case R.id.get_remove:
                SharedPreferences.Editor remove = sharedPreferences.edit();//获取Editor
                remove.remove("passWord").apply();
                showToast("移除密码");
                break;
        }
    }
}