package com.example.assigmentandroid_1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextUnitsUsed;
    private EditText editTextRebatePercentage;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUnitsUsed = findViewById(R.id.editTextUnitsUsed);
        editTextRebatePercentage = findViewById(R.id.editTextRebatePercentage);
        Button buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);
        Button btnHome = findViewById(R.id.btnHome);

        buttonCalculate.setOnClickListener(v -> calculateBill());
        btnHome.setOnClickListener(view -> openAboutPage());
    }
    public void openAboutPage(){
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }
    @SuppressLint("SetTextI18n")
    private void calculateBill() {
        String unitsUsedString = editTextUnitsUsed.getText().toString();
        String rebatePercentageString = editTextRebatePercentage.getText().toString();

        if (unitsUsedString.isEmpty() || rebatePercentageString.isEmpty()) {
            textViewResult.setText("Please enter valid values.");
            return;
        }

        double unitsUsed = Double.parseDouble(unitsUsedString);
        double rebatePercentage = Double.parseDouble(rebatePercentageString)/100;

        double totalCharges = calculateCharges(unitsUsed);
        double finalCost = totalCharges - (totalCharges * rebatePercentage);

        @SuppressLint("DefaultLocale") String result = String.format("Final Cost: RM %.2f", finalCost);
        textViewResult.setText("Final Cost: RM "+finalCost);
    }

    private double calculateCharges(double unitsUsed) {
        double totalCharges = 0.0;

        if (unitsUsed <= 200) {
            totalCharges = unitsUsed * 0.218;
        } else if (unitsUsed <= 300) {
            totalCharges = 200 * 0.218 + (unitsUsed - 200) * 0.334;
        } else if (unitsUsed <= 600) {
            totalCharges = 200 * 0.218 + 100 * 0.334 + (unitsUsed - 300) * 0.516;
        } else if (unitsUsed > 600) {
            totalCharges = 200 * 0.218 + 100 * 0.334 + 300 * 0.516 + (unitsUsed - 600) * 0.546;
        }

        return totalCharges;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }
}