package greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * 只有一个会议室，一个会议室不能同时开两场会议，问一天最多安排多少场会议
 *
 * @author weizheng
 * @date 2022/03/01
 */
public class Code02_ScheduleMeeting {


    /**
     * 方法一：暴力递归
     *
     * @param meetings
     * @return
     */
    public static int findMaxMeetingCount1(Meeting[] meetings) {
        if (meetings == null || meetings.length == 0) {
            return 0;
        }
        return process(meetings, 0, 0);
    }

    /**
     * 递归获取一天能安排的最多的会议次数
     *
     * @param availableMeetings 还可以安排的会议集合
     * @param done              已经安排的会议次数
     * @param endTime           一场会议结束时的时间
     * @return 一天能安排的最多的会议次数
     */
    private static int process(Meeting[] availableMeetings, int done, int endTime) {
        if (availableMeetings.length == 0) {
            return done;
        }
        int max = done;
        // 对每个待安排的会议递归
        for (Meeting meeting : availableMeetings) {
            // 只有会议的开始时间还没过，尝试安排上当前会议，然后递归
            if (meeting.start >= endTime) {
                // 将当前会议从可用会议中剔除
                Meeting[] newArray = Arrays.stream(availableMeetings)
                        .filter(i -> !meeting.equals(i))
                        .toArray(Meeting[]::new);
                // 递归获取最大会议场次
                max = Math.max(max, process(newArray, done + 1, meeting.end));
            }
        }
        return max;
    }


    static class Meeting {
        // 会议开始时间
        int start;
        // 会议结束时间
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Meeting{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    /**
     * 方法二：贪心算法
     * 策略：以会议结束时间进行排序，再统计一天能安排多少会议
     *
     * @param meetings
     * @return
     */
    public static int findMaxMeetingCount2(Meeting[] meetings) {
        if (meetings == null || meetings.length == 0) {
            return 0;
        }
        Arrays.sort(meetings, Comparator.comparingInt(a -> a.end));
        int result = 0;
        int time = 0;
        for (Meeting meeting : meetings) {
            if (time <= meeting.start) {
                result++;
                time = meeting.end;
            }
        }
        return result;
    }
    //================================== test =======================================

    public static void main(String[] args) {
        int testTime = 100_000;
        int size = 20;
        int timeRange = 1440;

        IntStream.range(0, testTime)
                .boxed()
                .parallel()
                .forEach(i -> {
                    Meeting[] meetings = generateMeetings(size, timeRange);
                    int i1 = findMaxMeetingCount1(meetings);
                    int i2 = findMaxMeetingCount2(meetings);
                    if (i1 != i2) {
                        throw new RuntimeException("Failed!" + "i1=" + i1 + ",i2=" + i2 + Arrays.toString(meetings));
                    }
                });
        System.out.println("Finished!");
    }


    /**
     * 生成随机容量的会议数组
     *
     * @param size
     * @param timeRange
     * @return
     */
    public static Meeting[] generateMeetings(int size, int timeRange) {
        Random random = new Random();
        return IntStream.range(0, random.nextInt(size) + 1)
                .boxed()
                .map(i -> generateMeeting(timeRange))
                .toArray(Meeting[]::new);
    }

    /**
     * 生成开始时间，结束时间都随机的会议
     *
     * @param timeRange 允许的时间最大范围
     * @return 会议
     */
    public static Meeting generateMeeting(int timeRange) {
        Random random = new Random();
        int time1 = random.nextInt(timeRange) + 1;
        int time2 = random.nextInt(timeRange) + 1;
        if (time1 == time2) {
            return generateMeeting(timeRange);
        }
        return new Meeting(Math.min(time1, time2), Math.max(time1, time2));
    }
}
