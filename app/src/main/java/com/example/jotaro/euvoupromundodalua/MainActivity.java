package com.example.jotaro.euvoupromundodalua;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
    private String[] opcoes = {"Cadastrar", "Editar", "Buscar", "Excluir"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> opcoesLista = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.opcoes);
        setListAdapter(opcoesLista);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if (position == 0) {
            Intent intencaoCadastra = new Intent(MainActivity.this, CadastroVeiculo.class);
            startActivity(intencaoCadastra);
        }
        if (position == 1) {
            Intent intencaoEdita = new Intent(MainActivity.this, ListaActivity.class);
            intencaoEdita.putExtra("acao", "editar");
            startActivity(intencaoEdita);
        }
        if (position == 2) {
            Intent intencaoBusca = new Intent(MainActivity.this, ListaActivity.class);
            startActivity(intencaoBusca);
        }
        if (position == 3) {
            Intent intencaoExcluir = new Intent(MainActivity.this, ListaActivity.class);
            intencaoExcluir.putExtra("acao", "excluir");
            startActivity(intencaoExcluir);
        }
    }
}
