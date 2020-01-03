import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Iterator;


public class Graph {
    private static final int INF = Integer.MAX_VALUE;
    private int V;
    private ArrayList<ArrayList<Pair<Integer, Integer>>> adj;

    public Graph(int V) {
        this.V = V;
        this.adj = new ArrayList<>(V);
        for(int i=0; i<V; i++) {
            ArrayList<Pair<Integer, Integer>> temp = new ArrayList<>();
            this.adj.add(temp);
        }
    }

    public void addEdge(int u, int v, int w) {
        Pair<Integer, Integer> elem = new Pair<>(v, w);
        ArrayList<Pair<Integer, Integer>> temp = this.adj.get(u);
        temp.add(elem);
        this.adj.set(u, temp);
    }

    public ArrayList<Integer> Dijkstra(int src) {
        Heap heap = new Heap();

        ArrayList<Integer> dist = new ArrayList<>();
        ArrayList<Integer> poprz = new ArrayList<>();
        for(int i=0; i<this.V; i++) {
            dist.add(INF);
            poprz.add(-1);
        }

        heap.insert(0, src);
        dist.set(src, 0);

        while(!heap.isEmpty()) {
            int u = heap.pop().getValue();
            Iterator<Pair<Integer, Integer>> iterator = this.adj.get(u).iterator();
            while (iterator.hasNext()) {
                Pair<Integer, Integer> elem = iterator.next();
                int v = elem.getKey();
                int weight = elem.getValue();

                if (dist.get(v) > dist.get(u) + weight) {
                    dist.set(v, dist.get(u) + weight);
                    poprz.set(v, u);
                    heap.insert(dist.get(v), v);
                }
            }
        }

        System.out.println("Vertex   Distance from Source");
        for (int i = 0; i < V; ++i)
            System.out.println(i + "\t \t \t" + dist.get(i));


//        ArrayList<Integer> stos = new ArrayList<>();
//        for (int j = finish; j > -1; j = poprz.get(j)) {
//            stos.add(j);
//        }
//
//        System.out.println("Droga do końca: ");
//        for (int i = stos.size()-1; i >= 0; i--) {
//            System.out.println(stos.get(i));
//        }
//
//        System.out.println("Koszt: ");
//        System.out.println(dist.get(finish));
        return poprz;
    }
}
