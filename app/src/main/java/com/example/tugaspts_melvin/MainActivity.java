package com.example.tugaspts_melvin;

import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DataAdapter.ViewHolder.OnNoteListener {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<DataClass> userList;
    DataAdapter adapter;
    ImageView btnbofc, btnbrowser, btnshare;
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window g = getWindow();
        g.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.TYPE_STATUS_BAR);
        setContentView(R.layout.activity_home);
        btnbrowser = (ImageView) findViewById(R.id.browserbtn);
        btnbofc = (ImageView) findViewById(R.id.bofcbtn);
        btnshare = (ImageView) findViewById(R.id.sharebtn);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);

        coordinatorLayout.setPadding(0, getStatusBarHeight(), 0, 0);



        initData();
        initRecyclerView();

        btnbofc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://melvintang-games.itch.io/bastion-of-ceres"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });
        btnbrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://melvintang-games.itch.io/bastion-of-ceres"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });

        btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Download game terbaru kami pada link berikut: https://melvintang-games.itch.io/bastion-of-ceres");
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });





    }

    //input data here
    private void initData() {
        userList = new ArrayList<>();

        userList.add(new DataClass(R.drawable.avatar2, "Steward Melvin Tang", "Programmer & GameDesigner"));
        userList.add(new DataClass(R.drawable.avatar3, "Muhammad Azmiy Nurrasyid", "GameProducer & GameDesigner"));
        userList.add(new DataClass(R.drawable.avatar5, "Ayuni Fu'adah", "Visual Artist"));
        userList.add(new DataClass(R.drawable.avatar1, "Samuel Yudi Gunawan", "Game Designer & BugHunter"));
    }



    private void initRecyclerView() {
        recyclerView=findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new DataAdapter(userList, this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onNoteClick(int position, String nama, String role, int profile) {
        Intent intentkeprofile = new Intent(com.example.tugaspts_melvin.MainActivity.this, profil.class);
        intentkeprofile.putExtra("namabos", ""+nama);
        intentkeprofile.putExtra("rolebos", ""+role);
        intentkeprofile.putExtra("profilebos", profile);
        startActivity(intentkeprofile);

    }

    private int getStatusBarHeight() {
        int height;

        Resources myResources = getResources();
        int idStatusBarHeight = myResources.getIdentifier(
                "status_bar_height", "dimen", "android");
        if (idStatusBarHeight > 0) {
            height = getResources().getDimensionPixelSize(idStatusBarHeight);

        }else{
            height = 0;
        }

        return height;
    }

}