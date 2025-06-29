class Solution {
    public int countComponents(int n, int[][] edges) {
        int res = 0;
        // convert edges to adj list
        List<List<Integer>> adjList = new ArrayList();
        
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
        
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int u = edge[0];
            int v = edge[1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            vis[i] = false;
        }

        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                dfs(i, adjList, vis);
                res++;
            }
        }

        return res;
    }

    public void dfs(int i, List<List<Integer>> adjList, boolean[] vis) {
        vis[i] = true;
        List<Integer> neighbours = adjList.get(i);
        for(int neighbour : neighbours) {
            if (!vis[neighbour]) {
                dfs(neighbour, adjList, vis);
            }
        }
    }
}
