public class HashTable {
    private int[] table;
    private int size;

    public HashTable(int size) {
        this.size = size;
        this.table = new int[size];
    }

    public void insertLinearProbing(int key) {
        int index = hash(key);

        while (table[index] != 0) {
            // Sonda lineal
            index = (index + 1) % size;
        }

        table[index] = key;
    }

    public void insertQuadraticProbing(int key) {
        int index = hash(key);
        int i = 1;

        while (table[index] != 0) {
            // Sonda cuadrática
            index = (index + i * i) % size;
            i++;
            if (index < 0) {
                index += size; // Asegurar que el índice sea no negativo
            }
        }

        table[index] = key;
    }

    public void displayTable() {
        System.out.println("Tabla Hash:");
        for (int i = 0; i < size; i++) {
            System.out.println("Índice " + i + ": " + table[i]);
        }
    }

    private int hash(int key) {
        return key % size;
    }

    public static void main(String[] args) {
        HashTable linearProbingTable = new HashTable(10);
        HashTable quadraticProbingTable = new HashTable(10);

        int[] keys = { 25, 35, 45, 55, 65, 75, 85, 95 };

        for (int key : keys) {
            linearProbingTable.insertLinearProbing(key);
            quadraticProbingTable.insertQuadraticProbing(key);
        }

        System.out.println("Sondeo Lineal:");
        linearProbingTable.displayTable();

        System.out.println("\nSondeo Cuadrático:");
        quadraticProbingTable.displayTable();
    }
}
