import java.util.*;

public class HashFunction {
    public static int hash(int x) {
        return x % 10;
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        int valueToHash = 25;
        int index = hash(valueToHash);

        System.out.println("Valor a almacenar: " + valueToHash);
        System.out.println("Índice en la tabla: " + index);
    }
}
