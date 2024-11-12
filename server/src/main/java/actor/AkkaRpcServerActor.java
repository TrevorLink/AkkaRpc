package actor;

import akka.actor.UntypedAbstractActor;
import model.RpcRequest;
import model.RpcResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.lang.reflect.Method;

public class AkkaRpcServerActor<T> extends UntypedAbstractActor {
   private static final Logger s_log = LoggerFactory.getLogger(AkkaRpcServerActor.class);
   private final T serviceImpl;
   private final Class<?> interfaceClass;


   public AkkaRpcServerActor(T serviceImpl, Class<?> interfaceClass) {
      this.serviceImpl = serviceImpl;
      this.interfaceClass = interfaceClass;
   }

   @Override
   public void onReceive(Object message) {
      if (message instanceof RpcRequest) {
         RpcRequest request = (RpcRequest) message;
         s_log.info("Received request:{}", request);
         // 处理请求
         RpcResponse response = handleRequest(request);
         // 将结果返回给客户端
         s_log.info("Send response to client.{}", response);
         getSender().tell(response, getSelf());
      } else {
         unhandled(message);
      }
   }

   private RpcResponse handleRequest(RpcRequest request) {
      RpcResponse response = new RpcResponse();
      try {
         s_log.info("The server is handling request.");
         Method method = interfaceClass.getMethod(request.getMethodName(), request.getParameterTypes());
         Object data = method.invoke(serviceImpl, request.getParameters());
         response.setData(data);
      } catch (Exception e) {
         response.setStatus(RpcResponse.FAILED).setMessage(e.getMessage());
      }
      return response;
   }
}