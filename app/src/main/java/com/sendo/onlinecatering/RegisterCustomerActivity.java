package com.sendo.onlinecatering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sendo.onlinecatering.activities.MainActivity;

import java.util.HashMap;
import java.util.Map;

public class RegisterCustomerActivity extends AppCompatActivity {

    private EditText ETName, ETTableNumber;
    private RadioGroup TypeRadioGroup;
    private RadioButton RadioDineIn, RadioTakeAway;
    private TextView TVErrorMessage;
    private Button BTNRegCus;

    private boolean dineIn;

    private FirebaseFirestore mFirestore;
    private CollectionReference ordersReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_customer);

        mFirestore = FirebaseFirestore.getInstance();
        ordersReference = mFirestore.collection("Orders");

        ETName = findViewById(R.id.edittextname);
        ETTableNumber = findViewById(R.id.edittexttablenum);
        TypeRadioGroup = findViewById(R.id.radiogroup);
        RadioDineIn = findViewById(R.id.RadioDineIn);
        RadioTakeAway = findViewById(R.id.RadioTakeAway);
        TVErrorMessage = findViewById(R.id.tv_error_message);
        BTNRegCus = findViewById(R.id.regbtnregcus);

        TypeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                dineIn = i == R.id.RadioDineIn;
            }
        });

        BTNRegCus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ETName.getText().toString();
                int tableNum = Integer.parseInt(ETTableNumber.getText().toString());

                if (RadioDineIn.isChecked() || RadioTakeAway.isChecked()){
                    if (RadioDineIn.isChecked()){
                        if (tableNum == 0){
                            TVErrorMessage.setText("Please fill your table number");
                            return;
                        }
                    }

                    if(name.isEmpty()){
                        TVErrorMessage.setText("Please fill your name");
                        return;
                    }

                    Map<String, Object> customerMap = new HashMap<>();
                    customerMap.put("name", name);
                    customerMap.put("tableNumber", tableNum);
                    customerMap.put("DineIn", RadioDineIn.isChecked());

                    ordersReference.add(customerMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Intent mainIntent = new Intent(RegisterCustomerActivity.this, MainActivity.class);
                            mainIntent.putExtra("OrderId", documentReference.getId());
                            startActivity(mainIntent);
                        }
                    });
                } else {
                    TVErrorMessage.setText("Please select order type");
                }
            }
        });
    }
}