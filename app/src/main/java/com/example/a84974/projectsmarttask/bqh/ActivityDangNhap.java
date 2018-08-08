package com.example.a84974.projectsmarttask.bqh;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.a84974.projectsmarttask.R;
import com.example.a84974.projectsmarttask.saukhidangnhap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityDangNhap extends AppCompatActivity {
    private ImageView imgAva;
    private EditText edtEmail;
    private EditText edtPassWord;
    private CheckBox cbRemember;

    private FirebaseAuth mAuth;

    private int clickCount = 0;

    String email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        imgAva = findViewById(R.id.imgAva);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassWord = findViewById(R.id.edtPassWord);
        cbRemember = findViewById(R.id.cbRemember);

        cbRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {

                } else {

                }
            }
        });

        mAuth = FirebaseAuth.getInstance();

    }
    //hieejn an passw
    public void ibtShowPW(View view) {
        clickCount += 1;
        if(clickCount != 2){
            // show password
            edtPassWord.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            edtPassWord.setSelection(edtPassWord.getText().length());
        }else{
            // hide password
            edtPassWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
            edtPassWord.setSelection(edtPassWord.getText().length());
        }
    }

    public void btSignIn(View view) {
        signIn();
        startActivity(new Intent(this, saukhidangnhap.class));
    }

    public void btSignUp(View view) {
        startActivity(new Intent(this, SignUp_Activity.class));
    }

    public void tvForgot(View view) {
        startActivity(new Intent(this, ForgotPW_Activity.class));
    }

    //kiểm tra xem phần email và mật khẩu nếu chưa nhập thì Toast lên
    public void signIn() {
        email = edtEmail.getText().toString().trim();
        password = edtPassWord.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        //đăng nhập bằng email và password vừa tạo
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(ActivityDangNhap.this, "Sign In Successful.", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(getApplicationContext(), Home_Activity.class));

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(ActivityDangNhap.this, "Wrong password or user doesn't exsist!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    /*//kiểm tra nêu email đã được verify thì cho phép đăng nhập
    private void checkEmailVerification() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Boolean emailFlag = firebaseUser.isEmailVerified();
        if (emailFlag) {

        } else {
            Toast.makeText(this, "Please verify your email first", Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();
        }
    }*/




}
