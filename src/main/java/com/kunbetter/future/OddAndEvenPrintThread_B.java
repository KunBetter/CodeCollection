package com.kunbetter.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class OddAndEvenPrintThread_B {
    public static int cnt = 0;
    public static final int RANGE = 1000;
    public static List<Integer> ans = new ArrayList<>(RANGE);
    public static final ExecutorService oddExecutorService = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
    public static final ExecutorService evenExecutorService = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    public static void main(String[] argus) {
        Future oddTask = oddExecutorService.submit(() -> {
            while (cnt < RANGE) {
                if ((cnt & 1) == 1) {
                    //System.out.println(cnt);
                    ans.add(cnt);
                    cnt++;
                }
            }
            //return “finish”； 通过future.get可以得到这边return的结果，因为默认的泛型是object，不限数据类型
        });

        Future evenTask = evenExecutorService.submit(() -> {
            while (cnt < RANGE) {
                if ((cnt & 1) == 0) {
                    //System.out.println(cnt);
                    ans.add(cnt);
                    cnt++;
                }
            }
        });

        try {
            oddTask.get();
            evenTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ans.forEach((a) -> System.out.print(a + " "));
    }
}
