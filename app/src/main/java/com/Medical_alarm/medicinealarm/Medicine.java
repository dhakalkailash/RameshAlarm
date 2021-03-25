package com.example.medicinealarm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Medicine extends AppCompatActivity {


    Button backBtn;
    EditText userName,dName,mName,mQuantity,mFor,mTime;

    TextView qTakeBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth fAuth;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        userName   = findViewById(R.id.userName);
        dName      = findViewById(R.id.dName);
        mName      = findViewById(R.id.mName);
        mQuantity  = findViewById(R.id.mQuantity);
        mFor       = findViewById(R.id.mFor);
        mTime      = findViewById(R.id.mTime);
        qTakeBtn   = findViewById(R.id.qTake);
        backBtn    = findViewById(R.id.cancelBtn);

        fAuth = FirebaseAuth.getInstance();
        user = fAuth.getCurrentUser();

        qTakeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userName.getText().toString().isEmpty()|| dName.getText().toString().isEmpty() || mName.getText().toString().isEmpty()||
                        mQuantity.getText().toString().isEmpty()|| mFor.getText().toString().isEmpty() || mTime.getText().toString().isEmpty()){
                    Toast.makeText(Medicine.this, "One or Many Fields are empty ", Toast.LENGTH_SHORT).show();
                    return;
                }
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Users");

                // Get all the values from the text fields

                String currentuserName = userName.getText().toString();
                String doctorName = dName.getText().toString();
                String hospitalName = mName.getText().toString();
                String lastVisit = mQuantity.getText().toString();
                String hospitalFor = mFor.getText().toString();
                String visitAgain = mTime.getText().toString();

                UserMedicineClass helperClass  = new UserMedicineClass(currentuserName,doctorName,hospitalName,lastVisit,hospitalFor,visitAgain);

                reference.child("HospitalRecord/"+user.getUid()).setValue(helperClass);
                Toast.makeText(Medicine.this, " Details Added !! ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Medicine_Details.class));
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),New_Home.class));

            }
        });

    }
}