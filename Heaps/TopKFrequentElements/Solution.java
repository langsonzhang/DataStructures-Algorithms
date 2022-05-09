// Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.



class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // Sort in nlogn (bottleneck. alternatively dont sort)
        Arrays.sort(nums);
        
        // track top k elements
        PriorityQueue<Integer[]> pq = new PriorityQueue<Integer[]>(k, (a, b) -> b[0]-a[0]);
        // count appearences of elements
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count++;
            if ( i == nums.length-1 || nums[i+1] != nums[i]) {
                if (pq.size() == k && pq.peek()[0] < count) 
                    pq.poll();
                pq.add(new Integer[]{count, nums[i]});
                count = 0;
            }
        }
        // make return array
        int[] ret = new int[k];
        for (int i = 0; i < k; i++) {
            ret[i] = pq.poll()[1];
        }
            
        return ret;
    }
}