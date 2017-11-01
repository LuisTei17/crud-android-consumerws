package consumer;

import android.util.Log;

import java.util.List;

import pojo.Veiculo;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Path;

import static android.content.ContentValues.TAG;

/**
 * Created by Jotaro on 10/31/2017.
 */

public class VeiculoConsumer implements IUveiculo {

    private IUveiculo veiculoService;
    private Retrofit retrofit;

    public VeiculoConsumer(){
        this.retrofit = new Retrofit.
                Builder().
                baseUrl(IUveiculo.baseURL).
                addConverterFactory(GsonConverterFactory.create()).
                build();

        this.veiculoService= retrofit.create(IUveiculo.class);
    }

    @Override
    public Call<Veiculo> cadastraVeiculo(@Body Veiculo veiculo) {

        return this.veiculoService.cadastraVeiculo(veiculo);
    }

    @Override
    public Call<Veiculo> editaVeiculo(@Body Veiculo veiculo) {
        return this.veiculoService.editaVeiculo(veiculo);
    }

    @Override
    public Call<List<Veiculo>> pegaListaVeiculos() {
        return this.veiculoService.pegaListaVeiculos();
    }

    @Override
    public Call<Veiculo> pegaVeiculoPorId(@Path("id") int id) {
        return this.veiculoService.pegaVeiculoPorId(id);
    }

    @Override
    public Call<Veiculo> deletaVeiculo(@Path("id") int id) {
        return this.veiculoService.deletaVeiculo(id);
    }
}
