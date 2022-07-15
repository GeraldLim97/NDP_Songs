package com.example.ndp_songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvSinger;
    TextView tvYear;
    TextView tvStars;
    EditText etTitle;
    EditText etSingers;
    EditText etYear;
    RadioGroup radioGroup;
    Button btnSubmit;
    Button btnShowList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(getTitle().toString()+"~"+"Insert Song");

        etTitle = findViewById(R.id.etTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        radioGroup = findViewById(R.id.rdGrp);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnShowList = findViewById(R.id.btnShowList);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                String title = etTitle.getText().toString().trim();
                String singer = etSingers.getText().toString().trim();

                String year_str = etYear.getText().toString().trim();
                int year = Integer.valueOf(year_str);
                int stars = getStars();
                dbh.insertSongs(title, singer, year_str, stars);
                dbh.close();
                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                etTitle.setText("");
                etSingers.setText("");
                etYear.setText("");
                return;
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ShowSong.class);
                startActivity(i);
            }
        });


    }
}