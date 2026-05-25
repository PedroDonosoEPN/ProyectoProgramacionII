package PedroMetodos;

/**
 * Clase encargada de la visualización del inventario en la terminal.
 * Se conecta con la estructura de datos para dar formato de tabla a los productos.
 * * @author Pedro José Donoso Vélez
 * @version 1.0
 */
public class mostrarInventario {

    /**
     * Recorre la matriz de inventario y muestra los productos existentes
     * en un formato de tabla limpio y alineado por consola.
     * * @param inventario La matriz bidimensional de String (String[][]) que contiene los datos.
     * @param maxProductos El tamaño máximo de filas que tiene la matriz original.
     */
    public void imprimirTabla(String[][] inventario, int maxProductos) {
        int contadorReales = 0;

        // Primero verificamos si hay al menos un producto para mostrar
        for (int i = 0; i < maxProductos; i++) {
            if (inventario[i][0] != null) {
                contadorReales++;
            }
        }

        if (contadorReales == 0) {
            System.out.println("\n-> El inventario está completamente vacío.");
            return;
        }

        // Impresión de la cabecera de la tabla con formato alineado
        System.out.println("\n=================================");
        System.out.println("       INVENTARIO DE STOCK       ");
        System.out.println("=================================");
        System.out.printf("%-20s | %-10s\n", "PRODUCTO", "CANTIDAD");
        System.out.println("---------------------------------");
        
        // Recorremos la matriz para imprimir solo las filas que tienen datos reales
        for (int i = 0; i < maxProductos; i++) {
            if (inventario[i][0] != null) {
                // %-20s alinea el texto a la izquierda ocupando 20 espacios fijos
                System.out.printf("%-20s | %-10s\n", inventario[i][0], inventario[i][1]);
            }
        }
        System.out.println("=================================\n");
    }
}