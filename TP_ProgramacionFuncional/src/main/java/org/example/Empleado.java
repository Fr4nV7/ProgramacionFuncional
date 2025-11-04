package org.example;

class Empleado {
    private String nombre;
    private String departamento;
    private double salario;
    private int edad;

    public Empleado(String nombre, String departamento, double salario, int edad) {
        this.nombre = nombre;
        this.departamento = departamento;
        this.salario = salario;
        this.edad = edad;
    }

    public String getNombre() { return nombre; }
    public String getDepartamento() { return departamento; }
    public double getSalario() { return salario; }
    public int getEdad() { return edad; }

    @Override
    public String toString() {
        return nombre + " - " + departamento + " - $" + salario + " - " + edad + " años";
    }
}

