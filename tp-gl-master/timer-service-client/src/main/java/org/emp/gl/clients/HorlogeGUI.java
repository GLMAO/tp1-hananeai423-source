package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class HorlogeGUI extends JFrame implements TimerChangeListener {

    private final JLabel label;
    private final TimerService timerService;

    public HorlogeGUI(TimerService timerService) {
        this.timerService = timerService;
        
        // Configuration de la fenêtre
        setTitle("Horloge Graphique");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Change pour éviter System.exit
        setSize(300, 150);
        setLocationRelativeTo(null);
        setResizable(false);

        // Configuration du label
        label = new JLabel("00:00:00", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 32));
        label.setForeground(Color.BLUE);
        
        // Ajout au content pane
        getContentPane().setBackground(Color.WHITE);
        getContentPane().add(label, BorderLayout.CENTER);
        
        // S'abonner au service de timer
        this.timerService.addTimeChangeListener(this);
        
        pack();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())) {
            updateTime();
        }
    }

    private void updateTime() {
        // Mettre à jour l'heure dans l'EDT (Event Dispatch Thread)
        if (SwingUtilities.isEventDispatchThread()) {
            displayTime();
        } else {
            SwingUtilities.invokeLater(this::displayTime);
        }
    }

    private void displayTime() {
        String time = String.format("%02d:%02d:%02d",
                timerService.getHeures(),
                timerService.getMinutes(),
                timerService.getSecondes());
        label.setText(time);
    }

    // Méthode pour fermer proprement
    public void close() {
        timerService.removeTimeChangeListener(this);
        dispose();
    }
    
    // Méthode main pour tester indépendamment
    public static void main(String[] args) {
        // Pour tester sans le reste de l'application
        SwingUtilities.invokeLater(() -> {
            // Créer un timer service mock pour le test
            TimerService mockTimer = new TimerService() {
                private int secondes = 0;
                
                @Override
                public int getHeures() { return secondes / 3600; }
                
                @Override
                public int getMinutes() { return (secondes % 3600) / 60; }
                
                @Override
                public int getSecondes() { return secondes % 60; }
                
                @Override
                public void addTimeChangeListener(TimerChangeListener listener) {
                    // Simuler le passage du temps
                    new Timer(1000, e -> {
                        secondes++;
                        listener.propertyChange(
                            new PropertyChangeEvent(this, SECONDE_PROP, secondes-1, secondes)
                        );
                    }).start();
                }
                
                @Override
                public void removeTimeChangeListener(TimerChangeListener listener) {}

                @Override
                public int getDixiemeDeSeconde() {
                    throw new UnsupportedOperationException("Not supported yet.");
                }
            };
            
            HorlogeGUI gui = new HorlogeGUI(mockTimer);
            gui.setVisible(true);
        });
    }
}