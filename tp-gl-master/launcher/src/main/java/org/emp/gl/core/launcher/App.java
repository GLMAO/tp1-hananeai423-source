package org.emp.gl.core.launcher;

import org.emp.gl.time.service.impl.DummyTimeServiceImpl;
import org.emp.gl.clients.Horloge;
import org.emp.gl.clients.CompteARebours;
import org.emp.gl.clients.HorlogeGUI;
import org.emp.gl.timer.service.TimerService;

public class App {

    public static void main(String[] args) {
        testTout();
    }

    // FONCTION DE TEST FINALE
   private static void testTout() {
    System.out.println("DÉMARRAGE DU TEST COMPLET...\n");

    TimerService timerService = new DummyTimeServiceImpl();

    // Créer les horloges console
    new Horloge("Horloge Console 1", timerService);
    new Horloge("Horloge Console 2", timerService);

    // Créer les compteurs à rebours
    new CompteARebours("CR 5s", timerService, 5);
    new CompteARebours("CR 8s", timerService, 8);
    new CompteARebours("CR 3s", timerService, 3);

    // Lancer la GUI
    HorlogeGUI gui = new HorlogeGUI(timerService);
    gui.setVisible(true);

    System.out.println("Application démarrée - La GUI est ouverte");
}
}