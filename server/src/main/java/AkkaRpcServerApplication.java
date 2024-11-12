import actor.AkkaRpcServerProvider;
import akka.actor.ActorRef;
import service.RpcService;
import service.RpcServiceImpl;


public class AkkaRpcServerApplication {
   public static void main(String[] args) {
      RpcServiceImpl demoService = new RpcServiceImpl();
      AkkaRpcServerProvider<RpcService> provider = new AkkaRpcServerProvider<>();
      provider.setPort(10086);
      provider.setName("akkaRpcServer");
      provider.setServiceImpl(demoService);
      provider.setInterfaceClass(RpcService.class);
      ActorRef actorRef = provider.get();
      System.out.println(actorRef.path());
   }
}
