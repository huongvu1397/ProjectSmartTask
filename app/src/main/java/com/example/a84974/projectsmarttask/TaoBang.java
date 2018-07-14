package com.example.a84974.projectsmarttask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class TaoBang extends AppCompatActivity {
    private Spinner spiner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_bang);
        spiner = findViewById(R.id.spinnertb);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.spineritem)
        );
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner.setAdapter(myAdapter);
    }
    public void trove(View view) {
        finish();
    }
    public void taomoibang(View view) {

        finish();
    }
}
