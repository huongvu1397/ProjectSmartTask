package com.example.a84974.projectsmarttask.bqh;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a84974.projectsmarttask.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPW_Activity extends AppCompatActivity {
    private EditText edtEmail2Change;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pw_);
        edtEmail2Change = findViewById(R.id.edtEmail2Change);

        firebaseAuth = FirebaseAuth.getInstance();
    }
    public void btBack(View view) {
        startActivity(new Intent(this, ActivityDangNhap.class));
    }

    public void btSubmit(View view) {
        String email = edtEmail2Change.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(ForgotPW_Activity.this, "Enter email!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(ForgotPW_Activity.this, "Password reset email was sent to your email address", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(ForgotPW_Activity.this, ActivityDangNhap.class));
                    }else{
                        Toast.makeText(ForgotPW_Activity.this, "Error in sending password reset email", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }
}
