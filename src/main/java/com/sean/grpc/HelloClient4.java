package com.sean.grpc;

import com.google.common.net.HostAndPort;
import com.orbitz.consul.Consul;
import com.orbitz.consul.HealthClient;
import com.orbitz.consul.model.health.ServiceHealth;
import example.GrpcActionGrpc;
import example.Hello;
import io.grpc.ManagedChannel;
import io.grpc.netty.NettyChannelBuilder;

import java.util.List;

public class HelloClient4 {

    public static void main(String[] args) throws InterruptedException {
        String consulHostAndPort = "127.0.0.1:8500";
        String grpcHost = "127.0.0.1";
        int grpcPort = 8188;
        String serviceName = "search-service";
        String name = "你好aa22你好";


        ManagedChannel managedChannel;
        GrpcActionGrpc.GrpcActionBlockingStub blockingStub;
        Hello.HelloRequest request = Hello.HelloRequest.newBuilder().setName(name).build();
        Consul consul = Consul.builder().withHostAndPort(HostAndPort.fromString(consulHostAndPort)).build();
        HealthClient healthClient = consul.healthClient();
        List<ServiceHealth> nodes = healthClient.getHealthyServiceInstances(serviceName).getResponse();
        System.out.println("健康的服务节点数: " + nodes.size());

        managedChannel = NettyChannelBuilder.forAddress(grpcHost, grpcPort).usePlaintext(true).build();
        blockingStub = GrpcActionGrpc.newBlockingStub(managedChannel);

        try {
            Hello.HelloReply helloReply = blockingStub.sayHello(request);
            System.out.println(helloReply.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}