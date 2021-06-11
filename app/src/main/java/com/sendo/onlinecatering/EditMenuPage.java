package com.sendo.onlinecatering;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class EditMenuPage extends AppCompatActivity {

    ImageButton btnBack, btnChangeImage;
    ImageView ivMenuImage;
    EditText etMenuName, etMenuPrice, etMenuDesc;
    Button btnSaveMenu;

    MenusDB menusDB = new MenusDB(EditMenuPage.this);
    Menus curMenu;
    int menuId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu_page);

        menuId = getIntent().getExtras().getInt("menuId");

        btnBack = findViewById(R.id.btn_back);
        btnChangeImage = findViewById(R.id.btn_change_image);
        ivMenuImage = findViewById(R.id.iv_menu_image);
        etMenuName = findViewById(R.id.et_menuname);
        etMenuPrice = findViewById(R.id.et_menuprice);
        etMenuDesc = findViewById(R.id.et_menudesc);
        btnSaveMenu = findViewById(R.id.btn_save_menu);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnChangeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(4, 3)
                        .setFixAspectRatio(true)
                        .start(EditMenuPage.this);
            }
        });

        btnSaveMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byte[] imageBytes = imageviewtobyte(ivMenuImage);
                String name = etMenuName.getText().toString();
                int price = Integer.parseInt(etMenuPrice.getText().toString());
                String desc = etMenuDesc.getText().toString();

                menusDB.updateMenus(menuId, imageBytes, name, price, desc);

                finish();
            }
        });

        curMenu = menusDB.getMenu(menuId);

        loadMenu(curMenu);
    }

    void loadMenu(Menus menu){
        byte[] foodimage = menu.getMenu_img_path();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodimage, 0, foodimage.length);
        ivMenuImage.setImageBitmap(bitmap);
        ivMenuImage.setScaleType(ImageView.ScaleType.CENTER_CROP);

        etMenuName.setText(menu.getMenu_name());
        etMenuPrice.setText("" + menu.getMenu_price());
        etMenuDesc.setText(menu.getMenu_description());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null){

            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            Uri uri = result.getUri();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                ivMenuImage.setImageBitmap(bitmap);
                ivMenuImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    private byte[] imageviewtobyte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return bytes;
    }
}