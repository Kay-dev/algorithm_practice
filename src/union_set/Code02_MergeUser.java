package union_set;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 一个账号，拥有手机号，用户名，邮箱
 * 只要两个账号有一项属性是相同的，则说明这两个账号是属于同一个用户
 * 问：一各账号集合中，共有多少个用户？
 *
 * @author weizheng
 * @date 2022/03/05
 */
public class Code02_MergeUser {


    public static int calUsersNum(List<User> users) {
        Code01_UnionSet<User> unionSet = new Code01_UnionSet<>(users);
        Map<String, User> phoneNumberMap = new HashMap<>();
        Map<String, User> nameMap = new HashMap<>();
        Map<String, User> emailMap = new HashMap<>();

        for (User user : users) {
            // 如果已经有手机号相同的账号，则两者合并
            if (phoneNumberMap.containsKey(user.phoneNumber)) {
                unionSet.union(phoneNumberMap.get(user.phoneNumber), user);
            } else {
                phoneNumberMap.put(user.phoneNumber, user);
            }
            // 如果已经有用户名相同的账号，则两者合并
            if (nameMap.containsKey(user.name)) {
                unionSet.union(nameMap.get(user.name), user);
            } else {
                nameMap.put(user.name, user);
            }
            // 如果已经有手机号相同的用户，则两者合并
            if (emailMap.containsKey(user.email)) {
                unionSet.union(emailMap.get(user.email), user);
            } else {
                emailMap.put(user.email, user);
            }
        }
        // 返回并查集中合并后，还剩多少个集合
        return unionSet.getSize();
    }


    private static class User {
        String phoneNumber;
        String name;
        String email;

        public User() {
        }

        public User(String phoneNumber, String name, String email) {
            this.phoneNumber = phoneNumber;
            this.name = name;
            this.email = email;
        }
    }

}
