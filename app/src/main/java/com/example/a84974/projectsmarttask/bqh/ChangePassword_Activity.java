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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChangePassword_Activity extends AppCompatActivity {
    private EditText edtCurrentPassword, edtNewPassword, edtConfirmNewPW;

    // tạo 1 biến oldPassword để gán giá trị của password cũ vào
    private String oldPassword = "";
    private String currentPassword, newPassword, confirmNewPassword;

    private FirebaseDatabase mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_);
        edtCurrentPassword = findViewById(R.id.edtCurrentPW);
        edtNewPassword = findViewById(R.id.edtNewPW);
        edtConfirmNewPW = findViewById(R.id.edtConfirmNewPW);
    }
    public void btCancel(View view) {
        startActivity(new Intent(getApplicationContext(), Home_Activity.class));
    }

    public void btChange(View view) {
        currentPassword = edtCurrentPassword.getText().toString().trim();
        newPassword = edtNewPassword.getText().toString().trim();
        confirmNewPassword = edtConfirmNewPW.getText().toString().trim();

        user = FirebaseAuth.getInstance().getCurrentUser();
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = mDatabase.getReference(mAuth.getUid()).child("Users").child("Password");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Password password = dataSnapshot.getValue(Password.class);

                //gán giá trị của PW cũ vào biến old password
                oldPassword = password.getPassword();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ChangePassword_Activity.this, "Upload Error", Toast.LENGTH_SHORT).show();
            }
        });

        if (TextUtils.isEmpty(currentPassword)) {
            Toast.makeText(getApplicationContext(), "Enter your current password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(newPassword)) {
            Toast.makeText(getApplicationContext(), "Enter your new password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(confirmNewPassword)) {
            Toast.makeText(getApplicationContext(), "Confirm your new password!", Toast.LENGTH_SHORT).show();
            return;
        }

        //kiểm tra xem password trên database có trùng với password nhập vào hay không
        if (oldPassword.equals(currentPassword)) {
            //kiểm tra passwôrd cũ và mới có trùng nhau hay không
            if (currentPassword.equals(newPassword)) {
                Toast.makeText(this, "Please enter diffirent password", Toast.LENGTH_SHORT).show();
            } else {
                //kiểm tra xác thực password có giống newPW hay không
                if (newPassword.equals(confirmNewPassword)) {
                    user.updatePassword(edtNewPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ChangePassword_Activity.this, "Password changed", Toast.LENGTH_SHORT).show();
                                updatePassword();
                                edtCurrentPassword.setText("");
                                edtNewPassword.setText("");
                                edtConfirmNewPW.setText("");
                                startActivity(new Intent(getApplicationContext(), Home_Activity.class));

                            } else {
                                Toast.makeText(ChangePassword_Activity.this, "Your password could not be changed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(this, "Your new password is not synchronized", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Toast.makeText(this, "Your old password is not right", Toast.LENGTH_SHORT).show();
        }
    }


    //update PW mới đổi lên database để khi lấy về gán vào old PW
    private void updatePassword() {
        Password password1 = new Password(newPassword);
        mRef = FirebaseDatabase.getInstance().getReference(mAuth.getUid());
        mRef.child("Users").child("Password").setValue(password1, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if (databaseError == null) {
                } else {

                }
            }
        });
    }

}
