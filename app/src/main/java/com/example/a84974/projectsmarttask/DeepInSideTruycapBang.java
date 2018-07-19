package com.example.a84974.projectsmarttask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class DeepInSideTruycapBang extends AppCompatActivity {
    Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_in_side_truycap_bang);
        tb = findViewById(R.id.tbTCBang);
        tb.setTitle("");
        setSupportActionBar(tb);

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
