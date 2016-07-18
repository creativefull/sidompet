package com.example.mshodiqul.sidompet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Welcome extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);
    }

    public void save(View v) {
        TextView namaText = (TextView) findViewById(R.id.personal_name);
        String nama = namaText.getText().toString();
        User dataUser = new User(this);
        dataUser.name = nama;
        dataUser.amount = 0;
        if (nama.equals("")) {
            Toast.makeText(this, "Nama Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
        }
        else {
            dataUser.insertUser(dataUser);
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }
    }
}
