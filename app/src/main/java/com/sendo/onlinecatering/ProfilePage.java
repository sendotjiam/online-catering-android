package com.sendo.onlinecatering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sendo.onlinecatering.activities.CartActivity;
import com.sendo.onlinecatering.activities.MainActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import Admin.ChatAdmin;
import Admin.CustomerOrder;

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
    UsersDB usersDB;
    Users users;
    NumberFormat formatter = new DecimalFormat("#,###");

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

         /*
        INGAT SEMUA 1 GANTI JADI user_id
         */

        usersDB = new UsersDB(this);

        Intent profileintent = getIntent();
        useridfromhome = profileintent.getIntExtra("IDFROMHOME", 0);
        useridfromcart = profileintent.getIntExtra("IDFROMCART", 0);

        user_id = 0;
        if (useridfromhome > user_id) {
            user_id = useridfromhome;
        }
        if (useridfromcart > user_id) {
            user_id = useridfromcart;
        }

        navbar();

        //1nya ingat ganti jdi user_id
        users = usersDB.getUser(1);

        fullname.setText(users.getUsername());
        gender.setText(users.getGender());
        phonenumber.setText(users.getPhone());
        dob.setText(users.getDateOfBirth());
        String formattemplate = formatter.format(users.getWallet());
        olshopcash.setText("Rp." + formattemplate + ",00");
    }

    public void topupgroup(View view) {
        int nominalradiogroup = nominalgroup.getCheckedRadioButtonId();
        nominal = findViewById(nominalradiogroup);
    }

    public void topup(View view) {
        int check = 0;
        String checkpassword = confirmpassword.getText().toString();
        if(nominal == null || nominal.equals("")){
            Toast.makeText(this,"Top Up Nominal must be selected!", Toast.LENGTH_SHORT).show();
        }
        else{
            check++;
        }
        if(checkpassword == null || checkpassword.equals("")){
            Toast.makeText(this,"Password must be filled!", Toast.LENGTH_SHORT).show();
        }
        else if(!checkpassword.equals(users.getPassword())){
            Toast.makeText(this,"Input the right password!",Toast.LENGTH_SHORT).show();
        }
        else {
            check++;
        }

        if(check == 2) {
            // ingat ganti id 1 nya jadi user_id juga;
            Toast.makeText(this, "Top Up Successfully", Toast.LENGTH_SHORT).show();
            if (nominal.getText().toString().equals("Rp.1.000.000,00")) {
                usersDB.updateNominal(users, 1, 1000000);
            } else{
                usersDB.updateNominal(users, 1, 2000000);
            }
            Intent intent = new Intent(getApplicationContext(), CartActivity.class);
            intent.putExtra("PROFILETOCART", user_id);
            startActivity(intent);
            finish();
        }
    }

    void navbar() {
        BottomNavigationView nav_klient = findViewById(R.id.navbar_klient);

        nav_klient.setSelectedItemId(R.id.menu_profiles);
        nav_klient.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    Intent intent = new Intent(getApplicationContext(), com.sendo.onlinecatering.activities.MainActivity.class);
                    intent.putExtra("PROFILETOHOME", user_id);
                    startActivity(intent);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.menu_cart) {
                    Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                    intent.putExtra("PROFILETOCART", user_id);
                    startActivity(intent);
                    finish();
                    return true;
                } else {
                    return true;
                }
            }
        });
    }

}