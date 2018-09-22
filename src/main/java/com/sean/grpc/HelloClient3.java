package com.sean.grpc;


import com.google.common.net.HostAndPort;
import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.HealthClient;
import com.orbitz.consul.model.agent.ImmutableRegistration;
import com.orbitz.consul.model.agent.Registration;
import com.orbitz.consul.model.health.ServiceHealth;
import example.GrpcActionGrpc;
import example.Hello;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Construct client connecting to HelloWorld server at {@code host:port}.
 */
public class HelloClient3 {
    private static final Logger logger = Logger.getLogger(HelloClient3.class.getName());

    private final ManagedChannel channel;
    private final GrpcActionGrpc.GrpcActionBlockingStub blockingStub;

    /**
     * Construct client connecting to HelloWorld server at {@code host:port}.
     */
    public HelloClient3(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                // needing certificates.
                .usePlaintext(true)
                .build());
    }

    /**
     * Construct client for accessing HelloWorld server using the existing channel.
     */
    HelloClient3(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = GrpcActionGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     * Say hello to server.
     */
    public void SayHello(String str) {
        Hello.HelloRequest request = Hello.HelloRequest.newBuilder().setName(str).build();
        Hello.HelloReply response = null;
        try {
            response = blockingStub.sayHello(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
        logger.info("python处理过后的参数: " + response.getMessage());
    }


    /**
     * Greet server. If provided, the first element of {@code args} is the name to use in the
     * greeting.
     */
    public static void main(String[] args) throws Exception {

        Consul client = Consul.builder().build();
        HealthClient healthClient = client.healthClient();

        while (true) {
            List<ServiceHealth> nodes = healthClient.getHealthyServiceInstances("search-service").getResponse();
            System.out.println(nodes.size());
            TimeUnit.SECONDS.sleep(3);
        }

    }
}