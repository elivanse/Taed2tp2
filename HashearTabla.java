// Apellido: Pereyra
// Nombre: Ivan Maximiliano
// Legajo: VINF011264
//inciso B

public class HashearTabla {
    private int[] tabla;
    private int tamanio;

    public HashearTabla(int tamanio) {
        this.tamanio = tamanio;
        this.tabla = new int[tamanio];
    }

    public void InsertarLinearDPrueba(int key) {
        int index = hash(key);

        while (tabla[index] != 0) {
            // lineal
            index = (index + 1) % tamanio;
        }

        tabla[index] = key;
    }

    public void InsertarCuadraticaDPrueba(int key) {
        int index = hash(key);
        int i = 1;

        while (tabla[index] != 0) {
            // cuadrática
            index = (index + i * i) % tamanio;
            i++;
            if (index < 0) {
                index += tamanio; // índice no negativo
            }
        }

        tabla[index] = key;
    }

    public void displaytabla() {
        System.out.println("Tabla Hash:");
        for (int i = 0; i < tamanio; i++) {
            System.out.println("Índice " + i + ": " + tabla[i]);
        }
    }

    private int hash(int key) {
        return key % tamanio;
    }

    public static void main(String[] args) {
        HashearTabla linearDPruebatabla = new HashearTabla(10);
        HashearTabla quadraticDPruebatabla = new HashearTabla(10);

        int[] keys = { 25, 35, 45, 55, 65, 75, 85, 95 };

        for (int key : keys) {
            linearDPruebatabla.InsertarLinearDPrueba(key);
            quadraticDPruebatabla.InsertarCuadraticaDPrueba(key);
        }

        System.out.println("Sondeo Lineal:");
        linearDPruebatabla.displaytabla();

        System.out.println("\nSondeo Cuadrático:");
        quadraticDPruebatabla.displaytabla();
    }
}
