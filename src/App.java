import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Controlador controlador = new Controlador();
        Scanner teclado = new Scanner(System.in);
        controlador.iniciarSistema(teclado);
        teclado.close();
    }
}
