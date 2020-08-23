package com.wuwenxu.codecamp.base.apache.commons;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

public class Lang {

    public static void main(String[] args) {
        StringUtils.isNotEmpty("test");
        for (int i=0;i<20;i++){
            System.out.println(SystemUtils.IS_OS_AIX);
        }
    }
}
