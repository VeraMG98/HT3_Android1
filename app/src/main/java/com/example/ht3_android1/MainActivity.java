package com.example.ht3_android1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textGetEdit;
    private ImageView imageFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initElements();
    }

    private void initElements() {
        textGetEdit = findViewById(R.id.text_get_edit);
        imageFirst = findViewById(R.id.image_first);
    }

    public void onClickButtonGetSecondActivity(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri uri = Uri.parse(data.getStringExtra("text"));
            imageFirst.setImageURI(uri);
            String text = data.getStringExtra(SecondActivity.TEXT_KEY);
            textGetEdit.setText(text);
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickButtonGetGmail(View view) {
        Intent intentGmail = new Intent(Intent.ACTION_VIEW);
        intentGmail.setType("message/rfc822")
                .setData(Uri.parse("mailto:your.email@gmail.com"))
                .putExtra(Intent.EXTRA_EMAIL, "your.email@gmail.com")
                .putExtra(Intent.EXTRA_SUBJECT, "тема")
                .putExtra(Intent.EXTRA_TEXT, textGetEdit.getText().toString())
                .setPackage("com.google.android.gm");
        startActivity(intentGmail);
    }
}