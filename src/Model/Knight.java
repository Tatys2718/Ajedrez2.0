package Model;
import javax.swing.*;

public class Knight extends Piece{
    public Knight(int positionRow, int positionColumn, ImageIcon image) {
        super(positionRow, positionColumn, image);
    }

    @Override
    public boolean validPath(int final_x, int final_y) {
        return false;
    }


    public void move(String destino, int indice) {
    }

}
