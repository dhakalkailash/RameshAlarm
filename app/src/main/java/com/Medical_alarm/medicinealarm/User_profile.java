package com.example.medicinealarm;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class User_profile extends AppCompatActivity {

    public static final String TAG = "TAG";
    TextView profileName, profileEmail, profilePhone,imageChangeBtn;
    ImageView profilePicture;
    Button saveBtn;
    StorageReference storageReference;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String userId;
    FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        profileName = findViewById(R.id.pName);
        profileEmail = findViewById(R.id.pEmail);
        profilePhone = findViewById(R.id.pPhone);
        saveBtn = findViewById(R.id.doneBtn);
        imageChangeBtn = findViewById(R.id.imgChange);

        profilePicture = findViewById(R.id.pic);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        user = fAuth.getCurrentUser();

        storageReference = FirebaseStorage.getInstance().getReference();

        Intent data = getIntent();
        String fullName = data.getStringExtra("fullName");
        String email = data.getStringExtra("Email");
        String phone = data.getStringExtra("phone");

       profileName.setText(fullName);
       profileEmail.setText(email);
       profilePhone.setText(phone);

        Log.d(TAG,"onCreate: " + fullName + " "+ email + " "+ phone);

        StorageReference profileRef = storageReference.child("users/"+userId+"Profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profilePicture);
            }
        });


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(profileName.getText().toString().isEmpty()|| profileEmail.getText().toString().isEmpty() || profilePhone.getText().toString().isEmpty() ){
                    Toast.makeText(User_profile.this, "One or Many Feilds are empty ", Toast.LENGTH_SHORT).show();
                    return;
                }
                String email = profileEmail.getText().toString();
                user.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        DocumentReference docRef = fStore.collection("users").document(user.getUid());
                        Map<String, Object> edited  = new HashMap<>();
                        edited.put( "email",email);
                        edited.put("fName",profileName.getText().toString());
                        edited.put("phone",profilePhone.getText().toString());
                        docRef.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(User_profile.this, "Profile sucessfully Updated ", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),New_Home.class));
                                finish();
                            }
                        });
                        Toast.makeText(User_profile.this, "EMail is changed ", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(User_profile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // Image change Button *******************
        imageChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            // Now User will open his gallery and select the picture************

                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent,1000);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            if (resultCode == Activity.RESULT_OK){
                Uri imageUri = data.getData();

                //profilePicture.setImageURI(imageUri);
                uploadImageToFirebase(imageUri);
            }
        }

    }



    private void uploadImageToFirebase(Uri imageUri) {
        //Now this will upload image to the firebase *********
        StorageReference fileRef = storageReference.child("users/"+userId+"Profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(profilePicture);

                    }
                });

                /*Toast.makeText(User_profile.this, "Image Uploaded ", Toast.LENGTH_SHORT).show();*/
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(User_profile.this, "Error!! ", Toast.LENGTH_SHORT).show();
            }
        });


    }
}