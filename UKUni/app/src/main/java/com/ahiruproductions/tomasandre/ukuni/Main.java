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
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


//-----------------------------------------------------------
public class Main extends AppCompatActivity {
    public static final String EXT_NOME = "com.ahiruproductions.tomasandre.ukuni.Main.EXT_NOME";
    public static final String EXT_DATA = "com.ahiruproductions.tomasandre.ukuni.Main.EXT_DATA";
    public static final String EXT_NOTA_INGLES = "com.ahiruproductions.tomasandre.ukuni.Main.INGLES";
    public static final String EXT_NOTA_SECUNDARIO = "com.ahiruproductions.tomasandre.ukuni.Main.SECUNDARIO";
    public static final String EXT_UNIVERSIDADE = "com.ahiruproductions.tomasandre.ukuni.Main.UNIVERSIDADE";

    Calendar calendar;
    EditText date;
    EditText nome;
    SeekBar barSecundario;
    SeekBar barIngles;
    TextView txtSec;
    TextView txtIng;
    TextView txtUni;
    ListView universidades;
    String universidade = "";

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
        txtUni = (TextView)findViewById(R.id.textViewUni);
        universidades = (ListView)findViewById(R.id.listViewUniversidades);
        calendar = Calendar.getInstance();

        String[] items  = new String[] {
                "Birmingham University",
                "Aston University",
                "East London University",
                "Harvard University",
                "Oxford University",
                "Cambridge University",
                "Edinburgh University"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.textview_custom,android.R.id.text1, items);
        universidades.setAdapter(adapter);

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
                } else {
                    Intent inte = new Intent(getBaseContext(), Secondary.class);

                    inte.putExtra(EXT_NOME, nome.getText().toString());
                    inte.putExtra(EXT_DATA, new int[]{calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR)});
                    inte.putExtra(EXT_NOTA_INGLES, barIngles.getProgress());
                    inte.putExtra(EXT_NOTA_SECUNDARIO, barSecundario.getProgress());
                    inte.putExtra(EXT_UNIVERSIDADE, universidade);

                    startActivity(inte);
                }
            }
        });

        //-----------------------------------------------------------
        universidades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //-----------------------------------------------------------
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                universidade = universidades.getItemAtPosition(position).toString();
                txtUni.setText("Universidade: " + universidade);
            }
        });

        //-----------------------------------------------------------
        datePicker = new DatePickerDialog.OnDateSetListener()  {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                calendar.set(year,monthOfYear,dayOfMonth);
                UpdateDate("dd/MM/yy");
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
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
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
        if (date.getText().toString().matches("") || nome.getText().toString().matches("") || universidade.isEmpty()){
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
