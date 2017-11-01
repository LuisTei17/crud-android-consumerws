package com.example.jotaro.euvoupromundodalua;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import consumer.VeiculoConsumer;
import pojo.Veiculo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaActivity extends ListActivity {
    private List<Veiculo> veiculos;
    private VeiculoConsumer veiculoConsumer;
    private String intencao;
    private Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        veiculoConsumer = new VeiculoConsumer();

        veiculoConsumer.pegaListaVeiculos().enqueue(new Callback<List<Veiculo>>() {
            @Override
            public void onResponse(Call<List<Veiculo>> call, Response<List<Veiculo>> response) {
                veiculos = response.body();
                ArrayAdapter<Veiculo> adapterListaVeiculos = new ArrayAdapter<Veiculo>(ListaActivity.this, android.R.layout.simple_list_item_1, veiculos);
                setListAdapter(adapterListaVeiculos);
            }

            @Override
            public void onFailure(Call<List<Veiculo>> call, Throwable t) {

            }
        });

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if(getIntent().getStringExtra("acao") != null) {
            if(getIntent().getStringExtra("acao").equals("excluir")) {
                veiculoConsumer.deletaVeiculo(veiculos.get((int) id).getId()).enqueue(new Callback<Veiculo>() {
                    @Override
                    public void onResponse(Call<Veiculo> call, Response<Veiculo> response) {
                        Intent intencaoMain = new Intent(ListaActivity.this, MainActivity.class);
                        startActivity(intencaoMain);
                    }

                    @Override
                    public void onFailure(Call<Veiculo> call, Throwable t) {

                    }
                });
            } else if(getIntent().getStringExtra("acao").equals("editar")) {
                Intent intencaoEditar = new Intent(ListaActivity.this, CadastroVeiculo.class);
                Bundle params = new Bundle();
                params.putSerializable("veiculo", this.veiculos.get((int) id));
                intencaoEditar.putExtras(params);
                startActivity(intencaoEditar);

            }
        }

    }
}
