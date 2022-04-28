// Sort input array, and DFS with backtracking

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> alphabet = new ArrayList<>();
        for (int n : nums)
            alphabet.add(n);
        permute(ret, new Stack(), alphabet);
        return new ArrayList(ret);
    }
    
    public void permute(List<List<Integer>> allPerms, Stack<Integer> currPerm, List<Integer> alphabet) {
        if (currPerm.size() == alphabet.size()) {
            allPerms.add(new ArrayList(currPerm));
        }
        else {
            for (int i = 0; i < alphabet.size(); i++) {
                Integer cter = alphabet.get(i);
                if (cter != null) {
                    if (i+1 < alphabet.size() && cter.equals(alphabet.get(i+1)))
                        continue;
                    alphabet.set(i, null);
                    currPerm.push(cter);
                    permute(allPerms, currPerm, alphabet);
                    currPerm.pop();
                    alphabet.set(i, cter);
                }
            }
        }
    }   
}