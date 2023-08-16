package com.lojc.jchat.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

/*
 *@Auther Lojc
 *@Date 2023/7/27
 */
@Component
public class WSServer {

    //单例获取

    private static final WSServer instance=new WSServer();


    public static WSServer getInstance(){
        return instance;
    }

    private NioEventLoopGroup boss;
    private NioEventLoopGroup worker;
    private ServerBootstrap server;
    private ChannelFuture future;


    public WSServer(){
        boss=new NioEventLoopGroup();
        worker=new NioEventLoopGroup();
        server=new ServerBootstrap();
        server.group(boss,worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WSServerInitialzer());
    }


    public void start(){
        //不需要.sync,因为不是在main线程里,main线程执行完会结束,springboot不会,不需要阻塞
        this.future=server.bind(8088);
        System.err.println("netty websocket server has started!!!!");

    }



    //不需要关闭了,依托给了Springboot来管理
     /*  finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();*/

}
