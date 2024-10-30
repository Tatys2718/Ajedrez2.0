package Controller;
import View.Menu;
import Model.ReaderPGN;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorMenu implements ActionListener {
    private Menu menu;
    private ReaderPGN readerPGN;
    private TraductorMovimientos traductor;
    private int contadorClicks = 0;

    public ControladorMenu(Menu menu, ReaderPGN readerPGN, TraductorMovimientos traductor) {
        this.menu = menu;
        this.readerPGN = readerPGN;
        this.traductor = traductor;

        // Registrar los escuchadores de los botones
        this.menu.getNextButton().addActionListener(this);
        this.menu.getPrevButton().addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu.getNextButton()) {
            if (contadorClicks < readerPGN.getMovimientos().size()) {  // Verifica que no exceda el tamaño
                traductor.traduccionMovimientos(contadorClicks);
                contadorClicks++;
            } else {
                System.out.println("No hay más movimientos.");
            }
        }
    }
}
