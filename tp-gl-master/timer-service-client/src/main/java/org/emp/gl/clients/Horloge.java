package org.emp.gl.clients ; 

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService ; 
// AJOUTER CETTE LIGNE EN HAUT
import java.beans.PropertyChangeEvent;

public class Horloge implements TimerChangeListener{

    private String name ; 
    private TimerService timerService ; 


    public Horloge (String name, TimerService timerService) {
        this.name = name ; 
        this.timerService = timerService;
        this.timerService.addTimeChangeListener(this);  // s'abonner
        System.out.println("Horloge " + name + " initialized!");
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
       if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())) {
        afficherHeure();
     }
    }

    public void afficherHeure () {
        if (timerService != null)
            System.out.println (name + " affiche " + 
                                timerService.getHeures() +":"+
                                timerService.getMinutes()+":"+
                                timerService.getSecondes()) ;
    }

}
