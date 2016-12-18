package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by takirala on 27/10/16.
 */
public class PrimMST {
    public class Node implements Comparable<Node> {
        int vertice, key;

        Node(int vertice, int key) {
            this.vertice = vertice;
            this.key = key;
        }

        @Override
        public int compareTo(Node o) {
            return this.key - o.key;
        }
    }

    public class AdjList {
        ArrayList<Node> nodes;
    }

    public class Graph {
        int V;
        AdjList[] adjLists;
        //for 3 vertices, something like
        //[]->[][][]
        //[]->[][]
        //[]->[][][][]
    }


    public Graph createGraph(int v) {
        Graph graph = new Graph();
        graph.V = v;
        graph.adjLists = new AdjList[v];
        for (int i = 0; i < v; i++) {
            AdjList adjList = new AdjList();
            adjList.nodes = new ArrayList<Node>(); //initialize its node list too
            graph.adjLists[i] = adjList;
        }
        return graph;
    }

    public void addEdge(Graph graph, int src, int dest, int key) {
        Node srcNode = new Node(src, key);
        Node destNode = new Node(dest, key);
        graph.adjLists[src].nodes.add(destNode);
        graph.adjLists[dest].nodes.add(srcNode);
    }

    public void printGraph(Graph graph) {
        for (int i = 0; i < graph.V; i++) {
            System.out.print(i + " ->");
            for (Node node : graph.adjLists[i].nodes) {
                System.out.print(" " + node.vertice);
            }
            System.out.println();
        }
    }

    void getPrimMST(Graph graph) {
        Node keys[] = new Node[graph.V];
        int parent[] = new int[graph.V];

        boolean mstSet[] = new boolean[graph.V];

        for (int i = 0; i < graph.V; i++) {
            keys[i] = new Node(i, Integer.MAX_VALUE);
            parent[i] = -1;
            mstSet[i] = false;
        }
        keys[0].key = 0;
        Queue<Node> pQueue = new PriorityQueue<>();
        pQueue.addAll(Arrays.asList(keys));

        while (pQueue.size() > 1) {
            Node u = pQueue.remove();
            mstSet[u.vertice] = true;

            for(Node node: graph.adjLists[u.vertice].nodes) {
                int v = node.vertice;
                if(!mstSet[v] && node.key < keys[v].key) {
                    pQueue.remove(keys[v]);

                    keys[v].key = node.key;
                    parent[v] = u.vertice;


                }
            }


        }


    }
}