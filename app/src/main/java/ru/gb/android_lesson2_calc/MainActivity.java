package ru.gb.android_lesson2_calc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

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







