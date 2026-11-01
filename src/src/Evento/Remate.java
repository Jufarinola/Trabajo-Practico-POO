package Evento;

public class Remate extends Evento {

    public Remate(int minuto) {
        super(minuto);
    }

    @Override
    public String obtenerDescripcion() {
        return "Remate en el minuto " + getMinuto();
    }
}