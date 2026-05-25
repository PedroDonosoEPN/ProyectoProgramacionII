package JhonatanMetodos;

public class ValidadorUsuarios {

    // Tus constantes basadas exactamente en tu grafo en papel
    public final int er = -10;
    public final int ESTADO_ADMIN = 14;   // q14
    public final int ESTADO_CLIENTE = 9;  // q9 

    // Matriz 
    final int mt[][] = {
        // .   c   l   i   e   n   t   a   d   m  Otro Enter
        { er,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  er }, // q0: Cualquier letra/número arranca el nombre -> q1
        {  2,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  er }, // q1: El nombre puede contener letras del alfabeto. '.' va a q2
        { er,  3, er, er, er, er, er, 10, er, er, er,  er }, // q2: Bifurcación ('c' -> q3, 'a' -> q10)
        { er, er,  4, er, er, er, er, er, er, er, er,  er }, // q3: 'l' -> q4
        { er, er, er,  5, er, er, er, er, er, er, er,  er }, // q4: 'i' -> q5
        { er, er, er, er,  6, er, er, er, er, er, er,  er }, // q5: 'e' -> q6
        { er, er, er, er, er,  7, er, er, er, er, er,  er }, // q6: 'n' -> q7
        { er, er, er, er, er, er,  8, er, er, er, er,  er }, // q7: 't' -> q8
        { er, er, er, er,  9, er, er, er, er, er, er,  er }, // q8: 'e' -> q9
        { er, er, er, er, er, er, er, er, er, er, er,   9 }, // q9: ESTADO DE ACEPTACIÓN (Consume el Enter)
        { er, er, er, er, er, er, er, er, 11, er, er,  er }, // q10: 'd' -> q11
        { er, er, er, er, er, er, er, er, er, 12, er,  er }, // q11: 'm' -> q12
        { er, er, er, 13, er, er, er, er, er, er, er,  er }, // q12: 'i' -> q13
        { er, er, er, er, er, 14, er, er, er, er, er,  er }, // q13: 'n' -> q14
        { er, er, er, er, er, er, er, er, er, er, er,  14 }  // q14: ESTADO DE ACEPTACIÓN (Consume el Enter)
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