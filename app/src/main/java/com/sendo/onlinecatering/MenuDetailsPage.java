package com.sendo.onlinecatering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MenuDetailsPage extends AppCompatActivity {

    ImageButton btnBack, btnEdit, btnDelete;
    ImageView ivMenuImage;
    TextView tvMenuName, tvMenuPrice, tvMenuDesc;

    MenusDB menusDB = new MenusDB(MenuDetailsPage.this);
    Menus curMenu;
    int menuId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_details_page);

        menuId = getIntent().getExtras().getInt("menuId");

        btnBack = findViewById(R.id.btn_back);
        btnEdit = findViewById(R.id.btn_edit);
        btnDelete = findViewById(R.id.btn_delete);

        ivMenuImage = findViewById(R.id.iv_menu_image);
        tvMenuName = findViewById(R.id.tv_menu_name);
        tvMenuPrice = findViewById(R.id.tv_menu_price);
        tvMenuDesc = findViewById(R.id.tv_menu_desc);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editIntent = new Intent(MenuDetailsPage.this, EditMenuPage.class);
                editIntent.putExtra("menuId", menuId);
                startActivity(editIntent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menusDB.deleteMenu(menuId);
                finish();
            }
        });

        loadMenu(menuId);
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadMenu(menuId);
    }

    void loadMenu(int menuId){

        Menus menu = menusDB.getMenu(menuId);

        byte[] foodimage = menu.getMenu_img_path();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodimage, 0, foodimage.length);
        ivMenuImage.setImageBitmap(bitmap);
        ivMenuImage.setScaleType(ImageView.ScaleType.CENTER_CROP);

        tvMenuName.setText(menu.getMenu_name());
        tvMenuPrice.setText("" + menu.getMenu_price());
        tvMenuDesc.setText(menu.getMenu_description());
    }
}