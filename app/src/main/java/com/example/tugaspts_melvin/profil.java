package com.example.tugaspts_melvin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class profil extends AppCompatActivity {

    TextView textnama;
    TextView textrole;
    ImageView profileicon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        Intent intentkeprofile = getIntent();
        String namabos=intentkeprofile.getStringExtra("namabos");
        String rolebos=intentkeprofile.getStringExtra("rolebos");
        textnama = (TextView) findViewById(R.id.namatext);
        textrole = (TextView) findViewById(R.id.namarole);
        profileicon = (ImageView) findViewById(R.id.profileicon);

        Bundle bundle = getIntent().getExtras();
        int resid = bundle.getInt("profilebos");

        textnama.setText("Nama : "+namabos);
        textrole.setText("Role : "+rolebos);
        profileicon.setImageResource(resid);
    }
}