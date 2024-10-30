package Controller;

import Model.*;
import View.ChessBoard;

public class TraductorMovimientos {
    private ReaderPGN reader;
    private ChessBoard Board;

    public TraductorMovimientos(ReaderPGN lector, ChessBoard Board) {
        this.reader = lector;
        //this.Board = Board;
    }
    public Piece traduccionMovimientos(int count) {

        if (count < reader.getMovimientos().size()) { // Verifica que el índice esté dentro del rango
            String movimiento = reader.getMovimientos().get(count);
            Piece piece;
            String destino;

            if (movimiento.matches("^[NBRQK].*")) {
                destino = movimiento.substring(1);  // Extrae el destino de movimiento
                int destinoCol = obtenerDestinoCol(destino);
                int destinoRow = obtenerDestinoRow(destino);
                char tipoPieza = movimiento.charAt(0);

                // Determina el tipo de pieza y crea la instancia correspondiente
                switch (tipoPieza) {
                    case 'N' -> piece = new Knight(destinoRow, destinoCol);
                    case 'R' -> piece = new Rook(destinoRow, destinoCol);
                    case 'K' -> piece = new King(destinoRow, destinoCol);
                    case 'B' -> piece = new Bishop(destinoRow, destinoCol);
                    default -> throw new IllegalStateException("Tipo de pieza desconocido");
                }
            } else {
                destino = movimiento;
                int destinoCol = obtenerDestinoCol(destino);
                int destinoRow = obtenerDestinoRow(destino);
                piece = new Pawn(destinoRow, destinoCol);
            }

//            if (piece.validPath(destinoRow, destinoCol)) {
//                piece.move(destinoRow, destinoCol);
//            } else {
//                System.out.println("Movimiento no válido para " + piece.getClass().getSimpleName());
//            }
            return piece;

        } else {
            System.out.println("No hay más movimientos.");
            return null;
        }
    }

    public int obtenerDestinoCol(String destino){
        char columnaDestino = destino.charAt(0);
        int destinoCol = columnaDestino - 'a';
        return destinoCol;
    }

    public int obtenerDestinoRow(String destino){
        int filaDestino = Integer.parseInt(destino.substring(1));
        int destinoRow = 8 - filaDestino; // Ajusta el índice de la fila según la estructura del tablero
        return destinoRow;
    }


}

