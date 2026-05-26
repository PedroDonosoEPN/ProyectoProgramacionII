# 🛒 Sistema de Gestión de Inventario, Ventas y Accesos

## 📝 Descripción del Proyecto
Este proyecto es una aplicación modular desarrollada en **Java** para la administración automatizada de productos y facturación en una tienda de tecnología. El sistema implementa un control de acceso estructurado para diferenciar los roles de **Administrador** y **Cliente**, coordinando las operaciones de almacén y compras mediante una matriz compartida en memoria.

---

## 👥 Integrantes
* Pedro 
* Eidan
* Jhonatan
* Matías

---

## 📐 Estructura y Arquitectura del Sistema

El proyecto se divide en módulos independientes organizados por paquetes (`packages`), los cuales se coordinan a través de los controladores principales:

* **Módulo de Autenticación (`JhonatanMetodos`)**: Se encarga de procesar la cadena de texto de acceso del usuario. Valida los formatos requeridos (`.admin` o `.cliente`) y retorna códigos de estado numéricos para derivar los privilegios en el sistema.
* **Módulo de Inventario (`MatiasMetodos`)**: Contiene las reglas de negocio para el registro y eliminación de productos. Controla que los IDs no excedan los 7 caracteres, valida que los nombres no contengan símbolos extraños y gestiona las bajas completas de artículos.
* **Módulo de Control de Stock (`PedroMetodos`)**: Encargado de las operaciones matemáticas directas sobre las celdas de la matriz. Permite abastecer (sumar) o retirar (restar) unidades por ID o Nombre, asegurando que el stock nunca sea inferior a cero.
* **Módulo de Visualización (`PedroMetodos`)**: Formatea los datos volátiles de la matriz para renderizar una tabla alineada por consola, mostrando de forma ordenada los campos de información con formato de moneda.
* **Módulo de Ventas (`EidanMetodos`)**: Implementa el sistema de transacciones comerciales orientado al usuario final. Se encarga de procesar las compras del cliente, verificar la disponibilidad del producto seleccionado, calcular los totales y rebajar las unidades correspondientes del inventario.

---

## 🛠️ Estructura de la Matriz de Datos
La persistencia de los productos se maneja a través de una matriz bidimensional de tipo `String[][]` con una arquitectura estricta de 4 columnas:

| Índice de Columna | Campo de Información | Tipo de Dato Interno |
| :---: | :--- | :--- |
| **`[i][0]`** | Identificador Único (ID) | `String` (Máx. 7 caracteres) |
| **`[i][1]`** | Nombre del Producto | `String` (Texto y espacios) |
| **`[i][2]`** | Cantidad / Existencias | `String` (Parseado a `int`) |
| **`[i][3]`** | Precio Unitario | `String` (Parseado a `double`) |

---

## 🔄 Flujo General de Ejecución

1.  **Ingreso:** El programa arranca en `App.java` y solicita las credenciales por consola.
2.  **Filtro de Roles:** * Si el usuario es administrador, se despliega el menú interactivo completo en `Controlador.java` (Altas, bajas, modificación de stock y reportes).
    * Si ingresa como cliente, el flujo se desvía a `ControladorCliente.java`, dando acceso directo al **Sistema de Ventas** para buscar productos, consultar precios y realizar compras.
3.  **Sincronización:** Todos los módulos operan sobre la misma referencia de memoria de la matriz, garantizando que cuando un cliente realiza una compra, el stock se actualice inmediatamente en el panel del administrador.
