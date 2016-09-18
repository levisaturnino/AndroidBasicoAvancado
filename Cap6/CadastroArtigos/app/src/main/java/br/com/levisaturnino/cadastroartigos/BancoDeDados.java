package br.com.levisaturnino.cadastroartigos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by saturnino on 17/09/16.
 */
public class BancoDeDados {

    static String KEY_ID = "_id";
    static String KEY_NOME = "nome";
    static String KEY_REVISTA = "revista";
    static String KEY_EDICAO = "edicao";
    static String KEY_STATUS = "status";
    static String KEY_PAGO = "pago";

    String NOME_BANCO = "db_Revistas";
    String NOME_TABELA = "artigo";

    int VERSAO_BANCO = 2;

    String SQL_CREATE_TABLE = "create table " + NOME_TABELA + " " + "(" + KEY_ID + " integer primary key autoincrement, "
            + KEY_NOME + " text not null, " +
            KEY_REVISTA + " text, " +
            KEY_EDICAO + " text, " +
            KEY_STATUS + " integer, " +
            KEY_PAGO + " integer);";


    Context context;
    MeuOpenHelper openHelper;
    SQLiteDatabase db;
    private boolean valor;

    public BancoDeDados(Context context) {
        this.context = context;
        openHelper = new MeuOpenHelper(context);
    }

    public BancoDeDados abrir() throws SQLException {
        db = openHelper.getWritableDatabase();
        return this;
    }

    public void fechar() {
        openHelper.close();
    }

    public long insereArtigo(String nome, String revista, String edicao, int status, int pago) {
        ContentValues campos = getContentValues(nome, revista, edicao, status, pago);
      long id;

        db.beginTransaction();
        try {
         id = db.insert(NOME_TABELA, null, campos);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return id;
    }

    public boolean apagaArtigo(long id) {

        db.beginTransaction();
        try {
            valor =   db.delete(NOME_TABELA, KEY_ID + " = " + id, null) > 0;
            db.setTransactionSuccessful();

        } finally {
            db.endTransaction();
        }
        return valor;
    }

    public Cursor retornaTodosArtigos() {
        return db.query(NOME_TABELA, new String[]{KEY_ID, KEY_NOME, KEY_REVISTA, KEY_EDICAO, KEY_STATUS, KEY_PAGO}, null, null, null, null, null);
    }

    public boolean atualizaArtigo(long id, String nome, String revista, String edicao, int status, int pago) {
        ContentValues campos = getContentValues(nome, revista, edicao, status, pago);

        db.beginTransaction();
        try {
            valor = db.update(NOME_TABELA, campos, KEY_ID + "=" + id, null) > 0;
            db.setTransactionSuccessful();

        } finally {
            db.endTransaction();
        }
        return valor;
    }

    @NonNull
    private ContentValues getContentValues(String nome, String revista, String edicao, int status, int pago) {
        ContentValues campos = new ContentValues();
        campos.put(KEY_NOME, nome);
        campos.put(KEY_REVISTA, revista);
        campos.put(KEY_EDICAO, edicao);
        campos.put(KEY_STATUS, status);
        campos.put(KEY_PAGO, pago);
        return campos;
    }

    class MeuOpenHelper extends SQLiteOpenHelper {

        public MeuOpenHelper(Context context) {
            super(context, NOME_BANCO, null, VERSAO_BANCO);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                Log.d("CREATE", "onCreate: "+SQL_CREATE_TABLE);
                db.execSQL(SQL_CREATE_TABLE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + NOME_TABELA);
            onCreate(db);
        }
    }

}