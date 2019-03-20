package com.sence.utils;

import java.util.Comparator;

public class SpComparator implements Comparator<String> {
    /**
     * 如果o1小于o2,返回一个负数;如果o1大于o2，返回一个正数;如果他们相等，则返回0;
     */

    @Override
    public int compare(String lhs, String rhs) {
        return lhs.compareTo(rhs);
    }
}