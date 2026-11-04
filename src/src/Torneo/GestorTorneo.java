
package Torneo;

import Torneo.Torneo;
import Club.Club;
import Partido.Partido;

public class GestorTorneo {

    public void inscribirClub(Torneo torneo, Club club) {
        torneo.inscribirClub(club);
    }

    public void programarPartido(Torneo torneo, Partido partido) {
        torneo.programarPartido(partido);
    }

    public void iniciarTorneo(Torneo torneo) {
        torneo.iniciarTorneo();
    }

    public void finalizarTorneo(Torneo torneo) {
        torneo.finalizarTorneo();
    }

    public void eliminarClub(Torneo torneo, Club club) {
        torneo.eliminarClub(club);
    }
}
