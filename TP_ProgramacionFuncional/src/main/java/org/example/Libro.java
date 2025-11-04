package org.example;

class Libro {
    private String titulo;
    private String autor;
    private int paginas;
    private double precio;

    public Libro(String titulo, String autor, int paginas, double precio) {
        this.titulo = titulo;
        this.autor = autor;
        this.paginas = paginas;
        this.precio = precio;
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getPaginas() { return paginas; }
    public double getPrecio() { return precio; }

    @Override
    public String toString() {
        return titulo + " - " + autor + " - " + paginas + " págs - $" + precio;
    }
}

