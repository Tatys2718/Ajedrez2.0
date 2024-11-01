package Model;

import javax.swing.*;

public class Pawn extends Piece{
    public Pawn(){};

    public Pawn(int positionRow, int positionColumn, boolean isRed, ImageIcon image) {
        super(positionRow, positionColumn, isRed, image);
    }

    @Override
    public boolean validMove(int destRow, int destColumn) {

        // Movimiento hacia adelante de 1 o 2 casillas desde la posición inicial
        int rowDirection = isRed() ? 1 : -1; // Corregido: blanco va hacia arriba, negro hacia abajo

        // Movimiento de 1 casilla adelante
        if (getPositionColumn() == destColumn && getPositionRow() + rowDirection == destRow) {
            return true;
        }

        // Movimiento de 2 casillas adelante desde la posición inicial
        if ((isRed() && getPositionRow() == 2 || !isRed() && getPositionRow() == 7) &&
                getPositionColumn() == destColumn && getPositionRow() + 2 * rowDirection == destRow) {
            return true;
        }

        // Movimiento de captura en diagonal
        if (Math.abs(getPositionColumn() - destColumn) == 1 && getPositionRow() + rowDirection == destRow) {
            return true;
        }

        // Si no cumple ninguna condición, el movimiento no es válido
        return false;
    }
}
