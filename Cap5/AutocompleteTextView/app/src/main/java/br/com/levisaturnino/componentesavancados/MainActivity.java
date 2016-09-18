package br.com.levisaturnino.componentesavancados;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {
    String[] estadios = { "Albertão", "Arena do Grêmio",
            "Arena Fonte Nova",

            "Arena Pantanal","Arena Pernambuco", "Arruda",
            "Beira-Rio", "Brinco de Ouro!",

            "Castelão", "Couto Pereira", "Engenhão",
            "Mangueirão", "Maracanã", "Mineirão",

            "Morenão","Morumbi", "Nacional Mané Garrincha","Serra Dourada" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,estadios);

        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.txtEstadios);

        textView.setThreshold(3);
        textView.setAdapter(adapter);


    }
}
