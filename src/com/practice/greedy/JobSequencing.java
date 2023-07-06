package com.practice.greedy;

import java.util.Arrays;

public class JobSequencing {
    // think like a human - pick the highest profit one - do it on its last day(so
    // that can do earlier jobs as well)
    // sort jobs on the basis of profit - do job on deadline - O(nlogn+n*m[m=max
    // deadline - going back for every n]), O(m[tt arr])
    int[] jobSchedulingI(Job arr[], int n) {
        // desc order - profits
        Arrays.sort(arr, (a, b) -> b.profit - a.profit);

        // maximum deadline = n (constraints)
        boolean[] timeTable = new boolean[n + 1];
        Arrays.fill(timeTable, false);

        int profit = 0;
        int jobs = 0;

        for (int i = 0; i < n; i++) {
            Job job = arr[i];

            // if you are free, do the job on the deadline
            if (!timeTable[job.deadline]) {
                jobs++;
                profit += job.profit;
                timeTable[job.deadline] = true;
            } else {
                // try to do it before the deadline - NOT i-1
                int j = job.deadline - 1;
                while (j > 0) {
                    if (!timeTable[j]) {
                        // do the job
                        jobs++;
                        profit += job.profit;
                        timeTable[j] = true;
                        break;
                    }

                    j--;
                }
            }
        }

        return new int[] { jobs, profit };
    }

    int[] jobSequencing(Job arr[], int n) {
        Arrays.sort(arr, (a, b) -> (b.profit - a.profit));

        int maxi = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i].deadline > maxi) {
                maxi = arr[i].deadline;
            }
        }

        int result[] = new int[maxi + 1];

        for (int i = 1; i <= maxi; i++) {
            result[i] = -1;
        }

        int countJobs = 0, jobProfit = 0;

        // two for loops
        for (int i = 0; i < n; i++) {
            for (int j = arr[i].deadline; j > 0; j--) {
                // Free slot found
                if (result[j] == -1) {
                    result[j] = i;
                    countJobs++;
                    jobProfit += arr[i].profit;
                    break;
                }
            }
        }

        int ans[] = new int[2];
        ans[0] = countJobs;
        ans[1] = jobProfit;
        return ans;
    }

}

class Job {
    int id, profit, deadline;

    Job(int x, int y, int z) {
        this.id = x;
        this.deadline = y;
        this.profit = z;
    }
}
