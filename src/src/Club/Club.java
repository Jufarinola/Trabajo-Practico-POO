package Club;

import Contrato.Contrato;
import Persona.Entrenador;

import java.util.ArrayList;
import java.util.List;

public class Club {
    private String nombre;
    private String fechaFundacion;
    private String sedeSocial;
    private List<Contrato> contratos;
    private List<Entrenador> entrenador;

    public Club(String nombre, String fechaFundacion, String sedeSocial) {
        this.nombre = nombre;
        this.fechaFundacion = fechaFundacion;
        this.sedeSocial = sedeSocial;
        this.contratos = new ArrayList<>();
        this.entrenador = new ArrayList<>();
    }

    public void agregarContrato(Contrato contrato) {
        contratos.add(contrato);
    }

    public void agregarEntrenador(Entrenador entrenador) {
        this.entrenador.add(entrenador);
        entrenador.setClub(this);
    }

    public void quitarEntrenador(Entrenador entrenador) {
        if (this.entrenador.remove(entrenador)) {
            entrenador.setClub(null);
        }
    }

    public List<Entrenador> obtenerCuerpoTecnico() {
        return new ArrayList<>(entrenador);
    }
}
