package com.olx.grpc.service.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: log.proto")
public final class LogingEventGrpc {

  private LogingEventGrpc() {}

  public static final String SERVICE_NAME = "LogingEvent";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.olx.grpc.service.grpc.Log.Event,
      com.olx.grpc.service.grpc.Log.Empty> getLogEventMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LogEvent",
      requestType = com.olx.grpc.service.grpc.Log.Event.class,
      responseType = com.olx.grpc.service.grpc.Log.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.olx.grpc.service.grpc.Log.Event,
      com.olx.grpc.service.grpc.Log.Empty> getLogEventMethod() {
    io.grpc.MethodDescriptor<com.olx.grpc.service.grpc.Log.Event, com.olx.grpc.service.grpc.Log.Empty> getLogEventMethod;
    if ((getLogEventMethod = LogingEventGrpc.getLogEventMethod) == null) {
      synchronized (LogingEventGrpc.class) {
        if ((getLogEventMethod = LogingEventGrpc.getLogEventMethod) == null) {
          LogingEventGrpc.getLogEventMethod = getLogEventMethod = 
              io.grpc.MethodDescriptor.<com.olx.grpc.service.grpc.Log.Event, com.olx.grpc.service.grpc.Log.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "LogingEvent", "LogEvent"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.olx.grpc.service.grpc.Log.Event.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.olx.grpc.service.grpc.Log.Empty.getDefaultInstance()))
                  .setSchemaDescriptor(new LogingEventMethodDescriptorSupplier("LogEvent"))
                  .build();
          }
        }
     }
     return getLogEventMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static LogingEventStub newStub(io.grpc.Channel channel) {
    return new LogingEventStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static LogingEventBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new LogingEventBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static LogingEventFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new LogingEventFutureStub(channel);
  }

  /**
   */
  public static abstract class LogingEventImplBase implements io.grpc.BindableService {

    /**
     */
    public void logEvent(com.olx.grpc.service.grpc.Log.Event request,
        io.grpc.stub.StreamObserver<com.olx.grpc.service.grpc.Log.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getLogEventMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLogEventMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.olx.grpc.service.grpc.Log.Event,
                com.olx.grpc.service.grpc.Log.Empty>(
                  this, METHODID_LOG_EVENT)))
          .build();
    }
  }

  /**
   */
  public static final class LogingEventStub extends io.grpc.stub.AbstractStub<LogingEventStub> {
    private LogingEventStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LogingEventStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LogingEventStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LogingEventStub(channel, callOptions);
    }

    /**
     */
    public void logEvent(com.olx.grpc.service.grpc.Log.Event request,
        io.grpc.stub.StreamObserver<com.olx.grpc.service.grpc.Log.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLogEventMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class LogingEventBlockingStub extends io.grpc.stub.AbstractStub<LogingEventBlockingStub> {
    private LogingEventBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LogingEventBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LogingEventBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LogingEventBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.olx.grpc.service.grpc.Log.Empty logEvent(com.olx.grpc.service.grpc.Log.Event request) {
      return blockingUnaryCall(
          getChannel(), getLogEventMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class LogingEventFutureStub extends io.grpc.stub.AbstractStub<LogingEventFutureStub> {
    private LogingEventFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LogingEventFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LogingEventFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LogingEventFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.olx.grpc.service.grpc.Log.Empty> logEvent(
        com.olx.grpc.service.grpc.Log.Event request) {
      return futureUnaryCall(
          getChannel().newCall(getLogEventMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LOG_EVENT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final LogingEventImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(LogingEventImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LOG_EVENT:
          serviceImpl.logEvent((com.olx.grpc.service.grpc.Log.Event) request,
              (io.grpc.stub.StreamObserver<com.olx.grpc.service.grpc.Log.Empty>) responseObserver);
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

  private static abstract class LogingEventBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    LogingEventBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.olx.grpc.service.grpc.Log.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("LogingEvent");
    }
  }

  private static final class LogingEventFileDescriptorSupplier
      extends LogingEventBaseDescriptorSupplier {
    LogingEventFileDescriptorSupplier() {}
  }

  private static final class LogingEventMethodDescriptorSupplier
      extends LogingEventBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    LogingEventMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (LogingEventGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new LogingEventFileDescriptorSupplier())
              .addMethod(getLogEventMethod())
              .build();
        }
      }
    }
    return result;
  }
}
