import java.util.Scanner;
import PedroMetodos.gestionStock;
import PedroMetodos.mostrarInventario;

public class ControladorCliente {
    public void menuCliente(Scanner teclado) {
        int opcionCliente;
        int maxProductos = 10;
        gestionStock gestorInventario = new gestionStock(maxProductos);
        mostrarInventario vistaTablas = new mostrarInventario();
        System.out.println("-> Bienvenido al Panel de clientes");
        
               
       do {
                System.out.println("\n========= MENÚ CLIENTES =========");
                System.out.println("1. Listar Productos Disponibles");
                System.out.println("2. Añadir Producto al Carrito");
                System.out.println("3. Borrar producto del Carrito");
                System.out.println("4. Finalizar Compra");
                System.out.println("5. Cerrar Sesión");
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
                System.out.println("Añadiendo producto al carrito...");
                // Aquí iría la lógica para añadir un producto al carrito
                break;
            case 3:
                System.out.println("Borrando producto del carrito...");
                // Aquí iría la lógica para borrar un producto del carrito
                break;
            case 4:
                System.out.println("Finalizando compra...");
                // Aquí iría la lógica para finalizar la compra
                break;
            case 5:
                System.out.println("Cerrando sesión cliente...");
                break;
            default:
                System.out.println("Opción no válida. Por favor, seleccione una opción del menú.");
         }
         }while (opcionCliente != 5);
    }
}
