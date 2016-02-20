package com.ahiruproductions.tomasandre.ukuni;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


//-----------------------------------------------------------
public class Main extends AppCompatActivity {
    public static final String EXT_NOME = "com.ahiruproductions.tomasandre.ukuni.Main.EXT_NOME";
    public static final String EXT_DATA = "com.ahiruproductions.tomasandre.ukuni.Main.EXT_DATA";

    Calendar calendar;
    EditText date;
    EditText nome;
    SeekBar barSecundario;
    SeekBar barIngles;
    TextView txtSec;
    TextView txtIng;

    DatePickerDialog.OnDateSetListener datePicker;

    //-----------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Init:
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        date = (EditText)findViewById(R.id.editTextData);
        nome = (EditText)findViewById(R.id.editTextNome);
        barIngles = (SeekBar)findViewById(R.id.seekBarNotaIngles);
        barSecundario = (SeekBar)findViewById(R.id.seekBarSecundario);
        txtIng = (TextView)findViewById(R.id.textViewNotaIngles);
        txtSec = (TextView)findViewById(R.id.textViewNotaSecundario);
        calendar = Calendar.getInstance();

        setSupportActionBar(toolbar);

        //-----------------------------------------------------------
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!AssertValues()) {
                    Snackbar.make(view, "Preencha todos os campos...", Snackbar.LENGTH_LONG).setAction("Ok", new View.OnClickListener() {
                        //-----------------------------------------------------------
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
                }
                else {
                    Intent inte = new Intent(getBaseContext(), Secondary.class);
                    startActivity(inte);
                }
            }
        });

        //-----------------------------------------------------------
        datePicker = new DatePickerDialog.OnDateSetListener()  {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(year,monthOfYear,dayOfMonth);
                UpdateDate("MM/dd/yy");
            }
        };

        //-----------------------------------------------------------
        nome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //-----------------------------------------------------------
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Main.this, datePicker, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //-----------------------------------------------------------
        barSecundario.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //-----------------------------------------------------------
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtSec.setText("Nota final do secundário: " + progress);
            }

            //-----------------------------------------------------------
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            //-----------------------------------------------------------
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //-----------------------------------------------------------
        barIngles.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //-----------------------------------------------------------
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtIng.setText("Nota de inglês: " + progress);
            }

            //-----------------------------------------------------------
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            //-----------------------------------------------------------
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    //-----------------------------------------------------------
    private void UpdateDate(String format){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
        date.setText(dateFormat.format(calendar.getTime()));
    }

    //-----------------------------------------------------------
    private boolean AssertValues(){
        if (date.getText().toString().matches("") || nome.getText().toString().matches("")){
            return false;
        }
        return true;
    }

    //-----------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //-----------------------------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
