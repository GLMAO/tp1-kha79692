package org.emp.gl.core.launcher;

import java.util.Random;
import com.example.lookup.Lookup;
import org.emp.gl.clients.Horloge;
import org.emp.gl.clients.HorlogeUI;
import org.emp.gl.clients.CompteARebours;
import org.emp.gl.timer.service.TimerService;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;

public class App {

    public static void main(String[] args) {
        // Run the test that actually shows the clocks
        //lancerHorloges();

        testCounters();

        DummyTimeServiceImpl timeService = new DummyTimeServiceImpl();
        new HorlogeUI(timeService);


    }

    private static void lancerHorloges() {
        // Create one TimerService (the time source)
        TimerService timerService = new DummyTimeServiceImpl();
        Lookup.getInstance().subscribeService(TimerService.class,timerService);
        // Create multiple Horloges (observers)
        Horloge h1 = new Horloge("A");
        Horloge h2 = new Horloge("B");
        Horloge h3 = new Horloge("C");

        // Inject the same service into each
        /*h1.setTimerService(timerService);
        h2.setTimerService(timerService);
        h3.setTimerService(timerService);*/

        // DummyTimeServiceImpl runs automatically using its internal Timer
        // No need to call start()
    }
    public static void testCounters(){
        TimerService timerService = new DummyTimeServiceImpl();
        Random rand = new Random();

        for (int i = 1; i <= 10; i++) {
            int valInit = 10 + rand.nextInt(11); // [10,20]
            CompteARebours c = new CompteARebours("C" + i, valInit);
            c.setTimerService(timerService);
        }
    }
}
