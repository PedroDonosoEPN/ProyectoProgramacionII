package EidanMetodos;

import PedroMetodos.gestionStock;

public class sistemaVentas {
    // [0] ID
    // [1] Nombre
    // [2] Cantidad
    // [3] Precio

    private String[][] carrito;

    public gestionStock gs;

    public sistemaVentas(int maxProductos, gestionStock gs) {
        carrito = new String[maxProductos][4];
        this.gs = gs;
    }

    public String[][] getCarrito() {
        return carrito;
    }

    // -------------------------------------------------
    // AGREGAR PRODUCTO AL CARRITO
    // -------------------------------------------------
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

    // -------------------------------------------------
    // MOSTRAR CARRITO
    // -------------------------------------------------
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
     // -------------------------------------------------
    // BORRAR PRODUCTO DEL CARRITO
    // -------------------------------------------------
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

    // -------------------------------------------------
    // FINALIZAR COMPRA
    // -------------------------------------------------
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


