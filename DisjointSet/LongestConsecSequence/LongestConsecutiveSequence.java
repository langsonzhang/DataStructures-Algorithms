import java.util.*;

class Solution {

    // Usage: java Solution [optional: num1 ... numN]
    public static void main(String[] args) {
        int[] input;
        if (args.length > 1) {
            input = new int[args.length-1];
            for (int i = 1; i < args.length; i++) {
                input[i-1] = Integer.parseInt(args[i]);
            }
        }
        else {
            input = new int[1000000];
            Random rand = new Random();
            for (int i = 0; i < input.length; i++) {
                input[i] = rand.nextInt(input.length*5) - input.length;
            }
        }


        Solution sol = new Solution();
        Solution2 sol2 = new Solution2();

        double startTime = System.currentTimeMillis();
        System.out.println(sol.longestConsecutive(input));
        double endTime = System.currentTimeMillis();
        System.out.println("Time to run sol w/ DJS: "+(endTime-startTime) + "ms");

        startTime = System.currentTimeMillis();
        System.out.println(sol2.longestConsecutive(input));
        endTime = System.currentTimeMillis();
        System.out.println("Time to run sol w/ HashSet: "+(endTime-startTime) + "ms");
    }
    
    HashMap<Integer, Integer> parent;
    HashMap<Integer, Integer> rank;
    HashMap<Integer, Integer> size;
    
    int longest;
    
    int depth = 0;
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        parent = new HashMap<Integer, Integer>();
        rank = new HashMap<Integer, Integer>();
        size = new HashMap<Integer, Integer>();
        longest = 1;

        for (int i = 0; i < nums.length; i++) {
            // System.out.println(nums[i] + ":");
            // double time = 
            makeSet(nums[i]);
            union(nums[i], nums[i]+1);
            union(nums[i], nums[i]-1);
        }
        return longest;
        
    }
    
    public void makeSet(int n) {
        if (parent.containsKey(n)) {
            return;
        }
        parent.put(n, n);
        rank.put(n, 0);
        size.put(n, 1);
    }
    
    public Integer findSet(int n) {
        depth++;
        if (parent.containsKey(n)) {
            int par = parent.get(n);
            if (par == n) { // found rep
                return n;
            }
            int rep = findSet(par);
            parent.put(n, rep);
            return rep;
        }
        return null;
    }
    
    public void union(Integer a, Integer b) {
        // depth = 0;
        a = findSet(a);
        // System.out.println(depth);
        // depth = 0;
        b = findSet(b);
        // System.out.println(depth);
        
        if (a == null || b == null) {
            return;
        }
        if (a.equals(b)) {
        // if (a == b) {
            return;
        }
        
        int ar = rank.get(a);
        int br = rank.get(b);
        
        int as = size.get(a);
        int bs = size.get(b);
        
        if ( as+bs > longest) {
            longest = as + bs;
            // System.out.println(a + " union " +b + "--" + (as+bs));
        }
        
        
        if (ar > br) {
            parent.put(b, a);
            size.put(a, as+bs);
        }
        else if (br > ar) {
            parent.put(a, b);
            size.put(b, as+bs);
        }
        else {
            parent.put(a, b);
            rank.put(b, rank.get(b)+1);
            size.put(b, as+bs);
        }
    
        
    }
}

class Solution2 {
    public int longestConsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num-1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum+1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}