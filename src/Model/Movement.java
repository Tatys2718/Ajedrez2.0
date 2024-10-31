package Model;

public class Movement {
    private Piece piece;
    private int destinoCol;
    private int destinoRow;

    public Movement(Piece piece, int destinoCol, int destinoRow) {
        this.piece = piece;
        this.destinoCol = destinoCol;
        this.destinoRow = destinoRow;
    }

    public Piece getPiece() {
        return piece;
    }

    public int getDestinoCol() {
        return destinoCol;
    }

    public int getDestinoRow() {
        return destinoRow;
    }

    @Override
    public String toString() {
        return piece + " to " + destinoCol + "," + destinoRow;
    }
}

