package com.lojc.jchat.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/*
 *@Auther Lojc
 *@Date 2023/7/27
 */
public class WSServerInitialzer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline=channel.pipeline();
        pipeline.addLast(new HttpServerCodec());
        //对写大的数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());
        /*
        * 在Netty中，HttpObjectAggregator是一个用于HTTP消息聚合的处理器。
        * 它可以将HTTP请求或响应中的多个部分（如HTTP头、HTTP体）聚合成一个完整的消息。
          当使用Netty构建HTTP服务器或客户端时，通常会收到分段的HTTP消息。
        * 例如，一个较大的HTTP请求可能被拆分为多个数据包进行传输。这就需要在接收到这些分段消息后将它们聚合为一个完整的HTTP消息。
        * HttpObjectAggregator的作用是将多个HTTP消息聚合成单个FullHttpRequest或FullHttpResponse对象，
        * 使得后续的处理器能够以完整的形式对HTTP消息进行操作和处理。要使用HttpObjectAggregator，在你的Netty应用程序中，
        * 你需要将其添加到ChannelPipeline中，并指定最大的聚合字节数。这个最大字节数表示当接收到的HTTP消息超过该字节数时，
        * HttpObjectAggregator会生成异常并关闭连接，以保护服务器免受潜在的恶意攻击。
        * */

        //基本上都要使用到这个handler
        pipeline.addLast(new HttpObjectAggregator(1024*64));

//  ============================以上是支持HTTP协议=================================


        /*
        * 当客户端发送一个HTTP请求到服务器时，如果服务器上配置了WebSocketServerProtocolHandler，并且该处理器添加到了ChannelPipeline中，
        * 在接收到请求后，WebSocketServerProtocolHandler会检测该请求是否符合WebSocket握手的要求。
          如果该HTTP请求是一个有效的WebSocket握手请求，WebSocketServerProtocolHandler会自动处理握手过程，并将连接升级为WebSocket协议。
        * 这个握手过程可以看作是简化的三次握手。
          一旦握手成功，服务器和客户端之间的连接就会从HTTP协议切换到WebSocket协议。此时，双方可以使用WebSocket帧进行实时、双向的数据传输。
          通过这种方式，服务器可以在收到HTTP请求后将其转换为WebSocket连接，使得客户端和服务器能够建立持久的、双向通信的连接。
        * 这种方式允许服务器和客户端之间进行实时的数据交互，而无需每次都发起新的HTTP请求。
总结起来，当服务器收到HTTP请求并通过WebSocketServerProtocolHandler进行处理后，可以将连接升级为WebSocket协议，从而实现实时、双向的数据传输。这个过程可以看作是将普通的HTTP请求转换为WebSocket连接的简化三次握手
        * */

        //websocket服务器处理的协议,用于指定客户端访问连接的路由: /ws
        //会帮你处理握手动作,handshaking(close,ping,pong) ping+pong=心跳
        //对于websocket来讲,都是以frame的形式进行传输,不同数据类型对应的frames也不同
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        //自定义handler
        pipeline.addLast(new ChatHandler());



    }
}
