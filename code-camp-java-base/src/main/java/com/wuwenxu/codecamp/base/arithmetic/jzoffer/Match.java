package com.wuwenxu.arithmetic.jzoffer;

import java.util.Arrays;

/**
 * 正则表达式匹配
 *
 * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，
 * 而'*'表示它前面的字符可以出现任意次（包含0次）。 在本题中，匹配是指字符串的所有字符匹配整个模式。
 * 例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 */
public class Match {

    public boolean match(char[] str, char[] pattern)
    {
        int lenS = str.length;
        int lenP = pattern.length;
        boolean[]  matchArr = new boolean[lenS + 1];
        Arrays.fill(matchArr,false);
        matchArr[lenS] = true;
        for(int i=lenP-1;i>=0;i--){
            if(pattern[i] == '*'){
                for(int j = lenS-1;j>=0;j--){
                    matchArr[j] = matchArr[j] || (matchArr[j+1] && (pattern[i-1] == '.' || pattern[i-1] == str[j]));
                }
                i--;
            }else{
                for(int j=0;j<lenS;j++){
                    matchArr[j] = matchArr[j+1] && (pattern[i] == '.' || pattern[i] == str[j]);
                }
                matchArr[lenS] = false;
            }
        }
        return matchArr[0];
    }
}
