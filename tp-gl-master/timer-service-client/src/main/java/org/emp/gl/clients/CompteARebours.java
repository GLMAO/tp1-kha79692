package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;
import java.beans.PropertyChangeEvent;

public class CompteARebours implements TimerChangeListener {

    private String name;
    private int compteur; // countdown value
    private TimerService timerService;

    public CompteARebours(String name, int valeurInitiale) {
        this.name = name;
        this.compteur = valeurInitiale;
        System.out.println("Compte à rebours " + name + " démarré à " + valeurInitiale);
    }

    public void setTimerService(TimerService timerService) {
        this.timerService = timerService;
        timerService.addTimeChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(SECONDE_PROP)) {
            compteur--;
            System.out.println(name + " : Il reste " + compteur + " secondes");

            if (compteur <= 0) {
                System.out.println("⏰ Temps écoulé pour: "+name+" !");
                timerService.removeTimeChangeListener(this);
            }
        }
    }
}
