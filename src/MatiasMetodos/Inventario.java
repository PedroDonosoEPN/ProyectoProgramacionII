package MatiasMetodos;

import PedroMetodos.gestionStock;

public class Inventario {

    // Subproceso para Agregar un Producto

    public void AgregarProducto(gestionStock gs, String id, String nombre, double precio, int stock) {
        String[][] inventario = gs.getInventario();
        
        //Verificacion de cada caracteristica del producto

        if (id == null || id.trim().isEmpty()) {
            System.out.println("Error");
            return;
        }

        // ID solo puede ser de 7 caracteres
        if (id.length() != 7) {
            System.out.println("Maximo 7 caracteres");
            return;
        }

        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Error al ingresar nombre");
            return;
        }

        // Nombre solo puede tener letras

        for (int i = 0; i < nombre.length(); i++) {
            char letra = nombre.charAt(i);
            if (!Character.isLetter(letra) && letra != ' ') {
                System.out.println("Error. Caracter no reconocido");
                return;
            }
        }

        if (precio <= 0) {
            System.out.println("Error");
            return;
        }

        if (stock < 0) {
            System.out.println("Error");
            return;
        }

        boolean existe = false;

        //Si el id coincide con uno del invetario, se actualiza el stock

        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i][0] != null && inventario[i][0].equals(id)) {
                int stockActual = Integer.parseInt(inventario[i][2]);
                int nuevoStock = stockActual + stock;

                // Guardar como texto
                inventario[i][2] = String.valueOf(nuevoStock);
                System.out.println("Producto Existente. Stock Actualizado");
                existe = true;
                break;
            }
        }

        // Caso de que no, se agrega un nuevo producto
        if (!existe) {
            boolean agregado = false;
            for (int i = 0; i < inventario.length; i++) {
                if (inventario[i][0] == null) {
                    inventario[i][0] = id;
                    inventario[i][1] = nombre;
                    inventario[i][2] = String.valueOf(stock);
                    inventario[i][3] = String.valueOf(precio);
                    
                    System.out.println("Producto agregado exitosamente.");
                    agregado = true;
                    break;
                }
            }

            if (!agregado) {
                System.out.println("Error. Inventario lleno");
            }
        }
    }


    // Subproceso de Eliminacion de un Producto
    public void EliminarProducto(gestionStock gs, String id, String nombre) {
        String[][] inventario = gs.getInventario();
        boolean encontrado = false;

        for (int i = 0; i < inventario.length; i++) {
            // Valida tanto el ID como el Nombre antes de borrar para que sea seguro
            if (inventario[i][0] != null && inventario[i][0].equals(id) && inventario[i][1].equalsIgnoreCase(nombre)) {
                
                // "liminamos el producto vaciando sus columnas
                inventario[i][0] = null; 
                inventario[i][1] = null; 
                inventario[i][2] = null; 
                inventario[i][3] = null; 
                
                System.out.println(nombre + " eliminado exitosamente.");
                encontrado = true;
                break;
            }
        }

        // Caso de que la ID no coincida, no se borra nada
        
        if (!encontrado) {
            System.out.println("El ID o el Nombre no coinciden con ningun producto registrado.");
        }
    }
}