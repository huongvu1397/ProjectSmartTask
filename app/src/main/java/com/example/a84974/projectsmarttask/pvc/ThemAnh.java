package com.example.a84974.projectsmarttask.pvc;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.a84974.projectsmarttask.R;
import com.example.a84974.projectsmarttask.fragment_main.FragmentAnh;
import com.example.a84974.projectsmarttask.saukhidangnhap;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ThemAnh extends AppCompatActivity {
    TextInputLayout textInputLTitle, textInputDescription;
    Button chonanh,save,delete;
    EditText edTitle, edDescription;
    ImageView imageAnh;
    int REQUEST_CODE_FOLDER = 456;
    int REQUEST_CODE_CAMERA = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_anh);
        chonanh = findViewById(R.id.btChonAnh);
        save = findViewById(R.id.btSave);
        delete = findViewById(R.id.btDelete);
        edTitle = findViewById(R.id.edTitle);
        edDescription = findViewById(R.id.edDescription);
        imageAnh = findViewById(R.id.imageanh);
        textInputLTitle = findViewById(R.id.textInputTitle);
        textInputDescription=findViewById(R.id.textInputDescription);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edTitle.setText("");
                edDescription.setText("");
            }
        });
        //      lay image ra
        chonanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // them anh bang cammera
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent,REQUEST_CODE_CAMERA);
                // them anh bang folder
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");  // lay loai hinh anh
                startActivityForResult(intent,REQUEST_CODE_FOLDER);

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // chuyen data imageview -> byte[]
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageAnh.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                byte[] hinhAnh = byteArray.toByteArray();
                String Title = edTitle.getText().toString().trim();
                String Description = edDescription.getText().toString().trim();
                if (TextUtils.isEmpty(Title)&&TextUtils.isEmpty(Description)){
                    Toast.makeText(ThemAnh.this, "nhap them thong tin", Toast.LENGTH_SHORT).show();
                }else {
                    FragmentAnh.database.INSERT_IMAGE(Title,Description,hinhAnh);
                    Toast.makeText(ThemAnh.this, "them thanh cong", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ThemAnh.this,saukhidangnhap.class));
                    finish();
                }

            }
        });


    }

    // gan image vao
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // lay anh tu camera gan vao
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data !=null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageAnh.setImageBitmap(bitmap);
        }
        // lay anh tu folder gan vao
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData(); // duong dan lay du lieu
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageAnh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}
