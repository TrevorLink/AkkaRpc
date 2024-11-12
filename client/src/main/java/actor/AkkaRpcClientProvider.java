package actor;

import java.lang.reflect.Proxy;

/**
 * RPC客户端实例化
 * @param <T> 代理的接口类
 */
public class AkkaRpcClientProvider<T> {

   private String address;

   private Class<T> interfaceClass;

   public void setInterfaceClass(Class<T> interfaceClass) {
      this.interfaceClass = interfaceClass;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   /**
    * 获取 RPC 服务端存根代理后的Service
    * @return 代理后的服务端存根
    */
   @SuppressWarnings("unchecked")
   public T get() {
      AkkaRpcClient client = new AkkaRpcClient();
      try {
         client.connect(this.address);
      } catch (Exception e) {
         e.printStackTrace();
      }
      AkkaRpcInvocationHandler handler = new AkkaRpcInvocationHandler(client);
      //代理 handler 封装方法调用为 RPC 请求
      return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, handler);

   }

}
