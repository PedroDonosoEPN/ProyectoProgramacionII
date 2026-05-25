package MatiasMetodos;

import PedroMetodos.gestionStock;

public class Inventario {

    public void AgregarProducto(gestionStock gs, String id, String nombre, double precio, int stock) {
        String[][] inventario = gs.getInventario();
        
        if (id == null || id.trim().isEmpty()) {
            System.out.println("Error");
            return;
        }

        // CORRECCIÓN 1: Ahora sí valida que no pase de 7 caracteres máximos
        if (id.length() > 7) {
            System.out.println("Maximo 7 caracteres");
            return;
        }

        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Error al ingresar nombre");
            return;
        }

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

    public void EliminarProducto(gestionStock gs, String id, String nombre) {
        String[][] inventario = gs.getInventario();
        boolean encontrado = false;

        for (int i = 0; i < inventario.length; i++) {
            // CORRECCIÓN 2: Valida tanto el ID como el Nombre antes de borrar para que sea seguro
            if (inventario[i][0] != null && inventario[i][0].equals(id) && inventario[i][1].equalsIgnoreCase(nombre)) {
                
                // "Eliminamos" el producto vaciando sus columnas
                inventario[i][0] = null; 
                inventario[i][1] = null; 
                inventario[i][2] = null; 
                inventario[i][3] = null; 
                
                System.out.println(nombre + " eliminado exitosamente.");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("El ID o el Nombre no coinciden con ningun producto registrado.");
        }
    }
}