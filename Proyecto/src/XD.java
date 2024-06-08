import javax.swing.JFrame;
import javax.swing.JLabel;

public class XD {
    public static void main(String[] args) throws Exception {

        JFrame frame = new JFrame("Mi primera ventana en Java");
        frame.setSize(300, 200); // Establecer el tamaño de la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar la aplicación al cerrar la ventana
        JLabel label = new JLabel("¡Hola, Mundo!"); // Crear un JLabel
        frame.add(label); // Agregar el JLabel al JFrame
        frame.setVisible(true); // Hacer visible la ventana

    }
}
