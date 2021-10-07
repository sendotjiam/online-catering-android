package com.sendo.onlinecatering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sendo.onlinecatering.activities.MainActivity;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    TextView TVerror, BTNLogin;
    EditText ETUsername, ETPassword, ETPhone, ETConfirmationPass, ETemail;
    Button BTNRegister, BTNDateBirth;
    RadioGroup radiogroupgender;
    RadioButton radioButtongender;
    CheckBox checkagreement;
    UsersDB usersDB;
    DatePickerDialog datebirth;
    String date= null;

    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;
    private CollectionReference usersReference;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        usersReference = mFirestore.collection("Users");

        ETUsername = findViewById(R.id.edittextusername);
        ETemail = findViewById(R.id.edittextemail);
        ETPassword = findViewById(R.id.edittextpassword);
        ETPhone = findViewById(R.id.edittextphone);
        ETConfirmationPass = findViewById(R.id.edittextrepassword);
        BTNRegister = findViewById(R.id.regbtnreg);
        BTNDateBirth = findViewById(R.id.datebirthbutton);
        BTNLogin = findViewById(R.id.regbtnlogin);
        radiogroupgender = findViewById(R.id.radiogroup);
        checkagreement = findViewById(R.id.checkboxagreement);
        TVerror = findViewById(R.id.texterrors);
        usersDB = new UsersDB(this);
        initDatePicker();

        BTNLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenLoginActivity();
            }
        });

        BTNRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkusername() && checkpassword() && checkPhone() && checkconfirmationpass() && checkGender() && checkDateOfBirth() && checkTerms()){

                    String username = ETUsername.getText().toString();
                    String email = ETemail.getText().toString();
                    String password = ETPassword.getText().toString();
                    String gender = radioButtongender.getText().toString();
                    String phone = ETPhone.getText().toString();

                    Users user = new Users();
                    user.username = username;
                    user.email = email;
                    user.phone_number = phone;
                    user.gender = gender;
                    user.dob = date;

                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("AUTH", "createUserWithEmail:success");
                                FirebaseUser firebaseUser = mAuth.getCurrentUser();

                                usersReference.document(firebaseUser.getUid()).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(RegisterActivity.this, "REGISTERED", Toast.LENGTH_SHORT).show();

                                            Intent modeIntent = new Intent(RegisterActivity.this, ChooseModeActivity.class);
                                            startActivity(modeIntent);
                                        } else {
                                            Log.e("AUTH", "registerUsertoFirestore:failure", task.getException());
                                            Toast.makeText(RegisterActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.e("AUTH", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(RegisterActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(RegisterActivity.this, "Form error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        radiogroupgender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                radioButtongender = findViewById(id);
            }
        });

    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String monthstring = null;
                if(month == 1){
                    monthstring = "January";
                }
                if(month == 2){
                    monthstring = "February";
                }
                if(month == 3){
                    monthstring = "March";
                }
                if(month == 4){
                    monthstring = "April";
                }
                if(month == 5){
                    monthstring = "May";
                }
                if(month == 6){
                    monthstring = "June";
                }
                if(month == 7){
                    monthstring = "July";
                }
                if(month == 8){
                    monthstring = "October";
                }
                if(month == 9){
                    monthstring = "September";
                }
                if(month == 10){
                    monthstring = "October";
                }
                if(month == 11){
                    monthstring = "November";
                }
                if(month == 12){
                    monthstring = "December";
                }
                date = dayOfMonth + " - " + monthstring + " - " +year;
                BTNDateBirth.setText(null);
                BTNDateBirth.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datebirth = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datebirth.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private void OpenLoginActivity() {
        finish();
    }


    private Boolean checkusername(){
        String usertext = ETUsername.getText().toString();
        int usernamelength = ETUsername.getText().toString().length();
        if(usernamelength >=6 && usernamelength<=12 ){
            return true;
        }
        else if(usertext.isEmpty()){
            TVerror.setText("Username cannot be empty");
            return false;
        }
        else{
            TVerror.setText("Username must be between 6 and 12 characters");
            return false;
        }
    }

    private Boolean checkpassword(){
        String passtext = ETPassword.getText().toString();
        int passLength = ETPassword.getText().toString().length();
        boolean checknumeric = false , checkalpha = false;
        char[] passtextarray = new char[passLength];
        for(int i=0 ; i<passLength ; i++){
            passtextarray[i] = passtext.charAt(i);
        }

        if(passtext.isEmpty()){
            TVerror.setText("Password cannot be empty");
            return false;
        }
        if(passLength >= 8){
            for (char cekchar:passtextarray) {
                if(checknumeric && checkalpha) {
                    return true;
                }
                else{
                    if (cekchar >= '0' && cekchar <= '9') {
                        checknumeric = true;
                    }
                    if (cekchar >= 'A' && cekchar <= 'Z' || cekchar >= 'a' && cekchar <= 'z') {
                        checkalpha = true;

                    }
                }
            }

            if(!checknumeric || !checkalpha){
                TVerror.setText("Password must contain both characters and numbers");
                return false;
            }
        }
        else if (passLength < 8){
            TVerror.setText("Password must be more than 8 characters");
            return false;
        }
        else{
            TVerror.setText("Password must be alphanumeric");
            return false;
        }
        return false;
    }

    private boolean checkconfirmationpass(){
        String passtext = ETPassword.getText().toString();
        String confirmpasstext = ETConfirmationPass.getText().toString();
        if(passtext.equals(confirmpasstext)){
            return true;
        }
        else if(passtext.isEmpty()){
            TVerror.setText("Confirmation Password cannot be empty");
            return false;
        }
        else {
            TVerror.setText("Confirmation Password must be the same with Password");
            return false;
        }
    }

    private boolean checkDateOfBirth()
    {
        if (date == null)
        {
            TVerror.setText("DOB cannot be blank");
            return false;
        }
        else{
            return true;
        }
    }

    private boolean checkPhone()
    {
        String PhoneNumber = ETPhone.getText().toString();
        int PhoneNumberlength = ETPhone.getText().toString().length();
        boolean phonenum = false, phonedigit =false;
        char[] PhoneNumberArray = new char[PhoneNumberlength];
        for(int i=0 ; i<PhoneNumberlength ; i++){
            PhoneNumberArray[i] = PhoneNumber.charAt(i);
        }
        if(PhoneNumber.isEmpty()){
            TVerror.setText("Phone Number cannot be emtpy");
            return false;
        }
        if(PhoneNumberlength >=10 && PhoneNumberlength <=12){
            phonedigit = true;
            for (char cekphone:PhoneNumberArray) {
                if(phonenum) {
                    TVerror.setText("Phone Number must contain only numbers");
                    return false;
                }
                else{
                    if (cekphone >= '0' && cekphone <= '9') {
                        phonenum = true;
                        return true;
                    }
                }
            }
        }
        else{
            TVerror.setText("Phone Number must be between 10 to 12 digits");
            return false;
        }
        return false;
    }

    private boolean checkTerms()
    {
        if (checkagreement.isChecked()){
            return true;
        }
        else{
            TVerror.setText("User must agree the terms and conditions");
            return false;
        }

    }

    private boolean checkGender()
    {
        if (radioButtongender == null){
            TVerror.setText("Gender must be selected");
            return false;
        }
        else{
            return true;
        }
    }

    public void opendatepicker(View view) {
        datebirth.show();
    }
}