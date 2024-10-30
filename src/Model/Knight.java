package Model;

public class Knight extends Piece{
    public Knight(int positionRow, int positionColumn) {
        super(positionRow, positionColumn);
    }

    @Override
    public boolean validPath(int final_x, int final_y) {
        return false;
    }


    public void move(String destino, int indice) {
    }

}
