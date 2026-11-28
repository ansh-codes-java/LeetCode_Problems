class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int maxFactor = 0;
        long minPrice = Long.MAX_VALUE;

        // Pass 1: find maxFactor and global minPrice
        for (int i = 0; i < n; i++) {
            int f = items[i][0];
            long p = items[i][1];
            if (f > maxFactor) maxFactor = f;
            if (p < minPrice) minPrice = p;
        }

        // Count occurrences of each factor
        int[] freq = new int[maxFactor + 1];
        for (int i = 0; i < n; i++) {
            freq[items[i][0]]++;
        }

        // multiplesAbove[f] = number of items with factor strictly > f and divisible by f
        int[] multiplesAbove = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            int countF = freq[f];
            if (countF == 0) continue;
            int sum = 0;
            for (int mult = f << 1; mult <= maxFactor; mult += f) {
                sum += freq[mult];
            }
            multiplesAbove[f] = sum;
        }

        // Only items with price < 2 * minPrice and gain > 0 can ever provide
        // a 2-item copy cheaper than buying two single copies of minPrice.
        long priceLimit = (minPrice > Long.MAX_VALUE / 2) ? Long.MAX_VALUE : (minPrice << 1);
        long[] candPrice = new long[n];
        long[] candGain = new long[n];
        int candCount = 0;

        for (int i = 0; i < n; i++) {
            long p = items[i][1];
            if (p >= priceLimit) continue;
            int f = items[i][0];
            long gain = (long) (freq[f] - 1) + multiplesAbove[f];
            if (gain > 0) {
                candPrice[candCount] = p;
                candGain[candCount] = gain;
                candCount++;
            }
        }

        // Sort candidate indices by price ascending without creating boxed objects
        Integer[] order = new Integer[candCount];
        for (int i = 0; i < candCount; i++) {
            order[i] = i;
        }
        Arrays.sort(order, (a, b) -> Long.compare(candPrice[a], candPrice[b]));

        long remBudget = budget;
        long totalItems = 0;
        long lastTakenPrice = -1;

        for (int idx = 0; idx < candCount; idx++) {
            int i = order[idx];
            long p = candPrice[i];
            if (remBudget < p) break;

            long take = Math.min(candGain[i], remBudget / p);
            if (take > 0) {
                totalItems += take << 1;
                remBudget -= take * p;
                lastTakenPrice = p;
            }
        }

        // Option A: Keep all 2-item copies and buy single copies of minPrice with leftover budget
        long best = totalItems + (remBudget / minPrice);

        // Option B: Refund one 2-item copy if that unlocks >= 3 single copies of minPrice
        if (lastTakenPrice != -1) {
            long refundedBudget = remBudget + lastTakenPrice;
            long candidate = (totalItems - 2L) + (refundedBudget / minPrice);
            if (candidate > best) {
                best = candidate;
            }
        }

        return (int) best;
    }
}