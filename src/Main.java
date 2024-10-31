import Controller.ControladorMenu;
import Model.MovementsAnalyzer;
import View.ChessBoard;
import View.Menu;

import javax.swing.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {

        File file = new File ("src\\Partidas\\pruebaConFe.txt");
        MovementsAnalyzer reader = new MovementsAnalyzer(file);
        reader.Lecture();
        reader.imprimirMovimientos();

        JFrame Ajedrez = new JFrame("Vizor de partidas de ajedrez");
        Ajedrez.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Ajedrez.setSize(600, 600);

        ChessBoard chessBoard = new ChessBoard();
        Ajedrez.add(chessBoard);
        Ajedrez.setVisible(true);

        //TraductorMovimientos traductor = new TraductorMovimientos(reader, chessBoard);

        JFrame menu = new JFrame("Menu juego");

        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(300, 300);
        Menu meniu = new Menu();
        menu.add(meniu);
        menu.setVisible(true);
        //ControladorMenu controladorMenu = new ControladorMenu(meniu, reader, traductor);
    }

}