package com.example.a84974.projectsmarttask.fragment_main;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a84974.projectsmarttask.R;
import com.example.a84974.projectsmarttask.pvc.Database;
import com.example.a84974.projectsmarttask.pvc.Image;
import com.example.a84974.projectsmarttask.pvc.ImageAdapter;

import java.util.ArrayList;

public class FragmentAnh extends Fragment {
    ListView lvImage;
    ArrayList<Image> arrayImage;
    ImageAdapter adapter;
    public static Database database;
    private TextView txtAnh;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_main_anh,container,false);
        lvImage = view.findViewById(R.id.lvImage);
        arrayImage = new ArrayList<>();
        txtAnh = view.findViewById(R.id.txtAnh);
        adapter =new ImageAdapter(view.getContext(), R.layout.item_image,arrayImage);
        lvImage.setAdapter(adapter);
        database =new Database(view.getContext(),"image.sql",null,1);
        database.QueryData("CREATE TABLE IF NOT EXISTS Anh(Id INTEGER PRIMARY KEY AUTOINCREMENT, Title VARCHAR(150), Description VARCHAR(250),Image BLOB )");

        Cursor cursor = database.GetData("SELECT * FROM Anh");
        while (cursor.moveToNext()){
            arrayImage.add(new Image(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getBlob(3)
            ));
        }
        if(arrayImage!=null){
            txtAnh.setVisibility(View.INVISIBLE);
        }
        adapter.notifyDataSetChanged();


        return view;
    }
}
