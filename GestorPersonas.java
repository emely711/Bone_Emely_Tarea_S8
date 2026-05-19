package academia;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase que gestiona las operaciones CRUD sobre la lista de personas.
 * Separa la lógica de negocio del menú principal.
 */
public class GestorPersonas {

    // ArrayList que almacena objetos de tipo Persona (polimorfismo)
    private ArrayList<Persona> listaPersonas;
    private Scanner scanner;

    public GestorPersonas(Scanner scanner) {
        this.listaPersonas = new ArrayList<>();
        this.scanner = scanner;
    }

    // ══════════════════════════════════════════════════════════════════════════
    // CREATE – Registrar persona
    // ══════════════════════════════════════════════════════════════════════════

    /**
     * Solicita datos al usuario y agrega una nueva Persona al ArrayList.
     */
    public void registrarPersona() {
        System.out.println("\n  Seleccione tipo de persona:");
        System.out.println("  1. Estudiante");
        System.out.println("  2. Docente");
        System.out.print("  Opción: ");

        int tipo = leerEntero();
        if (tipo != 1 && tipo != 2) {
            System.out.println("  ⚠ Error: opción inválida.");
            return;
        }

        // Leer campos comunes con validación de campo vacío
        String cedula = leerCampoObligatorio("Cédula");
        if (cedula == null) return;

        // Reto adicional: verificar cédula duplicada
        if (existeCedula(cedula)) {
            System.out.println("Error: ya existe una persona registrada con esa cédula.");
            return;
        }

        String nombre = leerCampoObligatorio("Nombre completo");
        if (nombre == null) return;

        System.out.print("  Ingrese edad: ");
        int edad = leerEntero();
        if (edad <= 0 || edad > 120) {
            System.out.println("  ⚠ Error: edad inválida.");
            return;
        }

        if (tipo == 1) {
            // Registrar Estudiante
            String carrera = leerCampoObligatorio("Carrera");
            if (carrera == null) return;
            listaPersonas.add(new Estudiante(cedula, nombre, edad, carrera));

        } else {
            // Registrar Docente
            String asignatura = leerCampoObligatorio("Asignatura");
            if (asignatura == null) return;
            listaPersonas.add(new Docente(cedula, nombre, edad, asignatura));
        }

        System.out.println("Registro agregado correctamente.");
    }

    // ══════════════════════════════════════════════════════════════════════════
    // READ – Mostrar todos los registros
    // ══════════════════════════════════════════════════════════════════════════

    /**
     * Recorre el ArrayList y muestra los datos de cada persona.
     */
    public void mostrarTodos() {
        if (listaPersonas.isEmpty()) {
            System.out.println("No hay registros almacenados.");
            return;
        }

        System.out.println("\n  Total de registros: " + listaPersonas.size());

        // Reto adicional: contar por tipo
        long totalEstudiantes = listaPersonas.stream()
                .filter(p -> p instanceof Estudiante).count();
        long totalDocentes = listaPersonas.stream()
                .filter(p -> p instanceof Docente).count();
        System.out.println("  Estudiantes: " + totalEstudiantes
                + "  |  Docentes: " + totalDocentes + "\n");

        // Recorrido con for e índice para facilitar UPDATE/DELETE
        for (int i = 0; i < listaPersonas.size(); i++) {
            System.out.println("  [" + (i + 1) + "]");
            listaPersonas.get(i).mostrarDatos();
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    // UPDATE – Actualizar registro existente
    // ══════════════════════════════════════════════════════════════════════════

    /**
     * Actualiza los datos de un registro según su posición en la lista.
     */
    public void actualizarRegistro() {
        if (listaPersonas.isEmpty()) {
            System.out.println("No hay registros para actualizar.");
            return;
        }

        mostrarTodos();
        System.out.print("\n  Ingrese el número del registro a actualizar: ");
        int pos = leerEntero() - 1; // Convertir a índice base 0

        if (pos < 0 || pos >= listaPersonas.size()) {
            System.out.println("Error: registro no encontrado.");
            return;
        }

        Persona persona = listaPersonas.get(pos);

        String nuevoNombre = leerCampoObligatorio("Nuevo nombre completo");
        if (nuevoNombre == null) return;

        System.out.print("  Nueva edad: ");
        int nuevaEdad = leerEntero();
        if (nuevaEdad <= 0 || nuevaEdad > 120) {
            System.out.println("Error: edad inválida.");
            return;
        }

        persona.setNombreCompleto(nuevoNombre);
        persona.setEdad(nuevaEdad);

        // Actualizar campo específico según tipo
        if (persona instanceof Estudiante est) {
            String nuevaCarrera = leerCampoObligatorio("Nueva carrera");
            if (nuevaCarrera == null) return;
            est.setCarrera(nuevaCarrera);

        } else if (persona instanceof Docente doc) {
            String nuevaAsignatura = leerCampoObligatorio("Nueva asignatura");
            if (nuevaAsignatura == null) return;
            doc.setAsignatura(nuevaAsignatura);
        }

        // set() reemplaza el objeto en la misma posición del ArrayList
        listaPersonas.set(pos, persona);
        System.out.println("Registro actualizado correctamente.");
    }

    // ══════════════════════════════════════════════════════════════════════════
    // DELETE – Eliminar registro
    // ══════════════════════════════════════════════════════════════════════════

    /**
     * Elimina un registro con confirmación previa (reto adicional).
     */
    public void eliminarRegistro() {
        if (listaPersonas.isEmpty()) {
            System.out.println("No hay registros para eliminar.");
            return;
        }

        mostrarTodos();
        System.out.print("\n  Ingrese el número del registro a eliminar: ");
        int pos = leerEntero() - 1;

        if (pos < 0 || pos >= listaPersonas.size()) {
            System.out.println("Error: registro no encontrado.");
            return;
        }

        // Reto adicional: confirmación antes de eliminar
        System.out.println("Está seguro de que desea eliminar a "
                + listaPersonas.get(pos).getNombreCompleto() + "? (s/n): ");
        String confirmacion = scanner.nextLine().trim().toLowerCase();

        if (confirmacion.equals("s")) {
            listaPersonas.remove(pos); // remove() por índice
            System.out.println("Registro eliminado correctamente.");
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    // BÚSQUEDA – Reto adicional: buscar por cédula
    // ══════════════════════════════════════════════════════════════════════════

    /**
     * Busca y muestra una persona según su número de cédula.
     */
    public void buscarPorCedula() {
        System.out.print("  Ingrese la cédula a buscar: ");
        String cedula = scanner.nextLine().trim();

        boolean encontrado = false;
        for (Persona p : listaPersonas) {
            if (p.getCedula().equals(cedula)) {
                p.mostrarDatos();
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró ninguna persona con esa cédula.");
        }
    }

    // MÉTODOS AUXILIARES DE VALIDACIÓN

    /**
     * Lee un entero desde consola. Si la entrada no es numérica retorna -1
     * y muestra el mensaje de error, sin cerrar el programa (try-catch).
     */
    private int leerEntero() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Error: debe ingresar solo números.");
            return -1;
        }
    }

    /**
     * Lee un campo de texto y valida que no esté vacío.
     * Retorna null si el campo está vacío (señal para abortar el registro).
     */
    private String leerCampoObligatorio(String campo) {
        System.out.print("  Ingrese " + campo + ": ");
        String valor = scanner.nextLine().trim();
        if (valor.isEmpty()) {
            System.out.println("Error: " + campo + " es un campo obligatorio.");
            return null;
        }
        return valor;
    }

    /**
     * Verifica si ya existe una persona con la misma cédula (reto adicional).
     */
    private boolean existeCedula(String cedula) {
        for (Persona p : listaPersonas) {
            if (p.getCedula().equals(cedula)) return true;
        }
        return false;
    }
}
