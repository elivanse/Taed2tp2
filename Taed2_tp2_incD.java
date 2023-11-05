// Apellido: Pereyra
// Nombre: Ivan Maximiliano
// Legajo: VINF011264

import java.util.Scanner;

public class HashTable {
    private int[] table;
    private int size;

    public HashTable(int size) {
        this.size = size;
        this.table = new int[size];
    }

    public void insert(int key, String probingMethod) {
        int index = hash(key);
        int i = 1;

        while (table[index] != 0) {
            // Manejo de colisiones
            if (probingMethod.equals("linear")) {
                index = (index + 1) % size; // Sonda lineal
            } else if (probingMethod.equals("quadratic")) {
                index = (index + i * i) % size; // Sonda cuadrática
                i++;
            }

            if (index < 0) {
                index += size; // Asegurar que el índice sea no negativo
            }
        }

        table[index] = key;
    }

    public boolean search(int key) {
        int index = hash(key);

        while (table[index] != 0) {
            if (table[index] == key) {
                return true; // Valor encontrado
            }

            index = (index + 1) % size; // Sonda lineal para búsqueda
        }

        return false; // Valor no encontrado
    }

    public void delete(int key) {
        int index = hash(key);

        while (table[index] != 0) {
            if (table[index] == key) {
                table[index] = 0; // Eliminar valor
                System.out.println("Valor " + key + " eliminado.");
                return;
            }

            index = (index + 1) % size; // Sonda lineal para eliminación
        }

        System.out.println("Valor " + key + " no encontrado.");
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
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el tamaño de la tabla hash: ");
        int tableSize = scanner.nextInt();

        HashTable hashTable = new HashTable(tableSize);

        while (true) {
            System.out.println("\nMenú:");
            System.out.println("1. Insertar valor");
            System.out.println("2. Buscar valor");
            System.out.println("3. Eliminar valor");
            System.out.println("4. Mostrar tabla");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Ingrese el valor a insertar: ");
                    int insertValue = scanner.nextInt();
                    System.out.print("Seleccione el método de manejo de colisiones (linear/quadratic): ");
                    String insertProbingMethod = scanner.next();
                    hashTable.insert(insertValue, insertProbingMethod);
                    break;
                case 2:
                    System.out.print("Ingrese el valor a buscar: ");
                    int searchValue = scanner.nextInt();
                    System.out.println("El valor " + searchValue + " " +
                            (hashTable.search(searchValue) ? "está presente." : "no está presente."));
                    break;
                case 3:
                    System.out.print("Ingrese el valor a eliminar: ");
                    int deleteValue = scanner.nextInt();
                    hashTable.delete(deleteValue);
                    break;
                case 4:
                    hashTable.displayTable();
                    break;
                case 0:
                    System.out.println("Saliendo del programa.");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }
}
