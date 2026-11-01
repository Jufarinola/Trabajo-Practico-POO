package Evento;

public class Tarjeta extends Evento {

    private String color;

    public Tarjeta(int minuto, String color) {
        super(minuto);
        this.color = color;
    }

    @Override
    public String obtenerDescripcion() {
        return "Tarjeta " + color
                + " en el minuto " + getMinuto();
    }
}