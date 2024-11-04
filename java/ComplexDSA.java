import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class ComplexDSA {
    // access_times 
    // [["a","0549"],["b","0457"],["a","0532"],["a","0621"],["b","0540"]]
    // find highest access means 
    // name with access time of 3 or more within 60 mins 
    static List<String> findHighAccessEmployees(List<List<String>> access_times) {
        int n = access_times.size();
        List<String> res = new ArrayList<>();
        Map<String, Queue<Integer>> empMap = new HashMap<>();

        for(int i=0;i<n;i++) {
            String name = access_times.get(i).get(0);
            int time = getTimeMinutes(access_times.get(i).get(1));
            if(!empMap.containsKey(name)) {
                Queue<Integer> timeList = new PriorityQueue<Integer>();
                timeList.add(time);
                empMap.put(name, timeList);
            } else {
                empMap.get(name).add(time);
            }
        }

        for(Map.Entry<String, Queue<Integer>> entry: empMap.entrySet()){
            if (entry.getValue().size() > 2) {
                List<Integer> values = List.copyOf(entry.getValue());
                Collections.sort(values); 
                for (int i=1;i<values.size()-1;i++) {
                    int prev = values.get(i-1);
                    int curr = values.get(i);
                    int next = values.get(i+1);

                    if (curr - prev < 60 && next - prev < 60) {
                        res.add(entry.getKey());
                    }
                }
            }
        }
        return res;
    }

    private static int getTimeMinutes(String time) {
        int min = Integer.parseInt(time.substring(2,4));
        int hour = Integer.parseInt(time.substring(0,2));
        return (hour*60) + min;
    }

    // [1,1,0,1,0,1, 1, 1, 1]
    static int longestSubarray(int[] nums) {
        int currCount = 0;
        int maxCount = 0;
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == 0) {
                int j = i+1;
                while (j<nums.length && nums[j]!=0) {
                    currCount++;
                    j++;
                }
                maxCount = Math.max(maxCount, currCount);
                currCount = 0;
            } else {
                currCount++;
            }

            i++;
        }
        

        return Math.max(maxCount, currCount);
    }

}
