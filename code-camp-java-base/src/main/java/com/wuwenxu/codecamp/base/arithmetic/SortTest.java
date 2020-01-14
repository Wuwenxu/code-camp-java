package com.wuwenxu.arithmetic;

import java.util.Arrays;

/**
 * @author pancm
 * @Title: SortTest
 * @Description: 排序算法
 * 主要包括插入、二分、冒泡和快排算法
 * @Version:1.0.0
 * @date 2017-5-31
 */
public class SortTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

        int[] t = {1, 44, 55, 22, 34, 88, 3};
        for (int i = 0, j = t.length; i < j; i++) {
            System.out.println("排序之前:" + t[i]);
        }
//		long a=System.currentTimeMillis();
//		int[] k=ps(t);
//		for(int i=0,j=k.length;i<j;i++){
//			System.out.println("插入排序之后:"+k[i]);
//		}
//		
        int[] s = crps(t);
        for (int i = 0, j = s.length; i < j; i++) {
            System.out.println("插入排序倒叙之后:" + s[i]);
        }


//		System.out.println("\r<br>执行耗时 : "+(System.currentTimeMillis()-a)+"毫秒 ");
//	    int[] p=sort(t);
//	    for(int i=0,j=p.length;i<j;i++){
//			System.out.println("二分法排序之后:"+p[i]);
//		}
//	    
//		int[] l=mp(t);
//		for(int i=0,j=l.length;i<j;i++){
//			System.out.println("冒泡排序之后:"+l[i]);
//		}
//		
        int[] mpdx = mpdx(t);
        for (int i = 0, j = mpdx.length; i < j; i++) {
            System.out.println("冒泡排序倒叙之后:" + mpdx[i]);
        }

        for (int i = 0; i < t.length; i++) {
            System.out.print(t[i] + " ");
        }
        //快速排序
        quick(t);
        System.out.println();
        System.out.println("快速排序之后：");
        for (int i = 0; i < t.length; i++) {
            System.out.print(t[i] + " ");
        }

        Arrays.sort(t);
//		for(int i:t){
//			System.out.println("快速排序之升序:"+i);
//		}
//	    for(int j=t.length-1;0<=j;j--){ 
//	    	System.out.println("快速排序之降序:"+t[j]);
//	    }
//	    System.out.println("\r<br>执行耗时 : "+(System.currentTimeMillis()-a)+"毫秒 ");
    }

    /**
     * 插入排序  升序
     * 插入排序是循环数组，然后将前一位的数字和后一位的进行比较，将数值大的向后移一位
     *
     * @param a
     * @return
     */
    public static int[] ps(int[] a) {
        // 第1个数肯定是有序的，从第2个数开始遍历，依次插入有序序列
        for (int i = 1, j = a.length; i < j; i++) {
            // 取出第i个数，和前i-1个数比较后，插入合适位置
            int t = a[i];
            int k;
            // 因为前i-1个数都是从小到大的有序序列，所以只要当前比较的数(a[k])比t大，就把这个数后移一位
            for (k = i - 1; k >= 0; k--) {
                if (a[k] > t) {
                    a[k + 1] = a[k];
                } else {
                    break;
                }
            }
            a[k + 1] = t;
        }
        return a;
    }

    /**
     * 插入排序  降序
     * 插入排序是循环数组，然后将前一位的数字和后一位的进行比较，将数值大的向后移一位
     *
     * @param a
     * @return
     */
    public static int[] crps(int[] a) {
        for (int i = 1, j = a.length; i < j; i++) {
            int t = a[i];
            int k;
            for (k = i - 1; k >= 0; k--) {
                if (a[k] < t) {
                    a[k + 1] = a[k];
                } else {
                    break;
                }
            }
            a[k + 1] = t;
        }
        return a;
    }


    /**
     * 冒泡排序 升序
     * 冒泡排序 是双重循环数组，前一位和后一位进行比较，若前一位大于后一位，就交换位置
     */
    public static int[] mp(int[] m) {
        for (int i = 0; i < m.length - 1; i++) {
            for (int j = i + 1; j < m.length; j++) {
                if (m[i] > m[j]) {
                    int tmp = m[i];
                    m[i] = m[j];
                    m[j] = tmp;
                }
            }
        }
        return m;
    }

    /**
     * 冒泡排序 倒序
     * 冒泡排序 是双重循环数组，前一位和后一位进行比较，若前一位大于后一位，就交换位置
     */
    public static int[] mpdx(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] < a[j]) {
                    int tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
        }
        return a;
    }

    /**
     * 二分法排序  升序
     *
     * @param a
     * @return
     */
    public static int[] sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int temp = a[i];
            int left = 0;
            int right = i - 1;
            int mid = 0;
            while (left <= right) {
                mid = (left + right) / 2;
                if (temp < a[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            for (int j = i - 1; j >= left; j--) {
                a[j + 1] = a[j];
            }
            if (left != i) {
                a[left] = temp;
            }
        }
        return a;
    }

    /**
     * 快速排序
     * 简单理解就是对数组内中间开始分两半，比基数小的在左边大的在右边
     * 然后在对左右数据再递归进行上面的操作
     *
     * @param a
     */
    private static void quick(int[] a) {
        //判断值是否大于0
        if (a.length > 0) {
            quickSort(a, 0, a.length - 1);
        }
    }

    private static void quickSort(int[] a, int low, int high) {
        //如果不加这个判断递归会无法退出导致堆栈溢出异常
        if (low < high) {
            // 对数组进行分割，取出下次分割的基准标号
            int middle = getMiddle(a, low, high);
            // 对“基准标号“左侧的一组数值进行递归的切割，以至于将这些数值完整的排序
            quickSort(a, 0, middle - 1);
            // 对“基准标号“右侧的一组数值进行递归的切割，以至于将这些数值完整的排序
            quickSort(a, middle + 1, high);
        }
    }

    private static int getMiddle(int[] a, int low, int high) {
        // 以最左边的数(low)为基准元素
        int temp = a[low];
        while (low < high) {
            // 从序列右端开始，向左遍历，直到找到小于temp的数
            while (low < high && a[high] >= temp) {
                high--;
            }
            // 找到了比temp小的元素，将这个元素放到最左边的位置
            a[low] = a[high];
            // 从序列左端开始，向右遍历，直到找到大于temp的数
            while (low < high && a[low] <= temp) {
                low++;
            }
            // 找到了比temp大的元素，将这个元素放到最右边的位置
            a[high] = a[low];
        }
        // 最后将temp放到low位置。此时，low位置的左侧数值应该都比low小；
        // 而low位置的右侧数值应该都比low大。
        a[low] = temp;
        return low;
    }


    private static <T extends Comparable<T>> void adjustHeat(T[] array, int parent, int length) {
        // temp保存当前父节点
        T temp = array[parent];
        // 先获得左孩子
        int child = 2 * parent + 1;

        while (child < length) {
            // 如果有右孩子结点，并且右孩子结点的值大于左孩子结点，则选取右孩子结点
            if (child + 1 < length && array[child].compareTo(array[child + 1]) < 0) {
                child++;
            }

            // 如果父结点的值已经大于孩子结点的值，则直接结束
            if (temp.compareTo(array[child]) >= 0) {
                break;
            }

            // 把孩子结点的值赋给父结点
            array[parent] = array[child];

            // 选取孩子结点的左孩子结点,继续向下筛选
            parent = child;
            child = 2 * child + 1;
        }

        array[parent] = temp;
    }

    /**
     * 堆排序
     */
    public <T extends Comparable<T>> void HeapSort(T[] list) {
        // 循环建立初始堆
        for (int i = list.length / 2; i >= 0; i--) {
            adjustHeat(list, i, list.length);
        }

        // 进行n-1次循环，完成排序
        for (int i = list.length - 1; i > 0; i--) {
            // 最后一个元素和第一元素进行交换
            T temp = list[i];
            list[i] = list[0];
            list[0] = temp;

            // 筛选 R[0] 结点，得到i-1个结点的堆
            adjustHeat(list, 0, i);
        }
    }

    private <T extends Comparable<T>> void merge(T[] array, int low, int mid, int high) {
        // i是第一段序列的下标
        int i = low;
        // j是第二段序列的下标
        int j = mid + 1;
        // k是临时存放合并序列的下标
        int k = 0;
        // array2是临时合并序列
        T[] array2 = (T[]) new Comparable[high - low + 1];

        // 扫描第一段和第二段序列，直到有一个扫描结束
        while (i <= mid && j <= high) {
            // 判断第一段和第二段取出的数哪个更小，将其存入合并序列，并继续向下扫描
            if (array[i].compareTo(array[j]) <= 0) {
                array2[k] = array[i];
                i++;
                k++;
            } else {
                array2[k] = array[j];
                j++;
                k++;
            }
        }

        // 若第一段序列还没扫描完，将其全部复制到合并序列
        while (i <= mid) {
            array2[k] = array[i];
            i++;
            k++;
        }

        // 若第二段序列还没扫描完，将其全部复制到合并序列
        while (j <= high) {
            array2[k] = array[j];
            j++;
            k++;
        }

        // 将合并序列复制到原始序列中
        for (k = 0, i = low; i <= high; i++, k++) {
            array[i] = array2[k];
        }
    }

    private <T extends Comparable<T>> void mergeSort(T[] array, int gap, int length) {
        int i = 0;

        // 归并gap长度的两个相邻子表
        for (i = 0; i + 2 * gap - 1 < length; i = i + 2 * gap) {
            merge(array, i, i + gap - 1, i + 2 * gap - 1);
        }

        // 余下两个子表，后者长度小于gap
        if (i + gap - 1 < length) {
            merge(array, i, i + gap - 1, length - 1);
        }
    }

    /**
     * 归并排序
     */
    public <T extends Comparable<T>> void Mergesort(T[] list) {
        for (int gap = 1; gap < list.length; gap = 2 * gap) {
            mergeSort(list, gap, list.length);
        }
    }

    /**
     * 希尔排序
     */
    public <T extends Comparable<T>> void ShellSort(T[] list) {
        int gap = list.length / 2;

        while (1 <= gap) {
            // 把距离为 gap 的元素编为一个组，扫描所有组
            for (int i = gap; i < list.length; i++) {
                int j = 0;
                T temp = list[i];

                // 对距离为 gap 的元素组进行排序
                for (j = i - gap; j >= 0 && temp.compareTo(list[j]) < 0; j = j - gap) {
                    list[j + gap] = list[j];
                }
                list[j + gap] = temp;
            }

            // 减小增量
            gap = gap / 2;
        }
    }

    /**
     * 选择排序
     */
    public <T extends Comparable<T>> void SelectionSort(T[] list) {
        // 需要遍历获得最小值的次数
        // 要注意一点，当要排序 N 个数，已经经过 N-1 次遍历后，已经是有序数列
        for (int i = 0; i < list.length - 1; i++) {
            // 用来保存最小值得索引
            int index = i;

            // 寻找第i个小的数值
            for (int j = i + 1; j < list.length; j++) {
                if (list[index].compareTo(list[j]) > 0) {
                    index = j;
                }
            }

            // 将找到的第i个小的数值放在第i个位置上
            T temp = list[index];
            list[index] = list[i];
            list[i] = temp;

        }
    }
}
