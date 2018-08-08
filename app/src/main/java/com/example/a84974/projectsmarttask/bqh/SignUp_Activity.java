package com.example.a84974.projectsmarttask.bqh;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a84974.projectsmarttask.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp_Activity extends AppCompatActivity {
    private EditText edtUserName;
    private EditText edtEmailAddress;
    private EditText edtPassWord1;
    private EditText edtConfirmPW;

    private DatabaseReference mRef;

    private FirebaseAuth mAuth;

    String userName, emailAD, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);

        edtUserName = findViewById(R.id.edtUserName);
        edtEmailAddress = findViewById(R.id.edtEmailAddress);
        edtPassWord1 = findViewById(R.id.edtPassWord1);
        edtConfirmPW = findViewById(R.id.edtConfirmPW);

        mAuth = FirebaseAuth.getInstance();

    }

    public void btCreateAccount(View view) {
        SignUp();
    }

    public void SignUp() {
        userName = edtUserName.getText().toString().trim();

        emailAD = edtEmailAddress.getText().toString().trim();

        password = edtPassWord1.getText().toString().trim();
        String cfpassword = edtConfirmPW.getText().toString().trim();

        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(getApplicationContext(), "Enter your user name!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(emailAD)) {
            Toast.makeText(getApplicationContext(), "Enter email!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(cfpassword)) {
            Toast.makeText(getApplicationContext(), "Please confirm your password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password is too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }
        //xác thực password nếu trùng với pass ở trên thì vứt hết data lên database

        if (password.equals(cfpassword)) {
            mAuth.createUserWithEmailAndPassword(emailAD, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                sendUserData();
                                // Sign in success, update UI with the signed-in user's information
                                startActivity(new Intent(getApplicationContext(), ActivityDangNhap.class));
                                sendEmailVerification();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(getApplicationContext(), "Sign up failse! ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "Your pass word is not synchronized, please re-enter! ", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    //verify email and upload user's data to database
    private void sendEmailVerification() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignUp_Activity.this, "Check your email for verification", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    //gửi thông tin user lên database
    private void sendUserData(){
        mRef = FirebaseDatabase.getInstance().getReference(mAuth.getUid());
        Users users = new Users(userName);
        Password password1 = new Password(password);
        mRef.child("Users").child("Password").setValue(password1);
        mRef.child("Users").child("Info").setValue(users, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if (databaseError == null) {
                    Toast.makeText(getApplicationContext(), "Sign up successful! ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignUp_Activity.this, "Error!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void tvSignIn(View view) {
        startActivity(new Intent(this, ActivityDangNhap.class));
    }

}
