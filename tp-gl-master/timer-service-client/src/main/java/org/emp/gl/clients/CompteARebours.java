package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;
import java.beans.PropertyChangeEvent;

public class CompteARebours implements TimerChangeListener {

    private final String name;
    private int valeur;
    private final TimerService timerService;

    public CompteARebours(String name, TimerService timerService , int valeurInitiale ) {
        this.name = name;
        this.valeur = valeurInitiale;
        this.timerService = timerService;
        this.timerService.addTimeChangeListener(this);
        System.out.println(name + " démarré avec " + valeurInitiale);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
     if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())) {
        valeur--;
        System.out.println(name + " : " + valeur);
        if (valeur <= 0) {
            System.out.println(name + " terminé !");
            timerService.removeTimeChangeListener(this);
        }
    }
  }
}