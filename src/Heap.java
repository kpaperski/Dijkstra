import javafx.util.Pair;

import java.util.ArrayList;

public class Heap {
    public ArrayList<Pair<Integer,Integer>> vect = new ArrayList<>();

    public Heap() {

    }

    public boolean isEmpty() {
        return vect.size() == 0;
    }

    public int indexParent(int child) {
        return (child-1)/2;
    }

    public int indexKthChild(int parent, int k) {
        return 2*parent+k;
    }

    public void percolateUp() {
        int hole = vect.size()-1;
        Pair<Integer,Integer> temp = vect.get(hole);
        int tempKey = temp.getKey();
        for(; hole > 0 && tempKey < vect.get(indexParent(hole)).getKey(); hole = indexParent(hole))
            vect.set(hole, vect.get(indexParent(hole)));
        vect.set(hole, temp);
    }

    public void insert(int key, int value) {
        Pair<Integer, Integer> insertionElement = new Pair<>(key, value);
        vect.add(insertionElement);
        percolateUp();
    }

    public void insert(Pair<Integer, Integer> key_value) {
        vect.add(key_value);
        percolateUp();
    }

    public Pair<Integer, Integer> peek() {
        if (!isEmpty()) {
            return vect.get(0);
        }
        else {
            System.out.println("Heap is empty!");
            return new Pair<>(0, 0);
        }
    }

    public int smallestChild(int hole) {
        int fstChildIndex = indexKthChild(hole, 1);
        int sndChildIndex = indexKthChild(hole, 2);
        if (sndChildIndex >= vect.size())
            return fstChildIndex;
        if (vect.get(fstChildIndex).getKey() < vect.get(sndChildIndex).getKey())
            return fstChildIndex;
        else
            return sndChildIndex;
    }

    public void percolateDown()
    {
        int hole = 0;
        Pair<Integer, Integer> tmp =vect.get(hole);
        int tempKey = tmp.getKey();

        int child;

        for ( ; indexKthChild(hole, 1) < vect.size(); hole = child)
        {
            child = smallestChild(hole);
            if (vect.get(child).getKey() < tempKey)
                vect.set(hole, vect.get(child));
            else
                break;
        }
        vect.set(hole, tmp);
    }

    public Pair<Integer, Integer> pop() {
        if (!isEmpty())
        {
            Pair<Integer, Integer> deleteElement = vect.get(0);
            vect.set(0, vect.get(vect.size()-1));
            vect.remove(vect.size()-1);
            if(!isEmpty())
                percolateDown();
            return deleteElement;
        }
        else {
            System.out.println("Heap is empty!");
            return new Pair<>(0, 0);
        }
    }

    public void printHeap() {
        System.out.println("Heap = ");
        for(int i = 0; i < vect.size(); i++) {
            System.out.print(vect.get(i).getValue());
            System.out.println(" ");
        }
        System.out.println();
    }

    String cp = "   ";
    public void print(String sn, int parentIndex) {
        String s;
        if(parentIndex < vect.size())
        {
            Pair<Integer, Integer> temp = vect.get(parentIndex);
            System.out.print(sn);
            System.out.println(temp.getKey());

            s = sn + cp;

            print(s, indexKthChild(parentIndex,1));

            print(s, indexKthChild(parentIndex,2));

        }
    }
}
