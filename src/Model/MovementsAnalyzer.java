package Model;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class MovementsAnalyzer {
    private ImageIcon alfilRojo = new ImageIcon(getClass().getResource("/View/utils/images/alfilRojo.png"));
    private ImageIcon alfilNegro = new ImageIcon(getClass().getResource("/View/utils/images/alfilNegro.png"));
    private ImageIcon caballoRojo = new ImageIcon(getClass().getResource("/View/utils/images/caballoRojo.png"));
    private ImageIcon caballoNegro = new ImageIcon(getClass().getResource("/View/utils/images/caballoNegro.png"));
    private ImageIcon peonNegro = new ImageIcon(getClass().getResource("/View/utils/images/peonNegro.png"));
    private ImageIcon peonRojo = new ImageIcon(getClass().getResource("/View/utils/images/peonRojo.png"));
    private ImageIcon reinaNegra = new ImageIcon(getClass().getResource("/View/utils/images/reinaNegra.png"));
    private ImageIcon reinaRoja = new ImageIcon(getClass().getResource("/View/utils/images/reinaRoja.png"));
    private ImageIcon reyNegro = new ImageIcon(getClass().getResource("/View/utils/images/reyNegro.png"));
    private ImageIcon reyRojo = new ImageIcon(getClass().getResource("/View/utils/images/reyRojo.png"));
    private ImageIcon torreNegra = new ImageIcon(getClass().getResource("/View/utils/images/torreNegra.png"));
    private ImageIcon torreRoja = new ImageIcon(getClass().getResource("/View/utils/images/torreRoja.png"));

    private File file;
    private List<Movement> movements = new ArrayList<Movement>();
    private Map<String, String> metadatos = new HashMap<>();
    private Pattern patron = Pattern.compile("\\[(\\w+)\\s+\"(.*)\"\\]");

    public MovementsAnalyzer(File file){
        this.file = file;
    }

    public void Lecture() {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                // Lee la línea una vez
                String linea = scanner.nextLine();

                Matcher matcher = patron.matcher(linea);
                if (matcher.find()) {
                    // Si es un metadato, guárdalo en el mapa
                    metadatos.put(matcher.group(1), matcher.group(2));
                } else {
                    // Si no es un metadato, trata la línea como movimientos
                    String[] partes = linea.split("\\s+");
                    for (String parte : partes) {
                        if (!parte.matches("\\d+\\.")) {  // Ignora números de movimiento (1., 2., etc.)
                            movementTranslation(parte);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found");
        }
    }
    public void movementTranslation(String movement) {
        Piece piece = null;
        String destiny;
        boolean isWhite = false;
        if (movement.matches("^[NBRQK].*")) {
            destiny = movement.substring(1);
            int destinyCol = obtenerDestinoCol(destiny);
            int destinyRow = obtenerDestinoRow(destiny);
            char pieceTypeChar = movement.charAt(0);

            switch (pieceTypeChar) {
                case 'N': piece = new Knight(destinyRow, destinyCol, isWhite, isWhite ? caballoRojo : caballoNegro );
                break;
                case 'B': piece = new Bishop(destinyRow, destinyCol, isWhite, isWhite ? alfilRojo : alfilNegro);
                break;
                case 'R': piece = new Rook(destinyRow, destinyCol, isWhite, isWhite ? torreRoja : torreNegra);
                break;
                case 'Q': piece = new Queen(destinyRow, destinyCol, isWhite, isWhite ? reinaRoja : reinaNegra);
                break;
                case 'K': piece = new King(destinyRow, destinyCol, isWhite, isWhite ? reyRojo : reyNegro);
                break;
            }
            // Aqui se crea el movement
            Movement move = new Movement(piece, destinyCol, destinyRow);
            // Aqui se almacena el movimiento
            movements.add(move);
        }else{
            destiny = movement.substring(0);
            int destinyCol = obtenerDestinoCol(destiny);
            int destinyRow = obtenerDestinoRow(destiny);
            piece = new Pawn(destinyRow, destinyCol, isWhite, isWhite ? peonRojo : peonNegro);
            // Aqui se crea el movement
            Movement move = new Movement(piece, destinyCol, destinyRow);
            // Aqui se almacena el movimiento
            movements.add(move);
        }
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

    public void imprimirMetadatos(){
        for (Map.Entry<String, String> entrada : metadatos.entrySet()) {
            System.out.println(entrada.getKey() + ": " + entrada.getValue());
        }
    }
    public List<Movement> getMovements() {
        return movements;
    }

    public Movement getMovement(int count){
        if (count >= 0 && count < movements.size()) {
            return movements.get(count);
        } else {
            System.out.println("Índice fuera de rango");
            return null;
        }
    }

    public void imprimirMovimientos(){
        for (Movement movimiento : movements) {
            System.out.print(movimiento + " ");
        }
    }
}
