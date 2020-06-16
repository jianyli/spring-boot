package com.li;

/**
 * @author lijianyou
 * @date 2020/6/15 20:07
 * @description
 */
public class Test {
    public static void main(String[] args) {
        String str = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(str));
    }
    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int max = 1;
        for(int i = 0; i < chars.length; i++) {
            int flag = 0;
            for(int j= i + 1; j < chars.length; j++) {
                if(j == chars.length) {
                    break;
                }
                if(chars[j] != chars[i]) {
                    flag ++;
                } else {
                    break;
                }
            }
            if(flag > max) {
                max = flag;
            }
        }
        return max;
    }
}
