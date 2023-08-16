package com.lojc.jchat;

import com.lojc.jchat.netty.WSServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/*
 *@Auther Lojc
 *@Date 2023/8/2
 */
@Component
public class nettyStarter implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {//ContextRefreshedEvent 字面理解即是容器刷新（启动或说初始化）后触发事件
        if (event.getApplicationContext().getParent()==null){
            System.err.println("Springboot has Initialized,netty is starting");
            try {
                WSServer.getInstance().start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
