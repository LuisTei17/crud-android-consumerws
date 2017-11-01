package consumer;

import java.util.List;

import pojo.Veiculo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Jotaro on 10/31/2017.
 */

public interface IUveiculo {
    final static String baseURL = "http://192.168.240.110:8080/compubras/";

    @POST("veiculo/")
    Call<Veiculo> cadastraVeiculo(@Body Veiculo veiculo);

    @PUT("veiculo/")
    Call<Veiculo> editaVeiculo(@Body Veiculo veiculo) ;

    @GET("veiculo/")
    Call<List<Veiculo>> pegaListaVeiculos();

    @GET("veiculo/{id}")
    Call<Veiculo> pegaVeiculoPorId(@Path("id") int id);

    @DELETE("veiculo/{id}")
    Call<Veiculo> deletaVeiculo(@Path("id") int id);
}
