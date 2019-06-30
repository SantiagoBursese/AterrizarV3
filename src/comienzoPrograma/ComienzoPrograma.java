package comienzoPrograma;

import Vistas.Bienvenida;
import java.awt.EventQueue;

public class ComienzoPrograma {
    
    public static void main(String[] args) {
       EventQueue.invokeLater(() -> {
           try {
               Bienvenida frame = new Bienvenida();
               frame.setVisible(true);
           } catch (Exception e) {
               e.printStackTrace();
           }
       });
    }
}