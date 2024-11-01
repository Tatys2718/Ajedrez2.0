package Controller;
import javax.swing.*;
import javax.swing.JButton;
import Model.*;
import View.ChessBoard;

import java.util.ArrayList;
import java.util.List;

public class ChessController {
    private ChessBoard chessBoard;
    private Piece[][] pieces;
    private MoveController moveController = new MoveController();

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
            pieces[6][col] = new Pawn(6, col, true, new ImageIcon(getClass().getResource("/View/utils/images/peonRojo.png")));
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

//    public void movePiece(Move move) {
//        int currentRow = move.getPiece().getPositionRow();
//        int currentCol = move.getPiece().getPositionColumn();
//        move.getPiece().changePosition(move.getDestinoRow(), move.getDestinoCol());
//        pieces[move.getDestinoRow()][move.getDestinoCol()] = move.getPiece();
//        pieces[currentRow][currentCol] = null;
//        updateBoard();
//    }

    public Move movementTranslation(String pureMove) {
        Move move = null;
        Piece pieceOrigin = null;
        String destiny;
        int destinyCol;
        int destinyRow;
        boolean isWhite = false;
        List<Piece> candidatesOrigin = new ArrayList<>();
        if (pureMove.matches("^[NBRQK].*")) {
            destiny = pureMove.substring(1);
            destinyCol = obtenerDestinoCol(destiny);
            destinyRow = obtenerDestinoRow(destiny);
            char pieceTypeChar = pureMove.charAt(0);

//            switch (pieceTypeChar) {
//                case 'N': pieceOrigin = new Knight(destinyRow, destinyCol, isWhite, isWhite ? caballoRojo : caballoNegro );
//                    break;
//                case 'B': pieceOrigin = new Bishop(destinyRow, destinyCol, isWhite, isWhite ? alfilRojo : alfilNegro);
//                    break;
//                case 'R': pieceOrigin = new Rook(destinyRow, destinyCol, isWhite, isWhite ? torreRoja : torreNegra);
//                    break;
//                case 'Q': pieceOrigin = new Queen(destinyRow, destinyCol, isWhite, isWhite ? reinaRoja : reinaNegra);
//                    break;
//                case 'K': pieceOrigin = new King(destinyRow, destinyCol, isWhite, isWhite ? reyRojo : reyNegro);
//                    break;
//            }
//
//            move = new Move(pieceOrigin, destinyCol, destinyRow);
        }else{
            destiny = pureMove.replaceAll("x|=", "");
            int originCol = -1;

            if (pureMove.contains("x")) { // Captura
                originCol = pureMove.charAt(0) - 'a';
            }

            destinyCol = obtenerDestinoCol(destiny);
            destinyRow = obtenerDestinoRow(destiny);

            // Looking for origin
            for(Piece[] piecesVector : pieces) {
                for(Piece individualPiece : piecesVector ) {
                    if (individualPiece.getClass() == Pawn.class && individualPiece.validMove(destinyRow, destinyCol)) {
                        // Si hay columna de origen especificada, filtrarla
                        if (originCol != -1 && individualPiece.getPositionColumn() != originCol) {
                            continue;
                        }
                        candidatesOrigin.add(individualPiece);
                    }
                }
            }
        }

        // Verificar si hay promoción
//        if (pureMove.contains("=")) {
//            char promotionPiece = pureMove.charAt(pureMove.length() - 1); // Obtener pieza de promoción
//            switch (promotionPiece) {
//                case 'Q': pieceOrigin = new Queen(destinyRow, destinyCol, isWhite, isWhite ? reinaRoja : reinaNegra);
//                    break;
//                case 'R': pieceOrigin = new Rook(destinyRow, destinyCol, isWhite, isWhite ? torreRoja : torreNegra);
//                    break;
//                case 'B': pieceOrigin = new Bishop(destinyRow, destinyCol, isWhite, isWhite ? alfilRojo : alfilNegro);
//                    break;
//                case 'N': pieceOrigin = new Knight(destinyRow, destinyCol, isWhite, isWhite ? caballoRojo : caballoNegro);
//                    break;
//                default:
//                    System.err.println("Error: Pieza de promoción no válida.");
//                    return null;
//            }
//        }

        if (candidatesOrigin.size() == 1) {
            pieceOrigin = candidatesOrigin.get(0);
            move = new Move(pieceOrigin, destinyCol, destinyRow);
        } else {
            // Manejar posibles errores si hay más de un origen válido
            System.err.println("Error: Movimiento ambiguo para el peón.");
        }

        return move;
    }

    public int obtenerDestinoCol(String destino){
        if (destino == null || destino.isEmpty())
            throw new IllegalArgumentException("Destino inválido");

        char columnaDestino = destino.charAt(0);
        return columnaDestino - 'a';
    }

    public int obtenerDestinoRow(String destino){
        if (destino == null || destino.length() < 2)
            throw new IllegalArgumentException("Destino inválido");

        int filaDestino = Integer.parseInt(destino.substring(1));
        return 8 - filaDestino;
    }

}