import actor.AkkaRpcClientProvider;
import service.RpcService;

/**
 * RPC服务端
 */
public class AkkaRpcClientApplication {
   public static void main(String[] args) {
      AkkaRpcClientProvider<RpcService> clientProvider = new AkkaRpcClientProvider<>();
      clientProvider.setAddress("akka.tcp://rpcSys@0.0.0.0:10086/user/akkaRpcServer");
      clientProvider.setInterfaceClass(RpcService.class);
      RpcService rpcService = clientProvider.get();
      String result = rpcService.sayHello("akka");
      System.out.println(result);
   }
}
