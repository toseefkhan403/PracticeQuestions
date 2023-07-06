package com.practice.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Nmeetings {
    // greedy - do those meetings first which end first - can accomodate more
    // meetings that way
    // sort on the basis of end time - finish em first - O(n+nlogn+n), O(2n)
    public static int maxMeetings(int start[], int end[], int n) {
        List<Pair> meetings = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            meetings.add(new Pair(start[i], end[i]));
        }

        Collections.sort(meetings, (a, b) -> a.end - b.end);

        // include an extra list and add indices to it - can use it to print the order
        // of the meetings
        int meetingCount = 0;
        int endLimit = -1;

        for (int i = 0; i < meetings.size(); i++) {
            // can start the meeting ONLY if prev is finished
            if (meetings.get(i).start > endLimit) {
                meetingCount++;
                endLimit = meetings.get(i).end;
            }
        }

        return meetingCount;
    }
}

// can also include the index to print the order of the meetings
class Pair {
    int start;
    int end;

    public Pair(int s, int e) {
        start = s;
        end = e;
    }
}
