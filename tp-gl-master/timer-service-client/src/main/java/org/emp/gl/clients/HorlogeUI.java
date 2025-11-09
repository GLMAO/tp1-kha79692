package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;
import java.beans.PropertyChangeEvent;
import javax.swing.*;
import java.awt.*;

public class HorlogeUI extends JFrame implements TimerChangeListener {

    private final JLabel labelHeure = new JLabel("00:00:00", SwingConstants.CENTER);
    private final TimerService timerService;

    public HorlogeUI(TimerService timerService) {
        this.timerService = timerService;
        this.timerService.addTimeChangeListener(this);

        setTitle("Horloge");
        setSize(250, 120);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        labelHeure.setFont(new Font("Monospaced", Font.BOLD, 28));
        add(labelHeure, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(SECONDE_PROP)
                || evt.getPropertyName().equals(MINUTE_PROP)
                || evt.getPropertyName().equals(HEURE_PROP)) {

            SwingUtilities.invokeLater(() -> {
                String heure = String.format("%02d:%02d:%02d",
                        timerService.getHeures(),
                        timerService.getMinutes(),
                        timerService.getSecondes());
                labelHeure.setText(heure);
            });
        }
    }
}
