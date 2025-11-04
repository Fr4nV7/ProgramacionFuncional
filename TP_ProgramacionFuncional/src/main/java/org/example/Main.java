package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        ejecutarEjercicioAlumnos();
        imprimirSeparador();
        procesarCatalogoProductos();
        imprimirSeparador();
        analizarBiblioteca();
        imprimirSeparador();
        gestionarPersonal();
    }

    private static void imprimirSeparador() {
        System.out.println("\n" + String.join("", Collections.nCopies(80, "=")) + "\n");
    }

    // --- EJERCICIO 1: GESTIÓN DE ESTUDIANTES ---
    private static void ejecutarEjercicioAlumnos() {
        System.out.println("--- EJERCICIO 1: GESTIÓN DE ESTUDIANTES ---\n");

        var listaEstudiantes = List.of(
                new Alumno("Ana Martínez", 8.5, "5C"),
                new Alumno("Roberto Fernández", 6.2, "5D"),
                new Alumno("Carmen López", 9.3, "5C"),
                new Alumno("Pedro González", 7.1, "5D"),
                new Alumno("María Rodríguez", 6.8, "5C"),
                new Alumno("Luis Sánchez", 8.7, "5D"),
                new Alumno("Isabel Torres", 9.5, "5C"),
                new Alumno("Miguel Ángel Jiménez", 7.0, "5D")
        );

        mostrarAprobados(listaEstudiantes);
        calcularPromedioNotas(listaEstudiantes);
        distribuirPorCurso(listaEstudiantes);
        obtenerTopEstudiantes(listaEstudiantes);
    }

    private static void mostrarAprobados(List<Alumno> estudiantes) {
        System.out.println("1. Estudiantes aprobados (nota mínima 7) en mayúsculas:");
        estudiantes.stream()
                .filter(estudiante -> estudiante.getNota() >= 7.0)
                .map(Alumno::getNombre)
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);
    }

    private static void calcularPromedioNotas(List<Alumno> estudiantes) {
        System.out.println("\n2. Promedio de calificaciones:");
        OptionalDouble promedio = estudiantes.stream()
                .mapToDouble(Alumno::getNota)
                .average();
        System.out.printf("Promedio: %.2f\n", promedio.orElse(0.0));
    }

    private static void distribuirPorCurso(List<Alumno> estudiantes) {
        System.out.println("\n3. Distribución de estudiantes por curso:");
        Map<String, List<Alumno>> distribucion = estudiantes.stream()
                .collect(Collectors.groupingBy(Alumno::getCurso));
        distribucion.entrySet().forEach(entry -> {
            System.out.println("Curso " + entry.getKey() + ":");
            entry.getValue().forEach(est -> System.out.println("  - " + est.getNombre()));
        });
    }

    private static void obtenerTopEstudiantes(List<Alumno> estudiantes) {
        System.out.println("\n4. Top 3 estudiantes por calificación:");
        estudiantes.stream()
                .sorted((a1, a2) -> Double.compare(a2.getNota(), a1.getNota()))
                .limit(3)
                .forEach(est -> System.out.printf("  %s - Nota: %.2f\n", est.getNombre(), est.getNota()));
    }

    // --- EJERCICIO 2: INVENTARIO DE PRODUCTOS ---
    private static void procesarCatalogoProductos() {
        System.out.println("--- EJERCICIO 2: INVENTARIO DE PRODUCTOS ---\n");

        var catalogo = List.of(
                new Producto("Mouse Inalámbrico", "Periféricos", 45.00, 80),
                new Producto("Teclado Mecánico", "Periféricos", 120.00, 35),
                new Producto("Monitor 27 pulgadas", "Pantallas", 280.00, 20),
                new Producto("Disco SSD 1TB", "Almacenamiento", 75.00, 55),
                new Producto("Memoria RAM 16GB", "Componentes", 90.00, 40),
                new Producto("Webcam HD", "Periféricos", 35.00, 60),
                new Producto("Tableta Gráfica", "Periféricos", 180.00, 15),
                new Producto("Hub USB-C", "Accesorios", 25.00, 90)
        );

        filtrarProductosCaros(catalogo);
        calcularInventarioPorCategoria(catalogo);
        generarListaFormateada(catalogo);
        mostrarPreciosPromedio(catalogo);
    }

    private static void filtrarProductosCaros(List<Producto> productos) {
        System.out.println("1. Productos con precio superior a $100 (orden por precio):");
        productos.stream()
                .filter(prod -> prod.getPrecio() > 100.0)
                .sorted((p1, p2) -> Double.compare(p2.getPrecio(), p1.getPrecio()))
                .forEach(System.out::println);
    }

    private static void calcularInventarioPorCategoria(List<Producto> productos) {
        System.out.println("\n2. Inventario total por categoría:");
        productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.summingInt(Producto::getStock)
                ))
                .forEach((categoria, inventario) ->
                        System.out.printf("  %s: %d unidades\n", categoria, inventario));
    }

    private static void generarListaFormateada(List<Producto> productos) {
        System.out.println("\n3. Listado en formato nombre;precio:");
        String resultado = productos.stream()
                .map(prod -> prod.getNombre() + ";" + prod.getPrecio())
                .collect(Collectors.joining(";"));
        System.out.println(resultado);
    }

    private static void mostrarPreciosPromedio(List<Producto> productos) {
        System.out.println("\n4. Análisis de precios promedio:");
        double promedioGlobal = productos.stream()
                .mapToDouble(Producto::getPrecio)
                .average()
                .orElse(0.0);
        System.out.printf("  Promedio general: $%.2f\n", promedioGlobal);

        System.out.println("  Promedio por categoría:");
        productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.averagingDouble(Producto::getPrecio)
                ))
                .forEach((categoria, promedio) ->
                        System.out.printf("    %s: $%.2f\n", categoria, promedio));
    }

    // --- EJERCICIO 3: COLECCIÓN DE LIBROS ---
    private static void analizarBiblioteca() {
        System.out.println("--- EJERCICIO 3: COLECCIÓN DE LIBROS ---\n");

        var biblioteca = List.of(
                new Libro("Cien años de soledad", "Gabriel García Márquez", 471, 22.50),
                new Libro("1984", "George Orwell", 328, 18.75),
                new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", 863, 28.00),
                new Libro("El señor de los anillos", "J.R.R. Tolkien", 1216, 35.50),
                new Libro("El principito", "Antoine de Saint-Exupéry", 96, 12.99),
                new Libro("El hobbit", "J.R.R. Tolkien", 310, 21.00),
                new Libro("Harry Potter y la piedra filosofal", "J.K. Rowling", 256, 19.99),
                new Libro("Los pilares de la tierra", "Ken Follett", 1076, 32.00)
        );

        listarLibrosExtensos(biblioteca);
        calcularPromedioPaginas(biblioteca);
        contarPorAutor(biblioteca);
        identificarLibroMasCaro(biblioteca);
    }

    private static void listarLibrosExtensos(List<Libro> libros) {
        System.out.println("1. Libros con más de 300 páginas (orden alfabético):");
        libros.stream()
                .filter(libro -> libro.getPaginas() > 300)
                .map(Libro::getTitulo)
                .sorted(String::compareTo)
                .forEach(titulo -> System.out.println("  - " + titulo));
    }

    private static void calcularPromedioPaginas(List<Libro> libros) {
        System.out.println("\n2. Promedio de páginas:");
        double promedio = libros.stream()
                .mapToInt(Libro::getPaginas)
                .average()
                .orElse(0.0);
        System.out.printf("  %.2f páginas\n", promedio);
    }

    private static void contarPorAutor(List<Libro> libros) {
        System.out.println("\n3. Estadísticas por autor:");
        libros.stream()
                .collect(Collectors.groupingBy(Libro::getAutor, Collectors.counting()))
                .forEach((autor, cantidad) ->
                        System.out.printf("  %s: %d libro(s)\n", autor, cantidad));
    }

    private static void identificarLibroMasCaro(List<Libro> libros) {
        System.out.println("\n4. Libro de mayor precio:");
        libros.stream()
                .max(Comparator.comparingDouble(Libro::getPrecio))
                .ifPresentOrElse(
                        libro -> System.out.println("  " + libro),
                        () -> System.out.println("  No hay libros disponibles")
                );
    }

    // --- EJERCICIO 4: RECURSOS HUMANOS ---
    private static void gestionarPersonal() {
        System.out.println("--- EJERCICIO 4: RECURSOS HUMANOS ---\n");

        var plantilla = List.of(
                new Empleado("Carlos Mendoza", "IT", 4200.00, 35),
                new Empleado("Patricia Ruiz", "RRHH", 3100.00, 29),
                new Empleado("Fernando Álvarez", "IT", 4800.00, 42),
                new Empleado("Sofía Herrera", "Ventas", 2600.00, 27),
                new Empleado("Alejandro Morales", "IT", 4100.00, 31),
                new Empleado("Lucía Ramírez", "RRHH", 3300.00, 38),
                new Empleado("Óscar Vargas", "Ventas", 2400.00, 25),
                new Empleado("Adriana Méndez", "Ventas", 2700.00, 30)
        );

        listarEmpleadosBienRemunerados(plantilla);
        calcularSalarioMedio(plantilla);
        totalizarSalariosPorArea(plantilla);
        identificarJovenes(plantilla);
    }

    private static void listarEmpleadosBienRemunerados(List<Empleado> empleados) {
        System.out.println("1. Empleados con salario superior a $2000 (orden descendente):");
        empleados.stream()
                .filter(emp -> emp.getSalario() > 2000.0)
                .sorted((e1, e2) -> Double.compare(e2.getSalario(), e1.getSalario()))
                .forEach(System.out::println);
    }

    private static void calcularSalarioMedio(List<Empleado> empleados) {
        System.out.println("\n2. Salario promedio de la empresa:");
        double promedio = empleados.stream()
                .mapToDouble(Empleado::getSalario)
                .average()
                .orElse(0.0);
        System.out.printf("  $%.2f\n", promedio);
    }

    private static void totalizarSalariosPorArea(List<Empleado> empleados) {
        System.out.println("\n3. Presupuesto salarial por departamento:");
        empleados.stream()
                .collect(Collectors.groupingBy(
                        Empleado::getDepartamento,
                        Collectors.summingDouble(Empleado::getSalario)
                ))
                .forEach((departamento, total) ->
                        System.out.printf("  %s: $%.2f\n", departamento, total));
    }

    private static void identificarJovenes(List<Empleado> empleados) {
        System.out.println("\n4. Los 2 empleados más jóvenes:");
        empleados.stream()
                .sorted(Comparator.comparingInt(Empleado::getEdad))
                .limit(2)
                .map(Empleado::getNombre)
                .forEach(nombre -> System.out.println("  - " + nombre));
    }
}