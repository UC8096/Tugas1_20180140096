package com.example.tugas_20180140096;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText edNamaD, edNamaB, edTempL, edTglL, edAlmt, edTelp, edEmail, edPass, edConfrmPass;
    Button btnDaftr, btnKemb;

    AwesomeValidation validation;


    DatePickerDialog.OnDateSetListener date;

    Calendar mycalendar;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edTglL = findViewById(R.id.te_tgl_lhr);





        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                mycalendar.set(Calendar.YEAR, year);
                mycalendar.set(Calendar.MONTH, month);
                mycalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateDate();
            }
        };

        edTglL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getApplicationContext(), date, mycalendar
                        .get(Calendar.YEAR), mycalendar.get(Calendar.MONTH),
                        mycalendar.get(Calendar.DAY_OF_MONTH)).show();
            }


        });


        edNamaD = findViewById(R.id.te_nama_d);
        edNamaB = findViewById(R.id.te_nama_b);
        edTempL = findViewById(R.id.te_temp_l);
        edAlmt = findViewById(R.id.te_alamt);
        edEmail = findViewById(R.id.te_email);
        edTelp = findViewById(R.id.te_telp);
        edPass = findViewById(R.id.te_pass);
        edConfrmPass = findViewById(R.id.te_pass_kem);

        btnDaftr = findViewById(R.id.daftar);
        btnKemb = findViewById(R.id.kembali);


        validation = new AwesomeValidation(ValidationStyle.BASIC);

        validation.addValidation(this, R.id.te_nama_d,
                RegexTemplate.NOT_EMPTY, R.string.invalid_name);
        validation.addValidation(this, R.id.te_nama_b,
                RegexTemplate.NOT_EMPTY, R.string.invalid_nameBelakang);
        validation.addValidation(this, R.id.te_telp,
                RegexTemplate.TELEPHONE,
                R.string.invalid_telepon);
        validation.addValidation(this, R.id.te_tgl_lhr,
                RegexTemplate.NOT_EMPTY, R.string.invalid_tanggal);
        validation.addValidation(this, R.id.te_temp_l,
                RegexTemplate.NOT_EMPTY, R.string.invalid_tempat);
        validation.addValidation(this, R.id.te_alamt,
                RegexTemplate.NOT_EMPTY, R.string.invalid_alamat);
        validation.addValidation(this, R.id.te_email,
                Patterns.EMAIL_ADDRESS, R.string.invalid_email);

        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";


        validation.addValidation(this, R.id.te_pass, regexPassword, R.string.invalid_password);
        validation.addValidation(this, R.id.te_pass_kem,
                R.id.te_pass, R.string.invalid_confirm_password);

        btnKemb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        btnDaftr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation.validate()) {
                    Toast.makeText(getApplicationContext(), "Form Validate Successfully...", Toast.LENGTH_SHORT).show();
                    openDialog();
                } else {
                    Toast.makeText(getApplicationContext(), "Validation Falid.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateDate() {
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        edTglL.setText(sdf.format(mycalendar.getTime()));
    }


    public void openDialog() {
        DataDialog dataDialog = new DataDialog();
        dataDialog.show(getSupportFragmentManager(), "Data dialog");
    }
}
