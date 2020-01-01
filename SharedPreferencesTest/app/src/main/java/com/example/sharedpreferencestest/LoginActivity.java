package com.example.sharedpreferencestest;

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

import java.util.prefs.PreferencesFactory;

/**
  *
  * @package:        com.example.broadcastbestpractice
  * @className:      LoginActivity
  * @description:     java类作用描述
  * @author:         liuzhuang
  * @createDate:     2019/12/30 0030
  * @updateUser:     更新者：
  * @updateDate:     2019/12/30 0030
  * @updateRemark:   更新说明：
  * @version:        1.0
 */


public class LoginActivity extends BasicActivity {

    private final String PUBLIC_ACCOUNT = "1";
    private final String PUBLIC_PASSWORD = "1";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;

    private CheckBox rememberPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit = findViewById(R.id.account);
        passwordEdit = findViewById(R.id.password);
        rememberPass = findViewById(R.id.remember_pass);
        login = findViewById(R.id.login);
        boolean isRemember = pref.getBoolean("remember_password", false);

        if (isRemember) {
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);

        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();

                if (account.equals(PUBLIC_ACCOUNT) && password.equals(PUBLIC_PASSWORD)) {
                    editor = pref.edit();
                    if (rememberPass.isChecked()) {
                        editor.putString("account", account);
                        editor.putString("password", password);
                        editor.putBoolean("remember_password", true);

                    } else {
                        editor.clear();
                    }
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "account or password is not invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
