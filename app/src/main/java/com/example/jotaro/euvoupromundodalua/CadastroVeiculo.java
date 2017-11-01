package com.example.jotaro.euvoupromundodalua;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import consumer.VeiculoConsumer;
import pojo.Veiculo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroVeiculo extends AppCompatActivity {

    private String[] marcas = {"Fiat", "Ford", "Wolkswagen", "Fusca"};

    // Componentes da view
    private EditText etPlaca, etCor;
    private Spinner spMarca;
    private RadioGroup rgTipo;
    private Button btEnvia;
    private RadioButton rbEscolhido, rbSemi, rbNovo;

    private int posicaoMarca;
    private VeiculoConsumer veiculoConsumer;
    private String idEdita;
    // Variaveis para receber texto
    private String placa, cor, marca;
    private Boolean novo;
    private Veiculo veiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_veiculo);
        Intent intencao = getIntent();

        inicializaComponents();

      //  if(intencao.getBundleExtra("veiculo") != null) {
            veiculo = (Veiculo) intencao.getSerializableExtra("veiculo");

            etPlaca.setText(veiculo.getPlaca());
            etCor.setText(veiculo.getCor());

            for (int pos = 0; pos<marcas.length; pos++) {
                if(marcas[pos].equals(veiculo.getMarca())) {
                    posicaoMarca = pos;
                }
            }

            spMarca.setSelection(posicaoMarca);
            if(veiculo.getTipo().equals("semi-usado")){
                rbSemi.setChecked(true);
            } else if(veiculo.getTipo().equals("novo")) {
                rbNovo.setChecked(true);
            }
        //}


        btEnvia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                placa = etPlaca.getText().toString();
                cor = etCor.getText().toString();
                marca = spMarca.getSelectedItem().toString();
                if(rbSemi.isChecked()) {
                    novo = false;
                } else if (rbNovo.isChecked()) {
                    novo = true;
                }
                veiculoConsumer = new VeiculoConsumer();
                veiculo = new Veiculo(placa, cor, marca, novo);
                Log.d("TAG", "onClick: " + novo);
                Log.d("TAG", "onClick:" + veiculo.getNovo());
                Log.d("TAG", "onClick:" + veiculo.getTipo());
                Log.d("TAG", "onClick:" + veiculo.getCor());
                veiculoConsumer.cadastraVeiculo(veiculo).enqueue(new Callback<Veiculo>() {
                    @Override
                    public void onResponse(Call<Veiculo> call, Response<Veiculo> response) {
                        Intent intencaoMain = new Intent(CadastroVeiculo.this, MainActivity.class);
                        startActivity(intencaoMain);
                    }

                    @Override
                    public void onFailure(Call<Veiculo> call, Throwable t) {

                    }
                });

                            }
        });

    }

    public void inicializaComponents() {
        etPlaca = (EditText) findViewById(R.id.etPlaca);
        etCor = (EditText) findViewById(R.id.etCor);
        spMarca = (Spinner) findViewById(R.id.spMarca);
        rgTipo = (RadioGroup) findViewById(R.id.rgTipos);
        rbEscolhido = (RadioButton) findViewById(rgTipo.getCheckedRadioButtonId());
        btEnvia = (Button) findViewById(R.id.btEnviar);
        rbNovo = (RadioButton) findViewById(R.id.rbNovo);
        rbSemi = (RadioButton) findViewById(R.id.rbSemiNovo);


        ArrayAdapter<String> marcasAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, marcas);

        spMarca.setAdapter(marcasAdapter);
    }
}
