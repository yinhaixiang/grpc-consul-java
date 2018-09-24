package com.sean.grpc;

import com.google.common.net.HostAndPort;
import com.orbitz.consul.Consul;
import com.orbitz.consul.HealthClient;
import com.orbitz.consul.model.health.Service;
import com.orbitz.consul.model.health.ServiceHealth;
import example.GrpcActionGrpc;
import example.Hello;
import io.grpc.ManagedChannel;
import io.grpc.netty.NettyChannelBuilder;

import java.util.List;
import java.util.Random;

public class HelloClient5 {

    public static void main(String[] args) throws InterruptedException {
        // 配置和入参
        String consulHostAndPort = "127.0.0.1:8500";
        String serviceName = "search-service";
        String name = "你好aa22你好";


        // 框架应该做好的
        ManagedChannel managedChannel;
        Hello.HelloRequest request = Hello.HelloRequest.newBuilder().setName(name).build();
        Consul consul = Consul.builder().withHostAndPort(HostAndPort.fromString(consulHostAndPort)).build();
        HealthClient healthClient = consul.healthClient();
        Service currentService = getCurrentServiceByServiceName(serviceName, healthClient);
        String grpcHost = currentService.getAddress();
        int grpcPort = currentService.getPort();
        // 用ManagedChannelBuilder 或者 NettyChannelBuilder 都可以
        managedChannel = NettyChannelBuilder.forAddress(grpcHost, grpcPort).usePlaintext(true).build();


        // 业务方真正关心的
        // 获取RPC方法
        GrpcActionGrpc.GrpcActionBlockingStub blockingStub = GrpcActionGrpc.newBlockingStub(managedChannel);
        // 调用RPC方法
        Hello.HelloReply helloReply = blockingStub.sayHello(request);
        System.out.println(helloReply.getMessage());

    }

    public static Service getCurrentServiceByServiceName(String serviceName, HealthClient healthClient) {
        List<ServiceHealth> nodes = healthClient.getHealthyServiceInstances(serviceName).getResponse();
        if (nodes.size() == 0) {
            return null;
        }
        ServiceHealth sh = nodes.get(new Random().nextInt(nodes.size()));
        return sh.getService();
    }
}