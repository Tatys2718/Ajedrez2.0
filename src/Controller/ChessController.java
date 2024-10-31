package Controller;
import javax.swing.*;
import javax.swing.JButton;
import Model.*;
import View.ChessBoard;

public class ChessController {
    private ChessBoard chessBoard;
    private Piece[][] pieces;

    public ChessController(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.pieces = new Piece[8][8];
        initializePieces();
        updateBoard();
    }

    private void initializePieces() {
        pieces[0][0] = new Rook(0, 0, false, new ImageIcon(getClass().getResource("/View/utils/images/torreNegra.png")));
        pieces[0][1] = new Knight(0, 1,false, new ImageIcon(getClass().getResource("/View/utils/images/caballoNegro.png")));
        pieces[0][2] = new Bishop(0, 2, false, new ImageIcon(getClass().getResource("/View/utils/images/alfilNegro.png")));
        pieces[0][3] = new Queen(0, 3, false, new ImageIcon(getClass().getResource("/View/utils/images/reinaNegra.png")));
        pieces[0][4] = new King(0, 4, false, new ImageIcon(getClass().getResource("/View/utils/images/reyNegro.png")));
        pieces[0][5] = new Bishop(0, 5, false, new ImageIcon(getClass().getResource("/View/utils/images/alfilNegro.png")));
        pieces[0][6] = new Knight(0, 6, false, new ImageIcon(getClass().getResource("/View/utils/images/caballoNegro.png")));
        pieces[0][7] = new Rook(0, 7, false, new ImageIcon(getClass().getResource("/View/utils/images/torreNegra.png")));
        pieces[7][0] = new Rook(0, 0, true, new ImageIcon(getClass().getResource("/View/utils/images/torreRoja.png")));
        pieces[7][1] = new Knight(0, 1,true, new ImageIcon(getClass().getResource("/View/utils/images/caballoRojo.png")));
        pieces[7][2] = new Bishop(0, 2, true, new ImageIcon(getClass().getResource("/View/utils/images/alfilRojo.png")));
        pieces[7][3] = new Queen(0, 3, true, new ImageIcon(getClass().getResource("/View/utils/images/reinaRoja.png")));
        pieces[7][4] = new King(0, 4, true, new ImageIcon(getClass().getResource("/View/utils/images/reyRojo.png")));
        pieces[7][5] = new Bishop(0, 5, true, new ImageIcon(getClass().getResource("/View/utils/images/alfilRojo.png")));
        pieces[7][6] = new Knight(0, 6, true, new ImageIcon(getClass().getResource("/View/utils/images/caballoRojo.png")));
        pieces[7][7] = new Rook(0, 7, true, new ImageIcon(getClass().getResource("/View/utils/images/torreRoja.png")));


        for (int col = 0; col < 8; col++) {
            pieces[1][col] = new Pawn(1, col, false, new ImageIcon(getClass().getResource("/View/utils/images/peonNegro.png")));
            pieces[6][col] = new Pawn(6, col, false, new ImageIcon(getClass().getResource("/View/utils/images/peonRojo.png")));
        }
    }
    private void updateBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton casilla = chessBoard.getCasilla(row, col);
                if (pieces[row][col] != null) {
                    casilla.setIcon(pieces[row][col].getImage());
                } else {
                    casilla.setIcon(null);
                }
            }
        }
    }
}
