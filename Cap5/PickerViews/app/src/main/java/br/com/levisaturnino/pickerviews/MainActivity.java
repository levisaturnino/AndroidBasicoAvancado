package br.com.levisaturnino.pickerviews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TimePicker hora = (TimePicker) findViewById(R.id.pckHora);
        final DatePicker data = (DatePicker) findViewById(R.id.pckData);
        final EditText edtNome = (EditText) findViewById(R.id.txtNome);

        Button botao = (Button) findViewById(R.id.btnCalcular);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calNasc = Calendar.getInstance();
                calNasc.set(Calendar.YEAR, data.getYear());
                calNasc.set(Calendar.MONTH, data.getMonth());
                calNasc.set(Calendar.DAY_OF_MONTH, data.getDayOfMonth());
                calNasc.set(Calendar.MINUTE, hora.getCurrentMinute());
                calNasc.set(Calendar.DAY_OF_MONTH, data.getDayOfMonth());

                Calendar calHoje = Calendar.getInstance();

                calHoje.setTime(new Date());

                int tempo = calHoje.compareTo(calNasc);

                if (tempo > 0) {
                    calcularData(calNasc, calHoje, edtNome.getText().toString());
                } else {
                    Toast.makeText(getBaseContext(), "Data de Nascimento superior a data Atual", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private void calcularData(Calendar nascimento, Calendar agora, String nome) {

        int ano = agora.get(Calendar.YEAR);
        int mes = agora.get(Calendar.MONTH);
        int dia = agora.get(Calendar.DAY_OF_MONTH);

        int anoNasc = nascimento.get(Calendar.YEAR);
        int mesNasc = nascimento.get(Calendar.MONTH);
        int diaNasc = nascimento.get(Calendar.DAY_OF_MONTH);

        int idade = ano - anoNasc;

        if (mes < mesNasc) {
            idade--;
        } else if (mes == mesNasc) {
            if (dia < diaNasc) {
                idade--;
            }
        }
        String mensagem = "";

        if (idade < 18) {
            mensagem = nome + ", faltam " + (18 - idade) + "ano para tirar a carteira!";
        } else if (idade >= 18 && idade < 65) {
            mensagem = "Calma " + nome + ", faltam " + (65 - idade) + " Anos para você se aposentar";

        }else{
            mensagem = nome + ", o senhor já assistiu " + (idade / 4) + "copas do mundo";
        }

        Toast.makeText(MainActivity.this, mensagem, Toast.LENGTH_SHORT).show();
    }

}
