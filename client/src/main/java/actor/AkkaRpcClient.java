package actor;

import akka.actor.*;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import scala.reflect.ClassTag$;
import utils.AkkaUtil;
import utils.ScalaFutureUtil;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

/**
 * 创建ActorSystem并获取远程ActorRef
 */
public class AkkaRpcClient {

    private ActorRef actorRef;

    public void connect(String address) throws ExecutionException, InterruptedException {
        ActorSystem localActorSystem = AkkaUtil.createRemoteActorSystem("rpcClientSystem", 10087);
        ActorSelection actorSel = localActorSystem.actorSelection(address);
        Timeout timeout = new Timeout(Duration.create(2, "seconds"));
        final Future<ActorIdentity> identityFuture = Patterns.ask(actorSel, new Identify(42), timeout).mapTo(ClassTag$.MODULE$.apply(ActorIdentity.class));
        final CompletableFuture<ActorIdentity> identifyFuture = ScalaFutureUtil.toJava(identityFuture);
        final CompletableFuture<ActorRef> actorRefFuture = identifyFuture.thenApply(
                (ActorIdentity ai) -> {
                    if (ai.getRef() == null) {
                        throw new CompletionException(new RuntimeException("Could not connect to rpc endpoint under address " + address + '.'));
                    } else {
                        return ai.getRef();
                    }
                }
        );
        this.actorRef = actorRefFuture.get();
    }

    public Object ask(Object message) throws ExecutionException, InterruptedException {
        Timeout timeout = new Timeout(Duration.create(2, "seconds"));
        CompletableFuture<Object> resultFuture = ScalaFutureUtil.toJava(Patterns.ask(this.actorRef, message, timeout));
        return resultFuture.get();
    }

}