// Apellido: Pereyra
// Nombre: Ivan Maximiliano
// Legajo: VINF011264
// inciso A

import java.util.*;

public class FuncionHash {
    public static int hash(int x) {
        return x % 10;
    }

    public static void main(String[] args) {
    
        int valor2Hash = 25;
        int index = hash(valor2Hash);

        System.out.println("Valor a almacenar: " + valor2Hash);
        System.out.println("Índice en la tabla: " + index);
    }
}

