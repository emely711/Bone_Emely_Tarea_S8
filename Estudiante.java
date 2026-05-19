package academia;


public class Estudiante extends Persona {

    private String carrera;


    public Estudiante(String cedula, String nombreCompleto, int edad, String carrera) {
        super(cedula, nombreCompleto, edad); // Llamada al constructor de Persona
        this.carrera = carrera;
    }

    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }

    @Override
    public String getTipo() { return "Estudiante"; }

    @Override
    public void mostrarDatos() {

        System.out.println("======== ESTUDIANTE =========");
        System.out.println("│ Cédula  : " + cedula);
        System.out.println("│ Nombre  : " + nombreCompleto);
        System.out.println("│ Edad    : " + edad + " años");
        System.out.println("│ Carrera : " + carrera);
        System.out.println("=================================");
    }
}
