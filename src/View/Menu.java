package View;
import javax.swing.*;
import java.awt.*;
public class Menu extends JPanel {
    private JButton next;
    private JButton prev;
    public Menu() {
        setLayout(null);
        next = new JButton("Next");
        prev = new JButton("Prev");

        next.setContentAreaFilled(true);
        next.setBorderPainted(true);
        next.setFocusPainted(false);

        prev.setContentAreaFilled(true);
        prev.setBorderPainted(true);
        prev.setFocusPainted(false);

        // Asegurar un color visible para el texto
        next.setForeground(Color.BLACK);
        prev.setForeground(Color.BLACK);

        // Establecer color de fondo de ser necesario
        next.setBackground(Color.LIGHT_GRAY);
        prev.setBackground(Color.LIGHT_GRAY);

        // Establecer tamaño y posición del botón
        next.setBounds(100,100,80,58);
        prev.setBounds(100,170,80,58);

        // Registrar los botones para escuchar eventos de clic
        this.add(next);
        this.add(prev);

        next.setVisible(true);
        prev.setVisible(true);
    }
    public JButton getNextButton() {
        return next;
    }
    public JButton getPrevButton() {
        return prev;
    }
}
