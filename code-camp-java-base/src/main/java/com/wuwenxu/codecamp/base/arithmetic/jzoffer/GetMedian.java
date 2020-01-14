package com.wuwenxu.arithmetic.jzoffer;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 数据流中的中位数
 *
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * 我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
 */
public class GetMedian {
    private PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(5000, Collections.reverseOrder());

    public void Insert(Integer num) {
        if(maxHeap.isEmpty() || num<maxHeap.peek()){
            maxHeap.offer(num);
        }else{
            minHeap.offer(num);
        }
        if(maxHeap.size() > minHeap.size() + 1){
            minHeap.offer(maxHeap.poll());
        }else if(minHeap.size() > maxHeap.size()){
            maxHeap.offer(minHeap.poll());
        }
    }
    public Double GetMedian() {
        if(maxHeap.size() > minHeap.size()){
            return (double)maxHeap.peek();
        }else{
            return (maxHeap.peek() + minHeap.peek())/2.0;
        }
    }
}
