### 基于 RPC-Netty-Framework 远程调用服务框架的在线即时通讯聊天室v1.0

#### 技术栈
- `RPC-Netty-Framework`：基于 `Netty` 的 `RPC` 框架；
- `Redis`：支持 `RNF` 的缓存数据库；
- `Nacos`：支持 `RNF` 的服务注册中心；
- `JDK`：`Java` 开发工具；

#### 实现思路

- 使用基于 RNF 的远程服务调用框架，用于支持远程通;
- ConcurrentHashMap + ArrayBlockingQueue 实现的用于广播的消息队列；
- 服务端一对多被动处理客户端接收和发送事件；
- 客户端主动向服务端发起接收和发送事件；