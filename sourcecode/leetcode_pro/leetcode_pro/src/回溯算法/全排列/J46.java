package 回溯算法.全排列;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/permutations/description/
 */

public class J46 {
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        // 记录作出的选择，记录在track中
        LinkedList<Integer> trace = new LinkedList<>();
        backtrace(nums, trace);
        return result;
    }

    private void backtrace(int[] nums, LinkedList<Integer> trace) {
        // 满足结束条件：nums 中的元素全都在 track 中出现
        if (trace.size() == nums.length) {
            result.add(new LinkedList<>(trace));
            return;
        }
        for (int i : nums) {
            if (trace.contains(i)) {
                // nums 中不存在于 track 的那些元素
                continue;
            }
            // 作出选择
            trace.add(i);
            // 进入下一层决策树
            backtrace(nums, trace);
            // 取消选择
            trace.removeLast();
        }
    }
}
