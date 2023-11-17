// Apellido: Pereyra
// Nombre: Ivan Maximiliano
// Legajo: VINF011264

import java.util.PriorityQueue;
import java.util.HashMap;

class NodoHuffman implements Comparable<NodoHuffman> {
    char caracter;
    int frecuencia;
    NodoHuffman izquierdo, derecho;

    public NodoHuffman(char caracter, int frecuencia) {
        this.caracter = caracter;
        this.frecuencia = frecuencia;
    }

    public int compareTo(NodoHuffman otroNodo) {
        return this.frecuencia - otroNodo.frecuencia;
    }
}

public class HuffmanCoding {
    public static void main(String[] args) {
        String textoOriginal = "estamos bien";
        System.out.println("Texto original: " + textoOriginal);

        HashMap<Character, String> codigos = generarCodigosHuffman(textoOriginal);
        System.out.println("Codificación de Huffman: " + codificarConHuffman(textoOriginal, codigos));
    }

    public static HashMap<Character, String> generarCodigosHuffman(String texto) {
        
        HashMap<Character, Integer> frecuencias = new HashMap<>();
        for (char c : texto.toCharArray()) {
            frecuencias.put(c, frecuencias.getOrDefault(c, 0) + 1);
        }

        // nodos iniciales
        PriorityQueue<NodoHuffman> colaPrioridad = new PriorityQueue<>();
        for (char c : frecuencias.keySet()) {
            colaPrioridad.add(new NodoHuffman(c, frecuencias.get(c)));
        }

        // el árbol de Huffman
        while (colaPrioridad.size() > 1) {
            NodoHuffman izquierdo = colaPrioridad.poll();
            NodoHuffman derecho = colaPrioridad.poll();

            NodoHuffman nuevoNodo = new NodoHuffman('\0', izquierdo.frecuencia + derecho.frecuencia);
            nuevoNodo.izquierdo = izquierdo;
            nuevoNodo.derecho = derecho;

            colaPrioridad.add(nuevoNodo);
        }

        // códigos Huffman
        HashMap<Character, String> codigos = new HashMap<>();
        generarCodigosRecursivo(colaPrioridad.peek(), "", codigos);

        return codigos;
    }

    private static void generarCodigosRecursivo(NodoHuffman nodo, String codigo, HashMap<Character, String> codigos) {
        if (nodo != null) {
            if (nodo.izquierdo == null && nodo.derecho == null) {
                codigos.put(nodo.caracter, codigo);
            }

            generarCodigosRecursivo(nodo.izquierdo, codigo + "0", codigos);
            generarCodigosRecursivo(nodo.derecho, codigo + "1", codigos);
        }
    }

    public static String codificarConHuffman(String texto, HashMap<Character, String> codigos) {
        StringBuilder resultado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            resultado.append(codigos.get(c));
        }
        return resultado.toString();
    }
}
