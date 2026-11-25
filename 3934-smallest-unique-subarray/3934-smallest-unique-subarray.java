class Solution {

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        if (n == 0) return 0;

        // Coordinate compression
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int[] s = new int[n + 1];

        for (int i = 0; i < n; i++) {
            s[i] = Arrays.binarySearch(sorted, nums[i]) + 1;
        }

        // Sentinel
        s[n] = 0;

        // Build suffix array
        int[] p = buildSuffixArray(s);

        int[] sa = new int[n];

        for (int i = 0; i < n; i++) {
            sa[i] = p[i + 1];
        }

        // Build rank array
        int[] rank = new int[n];

        for (int i = 0; i < n; i++) {
            rank[sa[i]] = i;
        }

        // Kasai's LCP algorithm
        int[] lcp = new int[n];
        int k = 0;

        for (int i = 0; i < n; i++) {
            int r = rank[i];

            if (r == n - 1) {
                k = 0;
                continue;
            }

            int j = sa[r + 1];

            while (i + k < n && j + k < n &&
                   nums[i + k] == nums[j + k]) {
                k++;
            }

            lcp[r] = k;

            if (k > 0) k--;
        }

        // Find minimum unique subarray length
        int ans = n;

        for (int i = 0; i < n; i++) {
            int r = rank[i];

            int left = (r > 0) ? lcp[r - 1] : 0;
            int right = (r < n - 1) ? lcp[r] : 0;

            int maxLCP = Math.max(left, right);

            if (maxLCP < n - i) {
                ans = Math.min(ans, maxLCP + 1);
            }
        }

        return ans;
    }

    // Suffix Array using doubling algorithm
    private int[] buildSuffixArray(int[] s) {
        int n = s.length;

        int[] p = new int[n];
        int[] c = new int[n];

        int maxVal = 0;

        for (int x : s) {
            maxVal = Math.max(maxVal, x);
        }

        int[] cnt = new int[Math.max(n, maxVal + 1)];

        for (int x : s) cnt[x]++;

        for (int i = 1; i < cnt.length; i++) {
            cnt[i] += cnt[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            p[--cnt[s[i]]] = i;
        }

        int classes = 1;
        c[p[0]] = 0;

        for (int i = 1; i < n; i++) {
            if (s[p[i]] != s[p[i - 1]]) {
                classes++;
            }
            c[p[i]] = classes - 1;
        }

        int[] pn = new int[n];
        int[] cn = new int[n];

        for (int len = 1; len < n; len *= 2) {

            for (int i = 0; i < n; i++) {
                pn[i] = p[i] - len;

                if (pn[i] < 0) {
                    pn[i] += n;
                }
            }

            Arrays.fill(cnt, 0);

            for (int i = 0; i < n; i++) {
                cnt[c[pn[i]]]++;
            }

            for (int i = 1; i < classes; i++) {
                cnt[i] += cnt[i - 1];
            }

            for (int i = n - 1; i >= 0; i--) {
                int x = pn[i];
                p[--cnt[c[x]]] = x;
            }

            cn[p[0]] = 0;
            int newClasses = 1;

            for (int i = 1; i < n; i++) {
                int cur = p[i];
                int prev = p[i - 1];

                int midCur = (cur + len) % n;
                int midPrev = (prev + len) % n;

                if (c[cur] != c[prev] ||
                    c[midCur] != c[midPrev]) {
                    newClasses++;
                }

                cn[cur] = newClasses - 1;
            }

            int[] temp = c;
            c = cn;
            cn = temp;

            classes = newClasses;

            if (classes == n) break;

            if (len > n / 2) break;
        }

        return p;
    }
}
