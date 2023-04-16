package unal.todosalau.unicorniomagico.Modelos;

public class Articulos {
    private String id;
    private String nombre;
    private String marca;
    private String modelo;
    private String precio;
    private String stock;
    private String linkImagen;
    public Articulos() {}

    public Articulos(String id, String nombre, String marca, String modelo, String precio, String stock, String linkImagen) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.stock = stock;
        this.linkImagen = linkImagen;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getPrecio() {
        return precio;
    }

    public String getStock() {
        return stock;
    }

    public String getLinkImagen() {
        return linkImagen;
    }
}
