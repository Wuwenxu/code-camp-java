package com.spark;

import java.net.URISyntaxException;

import org.junit.Test;

import com.data.spark.BasicClusterTask;

/**
 * @author onlyone
 */
public class BasicClusterTaskTest {

    @Test
    public void splitWordCountTest() throws URISyntaxException {
        String inputFile = getClass().getResource("/data/1.txt").toURI().toString();
        System.out.println(inputFile);
        new BasicClusterTask().splitWordCount(inputFile);
    }
    
    @Test
    public void countTest() throws URISyntaxException {
        String inputFile = getClass().getResource("/data/2.txt").toURI().toString();
        System.out.println(inputFile);
        new BasicClusterTask().countSize(inputFile);
    }

    @Test
    public void sumTest() throws URISyntaxException {
        new BasicClusterTask().sum();
    }
}
