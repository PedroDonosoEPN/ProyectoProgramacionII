package PedroMetodos;

/**
 * Clase encargada de la gestión y control del inventario de productos.
 * Permite administrar las existencias utilizando una estructura de matriz bidimensional.
 * * @author Pedro José Donoso Vélez
 * @version 1.0
 */
public class gestionStock {
    
    // Atributos de la clase
    private String[][] inventario;
    private int maxProductos;

    /**
     * Constructor de la clase. Inicializa la matriz de inventario vacía
     * con el tamaño máximo de productos permitido.
     * * @param filas Número máximo de filas (productos distintos) que soportará el sistema.
     */
    public gestionStock(int filas) {
        this.maxProductos = filas;
        // Matriz de 'filas' x 2 columnas (Columna 0: Nombre, Columna 1: Cantidad)
        this.inventario = new String[maxProductos][2];
    }

    /**
     * Modifica el stock de un producto específico, permitiendo tanto aumentos 
     * como disminuciones en una sola operación lógica.
     * * @param nombreBuscar El nombre del producto al cual se le alterará el stock.
     * @param cantidad El número de unidades a modificar. Use valores positivos 
     * para abastecer (sumar) y valores negativos para retirar (restar).
     * @return {@code true} si el producto existe y la operación se realizó con éxito; 
     * {@code false} si el producto no fue encontrado o si el stock resultante 
     * es menor a cero.
     */
    public boolean modificarStock(String nombreBuscar, int cantidad) {
        for (int i = 0; i < maxProductos; i++) {
            if (inventario[i][0] != null) {
                if (inventario[i][0].equalsIgnoreCase(nombreBuscar)) {
                    
                    // Paso 1: Convertir el texto de la matriz a número entero
                    int stockActual = Integer.parseInt(inventario[i][1]);
                    
                    // Paso 2: Calcular el nuevo stock (la ley de signos hace la magia)
                    int nuevoStock = stockActual + cantidad;
                    
                    // Validación de seguridad por si es una resta y se pasa de cero
                    if (nuevoStock < 0) {
                        System.out.println("-> Error: Stock insuficiente. Solo quedan " + stockActual + " unidades.");
                        return false; 
                    }
                    
                    // Paso 3: Guardar el nuevo valor como texto en la matriz
                    inventario[i][1] = String.valueOf(nuevoStock);
                    return true; // Éxito
                }
            }
        }
        System.out.println("-> Error: El producto '" + nombreBuscar + "' no existe.");
        return false;
    }
}