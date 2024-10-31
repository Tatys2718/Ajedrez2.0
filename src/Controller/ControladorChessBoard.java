package Controller;

import Model.Piece;
import View.ChessBoard;

import java.util.List;

public class ControladorChessBoard {
    private ChessBoard chessBoard;
    private List<Piece> pieces;
    public ControladorChessBoard(List<Piece> pieces, ChessBoard chessBoard) {
        this.pieces = pieces;
        this.chessBoard = chessBoard;
        initializeView();
    }
    private void initializeView() {

    }
}
