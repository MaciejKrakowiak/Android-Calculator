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

public class SimpleCalculator extends AppCompatActivity {
    private static String currentText = "";
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.simple);
        textView = findViewById(R.id.textView);
        if (savedInstanceState != null) {
            String savedText = savedInstanceState.getString(currentText);
            textView.setText(savedText);
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.simple), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int viewId = v.getId();
                if (viewId == R.id.buttonSv1 || viewId == R.id.buttonSv2 ||
                        viewId == R.id.buttonSv3 || viewId == R.id.buttonSv4 ||
                        viewId == R.id.buttonSv5 || viewId == R.id.buttonSv6 ||
                        viewId == R.id.buttonSv7 || viewId == R.id.buttonSv8 ||
                        viewId == R.id.buttonSv9 || viewId == R.id.buttonSv0) {
                    currentText = textView.getText().toString();
                    currentText = clearStringIfContainsEqual(currentText);
                    Button button = findViewById(viewId);
                    String buttonText = button.getText().toString();
                    textView.setText(currentText + buttonText);
                } else if (viewId == R.id.buttonSClear) {
                    textView.setText("");
                } else if (viewId == R.id.buttonSBraces) {
                    currentText = textView.getText().toString();
                    currentText = clearStringIfContainsEqual(currentText);
                    textView.setText(toggleBraces(currentText));
                } else if (viewId == R.id.buttonSBackspace) {
                    currentText = textView.getText().toString();
                    currentText = clearStringIfContainsEqual(currentText);
                    if (!currentText.isEmpty()) {
                        textView.setText(currentText.substring(0, currentText.length() - 1));
                    }

                } else if (viewId == R.id.buttonSDiv) {
                    currentText = textView.getText().toString();
                    currentText = clearStringIfContainsEqual(currentText);
                    textView.setText(currentText + "/");
                } else if (viewId == R.id.buttonSMulti) {
                    currentText = textView.getText().toString();
                    currentText = clearStringIfContainsEqual(currentText);
                    textView.setText(currentText + "*");
                } else if (viewId == R.id.buttonSMin) {
                    currentText = textView.getText().toString();
                    currentText = clearStringIfContainsEqual(currentText);
                    textView.setText(currentText + "-");
                } else if (viewId == R.id.buttonSPlus) {
                    currentText = textView.getText().toString();
                    currentText = clearStringIfContainsEqual(currentText);
                    ((TextView) findViewById(R.id.textView)).setText(currentText + "+");
                } else if (viewId == R.id.buttonSResult) {
                    currentText = textView.getText().toString();
                    try {
                        double result = evaluate(currentText);
                        String formatted = formatNumber(result);
                        textView.setText(currentText + " = " + formatted);
                    } catch (UnknownFunctionOrVariableException e) {
                        Toast.makeText(SimpleCalculator.this, "Unknown function or variable encountered.", Toast.LENGTH_SHORT).show();
                    } catch (ArithmeticException e) {
                        Toast.makeText(SimpleCalculator.this, "Arithmetic error occurred.", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(SimpleCalculator.this, "An unexpected error occurred.", Toast.LENGTH_SHORT).show();
                    }
                } else if (viewId == R.id.buttonSCom) {
                    currentText = textView.getText().toString();
                    currentText = clearStringIfContainsEqual(currentText);
                    textView.setText(currentText + ".");
                } else if (viewId == R.id.buttonSPlusMin) {
                    currentText = textView.getText().toString();
                    currentText = clearStringIfContainsEqual(currentText);
                    textView.setText(changeSign(currentText));
                }
            }
        };
        findViewById(R.id.buttonSPlus).setOnClickListener(clickListener);
        findViewById(R.id.buttonSMin).setOnClickListener(clickListener);
        findViewById(R.id.buttonSPlusMin).setOnClickListener(clickListener);
        findViewById(R.id.buttonSBraces).setOnClickListener(clickListener);
        findViewById(R.id.buttonSMulti).setOnClickListener(clickListener);
        findViewById(R.id.buttonSDiv).setOnClickListener(clickListener);
        findViewById(R.id.buttonSResult).setOnClickListener(clickListener);
        findViewById(R.id.buttonSCom).setOnClickListener(clickListener);
        findViewById(R.id.buttonSClear).setOnClickListener(clickListener);
        findViewById(R.id.buttonSBackspace).setOnClickListener(clickListener);
        findViewById(R.id.buttonSv1).setOnClickListener(clickListener);
        findViewById(R.id.buttonSv2).setOnClickListener(clickListener);
        findViewById(R.id.buttonSv3).setOnClickListener(clickListener);
        findViewById(R.id.buttonSv4).setOnClickListener(clickListener);
        findViewById(R.id.buttonSv5).setOnClickListener(clickListener);
        findViewById(R.id.buttonSv6).setOnClickListener(clickListener);
        findViewById(R.id.buttonSv7).setOnClickListener(clickListener);
        findViewById(R.id.buttonSv8).setOnClickListener(clickListener);
        findViewById(R.id.buttonSv9).setOnClickListener(clickListener);
        findViewById(R.id.buttonSv0).setOnClickListener(clickListener);

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