
import java.util.*;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {

        long ans = 0;
        long cost = 0;

        int n = nums.length;
        int j = n - 1;

        Deque<int[]> dq = new ArrayDeque<>();

        for (int i = n - 1; i >= 0; i--) {

            int num = nums[i];
            int count = 1;

            // Merge smaller values into the current value
            while (!dq.isEmpty() && dq.peekLast()[0] < num) {

                int[] last = dq.pollLast();

                int value = last[0];
                int freq = last[1];

                count += freq;

                cost += (long) (num - value) * freq;
            }

            dq.offerLast(new int[]{num, count});

            // Shrink window if operations exceed k
            while (cost > k) {

                int[] first = dq.pollFirst();

                int value = first[0];
                int freq = first[1];

                cost -= (long) (value - nums[j]);
                j--;

                if (freq > 1) {
                    dq.offerFirst(new int[]{value, freq - 1});
                }
            }

            // Count valid subarrays
            ans += j - i + 1;
        }

        return ans;
    }
}
