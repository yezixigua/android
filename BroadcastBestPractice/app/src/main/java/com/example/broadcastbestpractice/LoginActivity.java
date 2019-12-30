package com.example.broadcastbestpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        accountEdit = findViewById(R.id.account);
        passwordEdit = findViewById(R.id.password);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();

                if (account.equals(PUBLIC_ACCOUNT) && password.equals(PUBLIC_PASSWORD)) {
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
