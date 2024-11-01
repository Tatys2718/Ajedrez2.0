package Model;
import javax.swing.*;

public class Knight extends Piece{
    public Knight(int positionRow, int positionColumn, boolean isWhite, ImageIcon image) {

        super(positionRow, positionColumn, isWhite, image);
    }

    @Override
    public boolean validMove(int final_x, int final_y) {
        return false;
    }


    public void move(String destino, int indice) {
    }

}
