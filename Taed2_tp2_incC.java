// Apellido: Pereyra
// Nombre: Ivan Maximiliano
// Legajo: VINF011264

import java.util.ListaEnlazada;

class Hashtabla {
    private int tamanio;
    private ListaEnlazada<String>[] tabla;

    public Hashtabla(int tamanio) {
        this.tamanio = tamanio;
        tabla = new ListaEnlazada[tamanio];
        for (int i = 0; i < tamanio; i++) {
            tabla[i] = new ListaEnlazada<>();
        }
    }

    private int hash(String llave) {
        // Aquí puedes implementar tu función de hash
        // Puedes usar llave.hashCode() % tamanio, por ejemplo
        return Math.abs(llave.hashCode() % tamanio);
    }

    public void insert(String llave, String valor) {
        int index = hash(llave);
        tabla[index].add(valor);
    }

    public void displaytabla() {
        for (int i = 0; i < tamanio; i++) {
            System.out.print("Bucket " + i + ": ");
            for (String valor : tabla[i]) {
                System.out.print(valor + " ");
            }
            System.out.println();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Hashtabla hashtabla = new Hashtabla(10);

        hashtabla.insert("llave1", "valor1");
        hashtabla.insert("llave2", "valor2");
        hashtabla.insert("llave3", "valor3");
        hashtabla.insert("llave4", "valor4");
        hashtabla.insert("llave5", "valor5");

        hashtabla.displaytabla();
    }
}
