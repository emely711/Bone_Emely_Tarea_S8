package academia;

/**
 * Clase padre que representa a una persona de la comunidad académica.
 * Aplica encapsulamiento con atributos protegidos para permitir herencia.
 */
public abstract class Persona {

    // Atributos protegidos accesibles por subclases
    protected String cedula;
    protected String nombreCompleto;
    protected int edad;

    /**
     * Constructor que inicializa los datos comunes de toda persona.
     */
    public Persona(String cedula, String nombreCompleto, int edad) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
    }

    public String getCedula() { return cedula; }
    public String getNombreCompleto() { return nombreCompleto; }
    public int getEdad() { return edad; }

    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public void setEdad(int edad) { this.edad = edad; }

    /**
     * Método abstracto que cada subclase debe sobrescribir para mostrar sus datos.
     */
    public abstract void mostrarDatos();

    /**
     * Retorna el tipo de persona ("Estudiante" o "Docente") — implementado en subclases.
     */
    public abstract String getTipo();
}
