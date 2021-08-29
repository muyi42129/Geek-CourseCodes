package org.zhiyi.coursecode.week4.java0.conc0303.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LinkedHashMapDemo {
    public static void main(String[] args) {
        
        // test hash map
        System.out.println("=====>1. test hash map");
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("name1", "josan1");
        hashMap.put("name3", "josan3");
        hashMap.put("name2", "josan2");
        Set<Map.Entry<String, String>> set = hashMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            System.out.println("key:" + key + ",value:" + value);
        }
        
        // test linked hash map
        System.out.println("=====>2. test linked hash map");
        /**
         * accessOrder
         * false - ≤Â»ÎÀ≥–Ú
         * true - ∑√Œ À≥–Ú
         */
        Map<String, String> linkedHashMap = new LinkedHashMap<String, String>(16, 0.75f, true);
        linkedHashMap.put("name1", "josan1");
        linkedHashMap.put("name2", "josan2");
        linkedHashMap.put("name3", "josan3");

        linkedHashMap.get("name2");
        linkedHashMap.get("name1");

        Set<Map.Entry<String, String>> set1 = linkedHashMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator1 = set1.iterator();
        while(iterator1.hasNext()) {
            Map.Entry entry = iterator1.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            System.out.println("key:" + key + ",value:" + value);
        }
        
    }
}
