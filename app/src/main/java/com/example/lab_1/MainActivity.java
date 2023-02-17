package com.example.lab_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText weightText;
    private EditText heightText;
    private TextView resultText;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightText = findViewById(R.id.input_weight);
        heightText = findViewById(R.id.input_height);
        resultText = findViewById(R.id.text_BMI);
        calculateButton = findViewById(R.id.button_calculate);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //validating the input
                double weight = 0, height = 0;

                try {
                    weight = Double.parseDouble(weightText.getText().toString());
                    height = Double.parseDouble(heightText.getText().toString()) / 100.0;
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Please enter valid weight and height values.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (weight <= 0 || height <= 0) {
                    Toast.makeText(MainActivity.this, "Please enter positive weight and height values.", Toast.LENGTH_SHORT).show();
                    return;
                }

                //starting the calculation
                double bmi = weight / (height * height);
                String bmiResult = String.format(Locale.getDefault(), "%.1f", bmi);

                String message = "Your BMI is " + bmiResult + "\n";

                double idealWeight = 22.5 * (height * height);
                double weightDiff = idealWeight - weight;


                if (bmi < 18.5) {
                    message += "You are underweight. ";
                    message += "You need to gain " + String.format(Locale.getDefault(), "%.1f", Math.abs(weightDiff)) + " kg to reach a BMI of 22.5.";

                } else if (bmi < 25) {
                    message += "You have a healthy weight. ";
                    if (weightDiff < 0) {
                        message += "You need to lose " + String.format(Locale.getDefault(), "%.1f", Math.abs(weightDiff)) + " kg to reach a BMI of 22.5.";
                    } else if (weightDiff > 0) {
                        message += "You need to gain " + String.format(Locale.getDefault(), "%.1f", Math.abs(weightDiff)) + " kg to reach a BMI of 22.5.";
                    } else {
                        message += "You already have a BMI of 22.5!";
                    }

                } else if (bmi < 30) {
                    message += "You are overweight. ";
                    message += "You need to lose " + String.format(Locale.getDefault(), "%.1f", Math.abs(weightDiff)) + " kg to reach a BMI of 22.5.";

                } else {
                    message += "You are obese. ";
                    message += "You need to lose " + String.format(Locale.getDefault(), "%.1f", Math.abs(weightDiff)) + " kg to reach a BMI of 22.5.";

                }

                resultText.setText(message);
            }
        });
    }

}