package com.practice.hashing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKfrequent {
    // bucket sort - freq as index - instead of storing as value, freq(unbounded) -
    // store as freq, {value}(bounded) - O(3n), O(2n) [linear]
    public int[] topKFrequent(int[] nums, int k) {
        // freq map needed - value, freq
        Map<Integer, Integer> map = new HashMap<>();

        for (int it : nums) {
            int freq = map.getOrDefault(it, 0);
            map.put(it, freq + 1);
        }

        List<List<Integer>> bucket = new ArrayList<>();
        // bucket size = n+1(as freq cant exceed this) - thus bounded
        for (int i = 0; i <= nums.length; i++) {
            bucket.add(new ArrayList<>());
        }

        for (int key : map.keySet()) {
            int freq = map.get(key);
            // freq, {value}
            bucket.get(freq).add(key);
        }

        int[] res = new int[k];
        int ind = 0;
        // go from back - top k freq
        for (int i = bucket.size() - 1; i >= 0; i--) {
            for (int it : bucket.get(i)) {
                res[ind++] = it;
                if (ind == k)
                    return res;
            }
        }

        return res;
    }

    // freq arr - PQ of size k instead of sorting - O(n+nlogk+klogk), O(2n)
    public int[] topKFrequentPQ(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int it : nums) {
            int freq = map.getOrDefault(it, 0);
            map.put(it, freq + 1);
        }

        // cant use max heap - some max guys will be lost
        // min heap of size k on the basis of freq
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.freq - b.freq);

        for (int key : map.keySet()) {
            if (pq.size() != k) {
                pq.offer(new Pair(key, map.get(key)));
            } else {
                // only add big guys
                Pair p = new Pair(key, map.get(key));
                if (p.freq > pq.peek().freq) {
                    pq.poll();
                    pq.offer(p);
                }
            }
        }

        int[] res = new int[k];
        // top k frequent(in reverse order tho)
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll().value;
        }

        return res;
    }

    // freq arr - sort on the basis of freq - pick top k guys - O(n+nlogn+k),O(2n)
    public int[] topKFrequentI(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int it : nums) {
            int freq = map.getOrDefault(it, 0);
            map.put(it, freq + 1);
        }

        List<Pair> pairList = new ArrayList<>();
        for (int key : map.keySet()) {
            pairList.add(new Pair(key, map.get(key)));
        }

        Collections.sort(pairList, (a, b) -> b.freq - a.freq);

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pairList.get(i).value;
        }

        return res;
    }
}

class Pair {
    int value;
    int freq;

    public Pair(int v, int f) {
        value = v;
        freq = f;
    }
}