# kl-onwer
Just to learn Java with more demos

### RocketMQ

#### 1.特点
    | ---优点--- |
        1.高可用，高吞吐，海量消息堆积，低延迟，性能表现出色
        2.支持顺序消息
        3.支持实物消息
        4.支持消息过滤
        5.支持重复消费
        6.支持延迟消息
        7.支持消息跟踪
        8.支持集群、负载均衡
        9.支持重试机制
        10.单机部署，5-10W TPS
     
    | ---缺点--- |
        1.只支持Java
        2.不支持主从自动切换
        
#### 2.组成
      
    | ---组成部分--- |
        1.Producer: 消息的生产发送者。如发件者。
        2.Consumer: 消息的接收消费者。如收件者。
        3.Broker: 暂存和传输消息。如快递站。
        4.NameServer: 管理Broker。如快递站管理机构。
        5.Topic: 区分消息种类。一个Topic可以对应多个Broker。一个Producer可以发送消息给一个或多个Topic，
        一个Consumer可以接收一个或多个Topic消息。
        6.Message Queue: 相当于Tipic的分区，用于并行发送和接收消息，一个Topic可以对应多个MessageQueue。
        
#### 3.执行流程

    | --- 执行流程 --- |
        1）启动NameServer后，其会Broker、Producer、Consumer连接。
        2）Broker启动后会与NameServer建立长连接，并定时发送心跳包。
        心跳包中包含Broker的元信息（IP+端口等）以及其内部存储的所有Topic信息。
        注册成功后，NameServer中就存在了Broker与Topic的映射关系。
        3）收发消息前，需要先创建Topic并指定其存在在哪个Broker上，也可以在发送消息时自动创建Topic。
        4）Producer启动时，先与NameServer建立长连接。
        当发送消息时，会从NameServer获取当前发送的Topic存储在哪些Broker上，
        通过轮询方式从队列列表中选择一个队列，然后与队列所在的Broker建立长连接，从而完成消息发送。
        5）Consumer启动时，先与NameServer建立长连接，获取被订阅的Topic存在于哪些Broker上，
        接着与Broker建立连接通道，进行消息消费。
       
         
