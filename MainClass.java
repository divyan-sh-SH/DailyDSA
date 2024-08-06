import java.util.*;

public class MainClass {
    public static void main(String[] args) {
        // int[] arr = new int[]{1, 2, 3, 4, 5};
        // String str = "string";
        // String pat = "ekk";

        // [["a","0549"],["b","0457"],["a","0532"],["a","0621"],["b","0540"]];
        List<List<String>> access_times = new ArrayList<>();


        List<String> inputs = new ArrayList<>();
        inputs.add("a");
        inputs.add("0549");
        access_times.add(inputs);

        inputs = new ArrayList<>();
        inputs.add("b");
        inputs.add("0457");
        access_times.add(inputs);

        inputs = new ArrayList<>();
        inputs.add("a");
        inputs.add("0532");
        access_times.add(inputs);

        inputs = new ArrayList<>();
        inputs.add("a");
        inputs.add("0621");
        access_times.add(inputs);

        inputs = new ArrayList<>();
        inputs.add("b");
        inputs.add("0540");
        access_times.add(inputs);


        int[] input = new int[]{1,1,1,0,1,0,1};
        int res = ComplexDSA.longestSubarray(input);
        System.out.println(res);
    }
}