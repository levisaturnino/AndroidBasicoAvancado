package br.com.levisaturnino.listview;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    String[] paises = {"Africa do sul","Alemanha","Brasil"};

    int posicao[] = new int[]{47,13,3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,paises));


    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Toast.makeText(MainActivity.this, paises[position]+" está na "+posicao[position]+"º posicao ", Toast.LENGTH_SHORT).show();
    }
}
