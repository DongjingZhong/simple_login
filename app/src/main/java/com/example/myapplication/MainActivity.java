package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edt_account,edt_password;
    private CheckBox rememberPassword;
    private Button login;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        edt_account = (EditText)findViewById(R.id.edt_account);
        edt_password = (EditText)findViewById(R.id.edt_password);
        rememberPassword = (CheckBox)findViewById(R.id.remember_pass);
        login = (Button)findViewById(R.id.login);
        boolean isRemember = pref.getBoolean("remember_password",false);
        if(isRemember){
            String account = pref.getString("account","");
            String password = pref.getString("password","");
            edt_account.setText(account);
            edt_password.setText(password);
            rememberPassword.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = edt_account.getText().toString();
                String password = edt_password.getText().toString();
                if(account.equals("admin")&&password.equals("12345")){
                    editor = pref.edit();
                    if(rememberPassword.isChecked()){
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",account);
                        editor.putString("password",password);
                    }else{ editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                    startActivity(intent);
                    finish();
                      } else{
                    Toast.makeText(MainActivity.this, "Not right!", Toast.LENGTH_SHORT).show();
                }
}       });



    }
}