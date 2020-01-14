package com.wuwenxu.arithmetic.jzoffer;

/**
 * 把数组排成最小的树
 * <p>
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
 */
public class PrintMinNumber {


    /*
     * 单个元素里的数，不能换位置
     * 将数组每个元素转化为字符串，使用Array重写排序方法排序；使用冒泡排序
     * 例如： 2  21    221>212  则21排在2面前
     */
    public class 数组排成最小数 {
        public String PrintMinNumber(int[] numbers) {
            //冒泡排序算法
            String str = "";
            int t = 0;
            for (int i = 0; i < numbers.length; i++) {
                for (int j = i + 1; j < numbers.length; j++) {
                    //转化为字符串
                    String s1 = numbers[i] + "" + numbers[j];
                    String s2 = numbers[j] + "" + numbers[i];
                    //字符串比较大小,>0说明s1>s2，交换位置
                    if (s1.compareTo(s2) > 0) {
                        t = numbers[j];
                        numbers[j] = numbers[i];
                        numbers[i] = t;
                    }
                }
            }
            //将字符串数组转化为String
            for (int i = 0; i < numbers.length; i++) {
                str += String.valueOf(numbers[i]);
            }
            return str;


		/*  if(numbers.length==0||numbers==null) return "";
           int len = numbers.length;
	        String[] str = new String[len];
		for(int i=0;i<numbers.length;i++){
			str[i]=String.valueOf(numbers[i]);
		}
		Arrays.sort(str, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				String c1=o1+o2;
				String c2=o2+o1;
				return c1.compareTo(c2);
			}
		});
		StringBuilder s = new StringBuilder();
		for(int i=0;i<str.length;i++){
			s.append(str[i]);
		}
		return s.toString();*/
        }
    }
}
