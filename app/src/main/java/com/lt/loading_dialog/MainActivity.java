package com.lt.loading_dialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view){
        LoadingDialog loadingDialog = new LoadingDialog(this,"正在登录...",R.mipmap.ic_dialog_loading);
        loadingDialog.show();
    }
}
