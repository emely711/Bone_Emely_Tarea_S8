package academia;

import java.util.Scanner;

/**
 * Clase principal que contiene el menú interactivo del sistema.
 * Punto de entrada de la aplicación.
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        GestorPersonas gestor = new GestorPersonas(scanner);
        int opcion = 0;

        System.out.println("   SISTEMA DE GESTIÓN ACADÉMICA       ");

        do {
            mostrarMenu();
            System.out.print("  Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                // Validación: el usuario ingresó letras u otro carácter no numérico
                System.out.println("Error: debe ingresar solo números.");
                continue; // Volver al inicio del ciclo sin cerrar
            }

            // Ejecutamos la acción correspondiente según la opción elegida
            switch (opcion) {
                case 1 -> gestor.registrarPersona();
                case 2 -> gestor.mostrarTodos();
                case 3 -> gestor.actualizarRegistro();
                case 4 -> gestor.eliminarRegistro();
                case 5 -> gestor.buscarPorCedula();  // Reto adicional
                case 6 -> System.out.println("\n  Hasta luego. ¡Que tenga un buen día!");
                default -> System.out.println("Error: opción inválida. Intente nuevamente.");
            }

        } while (opcion != 6); // Condición de salida

        scanner.close();
    }

    /**
     * Imprime el menú de opciones en pantalla.
     */
    private static void mostrarMenu() {
        System.out.println("========  MENÚ PRINCIPAL ==============");
        System.out.println(" 1. Registrar persona  ");
        System.out.println(" 2. Mostrar registros  ");
        System.out.println(" 3. Actualizar registro ");
        System.out.println(" 4. Eliminar registro   ");
        System.out.println(" 5. Buscar por cédula  ");
        System.out.println(" 6. Salir ");
        System.out.println("========================================");
    }
}
