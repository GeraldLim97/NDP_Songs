package com.example.ndp_songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ShowSong extends AppCompatActivity {

    Button btn5Stars;
    ListView lv;
    RadioGroup rdGrp;
    ArrayList<ShowSong> al;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_song);

        setTitle(getTitle().toString()+"~"+"Insert Song");

        btn5Stars = findViewById(R.id.btn5Stars);
        lv = findViewById(R.id.lv);
        rdGrp = findViewById(R.id.rdGrp);

        DBHelper dbh = new DBHelper(this);
        al = dbh.getAllSongs();
        songs = dbh.getAllSongsByStars(5);

        ArrayAdapter<ShowSong> adapter = new ArrayAdapter<>(this,  android.R.layout.simple_list_item_1, songs);
        lv.setAdapter(adapter);

        lv.setOnClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ShowSong.this, ModifySong.class);
                i.putExtra("song",al.get(position));
                startActivity(i);
            }
        });

        btn5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ShowSong.this);
                al.clear();
                al.addAll(dbh.getAllSongsbyStars(5));
                adapter.notifyDataSetChanged();
            }
        });

    }
}