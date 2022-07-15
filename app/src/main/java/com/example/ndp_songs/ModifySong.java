package com.example.ndp_songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ModifySong extends AppCompatActivity {

    EditText etTitle;
    EditText  etSingers;
    EditText etYear;
    EditText etID;
    RadioGroup rdgrp;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    RadioButton rb4;
    RadioButton rb5;
    Button btnUpdate;
    Button btnDelete;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_song);

        etTitle = findViewById(R.id.etTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        etID = findViewById(R.id.etID);
        rdgrp = findViewById(R.id.rdGrp);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        Intent i = getIntent();
        ShowSong song = (ShowSong) i.getSerializableExtra("song");
        etID.setText(String.valueOf(song.getId()));
        etTitle.setText(song.getTitle());
        etSingers.setText(song.getSingers());
        etYear.setText(song.getYearReleased());
        int stars = song.getStars();
        if (stars == 1)
            rb1.setChecked(true);
        if (stars == 2)
            rb2.setChecked(true);
        if (stars == 3)
            rb3.setChecked(true);
        if (stars == 4)
            rb4.setChecked(true);
        if (stars == 5)
            rb5.setChecked(true);

        }

    btnUpdate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DBHelper dbh = new DBHelper(ModifySong.this);
            currentSong.setTitle((etTitle.getText().toString().trim()));
            currentSong.setSingers(etSingers.getText().toString().trim());
            int year = Integer.valueOf(etYear.getText().toString().trim());
            currentSong.setYear(year);

            int selectedRB = rdgrp.getCheckedRadioButtonId();
            RadioButton rb = (RadioButton) findViewById(selectedRB);
            currentSong.setStars(Integer.parseInt(rb.getText().toString()));
            int result = dbh.updateSong(currentSong);
            if(result < 0){
                Toast.makeText(ShowSong.this, "Song updated",Toast.LENGTH_SHORT).show();
                Intent i = new Intent();
                setResult(RESULT_OK);
                finish();
            }else{
                Toast.makeText(ShowSong.this,"Update Failed",Toast.LENGTH_SHORT).show();
            }
        }
    });

        btnDelete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DBHelper dbh = new DBHelper(ShowSong.this);
            int result = dbh.deleteSong(currentSong.get_id());
            finish();
        }
    });

        btnCancel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });
}

}