// Cascading power set construction
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        ret.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) {
            int size = ret.size();
            for (int j = 0; j < size; j++) {
                List extension = new ArrayList<>(ret.get(j));
                extension.add(nums[i]);
                ret.add(extension);
            }
        }
        return ret;
    }
}