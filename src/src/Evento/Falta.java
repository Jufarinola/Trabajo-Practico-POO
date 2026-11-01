package Evento;

public class Falta extends Evento {

    public Falta(int minuto) {
        super(minuto);
    }

    @Override
    public String obtenerDescripcion() {
        return "Falta cometida en el minuto " + getMinuto();
    }
}