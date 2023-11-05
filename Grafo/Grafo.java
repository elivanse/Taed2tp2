// Apellido: Pereyra
// Nombre: Ivan Maximiliano
// Legajo: VINF011264

import java.util.*;

public class Grafo {

    private int numVertices;
    private ArrayList<ArrayList<Integer>> listaAdyacencia;

    public Grafo(int numVertices) {
        this.numVertices = numVertices;
        this.listaAdyacencia = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            listaAdyacencia.add(new ArrayList<>());
        }
    }

    public void agregarArista(int origen, int destino) {
        listaAdyacencia.get(origen).add(destino);
    }

    public ArrayList<Integer> caminoMinimoSinPesos(int origen, int destino) {
        Queue<Integer> cola = new LinkedList<>();
        boolean[] visitado = new boolean[numVertices];
        int[] padre = new int[numVertices];

        cola.add(origen);
        visitado[origen] = true;

        while (!cola.isEmpty()) {
            int actual = cola.poll();

            for (int vecino : listaAdyacencia.get(actual)) {
                if (!visitado[vecino]) {
                    cola.add(vecino);
                    visitado[vecino] = true;
                    padre[vecino] = actual;
                }
            }
        }

        return reconstruirCamino(origen, destino, padre);
    }

    private ArrayList<Integer> reconstruirCamino(int origen, int destino, int[] padre) {
        ArrayList<Integer> camino = new ArrayList<>();
        int actual = destino;

        while (actual != origen) {
            camino.add(0, actual);
            actual = padre[actual];
        }

        camino.add(0, origen);
        return camino;
    }

    public boolean esAciclico() {
        HashSet<Integer> visitados = new HashSet<>();
        HashSet<Integer> enProceso = new HashSet<>();

        for (int i = 0; i < numVertices; i++) {
            if (!visitados.contains(i)) {
                if (tieneCicloDesde(i, visitados, enProceso)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean tieneCicloDesde(int vertice, HashSet<Integer> visitados, HashSet<Integer> enProceso) {
        visitados.add(vertice);
        enProceso.add(vertice);
    
        for (int vecino : listaAdyacencia.get(vertice)) {
            if (!visitados.contains(vecino)) {
                if (tieneCicloDesde(vecino, visitados, enProceso)) {
                    return true;
                }
            } else if (enProceso.contains(vecino)) {
                return true;
            }
        }
        enProceso.remove(vertice);
        return false;
    }


    public void operacionesArbol() {
        if (!esAciclico()) {
            HashSet<Integer> visitados = new HashSet<>();
            for (int i = 0; i < numVertices; i++) {
                if (!visitados.contains(i)) {
                    int raiz = i;
                    System.out.println("Operaciones del TDA Árbol:");
                    System.out.println("Raíz del árbol: " + raiz);
                    System.out.println("Recorrido en profundidad del árbol:");
                    recorridoProfundidad(raiz, visitados);
                }
            }
        } else {
            System.out.println("El grafo contiene ciclos y no puede representarse como un árbol.");
        }
    }
  
    private int encontrarRaiz() {
        HashSet<Integer> padres = new HashSet<>();
        HashSet<Integer> visitados = new HashSet<>();
    
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                padres.add(i);
                visitados.addAll(listaAdyacencia.get(j));
            }
        }
    
        for (int i = 0; i < numVertices; i++) {
            if (!padres.contains(i) && visitados.contains(i)) {
                return i;
            }
        }
    
        // Esto no tendria que pasar 
        // si el grafo es un arbolito válido
        return -1;
    }
    private void recorridoProfundidad(int vertice, HashSet<Integer> visitados) {
        visitados.add(vertice);
        System.out.println(vertice);
    
        for (int vecino : listaAdyacencia.get(vertice)) {
            if (!visitados.contains(vecino)) {
                recorridoProfundidad(vecino, visitados);
            }
        }
    }
    
    
    


    public ArrayList<Integer> dijkstra(int origen, int destino) {
        int[] distancias = new int[numVertices];
        Arrays.fill(distancias, Integer.MAX_VALUE);
        distancias[origen] = 0;

        PriorityQueue<Nodo> colaPrioridad = new PriorityQueue<>();
        colaPrioridad.add(new Nodo(origen, 0));

        while (!colaPrioridad.isEmpty()) {
            Nodo actual = colaPrioridad.poll();

            for (int vecino : listaAdyacencia.get(actual.vertice)) {
                int nuevaDistancia = distancias[actual.vertice] + 1; // Puedes cambiar esto si tus aristas tienen pesos
                if (nuevaDistancia < distancias[vecino]) {
                    distancias[vecino] = nuevaDistancia;
                    colaPrioridad.add(new Nodo(vecino, nuevaDistancia));
                }
            }
        }

        return reconstruirCamino(origen, destino, distancias);
    }

    public static void main(String[] args) {
        Grafo grafo = new Grafo(13);

        grafo.agregarArista(0, 1);
        grafo.agregarArista(0, 2);
        grafo.agregarArista(0, 8);
        grafo.agregarArista(1, 5);
        grafo.agregarArista(1, 2);
        grafo.agregarArista(2, 5);
        grafo.agregarArista(2, 4);
        grafo.agregarArista(2, 0);
        grafo.agregarArista(3, 2);
        grafo.agregarArista(4, 5);
        grafo.agregarArista(4, 9);
        grafo.agregarArista(5, 10);
        grafo.agregarArista(5, 11);
        grafo.agregarArista(6, 3);
        grafo.agregarArista(6, 2);
        grafo.agregarArista(6, 0);
        grafo.agregarArista(7, 6);
        grafo.agregarArista(7, 0);
        grafo.agregarArista(8, 7);
        grafo.agregarArista(8, 12);
        grafo.agregarArista(9, 3);
        grafo.agregarArista(10, 9);
        grafo.agregarArista(10, 4);
        grafo.agregarArista(11, 1);
        grafo.agregarArista(12, 11);
        grafo.agregarArista(12, 1);

        int origen = 0;
        int destino = 11;

        ArrayList<Integer> caminoMinimo = grafo.caminoMinimoSinPesos(origen, destino);

        System.out.println("Camino mínimo sin pesos desde " + origen + " a " + destino + ": " + caminoMinimo);

        grafo.operacionesArbol();

        // Algo d Dijkstra
        ArrayList<Integer> caminoDijkstra = grafo.dijkstra(origen, destino);

        System.out.println("Camino más corto (Dijkstra) desde " + origen + " a " + destino + ": " + caminoDijkstra);
    }
}

class Nodo implements Comparable<Nodo> {
    int vertice;
    int distancia;

    public Nodo(int vertice, int distancia) {
        this.vertice = vertice;
        this.distancia = distancia;
    }

    @Override
    public int compareTo(Nodo otro) {
        return Integer.compare(this.distancia, otro.distancia);
    }
}
