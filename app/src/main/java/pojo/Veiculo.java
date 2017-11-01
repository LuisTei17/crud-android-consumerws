package pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jotaro on 10/31/2017.
 */

public class Veiculo implements Serializable {
    private int id;
    private String placa;
    private String cor;
    private String marca;
    private Boolean novo;
    private String tipo;

    public Veiculo() {
       super();
    }

    public Veiculo(String placa, String cor, String marca, boolean novo) {
        this.placa = placa;
        this.cor = cor;
        this.marca = marca;
        this.novo = novo;
        this.setTipo();
    }

    public int getId() {
        return id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getMarca() {
        return marca;
    }



    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo() {
        if(this.getNovo()) {
            this.tipo = "novo";
        } else {
            this.tipo = "semi-novo";
        }
    }

    public Boolean getNovo() {
        return novo;
    }

    public void setNovo(Boolean novo) {
        this.novo = novo;
    }

    @Override
    public String toString() {
        if(this.novo) {
            this.tipo = "novo";
        } else {
            this.tipo = "semi-novo";
        }

        return this.placa + ": " + this.cor + ": " + this.marca + ": " + this.tipo;
    }
}
