package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

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
    public void propertyChange(String prop, Object oldValue, Object newValue) {
        if (TimerChangeListener.SECONDE_PROP.equals(prop)) {
            // Decrement the counter once per second
            if (compteur > 0) {
                compteur--;
                System.out.println(name + " : " + compteur);
            }

            // When countdown reaches 0, unsubscribe
            if (compteur == 0) {
                System.out.println(name + " terminé !");
                timerService.removeTimeChangeListener(this);
            }
        }
    }
}
