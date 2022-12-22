package com.example.ingemann;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText txtUsername, txtPassword;
    private String password;

    public static String USERNAME;

    public static String TODAY = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindUI();
    }

    private void bindUI() {
        txtUsername = findViewById(R.id.txt_username);
        txtPassword = findViewById(R.id.txt_password);
    }

    public void signIn(View view) {
        USERNAME = String.valueOf(txtUsername.getText());
        password = String.valueOf(txtPassword.getText());

        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }
}