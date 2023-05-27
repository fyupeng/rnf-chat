package cn.fyupeng.pojo;

import java.io.Serializable;

/**
 * @Auther: fyp
 * @Date: 2023/5/26
 * @Description: 聊天用户
 * @Package: cn.fyupeng.pojo
 * @Version: 1.0
 */
public class User implements Serializable {
    private String userId;
    private String userName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
