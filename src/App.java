import java.util.ArrayList;
import javax.swing.JOptionPane;

public class App {

    public static void main(String[] args) throws Exception {
        String FILE_NAME = "coches.dat";
        ArrayList<Coche> catalogoCoches = AppUtils.cargarCoches(FILE_NAME);
        String[] opciones = { "1. Añadir coche", "2. Borrar coche", "3. Calcular la rentabilidad",
                "4. Salir del programa" };
        int opcionUsuario = -1;

        while (opcionUsuario != 3) {
            opcionUsuario = JOptionPane.showOptionDialog(
                    null,
                    "Selecciona una opción:",
                    "Menú Principal",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);
            switch (opcionUsuario) {
                case 0:
                    AppUtils.añadirCoche(catalogoCoches);
                    break;
                case 1:
                    AppUtils.borrarCoche(catalogoCoches);
                    break;
                case 2:
                    AppUtils.calcularRentabilidad(catalogoCoches);
                    break;
                case 3:
                    AppUtils.guardarCoches(catalogoCoches, FILE_NAME);
                    JOptionPane.showConfirmDialog(null, "Cierre del programa");
                    System.exit(1);
                default:
                    AppUtils.guardarCoches(catalogoCoches, FILE_NAME);
                    System.exit(1);
                    break;
            }
        }

    }
}
