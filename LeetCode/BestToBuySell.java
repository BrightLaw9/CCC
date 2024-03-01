package LeetCode; 

class BestToBuySell {
    public static int maxProfit(int[] prices) {
        int profit = 0; 

        int buy = prices[0];

        for (int sell : prices) { 
            if (sell > buy) profit = Math.max(profit, sell - buy); 
            else buy = sell; 
        }
        return profit;
    }

    public static void main(String[] args) { 
        System.out.println(maxProfit(new int[] {7, 1, 3, 4, 6, 2})); 
    }
}