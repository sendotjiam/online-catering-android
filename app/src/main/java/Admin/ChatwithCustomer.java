package Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sendo.onlinecatering.R;

public class ChatwithCustomer extends AppCompatActivity {

    ImageButton ic_back, btn_send;
    Button ic_attachfile;
    TextView message_name;
    EditText typemessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatwith_customer);
        getSupportActionBar().hide();

        ic_back = findViewById(R.id.ic_back);
        btn_send = findViewById(R.id.btn_back);
        ic_attachfile = findViewById(R.id.ic_attachfile);
        message_name = findViewById(R.id.message_name);
        typemessage = findViewById(R.id.typemessage);


    }

    public void Icon_Back(View view) {
        Intent intent = new Intent(ChatwithCustomer.this, ChatAdmin.class);
        startActivity(intent);
    }
}