package com.lojc.jchat.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/*
 *@Auther Lojc
 *@Date 2023/7/27
 */

/*
*处理Websocket传输的数据Frame
* TextWebSocketFrame：在netty中,这个是用于为websocket专门处理文本的对象,frame是消息的载体
* */
public class ChatHandler extends  SimpleChannelInboundHandler<TextWebSocketFrame>{

    //用于记录和管理所有的客户端连接的channelgroup
    private static ChannelGroup clients=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //获取客户端传输过来的消息
        String content = msg.text();
        System.out.println("接收到的数据: "+content);
        for (Channel channel:clients){
            channel.writeAndFlush(new TextWebSocketFrame("[服务器接在: ]"+
                    LocalDateTime.now()+" 接到到消息,消息为为: "+content));
        }

        //clients.writeAndFlush("");这个和上面的循环是一致的

    }

    /*当客户端连接服务端后,handler会自动添加到这个channel组里面*/
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        clients.add(ctx.channel());

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //当触发handlerRemoved,channelgroup会自动移除对应客户端的channel
        //clients.remove(ctx.channel());   这句就多余了
//        System.out.println("客户端断开,channel长ID： "+ctx.channel().id().asLongText());
        System.out.println("客户端断开,channel长ID： "+ctx.channel().id().asShortText());

    }
}
