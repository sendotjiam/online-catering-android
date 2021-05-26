package com.sendo.onlinecatering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sendo.onlinecatering.activities.CartActivity;
import com.sendo.onlinecatering.activities.MainActivity;

public class ProfilePage extends AppCompatActivity {
    TextView fullname;
    TextView gender;
    TextView phonenumber;
    TextView dob;
    TextView olshopcash;
    RadioGroup nominalgroup;
    RadioButton nominal;
    EditText confirmpassword;
    int user_id;
    int useridfromhome;
    int useridfromcart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        fullname = findViewById(R.id.et_fullname);
        gender = findViewById(R.id.et_gender);
        phonenumber = findViewById(R.id.et_phonenumber);
        dob = findViewById(R.id.et_dob);
        olshopcash = findViewById(R.id.et_cash);
        nominalgroup = findViewById(R.id.nominalgroup);
        confirmpassword = findViewById(R.id.et_confirmpassword);

        Intent profileintent = getIntent();
        useridfromhome = profileintent.getIntExtra("IDFROMHOME",0);
        useridfromcart = profileintent.getIntExtra("IDFROMCART", 0);

        user_id = 0;
        if(useridfromhome > user_id){
            user_id = useridfromhome;
        }
        if(useridfromcart > user_id){
            user_id = useridfromcart;
        }

        fullname.setText("Sendo Tjiamis");
        gender.setText("Male");
        phonenumber.setText("081232342345");
        dob.setText("5/7/2001");
        olshopcash.setText("Rp200.000,00");
    }

    public void topupgroup(View view) {
        int nominalradiogroup = nominalgroup.getCheckedRadioButtonId();
        nominal = findViewById(nominalradiogroup);
    }

    public void topup(View view) {
    }

    public void backhome(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("PROFILETOHOME", user_id);
        startActivity(intent);
        finish();
    }

    public void backcart(View view) {
        Intent intent = new Intent(getApplicationContext(), CartActivity.class);
        intent.putExtra("PROFILETOCART", user_id);
        startActivity(intent);
        finish();
    }


}