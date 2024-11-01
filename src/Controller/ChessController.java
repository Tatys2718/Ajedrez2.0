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
        // Piezas negras en filas 6 y 7
        pieces[7][0] = new Rook(7, 0, false, new ImageIcon(getClass().getResource("/View/utils/images/torreNegra.png")));
        pieces[7][1] = new Knight(7, 1, false, new ImageIcon(getClass().getResource("/View/utils/images/caballoNegro.png")));
        pieces[7][2] = new Bishop(7, 2, false, new ImageIcon(getClass().getResource("/View/utils/images/alfilNegro.png")));
        pieces[7][3] = new Queen(7, 3, false, new ImageIcon(getClass().getResource("/View/utils/images/reinaNegra.png")));
        pieces[7][4] = new King(7, 4, false, new ImageIcon(getClass().getResource("/View/utils/images/reyNegro.png")));
        pieces[7][5] = new Bishop(7, 5, false, new ImageIcon(getClass().getResource("/View/utils/images/alfilNegro.png")));
        pieces[7][6] = new Knight(7, 6, false, new ImageIcon(getClass().getResource("/View/utils/images/caballoNegro.png")));
        pieces[7][7] = new Rook(7, 7, false, new ImageIcon(getClass().getResource("/View/utils/images/torreNegra.png")));

        // Piezas rojas en filas 0 y 1
        pieces[0][0] = new Rook(0, 0, true, new ImageIcon(getClass().getResource("/View/utils/images/torreRoja.png")));
        pieces[0][1] = new Knight(0, 1, true, new ImageIcon(getClass().getResource("/View/utils/images/caballoRojo.png")));
        pieces[0][2] = new Bishop(0, 2, true, new ImageIcon(getClass().getResource("/View/utils/images/alfilRojo.png")));
        pieces[0][3] = new Queen(0, 3, true, new ImageIcon(getClass().getResource("/View/utils/images/reinaRoja.png")));
        pieces[0][4] = new King(0, 4, true, new ImageIcon(getClass().getResource("/View/utils/images/reyRojo.png")));
        pieces[0][5] = new Bishop(0, 5, true, new ImageIcon(getClass().getResource("/View/utils/images/alfilRojo.png")));
        pieces[0][6] = new Knight(0, 6, true, new ImageIcon(getClass().getResource("/View/utils/images/caballoRojo.png")));
        pieces[0][7] = new Rook(0, 7, true, new ImageIcon(getClass().getResource("/View/utils/images/torreRoja.png")));

        // Peones en filas 1 y 6
        for (int col = 0; col < 8; col++) {
            pieces[1][col] = new Pawn(1, col, true, new ImageIcon(getClass().getResource("/View/utils/images/peonRojo.png")));
            pieces[6][col] = new Pawn(6, col, false, new ImageIcon(getClass().getResource("/View/utils/images/peonNegro.png")));
        }
    }

    private void updateBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton casilla = chessBoard.getCasilla(row, col);
                if (casilla != null) { // Asegurarse de que casilla no sea nula
                    if (pieces[row][col] != null) {
                        casilla.setIcon(pieces[row][col].getImage());
                    } else {
                        casilla.setIcon(null);
                    }
                }
            }
        }
    }

    public void movePiece(String pureMove, int counter) {
        Move move = movementTranslation(pureMove, counter);
        if (move == null) {
            System.err.println("Error: movimiento no válido.");
            return; // Salir si el movimiento es nulo
        }

        Piece pieceMoved = move.getOrigin();
        if (pieceMoved == null) {
            System.err.println("Error: pieza de origen es nula.");
            return; // Salir si no hay pieza
        }

        int currentRow = move.getOrigin().getPositionRow();
        int currentCol = move.getOrigin().getPositionColumn();
        pieceMoved.changePosition(move.getDestinoRow(), move.getDestinoCol());

        // Asegúrate de que las posiciones sean válidas
        if (move.getDestinoRow() >= 0 && move.getDestinoRow() < 8 &&
                move.getDestinoCol() >= 0 && move.getDestinoCol() < 8) {
            pieces[move.getDestinoRow()][move.getDestinoCol()] = pieceMoved;
            pieces[currentRow][currentCol] = null;
        } else {
            System.err.println("Error: destino fuera de los límites del tablero.");
        }

        updateBoard();
    }

    public boolean player(int count) {
        if (count % 2 == 0) { //es rojo
            return true;
        } else { //es negro
            return false;
        }
    }

    public Move movementTranslation(String pureMove, int counter) {
        Move move = null;
        Piece pieceOrigin = null;
        String destiny;
        int destinyCol = -1; // Inicializa en -1
        int destinyRow = -1; // Inicializa en -1
        boolean isRed = player(counter);
        List<Piece> candidatesOrigin = new ArrayList<>();

        if (pureMove.matches("^[NBRQK].*")) {
            // Procesar piezas distintas al peón
            destiny = pureMove.substring(1);
            destinyCol = getDestinationColumn(destiny);
            destinyRow = getDestinationRow(destiny);

            candidatesOrigin = findOriginPieceCandidates(pureMove, destinyCol, destinyRow, isRed);
        } else {
            // Procesar movimiento de peón
            destiny = pureMove.replaceAll("x|=", ""); // Remover solo "x" y "=" para obtener destino limpio
            destinyCol = getDestinationColumn(destiny);
            destinyRow = getDestinationRow(destiny);
            candidatesOrigin = findOriginPieceCandidates(pureMove, destinyCol, destinyRow, isRed);
        }

        if (candidatesOrigin.size() == 1) {
            pieceOrigin = candidatesOrigin.get(0);
            move = new Move(pieceOrigin, destinyCol, destinyRow);
        } else if (candidatesOrigin.size() > 1) {
            System.err.println("Error: Movimiento ambiguo para la pieza.");
        }

        return move;
    }

    public List<Piece> findOriginPieceCandidates(String pureMove, int destinyCol, int destinyRow, boolean isRed) {
        List<Piece> candidatesOrigin = new ArrayList<>();
        char pieceTypeChar = pureMove.matches("^[NBRQK].*") ? pureMove.charAt(0) : 'P';
        int originCol = getOriginColumn(pureMove);
        int originRow = getOriginRow(pureMove);

        // Filtrar piezas en el tablero
//        for (Piece[] piecesVector : pieces) {
//            for (Piece individualPiece : piecesVector) {
//                if(individualPiece != null) { // Validar que individualPiece no sea nulo
//                    // Verificar si la pieza pertenece al jugador correcto
//                    if (isRed == individualPiece.isRed() && matchesPieceType(pieceTypeChar, individualPiece)) {
//                        System.out.println();
//                        // Comprobar si el movimiento es válido
//                        if (individualPiece.validMove(destinyRow, destinyCol)) {
//                            // Aplicar restricciones de columna o fila en caso de ambigüedad
//                            if (isOriginValid(originCol, originRow, individualPiece)) {
//                                candidatesOrigin.add(individualPiece);
//                            }
//                        }
//                    }
//                }
//            }
//        }

        for (Piece[] piecesVector : pieces) {
            for (Piece individualPiece : piecesVector) {
                if (individualPiece != null) { // Validar que individualPiece no sea nulo
                    System.out.println("Evaluando pieza: " + individualPiece.getClass().getSimpleName() +
                            " color " + (individualPiece.isRed() ? "rojo" : "negro") +
                            " en posición (" + individualPiece.getPositionRow() + ", " + individualPiece.getPositionColumn() + ")");

                    // Verificar si la pieza pertenece al jugador correcto
                    if (isRed == individualPiece.isRed() && matchesPieceType(pieceTypeChar, individualPiece)) {
                        System.out.println("Pieza pertenece al jugador correcto: " + (isRed ? "Rojo" : "Negro"));

                        // Comprobar si el movimiento es válido
                        if (individualPiece.validMove(destinyRow, destinyCol)) {
                            System.out.println("Movimiento válido hacia destino: (" + destinyRow + ", " + destinyCol + ")");

                            // Aplicar restricciones de columna o fila en caso de ambigüedad
                            if (isOriginValid(originCol, originRow, individualPiece)) {
                                System.out.println("Origen válido: (" + originCol + ", " + originRow + ")");
                                candidatesOrigin.add(individualPiece);
                            } else {
                                System.out.println("Origen no válido para la pieza: " + individualPiece.getClass().getSimpleName());
                            }
                        } else {
                            System.out.println("Movimiento no válido hacia destino: (" + destinyRow + ", " + destinyCol + ")");
                        }
                    } else {
                        System.out.println("Pieza no pertenece al jugador correcto.");
                    }
                } else {
                    System.out.println("Pieza es nula, omitiendo.");
                }
            }
        }
        return candidatesOrigin;
    }

    private int getOriginColumn(String pureMove) {
        if (pureMove.length() == 4 && pureMove.charAt(1) == 'x') {
            return pureMove.charAt(0) - 'a'; // Si hay una "x" en el movimiento
        } else if (pureMove.length() >= 3) {
            return pureMove.charAt(1) - 'a'; // Columna si se especifica
        }
        return -1; // Sin columna de origen
    }

    private int getOriginRow(String pureMove) {
        if (pureMove.length() >= 3) {
            char potentialRow = pureMove.charAt(pureMove.length() - 2);
            return 8 - Character.getNumericValue(potentialRow); // Ajustar para la orientación del tablero
        }
        return -1; // Sin fila de origen
    }

    private boolean isOriginValid(int originCol, int originRow, Piece individualPiece) {
        return (originCol == -1 || individualPiece.getPositionColumn() == originCol) &&
                (originRow == -1 || individualPiece.getPositionRow() == originRow);
    }


    private boolean matchesPieceType(char pieceTypeChar, Piece piece) {
        switch (pieceTypeChar) {
            case 'N': return piece instanceof Knight;
            case 'B': return piece instanceof Bishop;
            case 'R': return piece instanceof Rook;
            case 'Q': return piece instanceof Queen;
            case 'K': return piece instanceof King;
            case 'P': return piece instanceof Pawn;
            default: return false;
        }
    }


    public int getDestinationColumn(String destination) {
        if (destination == null || destination.isEmpty())
            throw new IllegalArgumentException("Invalid destination");

        char destinationColumn = destination.charAt(0);
        return destinationColumn - 'a';
    }

    public int getDestinationRow(String destination) {
        if (destination == null || destination.length() < 2)
            throw new IllegalArgumentException("Invalid destination");

        int destinationRow = Integer.parseInt(destination.substring(1));
        return 8 - destinationRow - 1;
    }


}