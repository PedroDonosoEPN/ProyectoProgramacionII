package EidanMetodos;

import PedroMetodos.gestionStock;
/**
 * Clase encargada de las funciones relacionadas con el proceso de compra y gestión del carrito de compras.
 * Admite operaciones como agregar productos al carrito, mostrar el contenido del carrito, eliminar productos del carrito y finalizar la compra.
 * * @author Eidan Clavijo Rafael Alvarado
 * @version 1.3
 */
public class sistemaVentas {
    // [0] ID
    // [1] Nombre
    // [2] Cantidad
    // [3] Precio

    private String[][] carrito;

    public gestionStock gs;
    /**
     * Permite ingresar a un nuevo sistema de ventas con un carrito vacío y una referencia al gestor de inventario para poder modificar el stock al agregar o eliminar productos del carrito.
     * Inicializa la matriz del carrito vacía con espacio para 4 columnas (ID, Nombre, Cantidad, Precio).
     * * @param maxProductos Número máximo de productos distintos que el carrito puede contener.
      * @param gs Referencia al gestor de inventario para poder modificar el stock al agregar
     */
    public sistemaVentas(int maxProductos, gestionStock gs) {
        carrito = new String[maxProductos][4];
        this.gs = gs;
    }
    /** 
     * Obtiene el contenido del carrito.
     * * @return Matriz que representa el carrito de compras.
     */
    public String[][] getCarrito() {
        return carrito;
    }

 /** Funcion para agregar productos al carrito. Valida que el producto exista en el inventario, que la cantidad sea positiva y que haya stock suficiente antes de agregarlo al carrito. Si el producto ya existe en el carrito, simplemente actualiza la cantidad. Al agregar un producto al carrito, también descuenta la cantidad correspondiente del stock en el inventario.
  * @param gs Referencia al gestor de inventario para acceder a la matriz de inventario y modificar el stock.
  * @param id ID del producto a agregar al carrito. Debe coincidir con el ID registrado en el inventario.
  * @param cantidad Cantidad del producto a agregar al carrito. Debe ser un número entero positivo y no puede exceder el stock disponible en el inventario.
  *   */
    public void agregarAlCarrito(
            gestionStock gs,
            String id,
            int cantidad) {

        String[][] inventario = gs.getInventario();

        // Buscar producto en inventario
        for (int i = 0; i < inventario.length; i++) {

            if (inventario[i][0] != null &&
                inventario[i][0].equals(id)) {

                String nombre = inventario[i][1];

                int stockDisponible =
                    Integer.parseInt(inventario[i][2]);

                double precio =
                    Double.parseDouble(inventario[i][3]);

                // Validar cantidad
                if (cantidad <= 0) {
                    System.out.println("Cantidad invalida");
                    return;
                }

                // Validar stock
                if (cantidad > stockDisponible) {
                    System.out.println("Stock insuficiente");
                    return;
                }

                // Buscar si ya existe en carrito
                for (int j = 0; j < carrito.length; j++) {

                    if (carrito[j][0] != null &&
                        carrito[j][0].equals(id)) {

                        int cantidadActual =
                            Integer.parseInt(carrito[j][2]);

                        carrito[j][2] =
                            String.valueOf(cantidadActual + cantidad);

                        // Descontar inventario
                        inventario[i][2] =
                            String.valueOf(stockDisponible - cantidad);

                        System.out.println("Cantidad actualizada en carrito");
                        return;
                    }
                }

                // Agregar nuevo producto al carrito
                for (int j = 0; j < carrito.length; j++) {

                    if (carrito[j][0] == null) {

                        carrito[j][0] = id;
                        carrito[j][1] = nombre;
                        carrito[j][2] = String.valueOf(cantidad);
                        carrito[j][3] = String.valueOf(precio);

                        // Descontar inventario
                        inventario[i][2] =
                            String.valueOf(stockDisponible - cantidad);

                        System.out.println("Producto agregado al carrito");
                        return;
                    }
                }

                System.out.println("Carrito lleno");
                return;
            }
        }

        System.out.println("Producto no encontrado");
    }

   /** Funcion que enlista los productos actualmente en el carrito, mostrando su ID, nombre, cantidad, precio unitario y subtotal (cantidad x precio). Al final de la lista, muestra el total acumulado de la compra. Si el carrito está vacío, muestra un mensaje indicando que no hay productos en el carrito.
  *   */
    public void mostrarCarrito() {

        System.out.println("\n======= CARRITO =======");

        double total = 0;

        for (int i = 0; i < carrito.length; i++) {

            if (carrito[i][0] != null) {

                String id = carrito[i][0];
                String nombre = carrito[i][1];

                int cantidad =
                    Integer.parseInt(carrito[i][2]);

                double precio =
                    Double.parseDouble(carrito[i][3]);

                double subtotal = cantidad * precio;

                total += subtotal;

                System.out.println(
                    "ID: " + id +
                    " | Nombre: " + nombre +
                    " | Cantidad: " + cantidad +
                    " | Precio: $" + precio +
                    " | Subtotal: $" + subtotal
                );
            }
        }

        System.out.println("-----------------------");
        System.out.println("TOTAL: $" + total);
    }
     /** Permite eliminar un producto específico del carrito buscando por su ID. Al eliminar un producto del carrito, también regresa la cantidad correspondiente al stock en el inventario. Si el producto no se encuentra en el carrito, muestra un mensaje indicando que el producto no está en el carrito.
  * @param gs Referencia al gestor de inventario para acceder a la matriz de inventario y modificar el stock al eliminar un producto del carrito.
  * @param id ID del producto a eliminar del carrito. Debe coincidir con el ID registrado en el carrito. 
  *   */
    public void borrarProductoCarrito(
            gestionStock gs,
            String id) {

        String[][] inventario = gs.getInventario();

        for (int i = 0; i < carrito.length; i++) {

            if (carrito[i][0] != null &&
                carrito[i][0].equals(id)) {

                int cantidadCarrito =
                    Integer.parseInt(carrito[i][2]);

                // Regresar stock al inventario
                for (int j = 0; j < inventario.length; j++) {

                    if (inventario[j][0] != null &&
                        inventario[j][0].equals(id)) {

                        int stockActual =
                            Integer.parseInt(inventario[j][2]);

                        inventario[j][2] =
                            String.valueOf(
                                stockActual + cantidadCarrito
                            );

                        break;
                    }
                }

                // Eliminar producto del carrito
                carrito[i][0] = null;
                carrito[i][1] = null;
                carrito[i][2] = null;
                carrito[i][3] = null;

                System.out.println("Producto eliminado del carrito");
                return;
            }
        }

        System.out.println("Producto no encontrado en carrito");
    }

  /** Permite finalizar la compra, calculando el total de la compra sumando el subtotal de cada producto en el carrito (cantidad x precio). Luego verifica si el dinero inicial proporcionado por el cliente es suficiente para cubrir el total de la compra. Si el dinero es insuficiente, muestra un mensaje indicando que no se puede finalizar la compra y retorna el dinero inicial sin modificar. Si el dinero es suficiente, procede a modificar el stock en el inventario restando las cantidades compradas y luego descuenta el total de la compra del dinero inicial. Finalmente, vacía el carrito y muestra un mensaje indicando que la compra se ha realizado correctamente junto con el dinero restante.
  * @param dineroInicial El monto de dinero que el cliente tiene disponible para realizar la compra
  *   */
        public double finalizarCompra(
        double dineroInicial
    ) {

        double total = 0;

        // Calcular total
        for (int i = 0; i < carrito.length; i++) {

            if (carrito[i][0] != null) {

                int cantidad =
                    Integer.parseInt(carrito[i][2]);

                double precio =
                    Double.parseDouble(carrito[i][3]);

                total += cantidad * precio;
            }
        }

        System.out.println(
            "Total de compra: $" + total
        );

        // Verificar dinero
        if (total > dineroInicial) {

            System.out.println(
                "Dinero insuficiente"
            );

            return dineroInicial;
        }

        // Modificar stock
        for (int i = 0; i < carrito.length; i++) {

            if (carrito[i][0] != null) {

                String id = carrito[i][0];

                int cantidad =
                    Integer.parseInt(carrito[i][2]);

                // Restar stock
                gs.modificarStock(id, -cantidad);
            }
        }

        // Descontar dinero
        dineroInicial -= total;

        System.out.println(
            "Compra realizada correctamente"
        );

        System.out.println(
            "Dinero restante: $" + dineroInicial
        );

        // Vaciar carrito
        for (int i = 0; i < carrito.length; i++) {

            carrito[i][0] = null;
            carrito[i][1] = null;
            carrito[i][2] = null;
            carrito[i][3] = null;
        }

        return dineroInicial;
    }
}


