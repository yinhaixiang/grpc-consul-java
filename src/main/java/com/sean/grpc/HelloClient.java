package com.sean.grpc;

import example.GrpcActionGrpc;
import example.Hello;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Construct client connecting to HelloWorld server at {@code host:port}.
 */
public class HelloClient {
    private static final Logger logger = Logger.getLogger(HelloClient.class.getName());

    private final ManagedChannel channel;
    private final GrpcActionGrpc.GrpcActionBlockingStub blockingStub;

    /**
     * Construct client connecting to HelloWorld server at {@code host:port}.
     */
    public HelloClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                // needing certificates.
                .usePlaintext(true)
                .build());
    }

    /**
     * Construct client for accessing HelloWorld server using the existing channel.
     */
    HelloClient(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = GrpcActionGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     * Say hello to server.
     */
    public void SayHello() throws UnsupportedEncodingException {
        String str = "你好222se22an你好22";
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
        HelloClient helloClient = new HelloClient("localhost", 8188);
        helloClient.SayHello();
        helloClient.shutdown();
    }
}