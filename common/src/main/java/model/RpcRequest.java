package model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * RPC请求报文
 */
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = 4932007273709224551L;
    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 参数列表
     */
    private Object[] parameters;

    /**
     * 参数类型
     */
    private Class<?>[] parameterTypes;


    public String getMethodName() {
        return methodName;
    }

    public RpcRequest setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public RpcRequest setParameters(Object[] parameters) {
        this.parameters = parameters;
        return this;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public RpcRequest setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
        return this;
    }

    @Override
    public String toString() {
        return "RpcRequest{" +
                "methodName='" + methodName + '\'' +
                ", parameters=" + Arrays.toString(parameters) +
                ", parameterTypes=" + Arrays.toString(parameterTypes) +
                '}';
    }
}