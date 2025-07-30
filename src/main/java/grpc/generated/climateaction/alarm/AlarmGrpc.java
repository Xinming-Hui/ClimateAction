package grpc.generated.climateaction.alarm;

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
    comments = "Source: alarm.proto")
public final class AlarmGrpc {

  private AlarmGrpc() {}

  public static final String SERVICE_NAME = "climateaction.Alarm";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.generated.climateaction.common.CO2Concentration,
      grpc.generated.climateaction.common.ConfirmationMessage> getAlarmMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Alarm",
      requestType = grpc.generated.climateaction.common.CO2Concentration.class,
      responseType = grpc.generated.climateaction.common.ConfirmationMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.generated.climateaction.common.CO2Concentration,
      grpc.generated.climateaction.common.ConfirmationMessage> getAlarmMethod() {
    io.grpc.MethodDescriptor<grpc.generated.climateaction.common.CO2Concentration, grpc.generated.climateaction.common.ConfirmationMessage> getAlarmMethod;
    if ((getAlarmMethod = AlarmGrpc.getAlarmMethod) == null) {
      synchronized (AlarmGrpc.class) {
        if ((getAlarmMethod = AlarmGrpc.getAlarmMethod) == null) {
          AlarmGrpc.getAlarmMethod = getAlarmMethod = 
              io.grpc.MethodDescriptor.<grpc.generated.climateaction.common.CO2Concentration, grpc.generated.climateaction.common.ConfirmationMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "climateaction.Alarm", "Alarm"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.generated.climateaction.common.CO2Concentration.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.generated.climateaction.common.ConfirmationMessage.getDefaultInstance()))
                  .setSchemaDescriptor(new AlarmMethodDescriptorSupplier("Alarm"))
                  .build();
          }
        }
     }
     return getAlarmMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AlarmStub newStub(io.grpc.Channel channel) {
    return new AlarmStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AlarmBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new AlarmBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AlarmFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new AlarmFutureStub(channel);
  }

  /**
   */
  public static abstract class AlarmImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Unary - when the sensor detects abnormal climate values, it immediately alerts the server and waits for response confirmation
     * Server confirms 
     * </pre>
     */
    public void alarm(grpc.generated.climateaction.common.CO2Concentration request,
        io.grpc.stub.StreamObserver<grpc.generated.climateaction.common.ConfirmationMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getAlarmMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAlarmMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.generated.climateaction.common.CO2Concentration,
                grpc.generated.climateaction.common.ConfirmationMessage>(
                  this, METHODID_ALARM)))
          .build();
    }
  }

  /**
   */
  public static final class AlarmStub extends io.grpc.stub.AbstractStub<AlarmStub> {
    private AlarmStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AlarmStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AlarmStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AlarmStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary - when the sensor detects abnormal climate values, it immediately alerts the server and waits for response confirmation
     * Server confirms 
     * </pre>
     */
    public void alarm(grpc.generated.climateaction.common.CO2Concentration request,
        io.grpc.stub.StreamObserver<grpc.generated.climateaction.common.ConfirmationMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAlarmMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AlarmBlockingStub extends io.grpc.stub.AbstractStub<AlarmBlockingStub> {
    private AlarmBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AlarmBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AlarmBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AlarmBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary - when the sensor detects abnormal climate values, it immediately alerts the server and waits for response confirmation
     * Server confirms 
     * </pre>
     */
    public grpc.generated.climateaction.common.ConfirmationMessage alarm(grpc.generated.climateaction.common.CO2Concentration request) {
      return blockingUnaryCall(
          getChannel(), getAlarmMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AlarmFutureStub extends io.grpc.stub.AbstractStub<AlarmFutureStub> {
    private AlarmFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AlarmFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AlarmFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AlarmFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary - when the sensor detects abnormal climate values, it immediately alerts the server and waits for response confirmation
     * Server confirms 
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.generated.climateaction.common.ConfirmationMessage> alarm(
        grpc.generated.climateaction.common.CO2Concentration request) {
      return futureUnaryCall(
          getChannel().newCall(getAlarmMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ALARM = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AlarmImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AlarmImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ALARM:
          serviceImpl.alarm((grpc.generated.climateaction.common.CO2Concentration) request,
              (io.grpc.stub.StreamObserver<grpc.generated.climateaction.common.ConfirmationMessage>) responseObserver);
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

  private static abstract class AlarmBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AlarmBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.generated.climateaction.alarm.AlarmServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Alarm");
    }
  }

  private static final class AlarmFileDescriptorSupplier
      extends AlarmBaseDescriptorSupplier {
    AlarmFileDescriptorSupplier() {}
  }

  private static final class AlarmMethodDescriptorSupplier
      extends AlarmBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AlarmMethodDescriptorSupplier(String methodName) {
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
      synchronized (AlarmGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AlarmFileDescriptorSupplier())
              .addMethod(getAlarmMethod())
              .build();
        }
      }
    }
    return result;
  }
}
