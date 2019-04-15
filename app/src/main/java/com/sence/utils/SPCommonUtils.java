package com.sence.utils;

import com.blankj.utilcode.util.SPUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SPCommonUtils {
    public static final String key = "sence";

    public SPCommonUtils() {
    }

    public static String getTime() {
        long millis = System.currentTimeMillis() / 1000;
        String sysTime = SPUtils.getInstance().getString("sysTime", "0");
        long time = millis + Long.parseLong(sysTime);
        return String.valueOf(time);
    }

    public static String signParameter(Map<String, Object> params) {
        String signStr = null;
        Set keySet = params.keySet();
        ArrayList keyList = new ArrayList();
        Iterator firstPart = keySet.iterator();

        while (firstPart.hasNext()) {
            String valueList = (String) firstPart.next();
            keyList.add(valueList);
        }
        Collections.sort(keyList, new SpComparator());
        ArrayList valueList1 = new ArrayList();
        Iterator signBefore = keyList.iterator();

        String firstPart1;
        while (signBefore.hasNext()) {
            firstPart1 = (String) signBefore.next();
            String e = params.get(firstPart1).toString();
            valueList1.add(e);
        }
        firstPart1 = listToString(valueList1, "");
        String signBefore1 = firstPart1 + getTime() + key;


        try {
            signStr = MD5Transfer.MD5(signBefore1);
        } catch (Exception var11) {
            var11.printStackTrace();
            signStr = "";
        }

        return signStr;
    }

    public static String listToString(List list, String separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); ++i) {
            sb.append(list.get(i)).append(separator);
        }
        return "".equals(separator) ? sb.toString() : sb.toString().substring(0, sb.toString().length() - 1);
    }


}