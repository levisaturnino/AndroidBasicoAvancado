package br.com.levisaturnino.eventsproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtNome;

     String[] bairros = new String[]{"Macaxeira", "Imbiribeira", "Bom pastor", "Ibura", "Ipsep", "Torreão","Casa Amarela",
    "Boa Viagem", "Genipapo", "Bonifácio", "Santo Amaro", "Madalena", "Boa Vista",
    "Dois Irmãos", "Cais do porto", "Caxangá", "Brasilit", "Beberibe","CDU",
    "Capibaribe", "Sertão"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner s1 = (Spinner) findViewById(R.id.spRegiao);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,bairros);

     final ImageView imgBairro = (ImageView) findViewById(R.id.imgBairro);



        s1.setAdapter(adapter);


        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0){
                    imgBairro.setImageResource(R.drawable.macaxeira);
                } else if(position == 1){
                    imgBairro.setImageResource(R.drawable.imbiribeira);


                }else if(position == 2){
                    imgBairro.setImageResource(R.drawable.bompastor);


                }else if(position == 3) {
                    imgBairro.setImageResource(R.drawable.ibura);

                }
                }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        s1.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() {
            @Override
            public void onChildViewAdded(View parent, View child) {

            }

            @Override
            public void onChildViewRemoved(View parent, View child) {

            }
        });

        Button btn = (Button) findViewById(R.id.btnCadastro);

        LinearLayout llMain = (LinearLayout) findViewById(R.id.llMain);

        llMain.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                usarToast("Clique em um dos componentes");
                return false;
            }
        });

        edtNome = (EditText) findViewById(R.id.edtNome);

        edtNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usarToast("Cliquei no OnclickListener");
            }
        });

        edtNome.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                usarToast("Cliquei no OnLongclickListener");
                return false;
            }
        });

        EditText edtRegiao = (EditText) findViewById(R.id.edtRegiao);

        edtRegiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usarToast("Cliquei no OnclickListener");
            }
        });

        edtRegiao.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                usarToast("Cliquei no OnLongclickListener");
                return false;
            }
        });

    /*    btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarMensagem();
            }
        });*/


        edtNome.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                 if(hasFocus){
                     usarToast("Nome ganhou foco");
                 }else{
                     usarToast("Nome perdeu foco");
                 }
            }
        });

        edtRegiao.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    usarToast("Região ganhou foco");
                }else{
                    usarToast("Regiao perdeu foco");
                }
            }
        });
        edtNome.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                usarToast(String.valueOf(keyCode));

                return false;
            }
        });

        edtRegiao.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                usarToast(String.valueOf(keyCode));

                return false;
            }
        });


        registerForContextMenu(edtNome);
        registerForContextMenu(edtRegiao);

    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo contextMenuInfo) {
        super.onCreateContextMenu(menu, v, contextMenuInfo);

        if (v.getId() == R.id.edtNome) {
            getMenuInflater().inflate(R.menu.context_menu, menu);
        } else {
            getMenuInflater().inflate(R.menu.context_menu_text, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_ajuda) {
            usarToast("Cliquei no ajuda");
        } else if (item.getItemId() == R.id.action_prova) {
            usarToast("Cliquei no prova");
        }

        return super.onContextItemSelected(item);
    }

    public void cliquebotao(View v) {
        mostrarMensagem();
    }

    private void mostrarMensagem() {
        Intent intent = new Intent(this, Mensagem.class);
        startActivity(intent);
    }

    public void usarToast(String mensagem) {
        Toast.makeText(MainActivity.this, mensagem, Toast.LENGTH_SHORT).show();
    }
}
