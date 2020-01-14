package com.wuwenxu.arithmetic.jzoffer.String;

/**
 * 翻转单词顺序列
 *
 *
 * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。
 * 同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。
 * 例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，
 * 正确的句子应该是“I am a student.”。Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
 */
public class ReverseSentence {
    /**
     * @author LemonLin
     * @Description :ReverseSentence42_1
     *翻转单词顺序列
     *
     * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。
     * 同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。
     * 例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，
     * 正确的句子应该是“I am a student.”。Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
     *
     * 思路：先翻转全部的字符串，再翻转每个空格分割的单词字符串。
     * 本题调试bug的时候遇到很多例子无法通过：总结如下：
     * 1、翻转过程出错:就是对应不上，代码出错部分，就是设置一个j的变量，j变化01234...start+j与end-j互相交换
     *2、如果只有空格字符，需要返回空格，用str.trim().equals("")
     * 3、如果是字符串是偶数个数（字符数组首字符下标是0），那么翻转的middle值为 (start+end)/2+1;
     *      为什么加一，如果字符数组下标是012345，则必须为middle为3，小于号(0+5)/2=2,加一，则为3
     * 4、需要用下标来进行翻转，所以把输入的字符串转换为字符数组，用到 str.toCharArray();
     *      转换完之后再把字符数组转换为字符串String.valueOf(chars);
     *
     * 5、需要考虑输入的字符串没有空格分割的时候，那么翻转之后还要再翻转回来，用一个标志位进行标记
     * 6、如有空格的话，最后一个单词由于没有空格来做分割线，所以需要特殊翻转最后一个
     * 7、数组作为输入，一般情况下需要至少两个参数，一个是数组，一个是数组长度
     * @date 2018/6/12-15:29
     */
        /**
         *     翻转思路是用一个中间的变量来存，头尾互相交换,翻转的终止条件是到字符串的中间
         */
        public String ReverseSentence(String str) {
            if(str == null||str.trim().equals("")){
                return str;
            }
            char [] chars = str.toCharArray();
            //翻转整个句子
            Reverse(chars,0,chars.length-1);
            //翻转句子中的每个单词
            int j=0;
            int start = j;
            boolean flag = false;
            while (j<chars.length){
                if (chars[j]==' '){
                    flag = true;
                    int end = j-1;
                    j++;
                    Reverse(chars,start,end);
                    start = j;
                }
                j++;
            }
        /*5、需要考虑输入的字符串没有空格分割的时候，那么翻转之后还要再翻转回来，用一个标志位进行标记*/
            if (flag ==false){
                Reverse(chars,0,chars.length-1);
            }
        /* * 6、如有空格的话，最后一个单词由于没有空格来做分割线，所以需要特殊翻转最后一个*/
            if (flag == true){
                Reverse(chars,start,chars.length-1);
            }
            String strChange=String.valueOf(chars);
            return strChange;
        }
        //start 的参数为数组开头，end的参数为数组的结尾;
        //数组作为输入，一般情况下需要至少两个参数，一个是数组，一个是数组长度
        /**
         * 3、如果是字符串是偶数个数（字符数组首字符下标是0），那么翻转的middle值为 (start+end)/2+1;
         *      为什么加一，如果字符数组下标是012345，则必须为middle为3，因为for循环中i<middle注意体会这个小于号，
         *      而不是小于等于号，小于号(0+5)/2=2,加一，则为3*/
        public void Reverse(char[] chars,int start,int end){
            char  temp= ' ';
            int isZero = (start+end)%2;
            int middle = 0;
            if (isZero == 0){
                middle = (start+end)/2;
            }else {
                middle = (start+end)/2+1;
            }
            int j=0;
            for (int i =start;i<middle;i++){
                temp = chars[start+j];
                chars [start+j] = chars[end-j];
                chars[end-j] = temp;
                j++;
            }
        }
        public static void main(String[] args) {
            String string = "i am a student.";
            ReverseSentence stringReverseSentence42_1 = new ReverseSentence();
            String s = stringReverseSentence42_1.ReverseSentence(string);
            System.out.println(s);
        }


}
