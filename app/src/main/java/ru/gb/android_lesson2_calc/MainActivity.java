package ru.gb.android_lesson2_calc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView infoTextView;
    private Button clearButton;
    private Button pointButton;
    private Button equalityButton;
    private static final String NUMBER_KEY = "number_key";
    private String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.push_button).setOnClickListener(v -> {
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("name", infoTextView.getText().toString());
            startActivity(intent);
        });

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

        if (savedInstanceState != null && savedInstanceState.containsKey(NUMBER_KEY)) {
            number = savedInstanceState.getString(NUMBER_KEY);
            infoTextView.setText(number);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(NUMBER_KEY, infoTextView.getText().toString());
    }

    public void initInfo() {
        infoTextView = findViewById(R.id.info_text_view);
        clearButton = findViewById(R.id.clear_button);
        pointButton = findViewById(R.id.point_button);
        equalityButton = findViewById(R.id.equality_button);
    }

    public void onNumberClick(View view) {
        Button buttons = (Button) view;
        infoTextView.append(buttons.getText());
    }

}














