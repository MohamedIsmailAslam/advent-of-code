package com.github.mia.y2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import com.github.mia.Solvable;

public class SolverDay08 implements Solvable<Long> {

    private static final int MAX_CONNECTIONS = 1000;
    @Override
    public Long solvePartI(List<String> input) {
        int n = input.size();
        List<Coordinates> coordinates = new ArrayList<>(n);
        for (String s : input) {
            String[] parts = s.split(",");
            coordinates.add(new Coordinates(Integer.parseInt(parts[0]),
                    Integer.parseInt(parts[1]),
                    Integer.parseInt(parts[2]))
            );
        }

        List<Edge> edges = new ArrayList<>();
        for(int i=0; i<n; i++){
            Coordinates coordA = coordinates.get(i);
            for(int j=i+1; j<n; j++){
                Coordinates coordB = coordinates.get(j);
                long dx = coordA.x - coordB.x;
                long dy = coordA.y - coordB.y;
                long dz = coordA.z - coordB.z;
                double euclidean = Math.sqrt(dx*dx + dy*dy + dz*dz);
                edges.add(new Edge(i, j, euclidean));
            }
        }

        edges.sort(Comparator.comparingDouble(Edge::dist));

        UnionFind unionFind = new UnionFind(n, coordinates);

        int limit = Math.min(edges.size(), MAX_CONNECTIONS);
        for(int i=0; i<limit; i++){
            unionFind.union(edges.get(i).u, edges.get(i).v);
        }

        Arrays.sort(unionFind.size);

        return (long) unionFind.size[n-1] * unionFind.size[n-2] * unionFind.size[n-3];
    }


    @Override
    public Long solvePartII(List<String> input) {
        throw new UnsupportedOperationException("yet to be implemented");
    }

    class UnionFind {
        int[] parent;
        int[] size;
        //MinHeap heap; //required for part-II
        List<Coordinates> coordinates;

        UnionFind(int n, List<Coordinates> coordinates) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
                //heap = new MinHeap(2); //top 2
            }
            this.coordinates = coordinates;
        }

        int find(int x){
            if(parent[x] != x){
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        boolean union(int a, int b){
            int rootA = find(a);
            int rootB = find(b);

            if(rootA == rootB){ return false; }

            if(size[rootA] <= size[rootB]){
                parent[rootA] = rootB;
                size[rootB] += size[rootA];
                //heap.add(coordinates.get(rootB), size[rootB]);
            }
            else{
                parent[rootB] = rootA;
                size[rootA] += size[rootB];
                //heap.add(coordinates.get(rootA), size[rootA]);
            }
            return true;
        }

       /* boolean checkAllGrouped() {
            return heap.checkSumEqualsTo(parent.length);
        }*/

    }

    /*class MinHeap {
        int k;
        PriorityQueue<TopPair> pq;

        MinHeap(int k) {
            this.k = k;
            pq = new PriorityQueue<>(k, Comparator.comparingInt(TopPair::size));
        }

        void add(TopPair topPair) {
            pq.add(topPair);
            while(pq.size() > k){
                pq.poll();
            }
        }

        boolean checkSumEqualsTo(int target){
            int sum = pq.stream().mapToInt(TopPair::size).sum();
            return sum == target;
        }

        long getMultipliedValue(){
            return pq.stream()
                     .mapToInt(pair ->
                     {
                         System.out.println(pair);
                         return pair.coordinates().x();
                     })
                     .reduce(1, (a, b) -> a * b);
        }

    }*/

    record Coordinates(int x, int y, int z) {}
    record Edge(int u, int v, double dist) {}
    record TopPair(Coordinates coordinates, int size) {}
}
