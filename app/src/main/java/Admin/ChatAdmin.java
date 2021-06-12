package Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sendo.onlinecatering.AllMenuPage;
import com.sendo.onlinecatering.R;

import java.util.ArrayList;

public class ChatAdmin extends AppCompatActivity {
    BottomNavigationView nav_admin;

    Button btn_search;
    TextView et_search;
    RecyclerView chatlist;
    ArrayList<ChatAdminList> list = new ArrayList<>();
    ChatAdminAdapter chatAdminAdapter;

//    public static Boolean check(String name, String searchname)
//    {
//        for (int i = 0; i < name.length(); i++)
//        {
//            char test = name.charAt(i);
//            if (searchname.equals(test)) {
//                return Boolean.FALSE;
//            }
//        }
//        return Boolean.TRUE;
//    }

    public void filter(String searchname){
        ArrayList<ChatAdminList> searchlist = new ArrayList<>();
        for(ChatAdminList item : list){
            if(item.getName().toLowerCase().contains(searchname.toLowerCase())){
                searchlist.add(item);
            }
        }
        chatAdminAdapter.filterlist(searchlist);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_admin);

        getSupportActionBar().hide();

        btn_search = findViewById(R.id.btn_search);
        et_search = findViewById(R.id.et_search);
        chatlist = findViewById(R.id.chatlist);

//        navigation_bar();

        ChatAdminList list1 = new ChatAdminList("test1", "Andy", "selamat siang !", "16.30");
        list.add(list1);
        ChatAdminList list2 = new ChatAdminList("test2", "Sendo", "Apakah ayamnya bisa dipo...", "15.00");
        list.add(list2);
        ChatAdminList list3 = new ChatAdminList("test3", "Owen", "Apakah ikannya bisa di pis....", "13.30");
        list.add(list3);
        ChatAdminList list4 = new ChatAdminList("test4", "Hendry", "Terima kasih", "12.20");
        list.add(list4);
        ChatAdminList list5 = new ChatAdminList("test5", "Kenneth", "Mohon disiapkan secepatn...", "10.50");
        list.add(list5);

        chatAdminAdapter = new ChatAdminAdapter();
        chatAdminAdapter.setArrayListdata(list);
        chatlist.setAdapter(chatAdminAdapter);
        chatlist.setLayoutManager(new LinearLayoutManager(this));

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });


//        btn_search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (et_search.getText().toString().isEmpty()) {
//                    Toast.makeText(ChatAdmin.this, "Please type a name", Toast.LENGTH_SHORT).show();
//                } else {
//                    for(int i = 0 ; i < list.size(); i++){
//                        if(list.get(i).getName())
//                    }
//                }
//            }
//        });

    }

    void navigation_bar() {
        BottomNavigationView nav_admin = findViewById(R.id.nav_admin);

//        nav_admin.setSelectedItemId(R.id.menu_chat);
        nav_admin.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {
                if (item.getItemId() == R.id.menu_menus) {
                    Intent intent = new Intent(ChatAdmin.this, AllMenuPage.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.menu_order) {
                    Intent intent = new Intent(ChatAdmin.this, CustomerOrder.class);
                    startActivity(intent);
                    return true;
                } else {
                    Intent intent = new Intent(ChatAdmin.this, CustomerOrder.class);
                    startActivity(intent);
                    return true;
                }
            }
        });
    }
}