package cn.fyupeng.service;

import cn.fyupeng.annotation.Service;
import cn.fyupeng.api.ChatService;
import cn.fyupeng.pojo.Message;
import cn.fyupeng.pojo.ServerMessageResult;
import cn.fyupeng.pojo.User;
import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue;
import lombok.extern.slf4j.Slf4j;
import sun.util.calendar.LocalGregorianCalendar;

import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: fyp
 * @Date: 2023/5/26
 * @Description: 简化聊天服务
 * @Package: cn.fyupeng.service
 * @Version: 1.0
 */
@Slf4j
@Service(name = "simpleChatService", group = "1.0.0")
public class SimpleChatService implements ChatService {

    private ConcurrentHashMap<String, ArrayBlockingQueue<Message>> messageQueue = new ConcurrentHashMap<>();

    @Override
    public ServerMessageResult receive(User user) throws InterruptedException {
        if (!messageQueue.containsKey(user.getUserId())) {
            messageQueue.put(user.getUserId(), new ArrayBlockingQueue<>(100));
            return ServerMessageResult.errorMsg("服务器未接收到该用户注册信息，已为您完成注册。");
        }
        ArrayBlockingQueue<Message> messages = messageQueue.get(user.getUserId());
        Message msg = messages.poll(500, TimeUnit.MILLISECONDS);
        if (msg != null) {
            System.out.println("返回消息 => 用户：[ " +user.getUserName() + " ] 消息： [ " + msg + " ]");
        }
        return ServerMessageResult.ok(msg);
    }

    @Override
    public ServerMessageResult send(User user, String message) throws InterruptedException {
        if (!messageQueue.containsKey(user.getUserId())) {
            messageQueue.put(user.getUserId(), new ArrayBlockingQueue<>(100));
            return ServerMessageResult.errorMsg("服务器未接收到该用户注册信息，已为您完成注册。");
        }
        ConcurrentHashMap.KeySetView<String, ArrayBlockingQueue<Message>> keys = messageQueue.keySet();
        Iterator<String> iterator = keys.stream().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            if (!key.equals(user.getUserId())) {
                ArrayBlockingQueue<Message> messages = messageQueue.get(key);
                Message msg = new Message();
                msg.setMessage(message);
                msg.setCreateTime(new Date());
                msg.setUserName(user.getUserName());
                messages.put(msg);
                messageQueue.put(key, messages);
                System.out.println("广播消息 => 用户id：[ " + key + " ] 消息： [ " + msg + " ]");
            }
        }
        return ServerMessageResult.ok("服务器接收消息成功!");
    }
}
