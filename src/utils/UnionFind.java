package utils;

public class UnionFind {

    private final int[] parent;
    private final int[] rank;
    private int count;

    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];
        count = size;
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = i;
        }
    }

    public int find(int index) {
        if (parent[index] == index) {
            return index;
        } else {
            parent[index] = find(parent[index]);
            return parent[index];
        }
    }

    public void merge(int m, int n) {
        int x = find(m);
        int y = find(n);

        if (x == y) return;

        if (rank[x] < rank[y]) {
            parent[x] = y;
        } else if (rank[x] > rank[y]) {
            parent[y] = x;
        } else {
            parent[y] = x;
            rank[x]++;
        }

        count--;
    }

    public boolean isConnected(int m, int n) {
        return find(m) == find(n);
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parent.length; i++) {
            if (i != parent.length - 1) {
                sb.append(find(i)).append(" -> ");
            } else {
                sb.append(find(i));
            }
        }
        return sb.toString();
    }
}
