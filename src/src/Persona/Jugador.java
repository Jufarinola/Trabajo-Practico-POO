package Persona;

import Club.Club;
import Contrato.Contrato;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.RecursiveTask;


public class Jugador extends Persona{
    private String posicion;
    private String numeroCamiseta;
    private Double valorMercado;
    private List<Contrato> contrato;


    public Jugador(
            String dni,
            String nombre,
            String apellido,
            Date fechaNacimiento,
            String posicion,
            String numeroCamiseta,
            Double valorMercado
            ){

        super(dni, nombre, apellido, fechaNacimiento);
        this.posicion = posicion;
        this.numeroCamiseta = numeroCamiseta;
        this.valorMercado = valorMercado;
        this.contrato = new ArrayList<>();
    }

    public Club ObtenerClubActual(){
        for (Contrato contrato : contrato) {
            if (contrato.estaVigente()) {
                return contrato.getClub();
            }
        }
        return null;
    }

    public List<Contrato> ObtenerHistorialContrato(){
        return contrato;
    }

    public void ActualizarValorMercado(Double valorNuevo){
        valorMercado = valorNuevo;
    }

    public void agregarContrato(Contrato contrato) {
        if (contrato != null
                && contrato.getJugador() == this
                && !this.contrato.contains(contrato)) {
            this.contrato.add(contrato);
        }
    }

}
