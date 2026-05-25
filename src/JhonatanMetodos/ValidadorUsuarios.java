package JhonatanMetodos;

public class ValidadorUsuarios {

    // Constantes de estados
    public final int er = -10;
    public final int ESTADO_ADMIN = 14;   
    public final int ESTADO_CLIENTE = 15; 

    // Matriz de transiciones
    final int mt[][] = {
        // .   c   l   i   e   n   t   a   d   m  Otro Enter
        { er, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16,  er }, // q0 (Obliga a empezar con letra/número)
        { er,  2, er, er, er, er, er,  8, er, er, er,  er }, // q1
        { er, er,  3, er, er, er, er, er, er, er, er,  er }, // q2
        { er, er, er,  4, er, er, er, er, er, er, er,  er }, // q3
        { er, er, er, er,  5, er, er, er, er, er, er,  er }, // q4
        { er, er, er, er, er,  6, er, er, er, er, er,  er }, // q5
        { er, er, er, er, er, er,  7, er, er, er, er,  er }, // q6
        { er, er, er, er, 13, er, er, er, er, er, er,  er }, // q7
        { er, er, er, er, er, er, er, er,  9, er, er,  er }, // q8
        { er, er, er, er, er, er, er, er, er, 10, er,  er }, // q9
        { er, er, er, 11, er, er, er, er, er, er, er,  er }, // q10
        { er, er, er, er, er, 12, er, er, er, er, er,  er }, // q11
        { er, er, er, er, er, er, er, er, er, er, er,  14 }, // q12
        { er, er, er, er, er, er, er, er, er, er, er,  15 }, // q13
        { er, er, er, er, er, er, er, er, er, er, er,  er }, // q14
        { er, er, er, er, er, er, er, er, er, er, er,  er }, // q15
        {  1, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16,  er }  // q16 (Bucle del nombre)
    };

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