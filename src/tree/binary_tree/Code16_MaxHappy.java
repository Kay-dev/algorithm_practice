package tree.binary_tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 怎么邀请，才能获得最party的大快乐值
 * 条件：
 * 1. 每个员工都有自己的快乐值
 * 2. 每个员工都有唯一上级
 * 3. 如果某员工参加了party，那么它的直属下级都不能参加，下下级不受影响
 *
 * @author weizheng
 * @date 2022/02/28
 */
public class Code16_MaxHappy {

    public static int getMaxHappy(Employee boss) {
        if (boss == null) {
            return 0;
        }
        Info result = process(boss);
        return Math.max(result.join, result.notJoin);
    }

    private static Info process(Employee employee) {
        if (employee.subordinates.isEmpty()) {
            return new Info(employee.happy, 0);
        }
        int join = employee.happy;
        int notJoin = 0;
        for (Employee subordinate : employee.subordinates) {
            Info info = process(subordinate);
            // 当前员工参加，那么它的直接下级都不能来
            join += info.notJoin;
            // 当前员工不参加，那么它的直接下级可来可不来
            notJoin += Math.max(info.join, info.notJoin);
        }
        return new Info(join, notJoin);
    }


    static class Info {
        // 此员工加入派对的最大快乐值
        int join;
        // 此员工不加入派对的最大快乐值
        int notJoin;

        public Info(int join, int notJoin) {
            this.join = join;
            this.notJoin = notJoin;
        }
    }

    static class Employee {
        int happy;
        List<Employee> subordinates;

        public Employee(int happy) {
            this.happy = happy;
            this.subordinates = new ArrayList<>();
        }
    }
}
