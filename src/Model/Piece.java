package Model;

public abstract class Piece {
    private int positionRow;
    private int positionColumn;

    public Piece(int positionRow, int positionColumn){
        this.positionRow = positionRow;
        this.positionColumn = positionColumn;
    }

    //Comprobar si el movimiento leido es valido para esa ficha
    public boolean validPath (int final_x, int final_y){
        return true;
    };
    //El movimiento que debe tener la ficha
    public void move (int positionRow, int positionColumn){
        this.positionRow = positionRow;
        this.positionColumn = positionColumn;
    }

}
