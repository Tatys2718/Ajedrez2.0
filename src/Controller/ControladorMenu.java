package Controller;
import View.Menu;
import Model.MovementsAnalyzer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorMenu implements ActionListener {
    private Menu menu;
    private MovementsAnalyzer readerPGN;
    private int contadorClicks = 0;

    public ControladorMenu(Menu menu, MovementsAnalyzer readerPGN) {
        this.menu = menu;
        this.readerPGN = readerPGN;

        // Registrar los escuchadores de los botones
        this.menu.getNextButton().addActionListener(this);
        this.menu.getPrevButton().addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu.getNextButton()) {
            if (contadorClicks < readerPGN.getMovements().size()) {  // Verifica que no exceda el tamaño
                readerPGN.getMovement(contadorClicks);
                contadorClicks++;
            } else {
                System.out.println("No hay más movimientos.");
            }
        }
    }
}
