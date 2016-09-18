package br.com.levisaturnino.cadastroartigos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by saturnino on 17/09/16.
 */
public abstract class ArtigosAdapter extends BaseAdapter {

    private List<Artigo> artigos;
    private LayoutInflater inflater;

    ArtigosAdapter(Context context, List<Artigo> artigos){

        this.inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.artigos = artigos;

    }

    public void novosDados(List<Artigo> artigos){
        this.artigos = artigos;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return artigos.size();
    }

    @Override
    public Object getItem(int position) {
        return artigos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = inflater.inflate(R.layout.item_artigo, null);

        ((TextView) (v.findViewById(R.id.txtNome))).setText(artigos.get(position).nome);

        ((ImageButton) (v.findViewById(R.id.btnEditar))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editar(artigos.get(position));
            }
        });
        ((ImageButton) (v.findViewById(R.id.btnExcluir))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletar(artigos.get(position));

            }
        });
        return v;
    }

    public abstract void editar(Artigo artigo);
    public abstract void deletar(Artigo artigo);
}
