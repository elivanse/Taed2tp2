// Apellido: Pereyra
// Nombre: Ivan Maximiliano
// Legajo: VINF011264
//inciso C

public class HashTabla {
    private int[] table;
    private int tamanio;

    public HashTabla(int tamanio) {
        this.tamanio = tamanio;
        this.table = new int[tamanio];
    }

    public void insertarLinear(int key) {
        int index = hash(key);

        while (table[index] != 0) {
            // Sonda lineal
            index = (index + 1) % tamanio;
        }

        table[index] = key;
    }

    public void insertarCuadratico(int key) {
        int index = hash(key);
        int i = 1;

        while (table[index] != 0) {
            // Sonda cuadrática
            index = (index + i * i) % tamanio;
            i++;
            if (index < 0) {
                index += tamanio; // Asegurar que el índice sea no negativo
            }
        }

        table[index] = key;
    }

    public void displayTable() {
        System.out.println("Tabla Hash:");
        for (int i = 0; i < tamanio; i++) {
            System.out.println("Índice " + i + ": " + table[i]);
        }
    }

    private int hash(int key) {
        return key % tamanio;
    }

    public static void main(String[] args) {
        HashTabla TablaLinear = new HashTabla(10);
        HashTabla TablaCuadratica = new HashTabla(10);

        int[] keys = { 25, 35, 45, 55, 65, 75, 85, 95 };

        for (int key : keys) {
            TablaLinear.insertarLinear(key);
            TablaCuadratica.insertarCuadratico(key);
        }

        System.out.println("Sondeo Lineal:");
        TablaLinear.displayTable();

        System.out.println("\nSondeo Cuadratico:");
        TablaCuadratica.displayTable();
    }
}