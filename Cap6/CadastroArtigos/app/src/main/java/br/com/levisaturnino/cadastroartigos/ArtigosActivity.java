package br.com.levisaturnino.cadastroartigos;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saturnino on 17/09/16.
 */
public class ArtigosActivity extends AppCompatActivity {
    private BancoDeDados db;
    private List<Artigo> artigos = new ArrayList<Artigo>();
    private ArtigosAdapter artigosAdapter;
    public static final int REQUEST_EDICAO = 0;
    public static final int REQUEST_SALVOU = 1;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artigos);

        listView = (ListView) findViewById(android.R.id.list);

        db = new BancoDeDados(this);
        lerDados();
    }

    private void lerDados() {
        db.abrir();

        artigos.clear();

        Cursor cursor = db.retornaTodosArtigos();
        if (cursor.moveToFirst()) {
            do {
                Artigo a = new Artigo();
                a.id = cursor.getInt(cursor.
                        getColumnIndex(BancoDeDados.KEY_ID));
                a.nome = cursor.getString(cursor.
                        getColumnIndex(BancoDeDados.KEY_NOME));
                a.revista = cursor.getString(cursor.
                        getColumnIndex(BancoDeDados.KEY_REVISTA));
                a.edicao = cursor.getString(cursor.
                        getColumnIndex(BancoDeDados.KEY_EDICAO));
                a.status = cursor.getInt(cursor.
                        getColumnIndex(BancoDeDados.KEY_STATUS));
                a.pago = cursor.getInt(cursor.
                        getColumnIndex(BancoDeDados.KEY_PAGO));
                artigos.add(a);

            } while (cursor.moveToNext());
        }

        if (artigos.size() > 0) {
            if (artigosAdapter == null) {
                artigosAdapter = new ArtigosAdapter(this, artigos) {

                    @Override
                    public void editar(Artigo artigo) {
                        Intent intent = new
                                Intent(getApplicationContext(), NovoEdicaoActivity.class);
                        intent.putExtra("artigo", artigo);
                        startActivityForResult(intent, REQUEST_EDICAO);

                    }

                    @Override

                    public void deletar(Artigo artigo) {
                        db.abrir();
                        db.apagaArtigo(artigo.id);
                        db.fechar();
                        lerDados();
                    }

                };

           listView.setAdapter(artigosAdapter);

            } else {

                artigosAdapter.novosDados(artigos);

            }
        }
        db.fechar();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_artigos,
                menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_add) {

            Intent intent = new Intent(this, NovoEdicaoActivity.class);
            startActivityForResult(intent, REQUEST_EDICAO);
            return true;
        } else {

            return super.onOptionsItemSelected(item);
        }
    }

    /*    @Override
    public boolean onMenuItemSelected(int featureId,
                                      MenuItem item) {
        if (item.getItemId() == R.id.menu_add) {

            Intent intent = new Intent(this, NovoEdicaoActivity.class);
            startActivityForResult(intent, REQUEST_EDICAO);
            return true;
        } else {

            return super.onMenuItemSelected(featureId,
                    item);
        }
    }*/

    protected void onActivityResult(int requestCode, int
            resultCode, Intent data) {

        if (requestCode == REQUEST_EDICAO) {

            if (resultCode == REQUEST_SALVOU) {
                lerDados();

            }

        }
    }

}