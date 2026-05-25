package PedroMetodos;

/**
 * Clase encargada de la visualización del inventario en la terminal.
 * Se conecta con la estructura de datos para dar formato de tabla a los productos incluyendo su ID y Precio.
 * * @author Pedro José Donoso Vélez
 * @version 1.2
 */
public class mostrarInventario {

    /**
     * Recorre la matriz de inventario y muestra los productos existentes
     * junto con su ID y Precio en un formato de tabla limpio y alineado por consola.
     * * @param inventario La matriz bidimensional de String (String[][]) que contiene los datos.
     * [Fila][0: ID, 1: Nombre, 2: Cantidad, 3: Precio]
     * @param maxProductos El tamaño máximo de filas que tiene la matriz original.
     */
    public void imprimirTabla(String[][] inventario, int maxProductos) {
        int contadorReales = 0;

        // Primero verificamos si hay al menos un producto para mostrar (revisando si el ID no es null)
        for (int i = 0; i < maxProductos; i++) {
            if (inventario[i][0] != null) {
                contadorReales++;
            }
        }

        if (contadorReales == 0) {
            System.out.println("\n-> El inventario está completamente vacío.");
            return;
        }

        // Impresión de la cabecera de la tabla con formato alineado incluyendo el ID y el Precio
        System.out.println("\n=================================================================");
        System.out.println("                        INVENTARIO DE STOCK                      ");
        System.out.println("=================================================================");
        System.out.printf("%-10s | %-20s | %-12s | %-10s\n", "ID", "PRODUCTO", "CANTIDAD", "PRECIO");
        System.out.println("-----------------------------------------------------------------");
        
        // Recorremos la matriz para imprimir las 4 columnas de las filas válidas
        for (int i = 0; i < maxProductos; i++) {
            if (inventario[i][0] != null) {
                // Se añade la cuarta columna asignando espacios string alineados a la izquierda
                System.out.printf("%-10s | %-20s | %-12s | $%-10s\n", 
                                  inventario[i][0],   // Columna 0: ID
                                  inventario[i][1],   // Columna 1: Nombre
                                  inventario[i][2],   // Columna 2: Cantidad
                                  inventario[i][3]);  // Columna 3: Precio (Se le antepone el '$' de adorno)
            }
        }
        System.out.println("=================================================================\n");
    }
}