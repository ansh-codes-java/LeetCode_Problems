
class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {

        int n = parent.length;

        // Step 1: Build original tree
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            adj.get(parent[i]).add(i);
        }

        // Step 2: Find new parent using original tree
        int[] newParent = new int[n];
        int[] last = new int[26];
        int[] old = new int[n];

        Arrays.fill(last, -1);
        newParent[0] = -1;

        Stack<int[]> st = new Stack<>();
        st.push(new int[]{0, 1});

        while (!st.isEmpty()) {

            int[] curr = st.pop();

            int node = curr[0];
            int state = curr[1];

            int ch = s.charAt(node) - 'a';

            if (state == 1) {

                // Save previous ancestor
                old[node] = last[ch];

                // Determine new parent
                if (last[ch] != -1) {
                    newParent[node] = last[ch];
                } else {
                    newParent[node] = parent[node];
                }

                // Update closest ancestor
                last[ch] = node;

                // Exit event
                st.push(new int[]{node, 0});

                // Visit original children
                List<Integer> children = adj.get(node);

                for (int i = children.size() - 1; i >= 0; i--) {
                    st.push(new int[]{children.get(i), 1});
                }

            } else {

                // Restore previous ancestor
                last[ch] = old[node];
            }
        }

        // Step 3: Build modified tree
        List<List<Integer>> tree = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            tree.get(newParent[i]).add(i);
        }

        // Step 4: Calculate subtree sizes
        int[] size = new int[n];
        Arrays.fill(size, 1);

        List<Integer> order = new ArrayList<>();
        Stack<Integer> dfs = new Stack<>();

        dfs.push(0);

        while (!dfs.isEmpty()) {

            int node = dfs.pop();
            order.add(node);

            for (int child : tree.get(node)) {
                dfs.push(child);
            }
        }

        // Reverse order: children before parents
        for (int i = order.size() - 1; i > 0; i--) {

            int node = order.get(i);
            size[newParent[node]] += size[node];
        }

        return size;
    }
}
