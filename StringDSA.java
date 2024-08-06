import java.util.*;

public class StringDSA {
    static boolean palindromeString(String str) {
        int l=0;
        int r=str.length()-1;

        while (l<r) {
            if (str.charAt(l) != str.charAt(r)) {
                return false;
            }
            l++; r--;
        }

        return true;
    }

    // [a,b,c,d] [a,d]
    static boolean substringSeqStr(String str, String seq) {
        int i=0;
        int j=0;

        while(i<str.length() && j<seq.length()) {
            if(str.charAt(i) == seq.charAt(j)) {
                j++;
            }

            i++;
        }

        return j == seq.length();
    }

    static boolean anagramStr(String str1, String str2) {
        if(str1.length() != str2.length()) {
            return false;
        }

        int[] arr = new int[256];
        for(int i=0;i<str1.length();i++) {
            arr[str1.charAt(i)]++;
            arr[str2.charAt(i)]--;
        }

        for(int i=0;i<256;i++) {
            if(arr[i] != 0) {
                return false;
            }
        }

        return true;
    }


    // [a,b,c,b,a,d] -> 
    static int leftmostRepeatingChar(String str) {
        int[] place = new int[256];
        
        for(int i=0;i<256;i++) {
            place[i] = -1;
        }

        for(int i=0;i<str.length();i++) {
            place[str.charAt(i)]++;
        }

        for(int i=0;i<256;i++) {
            if(place[i] == 1) {
                 String ch = Character.toString((char) i);
                 return str.indexOf(ch);
            }
        }
        
        return -1;
    }

    static int leftmostNonRepeatingChar(String str) {
        int[] place = new int[256];
        
        for(int i=0;i<256;i++) {
            place[i] = 1;
        }

        for(int i=0;i<str.length();i++) {
            place[str.charAt(i)]--;
        }

        System.out.println(Arrays.toString(place));
        for(int i=0;i<256;i++) {
            if(place[i] == 0) {
                System.out.println((char) i);
                return str.indexOf((char) i);
            }
        }
        
        return -1;
    }

    static String reverseOrderOfWords(String str) {
        String[] strArr = str.split(" ");
        String res = "";
        for(String i: strArr) {
            res = i+ " " +res;
        }
        return res;
    }

    // Pattern Occuring Problem 
    /*
     * str = GEEKSFORGEEKS (n)
     * pat = EKS (m)
     * o/p = GE[EKS]FORGE[EKS] -> 2, 10
     */

    /*
     * Naive O((n-m +1) * m)
     */

     static List<Integer> patternSearchNaive(String str, String pat) {
        List<Integer> res = new ArrayList<>();
        int n = str.length();
        int m = pat.length();

        int i = 0;
        int j = m;

        while(i<j && j<=n) {
            String sbstr = str.substring(i,j);
            if(pat.equals(sbstr)) {
                res.add(i);
            }
            i++;j++;
        }

        return res;
     }

     /*
      * Rabin Karp Algorithm O((n-m +1) * m) but better than naive on avg
      */

    //   static List<Integer> patternSearchRK(String str, String pat) {
    //     List<Integer> res = new ArrayList<>();
    //     int n = str.length();
    //     int m = pat.length();
        

    //     for(int i=0;i<n-m;i++) {
    //         int j = 0;

    //         while(j<m) {
    //             int hash = 
    //         }
    //     }

    //     return res;
    //   }


      /*
       * KMP - O(n)
       * but most complicated
       * Pre Process
       */


    static boolean checkForRotation(String s1, String s2) {
        
        if(s1.length() == s2.length() ) {
            return false;
        }

        if(s1.equals(s2)) {
            return true;
        }
        return (s2+s2).contains(s1);
    }

    static boolean anagramStrSearch(String s1, String pat) {

        char[] ch = new char[256];
        for(int i=0;i<s1.length();i++) {
            ch[s1.charAt(i)]++;
            ch[pat.charAt(i)]--;
        }

        for(int i=0;i<pat.length();i++) {
            if(ch[pat.charAt(i)]!=0) {
                return false;
            }
        }
        return true;
    }

    static int lexographicRank(String str) {
        // abc -> a, b, c (1, 2, 3)     abc, acb, bac, bca, cab, cba ( n! ) a -> 1, b -> 2, c -> 3 
        // bac -> (2, 1, 3) bac -> 2 + lexo(ac) -> 2! + 1! + 0!
        // dcba -> 4*3*2*1
        // string -> ginrst-> s(5! + 5! + 5! + 5!) + st( 4! + 4! + 4!) + str(3!) + stri(2!) + strin(1) => 480 + 81 => 561
        
        /*  before s "ginr" -> 5! * 4  = 480
            with g ( allowed words -> inrst ) + with i ( allowed words -> gnrst )+ with n ( allowed words -> girst ) + with r ( allowed words -> ginst )
         *  we reach s before st  "ginr" -> 4! * 4 = 96
         * s + ( with sg+ with si + with sn + with sr)  ( allowed words -> 4 each)
         *  we reach st before str "gin" -> 3! * 3 = 18 
         * st + ( with stg, sti stn) (3 each)
         *  str , before stri "gn" 2! * 1 = 2
         * str ( strg )
         *  stri before strin "g" 1! * 1 = 1 
         *  stri (strig)
         *  string = 1
         */
        int res = 0;
        int n = str.length();
        char[] ch = str.toCharArray();
        Arrays.sort(ch);
        String alpha = new String(ch);
        for(int i=0;i<str.length();i++) {
            int before = alpha.indexOf(str.charAt(i));
            alpha = alpha.substring(0, before) + alpha.substring(before+1);
            res += before * (CommonUtils.factorial(n-i-1));
        }
        return res+1;
    }

    static int longestDisnSubstring(String s) {
        // abcdabc

        int maxLen = 0;
        char[] ch = new char[256];
        // for(int i=0;i<s.length();i++) {
        //     ch[s.charAt(i)]++;
        // }

        for(int i=0;i<s.length();i++){
            if(ch[s.charAt(i)] != 1) {
                maxLen++;
                ch
            }
        }
    }

}

