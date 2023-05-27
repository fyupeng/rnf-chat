package cn.fyupeng.api;

import cn.fyupeng.pojo.ServerMessageResult;
import cn.fyupeng.pojo.User;

/**
 * @Auther: fyp
 * @Date: 2023/5/26
 * @Description: 聊天服务
 * @Package: cn.fyupeng.api
 * @Version: 1.0
 */
public interface ChatService {
    ServerMessageResult receive(User user) throws InterruptedException;
    ServerMessageResult send(User user, String message) throws InterruptedException;
}
