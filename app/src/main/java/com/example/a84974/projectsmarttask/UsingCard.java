package com.example.a84974.projectsmarttask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class UsingCard extends AppCompatActivity {
    Toolbar tb;
    TextView uctitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_using_card);
        tb = findViewById(R.id.tbTCBang);
        tb.setTitle("");
        uctitle = findViewById(R.id.ucTitle);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        setSupportActionBar(tb);
        uctitle.setText(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu_deepinside_tcbang,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.moveCard:
                Toast.makeText(this, "Move Card", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.deleteCard:
                Toast.makeText(this, "Delete Card", Toast.LENGTH_SHORT).show();
                return true;
            default:
                break;
        }
        return true;
    }
}
