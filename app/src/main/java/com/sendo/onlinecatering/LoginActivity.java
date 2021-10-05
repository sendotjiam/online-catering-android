package com.sendo.onlinecatering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.sendo.onlinecatering.activities.MainActivity;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {
    TextView TVError;
    EditText ETEmail, ETPassword;
    Button BTNLogin, BTNRegister;
    UsersDB usersDB;
    int user_id;

    private FirebaseAuth mAuth;

    public static final String ADMIN_USERNAME = "admin";
    public static final String ADMIN_PASSWORD = "admin1234";
    public static final String ADMIN_PHONE = "01234567890";
    public static final String ADMIN_GENDER = "Male";
    public static final String ADMIN_DOB = "01-01-2001";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        ETEmail =findViewById(R.id.edittextemaillogin);
        ETPassword = findViewById(R.id.edittextpasswordlogin);
        BTNLogin = findViewById(R.id.btnlogin);
        BTNRegister = findViewById(R.id.btnregister);
        TVError = findViewById(R.id.txterrormessage);
        usersDB = new UsersDB(this);
        Users user = new Users();

        ETEmail.setText(null);
        ETPassword.setText(null);
        TVError.setText("");

        BTNRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenRegisterActivity();
            }
        });

        BTNLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = ETEmail.getText().toString();
                String password = ETPassword.getText().toString();

                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d("AUTH", "Login:Success");
                            Toast.makeText(LoginActivity.this, "Authed", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e("AUTH", "Login:failure", task.getException());
                        }
                    }
                });
            }
        });


    }

    public void openhomeactivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("USERIDLOGINTOHOME", user_id);
        ETEmail.setText(null);
        ETPassword.setText(null);
        TVError.setText("");
        startActivity(intent);
    }

    public void openadminactivity(View view) {
        Intent intent = new Intent(this, AllMenuPage.class);
        ETEmail.setText(null);
        ETPassword.setText(null);
        TVError.setText("");
        startActivity(intent);
        finish();
    }

    private void OpenRegisterActivity() {
        Intent intent = new Intent(this,RegisterActivity.class);
        ETEmail.setText(null);
        ETPassword.setText(null);
        TVError.setText("");
        startActivity(intent);
    }

    private Boolean checkusername(){
        if(ETEmail.getText().toString().isEmpty()){
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