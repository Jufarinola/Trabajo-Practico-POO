package Evento;

import Persona.Jugador;

public class Cambio extends Evento {

    private Jugador jugadorEntra;
    private Jugador jugadorSale;

    public Cambio(int minuto, Jugador jugadorEntra,
                  Jugador jugadorSale) {
        super(minuto);
        this.jugadorEntra = jugadorEntra;
        this.jugadorSale = jugadorSale;
    }

    public void realizarCambio() {
        System.out.println(
                "Sale: " + jugadorSale.getNombre()
                        + " - Entra: " + jugadorEntra.getNombre()
        );
    }

    @Override
    public String obtenerDescripcion() {
        return "Cambio en el minuto " + getMinuto()
                + ": sale " + jugadorSale.getNombre()
                + " y entra " + jugadorEntra.getNombre();
    }
}