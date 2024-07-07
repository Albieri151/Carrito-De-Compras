import java.util.ArrayList;
import java.util.Scanner;


public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        ArrayList<Items> Productos = new ArrayList<>();
        ArrayList<Facturas> Facturacion = new ArrayList<>();
        double Iva = 1.16, TasaCambio = 40;

        boolean Salir = true;

        do {

            System.out.println("\t\t\t\nMenu\n");
            System.out.println("1. Agregar Productos");
            System.out.println("2. Modificar Productos");
            System.out.println("3. Listado De Productos");
            System.out.println("4. Comprar Productos");
            System.out.println("5. Buscar Factura Por Codigo");
            System.out.println("6. Estadisticas De Los Productos");
            System.out.println("7. Configuraciones");
            Integer Opcion = sc.nextInt();

            switch (Opcion) {
                case 1 -> {
                    System.out.println("Ingrese la cantidad de productos nuevos: ");
                    Integer CantidadProductos = sc.nextInt();
                    for (int i = 0; i < CantidadProductos; i++) {

                        System.out.println("\n");
                        Items Producto = new Items();
                        Producto.AgregarDescripcion();
                        Producto.AgregarCantidad();
                        Producto.AgregarPrecio();
                        Integer CodigoNuevo = Producto.AgregarCodigo();
                        for (Items Producto1 : Productos) {
                            while (CodigoNuevo.equals(Producto1.getCodigo())) {
                                System.out.print("El codigo se encuentra registrado, reingrese uno nuevo: ");
                                CodigoNuevo = sc.nextInt();
                            }
                        }
                        Producto.AsignarCodigo(CodigoNuevo);
                        Productos.add(Producto);
                    }
                }

                case 2 -> {
                    System.out.print("Ingrese el codigo del producto a modificar: ");
                    Integer Busqueda = sc.nextInt();
                    boolean NoEncontrado = true;

                    for (Items elem : Productos) {
                        if (elem.getCodigo().equals(Busqueda)) {
                            NoEncontrado = false;
                            System.out.println("\t\t\nIngrese que solicita modificar");
                            System.out.println("1. Cambiar Descripcion");
                            System.out.println("2. Cambiar Precio");
                            System.out.println("3. Cambiar La Cantidad");
                            Integer Cambio = sc.nextInt();
                            Integer Indice = Productos.indexOf(elem);

                            switch (Cambio) {
                                case 1 -> {
                                    elem.AgregarDescripcion();
                                    Productos.set(Indice, elem);
                                }
                                case 2 -> {
                                    elem.AgregarPrecio();
                                    Productos.set(Indice, elem);
                                }
                                case 3 -> {
                                    elem.AgregarCantidad();
                                    Productos.set(Indice, elem);
                                }
                                default ->
                                    throw new AssertionError();
                            }
                        }
                    }
                    if (NoEncontrado) {
                        System.out.println("\n\nCodigo no encontrado.");
                    }
                }

                case 3 -> {
                    System.out.println("\nLista de Productos\n");
                    for (Items elem : Productos) {
                        System.out.println("Nombre: " + elem.getDescripcion() + " -- Cantidad: " + elem.getCantidad()
                                + " -- Precio ($): " + elem.getPrecio() +
                                " -- Codigo: " + elem.getCodigo());
                    }
                }

                case 4 -> {

                    // Mostrar los productos disponibles para poder comprar
                    System.out.println("\nLista de Productos\n");
                    for (Items elem : Productos) {
                        System.out.println("Nombre: " + elem.getDescripcion() + " -- Cantidad: " + elem.getCantidad()
                                + " -- Precio: " + elem.getPrecio() +
                                " -- Codigo: " + elem.getCodigo());
                    }

                    boolean Continuar;
                    Facturas Facturas1 = new Facturas();

                    do {
                        System.out.println("Ingrese el codigo de producto que desea comprar: ");
                        Integer Producto = sc.nextInt();
                        Integer Contador = 0;

                        for (Items elem : Productos) {

                            if (elem.getCodigo().equals(Producto)) {
                                System.out.println("Seleccione la cantidad de " + elem.getDescripcion());
                                Integer Cantidad = sc.nextInt();

                                while (Cantidad > elem.getCantidad()) {
                                    System.out.print("ERROR!! Cantidad excedida, reingrese la cantidad: ");
                                    Cantidad = sc.nextInt();
                                }

                                elem.Comprar(Cantidad);
                                Productos.set(Contador, elem);
                                Facturas1.setIva(Iva);
                                Facturas1.setTasaCambiaria(TasaCambio);
                                Double PrecioTotal = Cantidad * elem.getPrecio();
                                Facturas1.AgregarProductos(elem.getDescripcion(), Cantidad, PrecioTotal);
                                break;
                            }
                            Contador++;
                        }
                        
                        System.out.println("Desea comprar otro producto SI/NO: ");
                        String Decidir = sc.next().toLowerCase();
                        Continuar = Decidir.equals("si");
                    } while (Continuar);
                    Facturas1.CodigoFactura();
                    Facturacion.add(Facturas1);
                }

                case 5 -> {
                    System.out.print("\t\t\nIngrese el codigo de la factura que desea buscar: ");
                    Integer BuscarFactura = sc.nextInt();

                    for (Facturas Factura : Facturacion) {
                        if (Factura.getCodigo().equals(BuscarFactura)) {
                            System.out.println("Empresa: " + Factura.getNombreTienda());
                            System.out.println("RIF: " + Factura.getRif());
                            System.out.println("Codigo: " + Factura.getCodigo());
                            System.out.println("Productos Comprados: ");
                            for (int i = 0; i < Factura.ProductosComprados.size(); i++) {
                                System.out.println("Producto: " + Factura.ProductosComprados.get(i)+ " -- Precio Total ($): " + Factura.Precios.get(i)
                                + "  Bs " + Factura.Precios.get(i)*Factura.getTasaCambiaria() + " -- Cantidad: " + Factura.CantidadDeProductos.get(i));
                            }
                            System.out.println("\nSub-Total: " + Factura.CalcularTotal());
                            System.out.println("Total: " + Factura.TotalIva(Factura.CalcularTotal()));
                            System.out.println("Tasa de Cambio: " + Factura.getTasaCambiaria());

                        }
                    }
                }

                case 6 -> {
                    System.out.println("\t\t\t\nEstadisticas de FerreHogar");
                    double CalculoTotal=0;
                    for (Items Total : Productos) {
                        CalculoTotal += Total.TotalNeto();
                    }

                    System.out.println("Total Neto: " + CalculoTotal + " Bs "+CalculoTotal*TasaCambio);
                    System.out.println("Total Bruto: " + CalculoTotal*Iva + " Bs " + CalculoTotal*Iva*TasaCambio);

                    CalculoTotal=0;
                    for (Facturas Estadistica : Facturacion) {
                        CalculoTotal += Estadistica.CalcularTotal();
                    }
                    System.out.println("Total Neto de ventas ($): " + CalculoTotal + " Bs "+CalculoTotal*TasaCambio);
                    System.out.println("Total Bruto de ventas: ($)"+ CalculoTotal*Iva + " Bs " + CalculoTotal*Iva*TasaCambio);

                    Integer Mayor=0,Menor=100000;
                    for (Items DeterminarMayorMenor : Productos) {
                        if (DeterminarMayorMenor.getCantidad()>Mayor) {
                            Mayor=DeterminarMayorMenor.getCantidad();
                        }
                        if (DeterminarMayorMenor.getCantidad()<Menor) {
                            Menor=DeterminarMayorMenor.getCantidad();
                        }
                    }

                    System.out.println("Productos con mayor cantidad: ");
                    for (Items MostrarMayorMenor : Productos) {
                        if (MostrarMayorMenor.getCantidad().equals(Mayor)) {
                            System.out.println("Descripcion: " + MostrarMayorMenor.getDescripcion() + "-- Cantidad: " + MostrarMayorMenor.getCantidad());
                        }
                    }

                    System.out.println("Productos con Menor cantidad: ");
                    for (Items MostrarMayorMenor : Productos) {
                        if (MostrarMayorMenor.getCantidad().equals(Menor)) {
                            System.out.println("Descripcion: " + MostrarMayorMenor.getDescripcion() + "-- Cantidad: " + MostrarMayorMenor.getCantidad());
                        }
                    }

                    Double Mayor1=0.0;
                    Double Menor1=100000.0;
                    for (Items DeterminarMayorMenor : Productos) {
                        if (DeterminarMayorMenor.getPrecio()>Mayor1) {
                            Mayor1=DeterminarMayorMenor.getPrecio();
                        }
                        if (DeterminarMayorMenor.getPrecio()<Menor1) {
                            Menor1=DeterminarMayorMenor.getPrecio();
                        }
                    }

                    System.out.println("Productos con mayor Precio: ");
                    for (Items MostrarMayorMenor : Productos) {
                        if (MostrarMayorMenor.getPrecio().equals(Mayor1)) {
                            System.out.println("Descripcion: " + MostrarMayorMenor.getDescripcion() + "-- Cantidad: " + MostrarMayorMenor.getCantidad());
                        }
                    }

                    System.out.println("Productos con Menor Precio: ");
                    for (Items MostrarMayorMenor : Productos) {
                        if (MostrarMayorMenor.getPrecio().equals(Menor1)) {
                            System.out.println("Descripcion: " + MostrarMayorMenor.getDescripcion() + "-- Cantidad: " + MostrarMayorMenor.getCantidad());
                        }
                    }

                    Double mayor=0.0;
                    System.out.println("Factura con la mayor venta: ");
                    for (Facturas elem : Facturacion) {
                        if (elem.CalcularTotal()>mayor) {
                            mayor=CalculoTotal;
                        }
                    }
                    for (Facturas elem : Facturacion) {
                        if (elem.CalcularTotal().equals(mayor)) {
                            System.out.println("Codigo de Factura: " + elem.getCodigo() + "-- Venta: " + elem.CalcularTotal());
                        }
                    }
                }

                case 7 -> {
                    System.out.println("\t\t\t\nConfiguraciones ");
                    System.out.println("1. Cambiar Tasa");
                    System.out.println("2. Cambiar Iva");
                    Integer Opcion1 = sc.nextInt();

                    switch (Opcion1) {
                        case 1 -> {
                            System.out.print("Ingrese la nueva tasa cambiaria: ");
                            TasaCambio = sc.nextDouble();
                        }
                        case 2 -> {
                            System.out.print("Ingrese el nuevo IVA: ");
                            Iva = sc.nextDouble();
                        }
                        default-> {
                            throw new AssertionError();
                        }    
                    }
                }

                default -> {
                    System.out.println("ERROR!! Reingrese una opcion valida");
                }
            }

        } while (Salir);
    }
}
