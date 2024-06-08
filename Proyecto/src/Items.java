public class Items {

    private String Descripcion;
    private Integer Codigo;
    private Double Precio;
    private Integer Cantidad;

    public Items(Integer Cantidad, Integer Codigo, String Descripcion, Double Precio) {
        this.Cantidad = Cantidad;
        this.Codigo = Codigo;
        this.Descripcion = Descripcion;
        this.Precio = Precio;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public Integer getCodigo() {
        return Codigo;
    }

    public void setCodigo(Integer Codigo) {
        this.Codigo = Codigo;
    }

    public Double getPrecio() {
        return Precio;
    }

    public void setPrecio(Double Precio) {
        this.Precio = Precio;
    }

    public Integer getCantidad() {
        return Cantidad;
    }

    public void setCantidad(Integer Cantidad) {
        this.Cantidad = Cantidad;
    }

    
    
}
/*
 * add(X) -> Añade un valor u objeto al final del ArrayList.
 *size() -> Retorna el tamaño del ArrayList.
 *indexOf(X) -> Retorna el indice ó la posicion del elemento X.
 *contains(X) -> Retorna true si existe el elemento X en el ArrayList.
 *set(i, X) -> Modifica el elemento que esta en la posición i, por el nuevo elemento X.
 *remove(X) -> Elimina el elemento X o en su defecto el elemento en la posición X.
 *get(i) -> Obtiene el elemento en la posición i del Array List.
 */