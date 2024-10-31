package Model;
import javax.swing.*;

public abstract class Piece {
    private int positionRow;
    private int positionColumn;
    private ImageIcon image;
    private boolean isWhite;

    public Piece(int positionRow, int positionColumn, boolean isWhite, ImageIcon image){
        this.positionRow = positionRow;
        this.positionColumn = positionColumn;
        this.isWhite = isWhite;
        this.image = image;
    }
    public ImageIcon getImage(){
        return image;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
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
