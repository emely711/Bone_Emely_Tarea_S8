Sistema de Gestión Académica — Java POO
Aplicación de consola en Java que implementa un CRUD completo con herencia, ArrayList y manejo de excepciones.
---
Estructura del proyecto
```
SistemaAcademico/
└── src/
    └── academia/
        ├── Persona.java         ← Clase padre (abstracta)
        ├── Estudiante.java      ← Subclase con atributo: carrera
        ├── Docente.java         ← Subclase con atributo: asignatura
        ├── GestorPersonas.java  ← Lógica CRUD con ArrayList
        └── Main.java            ← Menú interactivo y punto de entrada
```
---
Conceptos aplicados
Herencia
`Estudiante` y `Docente` extienden la clase abstracta `Persona` mediante la palabra clave `extends`. Ambas subclases llaman al constructor padre con `super(cedula, nombre, edad)` y agregan su propio atributo específico. Esto evita duplicar los campos comunes.
```java
public class Estudiante extends Persona {
    private String carrera;

    public Estudiante(String cedula, String nombre, int edad, String carrera) {
        super(cedula, nombre, edad);
        this.carrera = carrera;
    }
}
```
Encapsulamiento
Los atributos de `Persona` son `protected` (accesibles en subclases pero no desde fuera). Los atributos propios de cada subclase son `private`. El acceso externo se hace siempre mediante getters y setters.
Sobrescritura con @Override
Cada subclase implementa el método `mostrarDatos()` declarado como abstracto en `Persona`. La anotación `@Override` obliga al compilador a verificar que el método realmente existe en la clase padre.
ArrayList y CRUD
Se usa `ArrayList<Persona>` para almacenar objetos en tiempo de ejecución sin tamaño fijo.
Operación	Método ArrayList	Descripción
CREATE	`add(objeto)`	Agrega al final de la lista
READ	`get(indice)`	Recupera por posición
UPDATE	`set(indice, objeto)`	Reemplaza en posición
DELETE	`remove(indice)`	Elimina por posición
Manejo de excepciones (try-catch)
Se captura `NumberFormatException` cada vez que el usuario debe ingresar un número. Esto impide que el programa se cierre ante entradas incorrectas:
```java
try {
    return Integer.parseInt(scanner.nextLine().trim());
} catch (NumberFormatException e) {
    System.out.println("⚠ Error: debe ingresar solo números.");
    return -1;
}
```
---
Retos adicionales implementados 
Búsqueda por cédula: opción 5 del menú.
Cédula duplicada: al registrar, se verifica si ya existe.
Conteo por tipo: al mostrar registros aparece el total de estudiantes y docentes.
Confirmación antes de eliminar: el sistema pide "s/n" antes de borrar.
Clases separadas en paquete `academia`: cada clase en su propio archivo.
---
Cómo compilar y ejecutar
```bash
# Desde la carpeta raíz del proyecto
javac -d out src/academia/*.java

# Ejecutar
java -cp out academia.Main
```
---
Diagrama UML de clases
```
              ┌─────────────────────────────┐
              │        <<abstract>>         │
              │           Persona           │
              ├─────────────────────────────┤
              │ # cedula: String            │
              │ # nombreCompleto: String    │
              │ # edad: int                 │
              ├─────────────────────────────┤
              │ + getCedula(): String       │
              │ + getNombreCompleto(): Str  │
              │ + getEdad(): int            │
              │ + setNombreCompleto(String) │
              │ + setEdad(int)              │
              │ + mostrarDatos(): void      │
              │ + getTipo(): String         │
              └──────────────┬──────────────┘
                             │
              ┌──────────────┴──────────────┐
              │                             │
  ┌───────────▼──────────┐    ┌─────────────▼────────────┐
  │      Estudiante       │    │          Docente          │
  ├──────────────────────┤    ├──────────────────────────┤
  │ - carrera: String    │    │ - asignatura: String     │
  ├──────────────────────┤    ├──────────────────────────┤
  │ + getCarrera()       │    │ + getAsignatura()        │
  │ + setCarrera(String) │    │ + setAsignatura(String)  │
  │ + mostrarDatos()     │    │ + mostrarDatos()         │
  │ + getTipo()          │    │ + getTipo()              │
  └──────────────────────┘    └──────────────────────────┘

  ┌────────────────────────────────────────────────────────┐
  │                    GestorPersonas                      │
  ├────────────────────────────────────────────────────────┤
  │ - listaPersonas: ArrayList<Persona>                    │
  │ - scanner: Scanner                                     │
  ├────────────────────────────────────────────────────────┤
  │ + registrarPersona(): void                             │
  │ + mostrarTodos(): void                                 │
  │ + actualizarRegistro(): void                           │
  │ + eliminarRegistro(): void                             │
  │ + buscarPorCedula(): void                              │
  │ - leerEntero(): int                                    │
  │ - leerCampoObligatorio(String): String                 │
  │ - existeCedula(String): boolean                        │
  └────────────────────────────────────────────────────────┘
```
