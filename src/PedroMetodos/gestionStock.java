package PedroMetodos;

/**
 * Clase encargada de la gestión y control del inventario de productos.
 * Permite administrar las existencias utilizando una estructura de matriz bidimensional con ID y Precio.
 * * @author Pedro José Donoso Vélez
 * @version 1.2
 */
public class gestionStock {
    
    // Atributos de la clase
    private String[][] inventario;
    private int maxProductos;

    /**
     * Constructor de la clase. Inicializa la matriz de inventario vacía
     * con espacio para 4 columnas (ID, Nombre, Cantidad, Precio).
     * * @param filas Número máximo de filas (productos distintos) que soportará el sistema.
     */
    public gestionStock(int filas) {
        this.maxProductos = filas;
        // Matriz de 'filas' x 4 columnas:
        // Columna 0: ID
        // Columna 1: Nombre del Producto
        // Columna 2: Cantidad en Stock
        // Columna 3: Precio del Producto
        this.inventario = new String[maxProductos][4];
    }

    /**
     * Modifica el stock de un producto específico buscando por su ID o su Nombre.
     * Permite tanto aumentos como disminuciones en una sola operación lógica.
     * * @param criterioBuscar El ID o el Nombre del producto al cual se le alterará el stock.
     * @param cantidad El número de unidades a modificar. Use valores positivos 
     * for abastecer (sumar) y valores negativos para retirar (restar).
     * @return {@code true} si el producto existe y la operación se realizó con éxito; 
     * {@code false} si el producto no fue encontrado o si el stock resultante 
     * es menor a cero.
     */
    public boolean modificarStock(String criterioBuscar, int cantidad) {
        for (int i = 0; i < maxProductos; i++) {
            // Verificamos que la fila tenga al menos el ID registrado para evitar errores null
            if (inventario[i][0] != null) {
                
                // El usuario puede buscar coincidencia con el ID (columna 0) O con el Nombre (columna 1)
                if (inventario[i][0].equalsIgnoreCase(criterioBuscar) || inventario[i][1].equalsIgnoreCase(criterioBuscar)) {
                    
                    // Paso 1: Convertir el texto de la columna 2 (Cantidad) a número entero
                    int stockActual = Integer.parseInt(inventario[i][2]);
                    
                    // Paso 2: Calcular el nuevo stock
                    int nuevoStock = stockActual + cantidad;
                    
                    // Validación de seguridad por si es una resta y se pasa de cero
                    if (nuevoStock < 0) {
                        System.out.println("-> Error: Stock insuficiente. Solo quedan " + stockActual + " unidades.");
                        return false; 
                    }
                    
                    // Paso 3: Guardar el nuevo valor como texto en la columna 2
                    inventario[i][2] = String.valueOf(nuevoStock);
                    return true; // Éxito
                }
            }
        }
        System.out.println("-> Error: El producto o ID '" + criterioBuscar + "' no existe.");
        return false;
    }

    /**
     * Retorna la referencia de la matriz de inventario privada.
     * Permite que tu compañero o el visualizador accedan a los datos desde el Main.
     * * @return La matriz bidimensional de Strings (4 columnas).
     */
    public String[][] getInventario() {
        return this.inventario;
    }
}