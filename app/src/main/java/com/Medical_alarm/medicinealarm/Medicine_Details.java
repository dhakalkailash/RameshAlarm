package com.example.medicinealarm;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Medicine_Details extends AppCompatActivity {

    EditText nameOfMedicine, medicineQuantity,medicineInDay,numberOfMedicine,timeHour,timeMinute ;
    Button setTime, setAlarm, done;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth fAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_details);

        timeHour   = findViewById(R.id.etHour);
        timeMinute = findViewById(R.id.etMinute);
        setTime    = findViewById(R.id.btnTime);
        setAlarm   = findViewById(R.id.btnAlarm);

        // this are for the user record ****
        nameOfMedicine         = findViewById(R.id.medicineName);
        medicineQuantity       = findViewById(R.id.medicinequantity);
        medicineInDay          = findViewById(R.id.timeDay);
        numberOfMedicine       = findViewById(R.id.numberTake);
        done                   = findViewById(R.id.btndone);



        fAuth = FirebaseAuth.getInstance();
        user = fAuth.getCurrentUser();

        setTime.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              calendar = Calendar.getInstance();
              currentHour = calendar.get(Calendar.HOUR_OF_DAY);
              currentMinute = calendar.get(Calendar.MINUTE);

              timePickerDialog = new TimePickerDialog(Medicine_Details.this, new TimePickerDialog.OnTimeSetListener() {
                  @Override
                  public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                      timeHour.setText(String.format("%02d", hourOfDay));
                      timeMinute.setText(String.format("%02d", minute));

                  }
              },
                      currentHour, currentMinute, false);
                      timePickerDialog.show();
          }

      });
        // set Alarm Button will come into the play

        setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!timeHour.getText().toString().isEmpty() && !timeMinute.getText().toString().isEmpty()) {
                    Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                    intent.putExtra(AlarmClock.EXTRA_HOUR, Integer.parseInt(timeHour.getText().toString()));
                    intent.putExtra(AlarmClock.EXTRA_MINUTES, Integer.parseInt(timeMinute.getText().toString()));
                    //intent.putExtra(AlarmClock.EXTRA_MESSAGE, notificationAlarm);
                    intent.putExtra(AlarmClock.EXTRA_MESSAGE,"Take Medicine Name"+ " : " +
                            nameOfMedicine.getText().toString()+" && "+" Quantity To Take :" + numberOfMedicine.getText().toString());

                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                        } else {
                        Toast.makeText(Medicine_Details.this, "App Cannot support this action !! ", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(Medicine_Details.this, " Please choose a time !! ", Toast.LENGTH_SHORT).show();
                }
            }
      });

        // Record of the medicine to the database *****
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameOfMedicine.getText().toString().isEmpty() || medicineQuantity.getText().toString().isEmpty()||
                        medicineInDay.getText().toString().isEmpty()|| numberOfMedicine.getText().toString().isEmpty() || timeHour.getText().toString().isEmpty() ||
                        timeMinute.getText().toString().isEmpty()){
                    Toast.makeText(Medicine_Details.this, "One or Many Fields are empty ", Toast.LENGTH_SHORT).show();
                    return;
                }
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Users");

                String medicineName         = nameOfMedicine.getText().toString();
                String medicinequantity     = medicineQuantity.getText().toString();
                String timesAday            = medicineInDay.getText().toString();
                String numberOfMedicineTake = numberOfMedicine.getText().toString();
                String timeInHours          = timeHour.getText().toString();
                String timeInMinute         = timeMinute.getText().toString();

                MedicineHelperClass medicineClass = new MedicineHelperClass(medicineName, medicinequantity,timesAday,numberOfMedicineTake,timeInHours,timeInMinute);


                reference.child("MedicineRecord/"+user.getUid()).setValue(medicineClass);
                Toast.makeText(Medicine_Details.this, " Reminder Added !! ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),New_Home.class));

            }
        });

    }
}