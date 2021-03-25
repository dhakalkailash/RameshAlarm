package com.example.medicinealarm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class bmi extends AppCompatActivity {

    Button CalculatBtn;
    EditText heightft, weight;
    TextView finalTxt;
    EditText heightinch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

      heightft  = (EditText)findViewById(R.id.pName);
      weight  = (EditText)findViewById(R.id.weight);
      finalTxt  = (TextView) findViewById(R.id.finalTxt);
      CalculatBtn  = (Button) findViewById(R.id.resltBtn);
      heightinch = (EditText)findViewById(R.id.heightinch);

        CalculatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               calculateBMI();
            }

            private void calculateBMI() {
                String heightStr = heightft.getText().toString();
                String heightinchStr = heightinch.getText().toString();
                String weightStr = weight.getText().toString();

                if(heightStr !=null && !"".equals(heightStr) && weightStr !=null && !"".equals(weightStr)){
                    double heightftValue = Float.parseFloat(heightStr) / 3.281;
                    double heightinchValue = Float.parseFloat(heightinchStr)/ 39.37;
                    double heightValue    =  (heightinchValue + heightftValue);
                    double weightValue = Float.parseFloat(weightStr) / 2.205;

                    float bmi = (float) (weightValue / (heightValue * heightValue));
                    
                    displayBMI(bmi);
                }
            }

            private void displayBMI(float bmi) {
                String bmiLabel = "";

                if(Float.compare(bmi, 15f) <=0){
                    bmiLabel = "Bruh Eat Heavy Food, You Are Underweight";
                }else if(Float.compare(bmi,15f )>0 && Float.compare(bmi, 16f)<=0){
                    bmiLabel = "Oh God, Please Eat, You are Underweight";
                }else if(Float.compare(bmi,16f )>0 && Float.compare(bmi, 18.5f)<=0){
                    bmiLabel = "You are Underweight";
                }else if(Float.compare(bmi,18.5f )>0 && Float.compare(bmi, 25f)<=0){
                    bmiLabel = "You Are in Good Shape";
                }else if(Float.compare(bmi,25f )>0 && Float.compare(bmi, 30f)<=0){
                    bmiLabel = "Please WatchOut While Eatting, You are OverWeight";
                }else if(Float.compare(bmi,30f )>0 && Float.compare(bmi, 35f)<=0){
                    bmiLabel = "Oh God, Its Time To WorkOut, You Are OverWeight";
                }else if(Float.compare(bmi,35f )>0 && Float.compare(bmi, 40f)<=0){
                    bmiLabel = "Damn Bruh!! You Better don't eat, Extremely OverWeight";
                }else{
                    bmiLabel = "Machine Broke !!! Machine Broke !! Get Out of here and Start WorkOut !!";
                }
                String finalbmiLabel = (bmi+ System.getProperty("line.separator") +bmiLabel);
                finalTxt.setText(finalbmiLabel);


            }
        });

    }
}