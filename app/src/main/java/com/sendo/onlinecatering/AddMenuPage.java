package com.sendo.onlinecatering;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class AddMenuPage extends AppCompatActivity {

    ImageButton btnBack, btnAddImage;
    EditText etMenuName, etMenuPrice, etMenuDesc;
    ImageView ivMenuImage;
    Button btnSubmitMenu;

    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;
    private CollectionReference menusReference;
    private FirebaseStorage mStorage;
    private StorageReference menuPicStorage;

    final int REQUEST_GALLERY = 101;
    final int REQUEST_STORAGE = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu_page);

        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        menusReference = mFirestore.collection("Menus");
        mStorage = FirebaseStorage.getInstance();
        menuPicStorage = mStorage.getReference().child("menus");

        btnBack = findViewById(R.id.btn_back);
        etMenuName = findViewById(R.id.et_menu_name);
        etMenuPrice = findViewById(R.id.et_menu_price);
        etMenuDesc = findViewById(R.id.et_menu_desc);
        ivMenuImage = findViewById(R.id.iv_menu_image);

        btnSubmitMenu = findViewById(R.id.btn_submit_menu);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSubmitMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    /*menusDB.insertMenus(
                            etMenuName.getText().toString().trim(),
                            imageviewtobyte(ivMenuImage),
                            Integer.parseInt(etMenuPrice.getText().toString()),
                            etMenuDesc.getText().toString().trim()
                    );*/

                    String menuName = etMenuName.getText().toString().trim();
                    int menuPrice = Integer.parseInt(etMenuPrice.getText().toString());
                    String menuDesc = etMenuDesc.getText().toString().trim();

                    Menus newMenu = new Menus();
                    newMenu.setMenu_name(menuName);
                    newMenu.setUser_id(mAuth.getUid());
                    newMenu.setMenu_price(menuPrice);
                    newMenu.setMenu_description(menuDesc);

                    menusReference.add(newMenu).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<DocumentReference> task) {
                            if(task.isSuccessful()){

                                DocumentReference document = task.getResult();
                                String documentId = document.getId();

                                StorageReference menuImgReference = menuPicStorage.child(documentId);
                                menuImgReference.putBytes(imageviewtobyte(ivMenuImage)).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull @NotNull Task<UploadTask.TaskSnapshot> task) {
                                        if(task.isSuccessful()){
                                            menuImgReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {
                                                    String imageUrl = uri.toString();

                                                    Map<String, Object> updateMap = new HashMap<>();
                                                    updateMap.put("menu_id", documentId);
                                                    updateMap.put("menu_img_path", imageUrl);

                                                    document.update(updateMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {
                                                            Toast.makeText(AddMenuPage.this, "Menu Added", Toast.LENGTH_SHORT).show();
                                                            finish();
                                                        }
                                                    });
                                                }
                                            });
                                        } else {

                                        }
                                    }
                                });
                            } else {
                                Log.e("MENU", "Add Menu fail:" + task.getException());
                                Toast.makeText(AddMenuPage.this, "Fail to add menu", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    public void addimage(View view) {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(4, 3)
                .setFixAspectRatio(true)
                .start(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        if(requestCode == REQUEST_GALLERY){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){}
            else{
                Toast.makeText(this, "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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