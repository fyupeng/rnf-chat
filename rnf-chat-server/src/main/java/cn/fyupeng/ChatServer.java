package cn.fyupeng;

import cn.fyupeng.annotation.ServiceScan;
import cn.fyupeng.enums.SerializerCode;
import cn.fyupeng.exception.RpcException;
import cn.fyupeng.net.netty.server.NettyServer;
import cn.fyupeng.serializer.CommonSerializer;

/**
 * @Auther: fyp
 * @Date: 2023/5/26
 * @Description: 即时聊天服务端
 * @Package: cn.fyupeng
 * @Version: 1.0
 */
@ServiceScan
public class ChatServer {


    public static void main(String[] args) {
        NettyServer nettyServer = null;
        try {
            nettyServer = new NettyServer("169.254.234.184", 8080, SerializerCode.HESSIAN.getCode());
        } catch (RpcException e) {
            e.printStackTrace();
        }
        nettyServer.start();
    }
}
