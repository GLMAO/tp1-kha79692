package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;
import com.example.lookup.Lookup;
import java.beans.PropertyChangeEvent;

public class Horloge implements TimerChangeListener {

    private String name;
    private TimerService timerService;

    public Horloge(String name) {
        this.name = name;
        this.timerService=(TimerService)Lookup.getInstance().getService(TimerService.class);
        if (this.timerService == null) {
            throw new IllegalStateException("TimerService non trouvé dans Lookup");
        }
        this.timerService.addTimeChangeListener(this);
        System.out.println("Horloge " + name + " initialized!");
    }

    // Inject TimerService dependency
    /*public void setTimerService(TimerService timerService) {
        this.timerService = timerService;
        // Subscribe to changes
        timerService.addTimeChangeListener(this);
    }
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Only display the time when a second changes
        if (evt.getPropertyName().equals(SECONDE_PROP)) {
            afficherHeure();
        }
    }

    private void afficherHeure() {
        if (timerService != null) {
            System.out.println(
                    name + " affiche " +
                            String.format("%02d:%02d:%02d",
                                    timerService.getHeures(),
                                    timerService.getMinutes(),
                                    timerService.getSecondes()
                            )
            );
        }
    }
}
