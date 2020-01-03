import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
//        System.out.println("Hello World!");
//        Heap heap = new Heap();
//        heap.insert(10, 10);
//        heap.insert(20, 20);
//        heap.insert(15, 15);
//        heap.insert(17, 17);
//        heap.insert(5, 5);
//        heap.insert(23, 23);
//        heap.print("", 0);
        int V = 12;
        int[][] W_matrix = new int[V][V];
        File file = new File("graph.txt");
        Scanner inFile = new Scanner(file).useDelimiter(" ");

        int k = 0;
        while(inFile.hasNextLine()) {
            String line = inFile.nextLine();
            String[] items = line.split(" ", 0);
            for (int i = 0; i < items.length; i++)
                W_matrix[k][i] = Integer.parseInt(items[i]);
            k++;
        }

        Graph graph = new Graph(V);

        for(int i = 0; i < V; i++)
            for(int j = 0; j < V; j++)
                if(W_matrix[i][j] != 0) {
                    graph.addEdge(i, j, W_matrix[i][j]);
                }

        long t1 = System.nanoTime();
        graph.Dijkstra(5);
        long t2 = System.nanoTime();
        System.out.println("Czas: " + (t2 - t1) + " ns");
    }
}
