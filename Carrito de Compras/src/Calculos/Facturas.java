package Calculos.*;

import java.util.ArrayList;

public class Facturas extends Items {

    private String NombreTienda = "FerreHogar";
    private String Rif = "J-123456789";
    private Double Total;
    private Double Iva = 1.16;
    private Double TasaCambiaria;
    ArrayList<String> ProductosComprados = new ArrayList<>();
    ArrayList<Integer> CantidadDeProductos = new ArrayList<>();
    ArrayList<Double> Precios = new ArrayList<>();

    public String getNombreTienda() {
        return NombreTienda;
    }

    public Double getTasaCambiaria() {
        return TasaCambiaria;
    }

    public void setTasaCambiaria(Double tasaCambiaria) {
        TasaCambiaria = tasaCambiaria;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double total) {
        Total = total;
    }

    public void setNombreTienda(String nombreTienda) {
        NombreTienda = nombreTienda;
    }

    public void setRif(String rif) {
        Rif = rif;
    }

    public String getRif() {
        return Rif;
    }

    public void CodigoFactura() {
        System.out.print("Escriba el codigo de la factura: ");
        Integer Codigo = sc.nextInt();
        setCodigo(Codigo);
    }

    public void AgregarProductos(String Producto, Integer Cantidad, Double Precios1) {
        ProductosComprados.add(Producto);
        CantidadDeProductos.add(Cantidad);
        Precios.add(Precios1);
    }

    public Double CalcularTotal() {
        double SumaPrecios = 0;
        for (int i = 0; i < CantidadDeProductos.size(); i++) {
            SumaPrecios += Precios.get(i);
        }
        return SumaPrecios;
    }

    public Double TotalIva(Double SubTotal) {
        return SubTotal * Iva;
    }

    public Double getIva() {
        return Iva;
    }

    public void setIva(Double Iva) {
        this.Iva = Iva;
    }

}
