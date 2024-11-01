package Controller;
import View.Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements ActionListener {
    private Menu menu;
    private ReaderPGN readerPGN;
    private ChessController chessController;
    private int contadorClicks = 0;

    public MenuController(Menu menu, ReaderPGN readerPGN, ChessController chessController) {
        this.menu = menu;
        this.readerPGN = readerPGN;
        this.chessController = chessController;

        // Registrar los escuchadores de los botones
        this.menu.getNextButton().addActionListener(this);
        this.menu.getPrevButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu.getNextButton()) {
            if (contadorClicks < readerPGN.getMovements().size()) {
                MoveController moveController = new MoveController();
//                System.out.println(moveController.moveTranslation(readerPGN.getMovements().get(contadorClicks), contadorClicks).toString());
//                chessController.movePiece(moveController.moveTranslation(readerPGN.getMovements().get(contadorClicks), contadorClicks));
                contadorClicks++;
            } else {
                System.out.println("No hay más movimientos.");
            }
        }
    }
}
