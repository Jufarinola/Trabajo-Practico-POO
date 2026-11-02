package Evento;

import Persona.Jugador;

public class Cambio extends Evento {

    private Jugador jugadorEntra;

    public Cambio(int minuto, Jugador jugadorEntra,
                  Jugador jugadorSale) {
        super(minuto, jugadorSale);
        this.jugadorEntra = jugadorEntra;
    }

    public void realizarCambio() {
        System.out.println(
                "Sale: " + getJugador().getNombre()
                        + " - Entra: " + jugadorEntra.getNombre()
        );
    }

    @Override
    public String obtenerDescripcion() {
        return "Cambio en el minuto " + getMinuto()
                + ": sale " + getJugador().getNombre()
                + " y entra " + jugadorEntra.getNombre();
    }
}