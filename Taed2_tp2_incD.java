// Apellido: Pereyra
// Nombre: Ivan Maximiliano
// Legajo: VINF011264

import java.util.Scanner;

public class Hashtabla {
    private int[] tabla;
    private int tamanio;

    public Hashtabla(int tamanio) {
        this.tamanio = tamanio;
        this.tabla = new int[tamanio];
    }

    public void insert(int llave, String probingMethod) {
        int indice = hash(llave);
        int i = 1;

        while (tabla[indice] != 0) {
            // jandler de colisiones
            if (probingMethod.equals("linear")) {
                indice = (indice + 1) % tamanio; // Sonda lineal
            } else if (probingMethod.equals("quadratic")) {
                indice = (indice + i * i) % tamanio; // cuadrática
                i++;
            }

            if (indice < 0) {
                indice += tamanio; //índice  no negativo
            }
        }

        tabla[indice] = llave;
    }

    public boolean search(int llave) {
        int indice = hash(llave);

        while (tabla[indice] != 0) {
            if (tabla[indice] == llave) {
                return true; //encontrado
            }

            indice = (indice + 1) % tamanio; // sonda lineal
        }

        return false; // 0 results encontrados
    }

    public void delete(int llave) {
        int indice = hash(llave);

        while (tabla[indice] != 0) {
            if (tabla[indice] == llave) {
                tabla[indice] = 0; // elimina valor
                System.out.println("Valor " + llave + " eliminado.");
                return;
            }

            indice = (indice + 1) % tamanio; //lineal para borrar
        }

        System.out.println("Valor " + llave + " no encontrado.");
    }

    public void MostrarTabla() {
        System.out.println("Tabla Hash:");
        for (int i = 0; i < tamanio; i++) {
            System.out.println("Índice " + i + ": " + tabla[i]);
        }
    }

    private int hash(int llave) {
        return llave % tamanio;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el tamanio de la tabla hash: ");
        int tablatamanio = scanner.nextInt();

        Hashtabla hashtabla = new Hashtabla(tablatamanio);

        while (true) {
            System.out.println("\nMenú:");
            System.out.println("1. Insertar valor");
            System.out.println("2. Buscar valor");
            System.out.println("3. Eliminar valor");
            System.out.println("4. Mostrar tabla");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            int elegido = scanner.nextInt();

            switch (elegido) {
                case 1:
                    System.out.print("Ingrese el valor a insertar: ");
                    int ValorIngresado = scanner.nextInt();
                    System.out.print("Seleccione el método de manejo de colisiones (linear/quadratic): ");
                    String ValorLinQuad = scanner.next();
                    hashtabla.insert(ValorIngresado, ValorLinQuad);
                    break;
                case 2:
                    System.out.print("Ingrese el valor a buscar: ");
                    int ValorABuscar = scanner.nextInt();
                    System.out.println("El valor " + ValorABuscar + " " +
                            (hashtabla.search(ValorABuscar) ? "está presente." : "no está presente."));
                    break;
                case 3:
                    System.out.print("Ingrese el valor a eliminar: ");
                    int ValorABorrar = scanner.nextInt();
                    hashtabla.delete(ValorABorrar);
                    break;
                case 4:
                    hashtabla.MostrarTabla();
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
