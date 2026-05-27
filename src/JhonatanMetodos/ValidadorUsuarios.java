package JhonatanMetodos;
/**
 * Clase encargada de la validacion de los usuarios
 * El usuario debe tener una estructura fija: nombre.cliente || nombre.usuario
 * @author Denis Jhonatan Chango Cutiopala
 * @version 1.1
 */
public class ValidadorUsuarios {

    public final int er = -10;
    public final int ESTADO_ADMIN = 14;   // q14
    public final int ESTADO_CLIENTE = 9;  // q9 

    // Matriz 
    final int mt[][] = {
        // .   c   l   i   e   n   t   a   d   m  Otro Enter
        { er,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  er }, // q0
        {  2,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  er }, // q1 
        { er,  3, er, er, er, er, er, 10, er, er, er,  er }, // q2 
        { er, er,  4, er, er, er, er, er, er, er, er,  er }, // q3 
        { er, er, er,  5, er, er, er, er, er, er, er,  er }, // q4 
        { er, er, er, er,  6, er, er, er, er, er, er,  er }, // q5 
        { er, er, er, er, er,  7, er, er, er, er, er,  er }, // q6 
        { er, er, er, er, er, er,  8, er, er, er, er,  er }, // q7 
        { er, er, er, er,  9, er, er, er, er, er, er,  er }, // q8 
        { er, er, er, er, er, er, er, er, er, er, er,   9 }, // q9 
        { er, er, er, er, er, er, er, er, 11, er, er,  er }, // q10 
        { er, er, er, er, er, er, er, er, er, 12, er,  er }, // q11 
        { er, er, er, 13, er, er, er, er, er, er, er,  er }, // q12 
        { er, er, er, er, er, 14, er, er, er, er, er,  er }, // q13 
        { er, er, er, er, er, er, er, er, er, er, er,  14 }  // q14 
    };
    /**
     * Nos dirije a la columna correspondiente en la matriz de transicion.
     * @param c: Evalua a que categoria pertenece el caracter y retorna el valor de la columna correspondiente.
     * @return: en caso de ser un caracter invalido retorna er correspondiente a la variable de error.
     */
    private int getIndexAlfabeto(char c) {
        switch (c) {
            case '.': return 0;
            case 'c': return 1;
            case 'l': return 2;
            case 'i': return 3;
            case 'e': return 4;
            case 'n': return 5;
            case 't': return 6;
            case 'a': return 7;
            case 'd': return 8;
            case 'm': return 9;
            case '\n': return 11; 
            default:
                if (Character.isLetterOrDigit(c)) return 10;
                return er; 
        }
    }
    /**
     * validarUsuario se encarga de recorrer la matriz de transicion comprobando caracter a caracter
     * si el usuario es valido o no.
     * @param nombreIngresado: Es el usuario que se va a comprobar.
     * @return: una vez recorrida toda la matriz de transicion nos retorna el estado q9 || q14 los cuales son los estados de aceptacion. 
     */
    public int validarUsuario(String nombreIngresado) {
        int indexAlfa = 0;
        int q = 0; 

        if (!nombreIngresado.endsWith("\n")) {
            nombreIngresado += "\n";
        }

        for (int i = 0; i < nombreIngresado.length(); i++) {
            char caracterActual = nombreIngresado.charAt(i);
            indexAlfa = getIndexAlfabeto(caracterActual);

            if (indexAlfa == er || mt[q][indexAlfa] == er) {
                return er;  
            }

            q = mt[q][indexAlfa];
        }

        return q; 
    }
}