package View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ChessBoard extends JPanel {
    private ImageIcon alfilRojo = new ImageIcon(getClass().getResource("/View/utils/images/alfilRojo.png"));
    private ImageIcon alfilNegro = new ImageIcon(getClass().getResource("/View/utils/images/alfilNegro.png"));
    private ImageIcon caballoRojo = new ImageIcon(getClass().getResource("/View/utils/images/caballoRojo.png"));
    private ImageIcon caballoNegro = new ImageIcon(getClass().getResource("/View/utils/images/caballoNegro.png"));
    private ImageIcon peonNegro = new ImageIcon(getClass().getResource("/View/utils/images/peonNegro.png"));
    private ImageIcon peonRojo = new ImageIcon(getClass().getResource("/View/utils/images/peonRojo.png"));
    private ImageIcon reinaNegra = new ImageIcon(getClass().getResource("/View/utils/images/reinaNegra.png"));
    private ImageIcon reinaRoja = new ImageIcon(getClass().getResource("/View/utils/images/reinaRoja.png"));
    private ImageIcon reyNegro = new ImageIcon(getClass().getResource("/View/utils/images/reyNegro.png"));
    private ImageIcon reyRojo = new ImageIcon(getClass().getResource("/View/utils/images/reyRojo.png"));
    private ImageIcon torreNegra = new ImageIcon(getClass().getResource("/View/utils/images/torreNegra.png"));
    private ImageIcon torreRoja = new ImageIcon(getClass().getResource("/View/utils/images/torreRoja.png"));

    private JButton[][] buttons = new JButton[8][8];

    public ChessBoard() {
        setLayout(new GridLayout(8, 8));

        // Crear los botones y añadirlos al tablero y a la matriz
        for (int fila = 0; fila < 8; fila++) {

            for (int col = 0; col < 8; col++) {

                JButton casilla = new JButton();

                // Alternar colores como un tablero de ajedrez
                if ((fila + col) % 2 == 0) {
                    casilla.setBackground(new Color(125, 0, 32, 255));  // Casilla VINOTINTO
                    casilla.setBorder(new LineBorder(new Color(128, 0, 32), 2));
                } else {
                    casilla.setBackground(Color.BLACK);   // Casilla negra
                    casilla.setBorder(new LineBorder(Color.BLACK, 2));
                }

                buttons[fila][col] = casilla;  // Guardar el botón en la matriz

                this.add(casilla);      // Añadir el botón al panel
            }
        }
    }

    public JButton getCasilla(int fila, int col) {
        if (fila >= 0 && fila < 8 && col >= 0 && col < 8) {
            return buttons[fila][col];
        } else {
            throw new IndexOutOfBoundsException("Coordenadas fuera del tablero");
        }
    }
}
