package com.example.medicinealarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Settings extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth fAuth;
    FirebaseUser user;

    TextView rateCount, showRating, profileShow;
    ImageView UserProfile, surveyApp;
    EditText review;
    Button submit;
    RatingBar ratingBar;
    float rateValue; String temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        rateCount       = findViewById(R.id.rateCount);
        showRating      = findViewById(R.id.showRating);
        ratingBar       = findViewById(R.id.ratingbar);
        review          = findViewById(R.id.review);
        submit          = findViewById(R.id.submitBtn);
        profileShow     = findViewById(R.id.profileSetting);
        UserProfile     = findViewById(R.id.profilePic);
        surveyApp       = findViewById(R.id.survey);

        // database
        fAuth = FirebaseAuth.getInstance();
        user = fAuth.getCurrentUser();

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rateValue = ratingBar.getRating();
                if(rateValue <=1 && rateValue>0)
                    rateCount.setText("Bad "+ rateValue + "/5");
                else if(rateValue <= 2 && rateValue >1){
                    rateCount.setText("Not Bad "+ rateValue + "/5");
                }else if(rateValue <= 3 && rateValue >2){
                    rateCount.setText("Just Okay "+ rateValue + "/5");
                }else if(rateValue <= 4 && rateValue >3){
                    rateCount.setText("Noice "+ rateValue + "/5");
                }else if (rateValue <= 5 && rateValue >4){
                    rateCount.setText("Perfect, Love it "+ rateValue + "/5");
                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rateCount.getText().toString().isEmpty() || review.getText().toString().isEmpty() ){
                    Toast.makeText(Settings.this, "Please Review Again ", Toast.LENGTH_SHORT).show();
                    return;
                }
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("User");

                String reviewByUser           = review.getText().toString();
                String ratingByUser           = rateCount.getText().toString();
                ReviewHelperClass ratingClass = new ReviewHelperClass(reviewByUser,ratingByUser);
                reference.child("Review").setValue(ratingClass);
                Toast.makeText(Settings.this, " Thanks For Review!! ", Toast.LENGTH_SHORT).show();

                temp = rateCount.getText().toString();
                showRating.setText("Your Rating For this APP is:  \n"+temp +"\n" +review.getText());
                ratingBar.setRating(0);
                rateCount.setText("");
            }
        });

        // To Navigate User to the Profile setting
        UserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),User_profile.class));
            }
        });
        profileShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),User_profile.class));
            }
        });
        // To Navigate towards the survey portal
        surveyApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.youtube.com/results?search_query=nepal+Tech+hub"));
                startActivity(intent);
            }
        });
    }
}