package MatiasMetodos;

import PedroMetodos.gestionStock;

public class Inventario{

    public void AgregarProducto(gestionStock gs, String id, String nombre, double precio, int stock){
        String[][] inventario = gs.getInventario();
        
        if(id == null || id.trim().isEmpty()){
            System.out.println("Error");
            return;
        }

        if(id.length()!=7){
            System.out.println("Maximo 7 caracteres");
            return;

        }

        if(nombre == null || nombre.trim().isEmpty()){
            System.out.println("Error al ingresar nombre");
            return;
        }

        for(int i = 0;i < nombre.length(); i++){
            char letra = nombre.charAt(i);
            if(!Character.isLetter(letra) && letra != ' '){
                System.out.println("Error. Caracter no reconocido");
                return;
            }
        }

        if(precio <=0){
            System.out.println("Error");
            return;
        }

        if(stock<0){
            System.out.println("Error");
            return;
        }

        boolean existe = false;

        for(int i = 0; i<inventario.length; i++){
            if(inventario[i][0] != null && inventario[i][0].equals(id)){
                int stockActual = Integer.parseInt(inventario[i][2]);
                int nuevoStock = stockActual + stock;

                //Guardar como texto
                inventario[i][2] = String.valueOf(nuevoStock);
                System.out.println("Producto Existente. Stock Actaulizado");
                existe = true;
                break;
            }
        }

    if(!existe){
        boolean agregado = false;
        for(int i = 0; i<inventario.length;i++){
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

            if(!agregado){
                System.out.println("Error. Inventario lleno");
            }
        }
    }

    public void EliminarProducto(gestionStock gs, String id, String nombre){
        String[][] inventario = gs.getInventario();
        boolean encontrado = false;

        for(int i = 0; i < inventario.length; i++){
            // Si la fila tiene datos y el ID coincide
            if (inventario[i][0] != null && inventario[i][0].equals(id)) {
                
                // "Eliminamos" el producto vaciando sus columnas
                inventario[i][0] = null; // ID vaciado
                inventario[i][1] = null; // Nombre vaciado
                inventario[i][2] = null; // Stock vaciado
                inventario[i][3] = null; // Precio vaciado
                
                System.out.println(nombre + " eliminado exitosamente.");
                encontrado = true;
                break;
            }
        }

        if (!encontrado){
            System.out.println("La ID no coincide con ningun producto");
        }
    }
}