package service;

/**
 * RPC服务端具体实现业务类
 */
public class RpcServiceImpl implements RpcService {
    @Override
    public String sayHello(String name) {
        return "This is akka RPC service.\nHello " + name;
    }
}