package com.sean.grpc;

import com.google.common.net.HostAndPort;
import com.orbitz.consul.Consul;
import com.orbitz.consul.HealthClient;
import com.orbitz.consul.model.health.Service;
import com.orbitz.consul.model.health.ServiceHealth;
import com.promotion.ComputeGrpc;
import com.promotion.Promotion;
import example.GrpcActionGrpc;
import example.Hello;
import io.grpc.ManagedChannel;
import io.grpc.netty.NettyChannelBuilder;

import java.util.List;
import java.util.Random;

public class HelloClient6 {

    public static void main(String[] args) throws InterruptedException {
        // 配置和入参
        String consulHostAndPort = "172.16.9.144:8500";
        String serviceName = "promotion-service";
        String message = "java你好aa22你好22";


        // 框架应该做好的, 不需要业务方关心的
        ManagedChannel managedChannel;
        Consul consul = Consul.builder().withHostAndPort(HostAndPort.fromString(consulHostAndPort)).build();
        HealthClient healthClient = consul.healthClient();
        Service currentService = getCurrentServiceByServiceName(serviceName, healthClient);
        String grpcHost = currentService.getAddress();
        int grpcPort = currentService.getPort();
        // 用ManagedChannelBuilder 或者 NettyChannelBuilder 都可以
        managedChannel = NettyChannelBuilder.forAddress(grpcHost, grpcPort).usePlaintext(true).build();


        // 业务方真正关心的
        // 设置请求参数
        Promotion.PromotionRequest promotionRequest = Promotion.PromotionRequest.newBuilder().setMessage(message).build();
        // 获取RPC方法
        ComputeGrpc.ComputeBlockingStub blockingStub = ComputeGrpc.newBlockingStub(managedChannel);
        // 调用RPC方法, 获取请求结果
        Promotion.PromotionResponse promotionResponse = blockingStub.handle(promotionRequest);
        System.out.println(promotionResponse.getMessage());

    }

    public static Service getCurrentServiceByServiceName(String serviceName, HealthClient healthClient) {
        List<ServiceHealth> nodes = healthClient.getHealthyServiceInstances(serviceName).getResponse();
        System.out.println("health services size: " + nodes.size());
        if (nodes.size() == 0) {
            return null;
        }
        ServiceHealth sh = nodes.get(new Random().nextInt(nodes.size()));
        return sh.getService();
    }
}