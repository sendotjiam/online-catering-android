package com.sendo.onlinecatering;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class EditMenuPage extends AppCompatActivity {

    ImageButton btnBack, btnChangeImage;
    ImageView ivMenuImage;
    EditText etMenuName, etMenuPrice, etMenuDesc;
    Button btnSaveMenu;
    boolean editImg = false;

    Menus curMenu;

    private FirebaseFirestore mFirestore;
    private CollectionReference menusReference;
    private FirebaseStorage mStorage;
    private StorageReference menuPicStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu_page);

        curMenu = getIntent().getExtras().getParcelable("menu");

        mFirestore = FirebaseFirestore.getInstance();
        menusReference = mFirestore.collection("Menus");
        mStorage = FirebaseStorage.getInstance();
        menuPicStorage = mStorage.getReference().child("menus");

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
                editImg = true;

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

                DocumentReference documentReference = menusReference.document(curMenu.getMenu_id());

                if (editImg){
                    StorageReference menuImgReference = menuPicStorage.child(curMenu.getMenu_id());
                    menuImgReference.putBytes(imageviewtobyte(ivMenuImage)).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()){
                                menuImgReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull @NotNull Task<Uri> task) {
                                        if (task.isSuccessful()){
                                            String imageUrl = task.getResult().toString();

                                            documentReference.update("menu_img_path", imageUrl);
                                        } else {
                                            Toast.makeText(EditMenuPage.this, "Error updating image", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(EditMenuPage.this, "Error updating image", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                Map<String, Object> menuMap = new HashMap<>();
                menuMap.put("menu_name", etMenuName.getText().toString());
                menuMap.put("menu_price", Integer.parseInt(etMenuPrice.getText().toString()));
                menuMap.put("menu_description", etMenuDesc.getText().toString());

                documentReference.update(menuMap);
            }
        });

        loadMenu(curMenu);
    }

    void loadMenu(Menus menu){
        /*byte[] foodimage = menu.getMenu_img_path();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodimage, 0, foodimage.length);
        ivMenuImage.setImageBitmap(bitmap);
        ivMenuImage.setScaleType(ImageView.ScaleType.CENTER_CROP);*/

        Picasso.get().load(menu.getMenu_img_path()).into(ivMenuImage);
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