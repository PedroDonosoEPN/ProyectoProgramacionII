import java.util.Scanner;

import EidanMetodos.sistemaVentas;
import PedroMetodos.gestionStock;
import PedroMetodos.mostrarInventario;

public class ControladorCliente {
<<<<<<< HEAD
    public void menuCliente(Scanner teclado) {
        int opcionCliente;
        int maxProductos = 10;
        double dineroInicial;
        gestionStock gestorInventario = new gestionStock(maxProductos);
        mostrarInventario vistaTablas = new mostrarInventario();
        sistemaVentas agregarAlCarrito = new sistemaVentas(maxProductos);
        sistemaVentas borrarDelCarrito = new sistemaVentas(maxProductos);
        sistemaVentas finalizarCompra = new sistemaVentas(maxProductos);
=======
    public void menuCliente(Scanner teclado, gestionStock gestorInventario) {
        int opcionCliente;
        int maxProductos = 10;
        double dineroInicial;
        //gestionStock gestorInventario = new gestionStock(maxProductos);
        mostrarInventario vistaTablas = new mostrarInventario();
        sistemaVentas carrito = new sistemaVentas(maxProductos);
        // sistemaVentas agregarAlCarrito = new sistemaVentas(maxProductos);
        // sistemaVentas borrarDelCarrito = new sistemaVentas(maxProductos);
        // sistemaVentas finalizarCompra = new sistemaVentas(maxProductos);
>>>>>>> 7d3a9cba53125f0188817e510a3bb0e0f34d42d0
        System.out.println("-> Bienvenido al Panel de clientes");
         // Pedir dinero inicial antes del menú
        System.out.print("Ingrese el dinero inicial: ");
        dineroInicial = teclado.nextDouble();
        teclado.nextLine();

      
               
       do {
                System.out.println("\n========= MENÚ CLIENTES =========");
                  System.out.println("Dinero disponible: $" + dineroInicial);
                System.out.println("1. Listar Productos Disponibles");
                System.out.println("2. Añadir Producto al Carrito");
                System.out.println("3. Borrar producto del Carrito");
                System.out.println("4. Finalizar Compra");
                System.out.println("5. Listar productos del Carrito");
                System.out.println("6. Cerrar Sesión");
                System.out.print("Seleccione una opción: ");
                opcionCliente = teclado.nextInt();
                teclado.nextLine();
                switch (opcionCliente) {
            case 1:
                System.out.println("Listando productos disponibles...");
                        // -----------------------------------------------------
                        // CASO 1: IMPRESIÓN DE LA TABLA
                        // -----------------------------------------------------
                        vistaTablas.imprimirTabla(gestorInventario.getInventario(), maxProductos);
                break;
            case 2:
                // -----------------------------------------------------
                // CASO 2: AÑADIR PRODUCTO AL CARRITO
                // -----------------------------------------------------
                System.out.print("Ingrese el ID del producto a añadir al carrito: ");
                String id = teclado.nextLine();
                System.out.print("Ingrese la cantidad a añadir: ");
                int cantidad = teclado.nextInt();
                teclado.nextLine();
<<<<<<< HEAD
                agregarAlCarrito.agregarAlCarrito(gestorInventario, id, cantidad);
=======
                carrito.agregarAlCarrito(gestorInventario, id, cantidad);
>>>>>>> 7d3a9cba53125f0188817e510a3bb0e0f34d42d0
                break;
            case 3:
                System.out.print("Ingrese el ID del producto a borrar del carrito: ");
                String idBorrar = teclado.nextLine();
<<<<<<< HEAD
                borrarDelCarrito.borrarProductoCarrito(gestorInventario, idBorrar);
                break;
            case 4:
                System.out.println("Finalizando compra...");
                finalizarCompra.finalizarCompra(dineroInicial);
                break;
            case 5:
                System.out.println("Listar productos del carrito...");
                agregarAlCarrito.mostrarCarrito();
=======
                carrito.borrarProductoCarrito(gestorInventario, idBorrar);
                break;
            case 4:
                System.out.println("Finalizando compra...");
                carrito.finalizarCompra(dineroInicial);
                break;
            case 5:
                System.out.println("Listar productos del carrito...");
                carrito.mostrarCarrito();
>>>>>>> 7d3a9cba53125f0188817e510a3bb0e0f34d42d0
                break;
            case 6:
                System.out.println("Cerrando sesión cliente...");
                return;
            default:
                System.out.println("Opción no válida. Por favor, seleccione una opción del menú.");
         }
         delay(1500); // Pausa de 1.5 segundos 
         }while (opcionCliente != 6);
    }
    public void delay(int milisegundos) {

    try {
        Thread.sleep(milisegundos);
    } catch (InterruptedException e) {
        System.out.println("Error en delay");
    }
}
}
