package MatiasEnriquez;

import java.util.ArrayList;

class Inventario{
    ArrayList<Producto> productos = new ArrayList<>();

    public void AgregarProducto(String id, String nombre, double precio, int stock){
        boolean existe = false;

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

        for(Producto nuevo : productos){
            if(nuevo.ID.equals(id)){
            nuevo.stock = nuevo.stock + stock;
            System.out.println("Producto Existente. Stock actualizado: " + nuevo.stock);
            existe = true;
            break;
            }
        }

    if(!existe){
        Producto nuevoProducto = new Producto(id, nombre, precio, stock);
        productos.add(nuevoProducto);
        System.out.println("Producto agregado");
        }
    }

    public void EliminarProducto(String id, String nombre){
        boolean encontrado = false;

        for(int i = 0; i < productos.size(); i++){
            if(productos.get(i).ID.equals(id)){
                productos.remove(i);
                System.out.println(nombre + " eliminado exitosamente");
                encontrado = true;
                break;
            }
        }

        if (!encontrado){
            System.out.println("La ID no coincide con ningun producto");
        }
    }
}