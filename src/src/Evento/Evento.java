package Evento;

public abstract class Evento {
    private int minuto;

    public Evento(int minuto){
        this.minuto = minuto;
    }

    public int getMinuto() {
        return minuto;
    }

    public abstract String obtenerDescripcion();
}

