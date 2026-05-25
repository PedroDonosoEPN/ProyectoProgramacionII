import JhonatanMetodos.ValidadorUsuarios;
import MatiasMetodos.Inventario;
import PedroMetodos.gestionStock;
import PedroMetodos.mostrarInventario;
import java.util.Scanner;

public class Controlador {
    
    public void iniciarSistema(Scanner teclado) {
        ValidadorUsuarios validador = new ValidadorUsuarios();
        
        System.out.println("=== BIENVENIDO A LA TIENDA DE TECNOLOGÍA ===");
        System.out.println("Formatos válidos: [nombre].cliente o [nombre].admin");
        System.out.print("Ingrese su usuario: ");
        
        String entrada = teclado.nextLine();
        
        // Asumiendo que adaptamos el Validador para que retorne el estado final (int)
        int estadoUsuario = validador.validarUsuario(entrada); 

        if (estadoUsuario==14){
            System.out.println("Bienvenido al panel de administrador");

        }else if(estadoUsuario==15){
            System.out.println("Bienvenido al Panel de clientes");
        }else{
            System.out.println("Error, formato no valido");
        }
        
        System.out.println("----------------------------------------");
    }

    private void menuAdministrador(Scanner teclado){
        int MAX_PRODUCTOS = 50;
        gestionStock baseDeDatos = new gestionStock(MAX_PRODUCTOS);
        Inventario MatiasMetodos = new Inventario();
    
        boolean salir = false;
        while(!salir){
            System.out.println("\n--MENU DE GESTION DE INVENTARIO--\n");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Eliminar Producto");
            System.out.println("3. Modificar Stock");
            System.out.println("4. Mostrar inventario");
            System.out.println("5. Salir");
            System.out.println("Selecciona una opcion: ");

            int opcion = teclado.nextInt();
            teclado.nextLine();

            switch(opcion){
                case 1:
                    System.out.print("Ingresar ID (7 Caracteres): ");
                    String id = teclado.nextLine();

                    System.out.print("Ingrese Nombre del producto: ");
                    String nombre = teclado.nextLine();

                    System.out.print("Ingrese Precio: ");
                    double precio = teclado.nextDouble();

                    System.out.print("Ingrese su stock: ");
                    int stock = teclado.nextInt();
                    teclado.nextLine();

                    MatiasMetodos.AgregarProducto(baseDeDatos, id, nombre, precio, stock);
                    break;
                case 2:
                    System.out.println("Ingrese ID a eliminar: ");
                    String idEliminar = teclado.nextLine();

                    MatiasMetodos.EliminarProducto(baseDeDatos, idEliminar);
                    break;

            }

        }
    }
}