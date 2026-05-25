import JhonatanMetodos.ValidadorUsuarios;
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
}