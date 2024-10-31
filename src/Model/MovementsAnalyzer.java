package Model;

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
        if (movement.matches("^[NBRQK].*")) {
            destiny = movement.substring(1);
            int destinyCol = obtenerDestinoCol(destiny);
            int destinyRow = obtenerDestinoRow(destiny);
            char pieceTypeChar = movement.charAt(0);

            switch (pieceTypeChar) {
                case 'N': piece = new Knight(destinyRow, destinyCol);
                break;
                case 'B': piece = new Bishop(destinyRow, destinyCol);
                break;
                case 'R': piece = new Rook(destinyRow, destinyCol);
                break;
                case 'Q': piece = new Queen(destinyRow, destinyCol);
                break;
                case 'K': piece = new King(destinyRow, destinyCol);
                break;
                default: piece = new Pawn(destinyRow, destinyCol);
                break; // Peon solo tiene el movimiento y ya
            }
            // Aqui se crea el movement
            Movement move = new Movement(piece, destinyCol, destinyRow);
            // Aqui se almacena el movimiento
            movements.add(move);
        }
    }

    public int obtenerDestinoCol(String destino){
        char columnaDestino = destino.charAt(0);
        int destinoCol = columnaDestino - 'a';
        return destinoCol;
    }

    public int obtenerDestinoRow(String destino){
        int filaDestino = Integer.parseInt(destino.substring(1));
        int destinoRow = 8 - filaDestino; // Ajusta el índice de la fila según la estructura del tablero
        return destinoRow;
    }

    public void imprimirMetadatos(){
        for (Map.Entry<String, String> entrada : metadatos.entrySet()) {
            System.out.println(entrada.getKey() + ": " + entrada.getValue());
        }
    }
    public List<Movement> getMovements() {
        return movements;
    }

    public Movement DoMovement(int count){
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
