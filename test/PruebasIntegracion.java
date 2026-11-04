import Club.Club;
import Evento.*;
import Partido.*;
import Persona.*;
import Torneo.*;
import java.time.*;
import java.util.Date;

public class PruebasIntegracion {
    private static int cantidad;
    private interface Accion { void ejecutar(); }
    private static void comprobar(String nombre, boolean condicion) {
        if (!condicion) throw new AssertionError(nombre);
        cantidad++;
        System.out.println("OK: " + nombre);
    }
    private static void rechazar(String nombre, Accion accion, boolean esperaErrorDeEstado) {
        try {
            accion.ejecutar();
        } catch (RuntimeException error) {
            comprobar(nombre, esperaErrorDeEstado
                    ? error instanceof IllegalStateException
                    : error instanceof IllegalArgumentException);
            return;
        }
        throw new AssertionError("No rechazo: " + nombre);
    }
    public static void main(String[] args) {
        Club a = new Club("A", "1900", "Sede"), b = new Club("B", "1900", "Sede");
        Club externo = new Club("Externo", "1900", "Sede");
        Torneo t = new Torneo("Liga", LocalDate.of(2026,11,1), LocalDate.of(2026,12,31));
        LocalDateTime hora = LocalDateTime.of(2026,11,4,18,0);
        Arbitro r1 = new Arbitro("1", "Uno", "Arbitro", new Date(), "Nacional", "Colegio");
        Arbitro r2 = new Arbitro("2", "Dos", "Arbitro", new Date(), "Nacional", "Colegio");
        Arbitro r3 = new Arbitro("3", "Tres", "Arbitro", new Date(), "Nacional", "Colegio");
        Jugador j = new Jugador("4", "Jugador", "Prueba", new Date(), "DEL", "9", 1.0);
        rechazar("Clubes iguales", new Accion() { public void ejecutar() { new Partido(hora,a,a,r1,r2,r3,t); } }, false);
        rechazar("Club nulo", new Accion() { public void ejecutar() { new Partido(hora,null,b,r1,r2,r3,t); } }, false);
        rechazar("Fecha nula", new Accion() { public void ejecutar() { new Partido(null,a,b,r1,r2,r3,t); } }, false);
        rechazar("Torneo nulo", new Accion() { public void ejecutar() { new Partido(hora,a,b,r1,r2,r3,null); } }, false);
        rechazar("Arbitro nulo", new Accion() { public void ejecutar() { new Partido(hora,a,b,null,r2,r3,t); } }, false);
        rechazar("Arbitro repetido", new Accion() { public void ejecutar() { new Partido(hora,a,b,r1,r1,r3,t); } }, false);
        Partido p = new Partido(hora,a,b,r1,r2,r3,t);
        Partido otro = new Partido(hora,a,b,r1,r2,r3,t);
        comprobar("Estado inicial", p.getEstado() == EstadoPartido.PROGRAMADO);
        rechazar("Minuto negativo", new Accion() { public void ejecutar() { new Falta(-1,j,p); } }, false);
        rechazar("Minuto superior a 120", new Accion() { public void ejecutar() { new Remate(121,j,p); } }, false);
        comprobar("Minuto cero permitido", new Falta(0,j,p).getMinuto() == 0);
        comprobar("Minuto 120 permitido", new Remate(120,j,p).getMinuto() == 120);
        rechazar("Jugador nulo", new Accion() { public void ejecutar() { new Falta(1,null,p); } }, false);
        rechazar("Partido nulo en evento", new Accion() { public void ejecutar() { new Falta(1,j,null); } }, false);
        rechazar("Gol de club externo", new Accion() { public void ejecutar() { new Gol(1,"Normal",j,externo,p); } }, false);
        rechazar("Tipo de gol vacio", new Accion() { public void ejecutar() { new Gol(1,"",j,a,p); } }, false);
        rechazar("Color nulo", new Accion() { public void ejecutar() { new Tarjeta(1,j,null,p); } }, false);
        rechazar("Color invalido", new Accion() { public void ejecutar() { new Tarjeta(1,j,"Verde",p); } }, false);
        Gol primero = new Gol(10,"Normal",j,a,p);
        comprobar("Datos del gol", primero.getClub() == a && primero.getTipoGol().equals("Normal"));
        comprobar("Descripcion de gol", primero.obtenerDescripcion().contains("(A)"));
        Tarjeta tarjeta = new Tarjeta(20,j,"Amarilla",p);
        comprobar("Color inicializado", tarjeta.getColor().equals("Amarilla"));
        comprobar("Descripcion de tarjeta", tarjeta.obtenerDescripcion().contains("Amarilla"));
        rechazar("Evento antes del inicio", new Accion() { public void ejecutar() { p.registrarEvento(primero); } }, true);
        p.iniciarPartido();
        rechazar("Evento de otro partido", new Accion() { public void ejecutar() { p.registrarEvento(new Falta(1,j,otro)); } }, false);
        comprobar("Rechazo no modifica datos", p.getEventos().isEmpty() && p.obtenerMarcador().equals("0 - 0"));
        p.registrarEvento(primero);
        rechazar("Evento duplicado", new Accion() { public void ejecutar() { p.registrarEvento(primero); } }, false);
        comprobar("Duplicado no suma goles", p.obtenerMarcador().equals("1 - 0"));
        p.registrarEvento(new Gol(30,"Cabeza",j,a,p));
        p.registrarEvento(new Gol(40,"Penal",j,b,p));
        p.registrarEvento(new Remate(45,j,p));
        p.registrarEvento(new Falta(50,j,p));
        p.registrarEvento(tarjeta);
        Jugador suplente = new Jugador("5", "Suplente", "Prueba", new Date(), "DEL", "10", 1.0);
        p.registrarEvento(new Cambio(60,suplente,j,p));
        comprobar("Marcador del recorrido", p.obtenerMarcador().equals("2 - 1"));
        comprobar("Se conservan los siete eventos del recorrido", p.getEventos().size() == 7);
        p.getEventos().clear();
        comprobar("Copia de eventos protege la lista", p.getEventos().size() == 7);
        p.finalizarPartido();
        comprobar("Partido finalizado", p.getEstado() == EstadoPartido.FINALIZADO);
        rechazar("Evento despues del cierre", new Accion() { public void ejecutar() { p.registrarEvento(new Falta(90,j,p)); } }, true);
        System.out.println("TOTAL: " + cantidad + " comprobaciones correctas");
    }
}
