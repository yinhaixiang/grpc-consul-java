package com.sean.grpc;

import example.GrpcActionGrpc;
import example.Hello;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A simple client that requests a greeting from the {@link HelloWorldServer}.
 */
public class Client {
    private static final Logger logger = Logger.getLogger(Client.class.getName());

    private final ManagedChannel channel;
    private final GrpcActionGrpc.GrpcActionBlockingStub blockingStub;

    public Client(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext(true)
                .build();
        blockingStub = GrpcActionGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     * Say hello to server.
     */
    public void SayHello() throws UnsupportedEncodingException {
        String str = "你好222se22an你好";
        Hello.HelloRequest request = Hello.HelloRequest.newBuilder().setName(str).build();
        Hello.HelloReply response = null;
        response = blockingStub.sayHello(request);
        logger.info("python处理过后的参数: " + response.getMessage());
    }

    /**
     * Greet server. If provided, the first element of {@code args} is the name to use in the
     * greeting.
     */
    public static void main(String[] args) throws Exception {
        Client client = new Client("localhost", 8188);
        client.SayHello();
        client.shutdown();
    }
}