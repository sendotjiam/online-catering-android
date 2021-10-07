package com.sendo.onlinecatering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class MenuDetailsPage extends AppCompatActivity {

    ImageButton btnBack, btnEdit, btnDelete;
    ImageView ivMenuImage;
    TextView tvMenuName, tvMenuPrice, tvMenuDesc;

    Menus curMenu;
    String menuId;

    private FirebaseFirestore mFirestore;
    private CollectionReference menusReference;
    private FirebaseStorage mStorage;
    private StorageReference menuPicStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_details_page);

        mFirestore = FirebaseFirestore.getInstance();
        menusReference = mFirestore.collection("Menus");
        mStorage = FirebaseStorage.getInstance();
        menuPicStorage = mStorage.getReference().child("menus");

        menuId = getIntent().getExtras().getString("menuId");
        curMenu = getIntent().getParcelableExtra("menu");

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
                editIntent.putExtra("menu", curMenu);
                startActivity(editIntent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                menuPicStorage.child(curMenu.getMenu_name()).delete();

                menusReference.document(curMenu.getMenu_id()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        finish();
                    }
                });
            }
        });

        loadMenu(curMenu);
    }

    @Override
    protected void onResume() {
        super.onResume();

        menusReference.document(menuId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                curMenu = documentSnapshot.toObject(Menus.class);
                loadMenu(curMenu);
            }
        });
    }

    void loadMenu(Menus menu){

        /*byte[] foodimage = menu.getMenu_img_path();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodimage, 0, foodimage.length);
        ivMenuImage.setImageBitmap(bitmap);
        ivMenuImage.setScaleType(ImageView.ScaleType.CENTER_CROP);*/

        Picasso.get().load(menu.getMenu_img_path()).into(ivMenuImage);
        ivMenuImage.setScaleType(ImageView.ScaleType.CENTER_CROP);

        tvMenuName.setText(menu.getMenu_name());
        tvMenuPrice.setText("" + menu.getMenu_price());
        tvMenuDesc.setText(menu.getMenu_description());
    }
}