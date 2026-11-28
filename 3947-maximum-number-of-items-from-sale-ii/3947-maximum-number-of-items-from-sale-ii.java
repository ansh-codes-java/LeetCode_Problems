class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int maxFactor = 0;
        long minPrice = Long.MAX_VALUE;

        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            minPrice = Math.min(minPrice, item[1]);
        }

        int[] freq = new int[maxFactor + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }

        // Count how many items have a factor strictly greater than f and divisible by f
        int[] multiplesAbove = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            if (freq[f] == 0) continue;
            int sum = 0;
            for (int mult = f * 2; mult <= maxFactor; mult += f) {
                sum += freq[mult];
            }
            multiplesAbove[f] = sum;
        }

        // Each item i can grant at most 1 free copy for each distinct j != i
        // where items[i][0] divides items[j][0].
        long[][] pairs = new long[n][2]; // [price, gain]
        for (int i = 0; i < n; i++) {
            int f = items[i][0];
            long price = items[i][1];
            long gain = (long) (freq[f] - 1) + multiplesAbove[f];
            pairs[i][0] = price;
            pairs[i][1] = gain;
        }

        // Sort items by price ascending
        Arrays.sort(pairs, Comparator.comparingLong(a -> a[0]));

        long remBudget = budget;
        long totalItems = 0;
        long lastTakenPrice = -1;

        for (int i = 0; i < n; i++) {
            long price = pairs[i][0];
            long gain = pairs[i][1];

            // A 2-item copy is only worthwhile if its price is strictly less than
            // buying two single copies of the cheapest item (2 * minPrice).
            if (price >= 2L * minPrice) break;
            if (gain == 0) continue;
            if (remBudget < price) break;

            long take = Math.min(gain, remBudget / price);
            if (take > 0) {
                totalItems += take * 2L;
                remBudget -= take * price;
                lastTakenPrice = price;
            }
        }

        // Option A: Keep all taken 2-item copies and spend leftover budget on minPrice
        long best = totalItems + (remBudget / minPrice);

        // Option B: Refund 1 copy of the most expensive 2-item purchase if doing so
        // allows buying at least 3 single copies of minPrice instead of 2 items
        if (lastTakenPrice != -1) {
            long refundedBudget = remBudget + lastTakenPrice;
            long candidate = (totalItems - 2L) + (refundedBudget / minPrice);
            best = Math.max(best, candidate);
        }

        return (int) best;
    }
}