package ru.gb.android_lesson2_calc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private TextView secondActivityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        secondActivityTextView = findViewById(R.id.second_activity_text_view);
        String putNumber = getIntent().getStringExtra("name");

        secondActivityTextView.setText(secondActivityTextView.getText().toString() + putNumber);

    }
}









