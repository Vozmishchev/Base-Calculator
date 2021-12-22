package ru.gb.android_lesson2_calc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {
    private Button oneButton;
    private TextView infoTextView;
    private Button clearButton;
    private Button pointButton;
    private Button equalityButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInfo();
        pointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = infoTextView.getText().toString();
                if (!result.contains(".")) {
                    infoTextView.append(".");
                } else Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoTextView.setText(" ");
            }
        });
    }

    public void initInfo() {
        infoTextView = findViewById(R.id.info_text_view);
        clearButton = findViewById(R.id.clear_button);
        pointButton = findViewById(R.id.point_button);
        equalityButton = findViewById(R.id.equality_button);
    }

    public void onNumberClick(View view) {
        Button buttons = (Button) view;
        String number = infoTextView.getText().toString();
        infoTextView.append(buttons.getText());

    }
}









