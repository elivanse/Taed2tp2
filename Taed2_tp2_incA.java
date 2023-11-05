// Apellido: Pereyra
// Nombre: Ivan Maximiliano
// Legajo: VINF011264

import java.util.*;

public class HashFunction {
    public static int hash(int x) {
        return x % 10;
    }

    public static void main(String[] args) {
    
        int valor2Hash = 25;
        int index = hash(valor2Hash);

        System.out.println("Valor a almacenar: " + value2Hash);
        System.out.println("Índice en la tabla: " + index);
    }
}
