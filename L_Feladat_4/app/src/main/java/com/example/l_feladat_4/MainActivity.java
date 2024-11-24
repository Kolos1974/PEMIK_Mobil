package com.example.l_feladat_4;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Switch switch1;
    SeekBar seekBar;

    TextView seekBarTv, mintaszoveg;

    ImageView kep;
    RadioGroup rg1;
    RadioButton rbKutya, rbMacska;

    EditText datumEt;

    Button datumBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        switch1 = findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    switch1.setText("Bekapcsolva");
                    switch1.setBackgroundColor(Color.rgb(255,214,0));
                } else {
                    switch1.setText("Kikapcsolva");
                    switch1.setBackgroundColor(Color.rgb(200,200,200));
                }
            }
        });

        seekBar = findViewById(R.id.seekBar);
        seekBarTv = findViewById(R.id.seekBarTv);
        mintaszoveg = findViewById(R.id.mintaszoveg);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                seekBarTv.setText(progress+"sp");
                mintaszoveg.setTextSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekBarTv.setBackgroundColor(Color.RED);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBarTv.setBackgroundColor(Color.GREEN);
            }
        });

        kep = findViewById(R.id.kep);
        rg1 = findViewById(R.id.rg1);
        rbKutya = findViewById(R.id.rbKutya);
        rbMacska = findViewById(R.id.rbMacska);
        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == rbKutya.getId()){
                    kep.setImageResource(R.drawable.kutya);
                } else if (checkedId == rbMacska.getId()) {
                    kep.setImageResource(R.drawable.macska);
                }
            }
        });

        datumEt = findViewById(R.id.datumEt);
        datumBtn = findViewById(R.id.datumBtn);
        datumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dpd = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String monthStr = Integer.toString(month+1);
                        String dayStr = Integer.toString(day);
                        if (month < 9)
                            monthStr = "0" + monthStr;
                        if (day< 10)
                            dayStr = "0" + dayStr;
                        datumEt.setText(year + "." + monthStr + "." + dayStr);
                    }
                }, 2024,9, 18);
                dpd.show();
            }
        });

        findViewById(R.id.kepek).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });


    }
}