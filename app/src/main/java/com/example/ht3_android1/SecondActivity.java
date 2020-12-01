package com.example.ht3_android1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity {
    private ImageView imageSecond;
    private EditText editSetText;
    public static final String TEXT_KEY = "text2";
    private static final int IMAGE_PICK_CODE = 1;
    public String imageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        init();
    }

    private void init() {
        imageSecond = findViewById(R.id.image_second);
        editSetText = findViewById(R.id.text_set_edit);
    }

    public void onClickImageSecond(View view) {
        Intent intentSetImage = new Intent(Intent.ACTION_PICK);
        intentSetImage.setType("image/*");
        startActivityForResult(intentSetImage, IMAGE_PICK_CODE);
    }

    public void onClickGetFirstActivity(View view) {
        String textFromEdit = editSetText.getText().toString();
        Intent intent = new Intent();
        intent.putExtra(TEXT_KEY, textFromEdit);
        intent.putExtra("text", imageText);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_PICK_CODE && resultCode == RESULT_OK && data !=null) {
            Uri imageUri = data.getData();
            imageText = imageUri.toString();
            imageSecond.setImageURI(imageUri);
        }
    }
}