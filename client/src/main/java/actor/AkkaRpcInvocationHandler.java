package actor;

import model.RpcRequest;
import model.RpcResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 创建用户RPC服务的动态代理处理器
 */
public class AkkaRpcInvocationHandler implements InvocationHandler {

   private final AkkaRpcClient client;

   public AkkaRpcInvocationHandler(AkkaRpcClient client) {
      this.client = client;
   }

   @Override
   public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      // 构建请求对象
      RpcRequest rpcRequest = new RpcRequest();
      rpcRequest.setMethodName(method.getName())
              .setParameterTypes(method.getParameterTypes())
              .setParameters(args);
      // 使用客户端发送请求
      RpcResponse response = (RpcResponse) client.ask(rpcRequest);

      // 响应成功返回结果
      if (RpcResponse.SUCCESS.equals(response.getStatus())) {
         return response.getData();
      }
      throw new RuntimeException(response.getMessage());
   }
}
