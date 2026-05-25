import JhonatanMetodos.ValidadorUsuarios;
import MatiasMetodos.Inventario;
import PedroMetodos.gestionStock;
import PedroMetodos.mostrarInventario;
import java.util.Scanner;

public class Controlador {
    
    public void iniciarSistema(Scanner teclado) {
        ValidadorUsuarios validador = new ValidadorUsuarios();
        
        // Nombres lógicos y profesionales para los componentes del sistema
        int maxProductos = 10;
        gestionStock gestorInventario = new gestionStock(maxProductos);
        mostrarInventario vistaTablas = new mostrarInventario();
        Inventario controladorProductos = new Inventario();
        
        System.out.println("=== BIENVENIDO A LA TIENDA DE TECNOLOGÍA ===");
        System.out.println("Formatos válidos: [nombre].cliente o [nombre].admin");
        System.out.print("Ingrese su usuario: ");
        
        String entrada = teclado.nextLine();
        int estadoUsuario = validador.validarUsuario(entrada); 

        System.out.println("----------------------------------------");

        if (estadoUsuario == 14) {
            System.out.println("-> Bienvenido al panel de administrador");
            int opcionAdmin;
            
            do {
                System.out.println("\n=== MENÚ DE ADMINISTRADOR ===");
                System.out.println("1. Registrar o Incrementar Producto");
                System.out.println("2. Eliminar Producto por ID");
                System.out.println("3. Modificar Stock manual");
                System.out.println("4. Ver Inventario Completo");
                System.out.println("5. Cerrar Sesión");
                System.out.print("Seleccione una opción: ");
                opcionAdmin = teclado.nextInt();
                teclado.nextLine(); // Limpiar el buffer del Scanner
                
                switch (opcionAdmin) {
                    case 1:
                        // -----------------------------------------------------
                        // CASO 1: REGISTRO DE PRODUCTO
                        // -----------------------------------------------------
                        System.out.print("Ingrese ID del producto (7 caracteres): ");
                        String id = teclado.nextLine();
                        System.out.print("Ingrese Nombre del producto: ");
                        String nombre = teclado.nextLine();
                        System.out.print("Ingrese Stock inicial: ");
                        int stockInicial = teclado.nextInt();
                        System.out.print("Ingrese Precio del producto: ");
                        double precio = teclado.nextDouble();
                        teclado.nextLine(); // Limpiar el buffer
                        
                        controladorProductos.AgregarProducto(gestorInventario, id, nombre, precio, stockInicial);
                        break;
                        
                    case 2:
                        // -----------------------------------------------------
                        // CASO 2: ELIMINACIÓN DE PRODUCTO
                        // -----------------------------------------------------
                        System.out.print("Ingrese el ID del producto que desea eliminar: ");
                        String idEliminar = teclado.nextLine();
                        System.out.print("Ingrese el Nombre del producto que desea eliminar: ");
                        String nombreEliminar = teclado.nextLine();

                        controladorProductos.EliminarProducto(gestorInventario, idEliminar, nombreEliminar);
                        break;
                        
                    case 3:
                        // -----------------------------------------------------
                        // CASO 3: MODIFICACIÓN MATEMÁTICA DEL STOCK
                        // -----------------------------------------------------
                        System.out.print("Ingrese ID o Nombre del producto: ");
                        String criterio = teclado.nextLine();
                        System.out.print("Cantidad a cambiar (Positivo suma / Negativo resta): ");
                        int cambio = teclado.nextInt();
                        teclado.nextLine(); // Limpiar el buffer
                        
                        boolean exito = gestorInventario.modificarStock(criterio, cambio);
                        if (exito) {
                            System.out.println("-> Stock actualizado correctamente.");
                        }
                        break;
                        
                    case 4:
                        // -----------------------------------------------------
                        // CASO 4: IMPRESIÓN DE LA TABLA
                        // -----------------------------------------------------
                        vistaTablas.imprimirTabla(gestorInventario.getInventario(), maxProductos);
                        break;
                        
                    case 5:
                        System.out.println("Cerrando sesión de administrador...");
                        break;
                        
                    default:
                        System.out.println("Opción inválida.");
                }
            } while (opcionAdmin != 5);

        } else if (estadoUsuario == 15) {
            ControladorCliente cliente = new ControladorCliente();
            cliente.menuCliente(teclado);
            
        } else {
            System.out.println("Error, formato no válido");
        }
        
        System.out.println("----------------------------------------");
    }
}