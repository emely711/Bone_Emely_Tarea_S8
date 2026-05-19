package academia;


public class Docente extends Persona {

    private String asignatura;

    public Docente(String cedula, String nombreCompleto, int edad, String asignatura) {
        super(cedula, nombreCompleto, edad);
        this.asignatura = asignatura;
    }

    public String getAsignatura() { return asignatura; }
    public void setAsignatura(String asignatura) { this.asignatura = asignatura; }

    @Override
    public String getTipo() { return "Docente"; }

    @Override
    public void mostrarDatos() {

        System.out.println("============== DOCENTE =============");
        System.out.println("│ Cédula     : " + cedula);
        System.out.println("│Nombre     : " + nombreCompleto);
        System.out.println("|Edad       : " + edad + " años");
        System.out.println("|Asignatura : " + asignatura);
        System.out.println("===================================");
    }
}
