package model;

import java.io.Serializable;

/**
 * RPC响应报文
 */
public class RpcResponse implements Serializable {
    public final static String SUCCESS = "success";
    public final static String FAILED = "failed";
    private static final long serialVersionUID = 6595683424889346485L;

    /**
     * 响应状态
     */
    private String status = RpcResponse.SUCCESS;
    /**
     * 响应信息，如异常信息
     */
    private String message;

    /**
     * 响应数据，返回值
     */
    private Object data;


    public String getStatus() {
        return status;
    }

    public RpcResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public RpcResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public RpcResponse setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "RpcResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}