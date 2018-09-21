package example;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 0.14.0)",
    comments = "Source: hello.proto")
public class GrpcActionGrpc {

  private GrpcActionGrpc() {}

  public static final String SERVICE_NAME = "example.GrpcAction";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<example.Hello.HelloRequest,
      example.Hello.HelloReply> METHOD_SAY_HELLO =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "example.GrpcAction", "SayHello"),
          io.grpc.protobuf.ProtoUtils.marshaller(example.Hello.HelloRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(example.Hello.HelloReply.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GrpcActionStub newStub(io.grpc.Channel channel) {
    return new GrpcActionStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GrpcActionBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GrpcActionBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static GrpcActionFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GrpcActionFutureStub(channel);
  }

  /**
   */
  public static interface GrpcAction {

    /**
     */
    public void sayHello(example.Hello.HelloRequest request,
        io.grpc.stub.StreamObserver<example.Hello.HelloReply> responseObserver);
  }

  @io.grpc.ExperimentalApi
  public static abstract class AbstractGrpcAction implements GrpcAction, io.grpc.BindableService {

    @java.lang.Override
    public void sayHello(example.Hello.HelloRequest request,
        io.grpc.stub.StreamObserver<example.Hello.HelloReply> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_SAY_HELLO, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return GrpcActionGrpc.bindService(this);
    }
  }

  /**
   */
  public static interface GrpcActionBlockingClient {

    /**
     */
    public example.Hello.HelloReply sayHello(example.Hello.HelloRequest request);
  }

  /**
   */
  public static interface GrpcActionFutureClient {

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<example.Hello.HelloReply> sayHello(
        example.Hello.HelloRequest request);
  }

  public static class GrpcActionStub extends io.grpc.stub.AbstractStub<GrpcActionStub>
      implements GrpcAction {
    private GrpcActionStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GrpcActionStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcActionStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GrpcActionStub(channel, callOptions);
    }

    @java.lang.Override
    public void sayHello(example.Hello.HelloRequest request,
        io.grpc.stub.StreamObserver<example.Hello.HelloReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_SAY_HELLO, getCallOptions()), request, responseObserver);
    }
  }

  public static class GrpcActionBlockingStub extends io.grpc.stub.AbstractStub<GrpcActionBlockingStub>
      implements GrpcActionBlockingClient {
    private GrpcActionBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GrpcActionBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcActionBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GrpcActionBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public example.Hello.HelloReply sayHello(example.Hello.HelloRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_SAY_HELLO, getCallOptions(), request);
    }
  }

  public static class GrpcActionFutureStub extends io.grpc.stub.AbstractStub<GrpcActionFutureStub>
      implements GrpcActionFutureClient {
    private GrpcActionFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GrpcActionFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcActionFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GrpcActionFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<example.Hello.HelloReply> sayHello(
        example.Hello.HelloRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_SAY_HELLO, getCallOptions()), request);
    }
  }

  private static final int METHODID_SAY_HELLO = 0;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GrpcAction serviceImpl;
    private final int methodId;

    public MethodHandlers(GrpcAction serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAY_HELLO:
          serviceImpl.sayHello((example.Hello.HelloRequest) request,
              (io.grpc.stub.StreamObserver<example.Hello.HelloReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServerServiceDefinition bindService(
      final GrpcAction serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
        .addMethod(
          METHOD_SAY_HELLO,
          asyncUnaryCall(
            new MethodHandlers<
              example.Hello.HelloRequest,
              example.Hello.HelloReply>(
                serviceImpl, METHODID_SAY_HELLO)))
        .build();
  }
}
