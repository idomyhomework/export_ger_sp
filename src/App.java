import java.util.ArrayList;
import javax.swing.JOptionPane;

public class App {
    public static Integer saveDataInteger(String message) {
        boolean correct = false;
        int userInputInt = 0;
        do {
            String userInput = JOptionPane.showInputDialog(null, message);
            try {
                userInputInt = Integer.valueOf(userInput);
                correct = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } while (correct == false);

        return userInputInt;
    }

    public static Double saveDataDouble(String message) {
        boolean correct = false;
        double userInputDouble = 0.0;
        do {
            String userInput = JOptionPane.showInputDialog(null, message);
            try {
                userInputDouble = Double.valueOf(userInput);
                correct = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } while (!correct);

        return userInputDouble;
    }

    public static void añadirCoche(ArrayList<Coche> catalogo) {
        String marca = JOptionPane.showInputDialog(null, "Introduce la marca del coche: ");
        System.out.println();
        String modelo = JOptionPane.showInputDialog(null, "Introduce el modelo del coche: ");
        System.out.println();
        int precioSpain = saveDataInteger("Introduce el precio en España");
        System.out.println();
        int precioAlemania = saveDataInteger("Introduce el precio en Alemania: ");
        double contaminacion = saveDataDouble("Cuanto contamina el coche? (en g/km de CO₂)");
        String urlSpain = JOptionPane.showInputDialog(null, "Donde has visto el coche? (url Spain)");
        String urlGermany = JOptionPane.showInputDialog(null, "Donde has visto el coche? (url Alemania)");
        int respuesta = JOptionPane.showConfirmDialog(
                null,
                "Quieres editar los impuestos? En caso contrario dejamos los impuestos por defecto.",
                "Confirmación",
                JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Has elegido SÍ");
            int tasaMatriculaGer = saveDataInteger("Introduce la tasa de matrícula en Alemania");
            int placasFisicas = saveDataInteger("Introduce el importe de las placas fisicas (Alemania)");
            int seguroTemporal = saveDataInteger("Introduce el importe del seguro temporal: ");
            int tuv = saveDataInteger("Introduce el importe del TuV: ");
            TaxesGermany txGer = new TaxesGermany(tasaMatriculaGer, placasFisicas, seguroTemporal, tuv);
            JOptionPane.showMessageDialog(null, "Los impuestos alemanes han sido guardados.");
            int ivtm = saveDataInteger("Introduce el importe: ");
            int itv = saveDataInteger("Introduce el importe del ITV");
            int dgt = saveDataInteger("Introduce el importe del DGT");
            int placas = saveDataInteger("Cuanto te han costado las placas?");
            TaxesSpain txSpain = new TaxesSpain(ivtm, itv, dgt, placas, 0.0);
            txSpain.setIEDMT(contaminacion, precioAlemania);
            Coche coche = new Coche(modelo, marca, precioAlemania, precioSpain, contaminacion, txGer,
                    txSpain, urlGermany, urlSpain);
            JOptionPane.showConfirmDialog(null, "El coche fue añadido con éxito!");
            catalogo.add(coche);
        } else if (respuesta == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Has elegido NO");
            TaxesGermany txGer = new TaxesGermany();
            TaxesSpain txSpain = new TaxesSpain();
            Coche coche = new Coche(modelo, marca, precioAlemania, precioSpain, contaminacion, txGer,
                    txSpain, urlGermany, urlSpain);
            catalogo.add(coche);
            JOptionPane.showConfirmDialog(null, "El coche fue añadido con éxito!");
        }
    }

    public static void borrarCoche(ArrayList<Coche> catalogo) {
        if (catalogo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay ningún coche en la lista");
            return;
        }

        String[] nombresCoches = new String[catalogo.size()];
        for (int i = 0; i < nombresCoches.length; i++) {
            Coche c = catalogo.get(i);
            nombresCoches[i] = (i + 1) + "." + " " + c.getMarca() + " " + c.getModelo();
        }

        String cocheSeleccionado = (String) JOptionPane.showInputDialog(
                null,
                "Elige el coce que quieres borrar: ",
                "Borrar coche",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nombresCoches,
                nombresCoches[0]);

        if (cocheSeleccionado == null) {
            return;
        }

        int indice = -1;
        for (int i = 0; i < nombresCoches.length; i++) {
            if (nombresCoches[i].equals(cocheSeleccionado)) {
                indice = i;
                break;
            }
        }

        if (indice != 1) {
            Coche c = catalogo.get(indice);
            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Seguro que quieres borrar " + c.getMarca() + " " + c.getModelo() + " ?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                catalogo.remove(indice);
                JOptionPane.showMessageDialog(null, "Borrado completado!");
            }
        }

    }

    public static void main(String[] args) throws Exception {
        ArrayList<Coche> catalogoCoches = new ArrayList<>();
        String[] opciones = { "1. Añadir coche", "2. Borrar coche", "3. Salir del programa" };
        int opcionUsuario = -1;

        while (opcionUsuario != 2) {
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
                    añadirCoche(catalogoCoches);
                    break;
                case 1:
                    borrarCoche(catalogoCoches);
                    break;
                case 2:
                    JOptionPane.showConfirmDialog(null, "Cierre del programa");
                    System.exit(1);
                default:
                    break;
            }
        }

    }
}
