import EidanMetodos.sistemaVentas;
import PedroMetodos.gestionStock;
import PedroMetodos.mostrarInventario;
import java.util.Scanner;

public class ControladorCliente {

    public void menuCliente(Scanner teclado, gestionStock gestorInventario) {
        int opcionCliente;
        int maxProductos = 10;
        double dineroInicial;
        
        // Inicialización de componentes de visualización y ventas de Eidan
        mostrarInventario vistaTablas = new mostrarInventario();
        sistemaVentas carrito = new sistemaVentas(maxProductos);
        
        System.out.println("-> Bienvenido al Panel de clientes");
        
        // Pedir dinero inicial antes de desplegar las opciones
        System.out.print("Ingrese el dinero inicial: ");
        dineroInicial = teclado.nextDouble();
        teclado.nextLine(); // Limpiar buffer de entrada

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
            teclado.nextLine(); // Limpiar buffer de entrada
            
            switch (opcionCliente) {
                case 1:
                    System.out.println("Listando productos disponibles...");
                    // Se imprime la tabla compartida de 4 columnas
                    vistaTablas.imprimirTabla(gestorInventario.getInventario(), maxProductos);
                    break;
                    
                case 2:
                    // --- AÑADIR PRODUCTO AL CARRITO ---
                    System.out.print("Ingrese el ID del producto a añadir al carrito: ");
                    String id = teclado.nextLine();
                    System.out.print("Ingrese la cantidad a añadir: ");
                    int cantidad = teclado.nextInt();
                    teclado.nextLine(); // Limpiar buffer
                    
                    carrito.agregarAlCarrito(gestorInventario, id, cantidad);
                    break;
                    
                case 3:
                    // --- BORRAR PRODUCTO DEL CARRITO ---
                    System.out.print("Ingrese el ID del producto a borrar del carrito: ");
                    String idBorrar = teclado.nextLine();
                    
                    carrito.borrarProductoCarrito(gestorInventario, idBorrar);
                    break;
                    
                case 4:
                    // --- FINALIZAR PROCESO DE COMPRA ---
                    System.out.println("Finalizando compra...");
                    carrito.finalizarCompra(dineroInicial);
                    break;
                    
                case 5:
                    // --- VISUALIZAR EL CARRITO ACTUAL ---
                    System.out.println("Listar productos del carrito...");
                    carrito.mostrarCarrito();
                    break;
                    
                case 6:
                    System.out.println("Cerrando sesión cliente...");
                    return;
                    
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción del menú.");
            }
            delay(1500); // Pausa estética de 1.5 segundos entre transacciones
            
        } while (opcionCliente != 6);
    }

    public void delay(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            System.out.println("Error en delay");
        }
    }
}