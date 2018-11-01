package com.example.a84974.projectsmarttask.fragment_main;

import android.app.Dialog;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.a84974.projectsmarttask.Module.lich;
import com.example.a84974.projectsmarttask.R;
import com.example.a84974.projectsmarttask.adapter.lichAdapter;
import com.example.a84974.projectsmarttask.database.DatabaseManager;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class FragmentLich extends Fragment {
    public CalendarView calendarView;
    private FloatingActionMenu menufab;
    private DatabaseManager manager;
    private Cursor cursor,cursor1,cursor2;
    private List<EventDay> events;
    private lichAdapter lichAdapters;
    private List<lich> liches;
    private RecyclerView rcView;
    private TextView txtNgay;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_main_lich, container, false);
        calendarView = view.findViewById(R.id.calendarview);
        menufab = view.findViewById(R.id.menu_fab);
        manager = new DatabaseManager(view.getContext());
        events = new ArrayList<>();
        Calendar c = Calendar.getInstance();

        try {
            calendarView.setDate(c.getTime());
        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }
        getDate();


        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {

                Calendar clickedDayCalendar = eventDay.getCalendar();
                long timeinmillis = eventDay.getCalendar().getTimeInMillis();
                Dialog dialog = new Dialog(view.getContext());
                dialog.setContentView(R.layout.dialog_lich);
                txtNgay = dialog.findViewById(R.id.cacthetrongngay);
                rcView = dialog.findViewById(R.id.rcLich);
                txtNgay.setText(convertDate(String.valueOf(timeinmillis), "dd/MM/yyyy"));
                liches = new ArrayList<>();
                lichAdapters = new lichAdapter(liches,view.getContext(),rcView);
                RecyclerView.LayoutManager managerLayout = new LinearLayoutManager(dialog.getContext());
                rcView.setLayoutManager(managerLayout);

                String ngay = convertDate(String.valueOf(timeinmillis), "dd");
                String thang = convertDate(String.valueOf(timeinmillis), "MM"); //08 ->8
                String nam = convertDate(String.valueOf(timeinmillis), "yyyy");

                cursor1 = manager.getDateLich(ngay,thang,nam);
                while(cursor1.moveToNext()){
                    String ngay1 = cursor1.getString(3);
                    String thang1 = cursor1.getString(4);
                    Log.d("Hexlo","thang "+thang1);
                    String nam1 = cursor1.getString(5);
                    String idthe = cursor1.getString(6);
                    String phut = cursor1.getString(1);
                    String gio = cursor1.getString(2);
                    String tenthe = "",tenlist="",tenbang="";
                    if (ngay.equals(ngay1) && thang.equals(thang1) && nam.equals(nam1)){
                        cursor2 = manager.getCardByID(idthe);
                        while(cursor2.moveToNext()){
                              tenthe = cursor2.getString(1);
                              tenlist = cursor2.getString(3);
                              tenbang = cursor2.getString(4);
                            liches.add(new lich(tenthe,tenlist,tenbang,phut,gio));
                        }
                        Log.d("HexLo",""+tenthe+" "+tenlist+" "+tenbang+" "+phut+" "+gio);
                    }
                }
                rcView.setAdapter(lichAdapters);


                dialog.show();
            }
        });

        return view;
    }

    public void getDate() {
        cursor = manager.getDate();
        while (cursor.moveToNext()) {
            String phut = cursor.getString(1);
            String gio = cursor.getString(2);
            String ngay = cursor.getString(3);
            String thang = cursor.getString(4);
            String nam = cursor.getString(5);
            String idthe = cursor.getString(6);
            Log.d("NgayThangNam", " ngay" + ngay + " thang " + thang + " nam " + nam);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(Integer.parseInt(nam), Integer.parseInt(thang) - 1, Integer.parseInt(ngay));
            events.add(new EventDay(calendar1, R.drawable.icon_red_dot));
        }
        calendarView.setEvents(events);
    }

    public static String convertDate(String dateInMilliseconds, String dateFormat) {
        return DateFormat.format(dateFormat, Long.parseLong(dateInMilliseconds)).toString();
    }
}
