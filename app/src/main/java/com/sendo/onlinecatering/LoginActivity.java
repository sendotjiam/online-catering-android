package com.sendo.onlinecatering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sendo.onlinecatering.activities.MainActivity;

import Admin.ChatAdmin;

public class LoginActivity extends AppCompatActivity {
    TextView ETUsername, ETPassword,TVError;
    Button BTNLogin, BTNRegister;
    UsersDB usersDB;
    int user_id;

    public static final String ADMIN_USERNAME = "admin";
    public static final String ADMIN_PASSWORD = "admin1234";
    public static final String ADMIN_PHONE = "01234567890";
    public static final String ADMIN_GENDER = "Male";
    public static final String ADMIN_DOB = "01-01-2001";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ETUsername =findViewById(R.id.edittextusernamelogin);
        ETPassword = findViewById(R.id.edittextpasswordlogin);
        BTNLogin = findViewById(R.id.btnlogin);
        BTNRegister = findViewById(R.id.btnregister);
        TVError = findViewById(R.id.txterrormessage);
        usersDB = new UsersDB(this);
        Users user = new Users();

        BTNRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenRegisterActivity();
            }
        });

        BTNLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //


                if(checkusername() && checkpassword()){
                    String username = ETUsername.getText().toString();
                    String password = ETPassword.getText().toString();
                    int check = usersDB.checkUsers(username, password);
                    int checkadmin = usersDB.checkAdmin(username,password);

                    if(checkadmin == -1){
                        user.username = ADMIN_USERNAME;
                        user.password = ADMIN_PASSWORD;
                        user.phone_number = ADMIN_PHONE;
                        user.gender = ADMIN_GENDER;
                        user.dob = ADMIN_DOB;
                        usersDB.insertUsers(user);
                    }
                    else if(checkadmin !=1 && username.contentEquals("admin")  && password.contentEquals("admin1234") ){
                            openadminactivity(view);
                    }

                    if(check != -1){
                        user_id = check;
                        openhomeactivity(view);
                    }
                    else{
                        TVError.setText("Username or Password are wrong");
                    }

                }
            }
        });


    }

    public void openhomeactivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("USERIDLOGINTOHOME", user_id);
        Toast.makeText(this, user_id + "", Toast.LENGTH_SHORT).show();
        startActivity(intent);
        finish();
    }

    public void openadminactivity(View view) {
        Intent intent = new Intent(this, AllMenuPage.class);
        ETUsername.setText(null);
        ETPassword.setText(null);
        startActivity(intent);
    }

    private void OpenRegisterActivity() {
        Intent intent = new Intent(this,RegisterActivity.class);
        ETUsername.setText(null);
        ETPassword.setText(null);
        startActivity(intent);
    }

    private Boolean checkusername(){
        if(ETUsername.getText().toString().isEmpty()){
            TVError.setText("Username cannot be empty");
            return false;
        }
        else{
            return true;
        }
    }

    private Boolean checkpassword(){
        if(ETPassword.getText().toString().isEmpty()){
            TVError.setText("Password cannot be empty");
            return false;
        }
        else{
            return true;
        }
    }
}