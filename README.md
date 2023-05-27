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

#### 效果

服务端
```ruby
广播消息 => 用户id：[ lettce ] 消息： [ Message{message='hello', userName='茉莉托', createTime=Sat May 27 19:50:00 CST 2023} ]
返回消息 => 用户：[ 利特 ] 消息： [ Message{message='hello', userName='茉莉托', createTime=Sat May 27 19:50:00 CST 2023} ]
广播消息 => 用户id：[ motto ] 消息： [ Message{message='你也好啊', userName='利特', createTime=Sat May 27 19:50:23 CST 2023} ]
返回消息 => 用户：[ 茉莉托 ] 消息： [ Message{message='你也好啊', userName='利特', createTime=Sat May 27 19:50:23 CST 2023} ]
广播消息 => 用户id：[ lettce ] 消息： [ Message{message='哪里好了', userName='茉莉托', createTime=Sat May 27 19:51:52 CST 2023} ]
返回消息 => 用户：[ 利特 ] 消息： [ Message{message='哪里好了', userName='茉莉托', createTime=Sat May 27 19:51:52 CST 2023} ]
```

客户端1
```ruby
欢迎来到基于 Rpc-Netty-Framework -- cn.fyupeng RPC框架 实现的即时在线远程通讯聊天室
您的聊天室 id：
motto
您的聊天室 名字：
茉莉托
发送消息：hello

服务器返回消息：
===============
状态码：500
消息内容：服务器未接收到该用户注册信息，已为您完成注册。
===============

用户 [ 利特] 发送消息: 
===============
消息内容：你也好啊
发送时间： Sat May 27 19:50:23 CST 2023
===============

发送消息：
```

客户端2：
```ruby
欢迎来到基于 Rpc-Netty-Framework -- cn.fyupeng RPC框架 实现的即时在线远程通讯聊天室
您的聊天室 id：
lettce
您的聊天室 名字：
利特

服务器返回消息：
===============
状态码：500
消息内容：服务器未接收到该用户注册信息，已为您完成注册。
===============

用户 [ 茉莉托] 发送消息: 
===============
消息内容：哪里好了
发送时间： Sat May 27 19:51:52 CST 2023
===============

发送消息：
```