package com.example.myapplication;

import static com.example.myapplication.BraceManipulator.toggleBraces;
import static com.example.myapplication.MathEvaluator.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import net.objecthunter.exp4j.tokenizer.UnknownFunctionOrVariableException;

import java.text.DecimalFormat;

public class AdvancedCalculator extends AppCompatActivity {

    private static String currentText = "";
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.advanced);
        textView = findViewById(R.id.textView2);
        if (savedInstanceState != null) {
            String savedText = savedInstanceState.getString(currentText);
            textView.setText(savedText);
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.advanced), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int viewId = v.getId();
                if (viewId == R.id.buttonv1 || viewId == R.id.buttonv2 ||
                        viewId == R.id.buttonv3 || viewId == R.id.buttonv4 ||
                        viewId == R.id.buttonv5 || viewId == R.id.buttonv6 ||
                        viewId == R.id.buttonv7 || viewId == R.id.buttonv8 ||
                        viewId == R.id.buttonv9 || viewId == R.id.buttonv0) {
                    currentText = textView.getText().toString();
                    currentText = clearStringIfContainsEqual(currentText);
                    Button button = findViewById(viewId);
                    String buttonText = button.getText().toString();
                    textView.setText(currentText + buttonText);
                } else if (viewId == R.id.buttonClear) {
                    textView.setText("");
                } else if (viewId == R.id.buttonBraces) {
                    currentText = textView.getText().toString();
                    currentText = clearStringIfContainsEqual(currentText);
                    textView.setText(toggleBraces(currentText));
                } else if (viewId == R.id.buttonBackspace) {
                    currentText = textView.getText().toString();
                    currentText = clearStringIfContainsEqual(currentText);
                    if (!currentText.isEmpty()) {
                        textView.setText(currentText.substring(0, currentText.length() - 1));
                    }

                } else if (viewId == R.id.buttonvDiv) {
                    currentText = textView.getText().toString();
                    currentText = clearStringIfContainsEqual(currentText);
                    textView.setText(currentText + "/");
                } else if (viewId == R.id.buttonMulti) {
                    currentText = textView.getText().toString();
                    currentText = clearStringIfContainsEqual(currentText);
                    textView.setText(currentText + "*");
                } else if (viewId == R.id.buttonMin) {
                    currentText = textView.getText().toString();
                    currentText = clearStringIfContainsEqual(currentText);
                    textView.setText(currentText + "-");
                } else if (viewId == R.id.buttonPlus) {
                    currentText = textView.getText().toString();
                    currentText = clearStringIfContainsEqual(currentText);
                    ((TextView) findViewById(R.id.textView)).setText(currentText + "+");
                } else if (viewId == R.id.buttonResult) {
                    currentText = textView.getText().toString();
                    try {
                        double result = evaluate(currentText);
                        if (Double.isNaN(result)) {
                            Toast.makeText(AdvancedCalculator.this, "Can't show undefined result.", Toast.LENGTH_SHORT).show();
                        } else {
                            String formatted = formatNumber(result);
                            textView.setText(currentText + " = " + formatted);
                        }
                    } catch (UnknownFunctionOrVariableException e) {
                        Toast.makeText(AdvancedCalculator.this, "Unknown function or variable encountered.", Toast.LENGTH_SHORT).show();
                    } catch (ArithmeticException e) {
                        Toast.makeText(AdvancedCalculator.this, "Arithmetic error occurred.", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(AdvancedCalculator.this, "An unexpected error occurred.", Toast.LENGTH_SHORT).show();
                    }
                } else if (viewId == R.id.buttonCom) {
                    currentText = textView.getText().toString();
                    currentText = clearStringIfContainsEqual(currentText);
                    textView.setText(currentText + ".");
                } else if (viewId == R.id.buttonPlusMin) {
                    currentText = textView.getText().toString();
                    currentText = clearStringIfContainsEqual(currentText);
                    textView.setText(changeSign(currentText));
                } else if (viewId == R.id.buttonSin) {
                    currentText = textView.getText().toString();
                    currentText = clearStringIfContainsEqual(currentText);
                    textView.setText(currentText + "sin(");
                } else if (viewId == R.id.buttonCos) {
                    currentText = textView.getText().toString();
                    currentText = clearStringIfContainsEqual(currentText);
                    textView.setText(currentText + "cos(");
                } else if (viewId == R.id.buttonTan) {
                    currentText = textView.getText().toString();
                    currentText = clearStringIfContainsEqual(currentText);
                    textView.setText(currentText + "tan(");
                } else if (viewId == R.id.buttonPow2) {
                    currentText = textView.getText().toString();
                    currentText = clearStringIfContainsEqual(currentText);
                    textView.setText(currentText + "^2");
                } else if (viewId == R.id.buttonPow) {
                    currentText = textView.getText().toString();
                    currentText = clearStringIfContainsEqual(currentText);
                    textView.setText(currentText + "^(");
                } else if (viewId == R.id.buttonSqrt) {
                    currentText = textView.getText().toString();
                    currentText = clearStringIfContainsEqual(currentText);
                    textView.setText(currentText + "sqrt(");
                } else if (viewId == R.id.buttonLog) {
                    currentText = textView.getText().toString();
                    currentText = clearStringIfContainsEqual(currentText);
                    textView.setText(currentText + "log2(");
                } else if (viewId == R.id.buttonLn) {
                    currentText = textView.getText().toString();
                    currentText = clearStringIfContainsEqual(currentText);
                    textView.setText(currentText + "log(");
                }
            }
        };
        findViewById(R.id.buttonPlus).setOnClickListener(clickListener);
        findViewById(R.id.buttonMin).setOnClickListener(clickListener);
        findViewById(R.id.buttonPlusMin).setOnClickListener(clickListener);
        findViewById(R.id.buttonBraces).setOnClickListener(clickListener);
        findViewById(R.id.buttonMulti).setOnClickListener(clickListener);
        findViewById(R.id.buttonvDiv).setOnClickListener(clickListener);
        findViewById(R.id.buttonResult).setOnClickListener(clickListener);
        findViewById(R.id.buttonCom).setOnClickListener(clickListener);
        findViewById(R.id.buttonClear).setOnClickListener(clickListener);
        findViewById(R.id.buttonBackspace).setOnClickListener(clickListener);
        findViewById(R.id.buttonv1).setOnClickListener(clickListener);
        findViewById(R.id.buttonv2).setOnClickListener(clickListener);
        findViewById(R.id.buttonv3).setOnClickListener(clickListener);
        findViewById(R.id.buttonv4).setOnClickListener(clickListener);
        findViewById(R.id.buttonv5).setOnClickListener(clickListener);
        findViewById(R.id.buttonv6).setOnClickListener(clickListener);
        findViewById(R.id.buttonv7).setOnClickListener(clickListener);
        findViewById(R.id.buttonv8).setOnClickListener(clickListener);
        findViewById(R.id.buttonv9).setOnClickListener(clickListener);
        findViewById(R.id.buttonv0).setOnClickListener(clickListener);
        findViewById(R.id.buttonSin).setOnClickListener(clickListener);
        findViewById(R.id.buttonCos).setOnClickListener(clickListener);
        findViewById(R.id.buttonTan).setOnClickListener(clickListener);
        findViewById(R.id.buttonPow).setOnClickListener(clickListener);
        findViewById(R.id.buttonPow2).setOnClickListener(clickListener);
        findViewById(R.id.buttonSqrt).setOnClickListener(clickListener);
        findViewById(R.id.buttonLog).setOnClickListener(clickListener);
        findViewById(R.id.buttonLn).setOnClickListener(clickListener);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(currentText, textView.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String savedText = savedInstanceState.getString(currentText);
        textView.setText(savedText);
    }

}