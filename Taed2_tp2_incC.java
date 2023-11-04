import java.util.LinkedList;

class HashTable {
    private int size;
    private LinkedList<String>[] table;

    public HashTable(int size) {
        this.size = size;
        table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hash(String key) {
        // Aquí puedes implementar tu función de hash
        // Puedes usar key.hashCode() % size, por ejemplo
        return Math.abs(key.hashCode() % size);
    }

    public void insert(String key, String value) {
        int index = hash(key);
        table[index].add(value);
    }

    public void displayTable() {
        for (int i = 0; i < size; i++) {
            System.out.print("Bucket " + i + ": ");
            for (String value : table[i]) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(10);

        hashTable.insert("key1", "value1");
        hashTable.insert("key2", "value2");
        hashTable.insert("key3", "value3");
        hashTable.insert("key4", "value4");
        hashTable.insert("key5", "value5");

        hashTable.displayTable();
    }
}
