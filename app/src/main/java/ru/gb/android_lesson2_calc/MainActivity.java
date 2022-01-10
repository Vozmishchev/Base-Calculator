package ru.gb.android_lesson2_calc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


public class MainActivity extends AppCompatActivity {
    private TextView resultTextView;
    private Button clearButton;
    private Button dotButton;
    private Button equalityButton;
    private static final String NUMBER_KEY = "number_key";
    private String number;
    private boolean lastNumber;
    private boolean stateError;
    private boolean lastDot;
    private int[] numberButtons;
    private int[] operationButtons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberButtons = new int[]{R.id.zero_button, R.id.one_button, R.id.two_button,
                R.id.three_button, R.id.four_button, R.id.five_button, R.id.six_button,
                R.id.seven_button, R.id.eight_button, R.id.nine_button};

        operationButtons = new int[]{R.id.addition_button, R.id.subtraction_button,
                R.id.multiplication_button, R.id.division_button};

        findViewById(R.id.push_button).setOnClickListener(v -> {
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("name", resultTextView.getText().toString());
            startActivity(intent);
        });

        initInfo();
        setNumberOnClickListener();
        setOperatorOnClickListener();

        if (savedInstanceState != null && savedInstanceState.containsKey(NUMBER_KEY)) {
            number = savedInstanceState.getString(NUMBER_KEY);
            resultTextView.setText(number);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(NUMBER_KEY, resultTextView.getText().toString());
    }

    public void initInfo() {
        resultTextView = findViewById(R.id.result_text_view);
        clearButton = findViewById(R.id.clear_button);
        dotButton = findViewById(R.id.dot_button);
        equalityButton = findViewById(R.id.equality_button);
    }

    private void setNumberOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                if (stateError) {
                    resultTextView.setText(button.getText());
                    stateError = false;
                } else {
                    resultTextView.append(button.getText());
                }
                lastNumber = true;
            }
        };
        for (int id : numberButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setOperatorOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastNumber && !stateError) {
                    Button button = (Button) v;
                    resultTextView.append(button.getText());
                    lastNumber = false;
                    lastDot = false;
                }
            }
        };
        for (int id : operationButtons) {
            findViewById(id).setOnClickListener(listener);
        }
        findViewById(R.id.dot_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastNumber && !stateError && !lastDot) {
                    resultTextView.append(".");
                    lastNumber = false;
                    lastDot = true;
                }
            }
        });

        findViewById(R.id.clear_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultTextView.setText("");
                lastNumber = false;
                stateError = false;
                lastDot = false;
            }
        });

        findViewById(R.id.equality_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEqual();
            }
        });
    }

    private void onEqual() {
        if (lastNumber && !stateError) {
            String txt = resultTextView.getText().toString();
            Expression expression = new ExpressionBuilder(txt).build();
            try {
                double result = expression.evaluate();
                resultTextView.setText(Double.toString(result));
                lastDot = true;
            } catch (ArithmeticException ex) {
                resultTextView.setText("Error");
                stateError = true;
                lastNumber = false;
            }
        }
    }
}




















