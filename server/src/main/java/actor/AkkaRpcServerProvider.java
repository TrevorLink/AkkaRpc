package actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import utils.AkkaUtil;

public class AkkaRpcServerProvider<T> {
   /**
    * 代理的业务实现类
    */
   private T serviceImpl;
   /**
    * 运行的端口
    */
   private int port;
   /**
    * 服务名称
    */
   private String name;
   /**
    * 代理的接口
    */
   private Class<T> interfaceClass;


   public void setServiceImpl(T serviceImpl) {
      this.serviceImpl = serviceImpl;
   }


   public void setPort(int port) {
      this.port = port;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setInterfaceClass(Class<T> interfaceClass) {
      this.interfaceClass = interfaceClass;
   }

   public ActorRef get() {
      ActorSystem system = AkkaUtil.createRemoteActorSystem("rpcSys", port);
      return system.actorOf(Props.create(AkkaRpcServerActor.class, serviceImpl, interfaceClass), name);
   }

}