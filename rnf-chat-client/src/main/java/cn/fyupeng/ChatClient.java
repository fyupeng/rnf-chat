package cn.fyupeng;

import cn.fyupeng.annotation.Reference;
import cn.fyupeng.api.ChatService;
import cn.fyupeng.factory.ThreadPoolFactory;
import cn.fyupeng.loadbalancer.RoundRobinLoadBalancer;
import cn.fyupeng.net.netty.client.NettyClient;
import cn.fyupeng.pojo.Message;
import cn.fyupeng.pojo.ServerMessageResult;
import cn.fyupeng.pojo.User;
import cn.fyupeng.proxy.RpcClientProxy;
import cn.fyupeng.serializer.CommonSerializer;
import lombok.extern.slf4j.Slf4j;

import javax.xml.ws.handler.LogicalHandler;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

/**
 * @Auther: fyp
 * @Date: 2023/5/26
 * @Description: 即时聊天客户端
 * @Package: cn.fyupeng
 * @Version: 1.0
 */
@Slf4j
public class ChatClient {
    private static RoundRobinLoadBalancer randomLoadBalancer = new RoundRobinLoadBalancer();
    private static NettyClient nettyClient = new NettyClient(randomLoadBalancer, CommonSerializer.HESSIAN_SERIALIZER);
    private static RpcClientProxy rpcClientProxy = new RpcClientProxy(nettyClient);
    @Reference(name = "simpleChatService", group = "1.0.0",timeout = 5000, asyncTime = 6000)
    private static ChatService service = rpcClientProxy.getProxy(ChatService.class, ChatClient.class);



    public static void main(String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);
        System.out.println("欢迎来到基于 Rpc-Netty-Framework -- cn.fyupeng RPC框架 实现的即时在线远程通讯聊天室");
        System.out.println("您的聊天室 id：");
        String userId = sc.next();
        System.out.println("您的聊天室 名字：");
        String userName = sc.next();

        User user = new User();
        user.setUserId(userId);
        user.setUserName(userName);

        new Thread(() -> {
            while (true) {
                ServerMessageResult msg = null;
                try {
                    msg = service.receive(user);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message data = (Message) msg.getData();
                if  (200 == msg.getStatus() && data != null) {
                    System.out.println("\n用户 [ " + data.getUserName() + "] 发送消息: ");
                    System.out.println("===============");
                    System.out.println("消息内容：" + data.getMessage());
                    System.out.println("发送时间： " + data.getCreateTime());
                    System.out.println("===============");
                } else if (500 == msg.getStatus()) {
                    System.out.println("服务器返回消息：");
                    System.out.println("===============");
                    System.out.println("状态码：" + msg.getStatus());
                    System.out.println("消息内容：" + msg.getMsg());
                    System.out.println("===============");
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        while (true) {
            System.out.print("发送消息：");
            String msg = sc.next();
            service.send(user, msg);
        }



    }
}
