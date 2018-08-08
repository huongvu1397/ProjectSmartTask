package com.example.a84974.projectsmarttask.bqh;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a84974.projectsmarttask.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home_Activity extends AppCompatActivity {
    private EditText edtName;
    private TextView tvSave;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mRef;
    private FirebaseAuth mAuth;

    private String newUserName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);
        edtName = findViewById(R.id.edtName);
        tvSave = findViewById(R.id.tvSave);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference(mAuth.getUid()).child("Users").child("Info");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Users users = dataSnapshot.getValue(Users.class);
                edtName.setText(users.getUserName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Home_Activity.this, "Upload Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void btLogout(View view) {
        dialogSetup();
    }

    public void btChangePassword(View view) {
        startActivity(new Intent(getApplicationContext(), ChangePassword_Activity.class));
    }

    private void dialogSetup() {
        AlertDialog.Builder alert = new AlertDialog.Builder(Home_Activity.this);
        alert.setTitle("Log out");
        alert.setIcon(R.drawable.icons8_logout);
        alert.setMessage("Do you want to log out?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //thoát khổi database và về màn hình chính
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(getApplicationContext(), ActivityDangNhap.class));
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        alert.show();
    }

    //khi click vào imagebutton edit thì mới được phép sửa edittext và hiện textview Save
    public void ibtEdit(View view) {
        edtName.setClickable(true);
        edtName.setCursorVisible(true);
        edtName.setFocusable(true);
        edtName.setFocusableInTouchMode(true);
        edtName.setText("");
        tvSave.setVisibility(View.VISIBLE);
    }

    //khi ấn vào textview save thì upload lại tên user lên database đồng thời vô hiệu edittext + ẩn nút save
    public void tvSave(View view) {
        newUserName = edtName.getText().toString().trim();
        mRef = FirebaseDatabase.getInstance().getReference(mAuth.getUid());
        Users users = new Users(newUserName);
        mRef.child("Users").child("Info").setValue(users, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if (databaseError == null) {
                    Toast.makeText(getApplicationContext(), "Editted! ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Home_Activity.this, "Error!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        edtName.setClickable(false);
        edtName.setCursorVisible(false);
        edtName.setFocusable(false);
        edtName.setFocusableInTouchMode(false);
        tvSave.setVisibility(View.INVISIBLE);
    }

}
