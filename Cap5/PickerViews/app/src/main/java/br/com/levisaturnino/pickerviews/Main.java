package br.com.levisaturnino.pickerviews;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

/**
 * Created by saturnino on 17/09/16.
 */

    public class Main extends AppCompatActivity {

    private static final int TIME_DIALOG_ID = 0;
    private TextView lblAlarme;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        lblAlarme = (TextView) findViewById(R.id.txtAlarme);
        btn = (Button) findViewById(R.id.btnConfigurar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID);
            }
        });



    }

    protected Dialog onCreateDialog(int id)
    {
        switch (id){
            case  TIME_DIALOG_ID:
                TimePickerDialog td = new TimePickerDialog(this,mTimeSetListener,12,00,false);

                return td;
        }
        return null;
    }

    private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            lblAlarme.setText("Alarme configura para " + hourOfDay + ":" + minute);
        }
    };
}
