package com.example.an.pra3;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "zz";
    private EditText editText;
    private ImageView imageView;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_text);
        imageView = findViewById(R.id.image_view);
        progressBar =findViewById(R.id.process_bar);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);

        Button imgButton = findViewById(R.id.change_img_button);
        imgButton.setOnClickListener(this);

        Button alertButton = findViewById(R.id.alert_button);
        alertButton.setOnClickListener(this);

        Button prcessButton = findViewById(R.id.progress_button);
        prcessButton.setOnClickListener(this);

        Log.d(TAG, "onCreate");
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick");

        switch (v.getId()) {
            case R.id.button:
                String inputString = editText.getText().toString();
                Toast.makeText(MainActivity.this, inputString, Toast.LENGTH_SHORT).show();
                break;
            case R.id.change_img_button:
                imageView.setImageResource(R.drawable.img_2);

                if (progressBar.getVisibility() == View.GONE) {
                    progressBar.setVisibility(View.VISIBLE);
                }   else {
                    progressBar.setVisibility(View.GONE);
                }
                break;
            case R.id.alert_button:
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("This is a dialog");
                dialog.setMessage("something important");
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
                break;
            case R.id.progress_button:
                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("this is a progress");
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(true);
                progressDialog.show();
                break;
            default:
                break;
        }
    }
}
