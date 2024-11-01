package Controller;

import Model.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MoveController {
//    public MoveController(List<String> pureMoves) {
//        for(String move : pureMoves) {
//            moves.add(moveTranslation(move));
//        }
//    }

    public boolean player(int count) {
        if (count % 2 == 0) { //es rojo
            return true;
        } else { //es negro
            return false;
        }
    }

    public Move moveTranslation(String pureMove) {
        Move move;
        String destiny;
        if (pureMove.matches("^[NBRQK].*"))
            destiny = pureMove.substring(1);
        else
            destiny = pureMove.substring(0);

        int destinyCol = obtenerDestinoCol(destiny);
        int destinyRow = obtenerDestinoRow(destiny);
        char pieceTypeChar = pureMove.charAt(0);
        move = new Move(pieceTypeChar, destinyCol, destinyRow);
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

//    public List<Move> getMoves() {
//        return moves;
//    }
//
//    public Move getMove(int count){
//        if (count >= 0 && count < moves.size()) {
//            return moves.get(count);
//        } else {
//            System.out.println("Índice fuera de rango");
//            return null;
//        }
//    }



}
