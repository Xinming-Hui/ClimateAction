package grpc.generated.climateaction.monitor;

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
 * <pre>
 * Bidirectional. The client reports the carbon dioxide (CO2) concentration continuously. 
 * The server responds continuously with a statistics of the CO2 concentration
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: co2_monitor.proto")
public final class CO2MonitorGrpc {

  private CO2MonitorGrpc() {}

  public static final String SERVICE_NAME = "climateaction.CO2Monitor";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.generated.climateaction.common.CO2Concentration,
      grpc.generated.climateaction.monitor.CO2Stats> getReportCO2Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReportCO2",
      requestType = grpc.generated.climateaction.common.CO2Concentration.class,
      responseType = grpc.generated.climateaction.monitor.CO2Stats.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.generated.climateaction.common.CO2Concentration,
      grpc.generated.climateaction.monitor.CO2Stats> getReportCO2Method() {
    io.grpc.MethodDescriptor<grpc.generated.climateaction.common.CO2Concentration, grpc.generated.climateaction.monitor.CO2Stats> getReportCO2Method;
    if ((getReportCO2Method = CO2MonitorGrpc.getReportCO2Method) == null) {
      synchronized (CO2MonitorGrpc.class) {
        if ((getReportCO2Method = CO2MonitorGrpc.getReportCO2Method) == null) {
          CO2MonitorGrpc.getReportCO2Method = getReportCO2Method = 
              io.grpc.MethodDescriptor.<grpc.generated.climateaction.common.CO2Concentration, grpc.generated.climateaction.monitor.CO2Stats>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "climateaction.CO2Monitor", "ReportCO2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.generated.climateaction.common.CO2Concentration.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.generated.climateaction.monitor.CO2Stats.getDefaultInstance()))
                  .setSchemaDescriptor(new CO2MonitorMethodDescriptorSupplier("ReportCO2"))
                  .build();
          }
        }
     }
     return getReportCO2Method;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CO2MonitorStub newStub(io.grpc.Channel channel) {
    return new CO2MonitorStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CO2MonitorBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new CO2MonitorBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CO2MonitorFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new CO2MonitorFutureStub(channel);
  }

  /**
   * <pre>
   * Bidirectional. The client reports the carbon dioxide (CO2) concentration continuously. 
   * The server responds continuously with a statistics of the CO2 concentration
   * </pre>
   */
  public static abstract class CO2MonitorImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<grpc.generated.climateaction.common.CO2Concentration> reportCO2(
        io.grpc.stub.StreamObserver<grpc.generated.climateaction.monitor.CO2Stats> responseObserver) {
      return asyncUnimplementedStreamingCall(getReportCO2Method(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getReportCO2Method(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                grpc.generated.climateaction.common.CO2Concentration,
                grpc.generated.climateaction.monitor.CO2Stats>(
                  this, METHODID_REPORT_CO2)))
          .build();
    }
  }

  /**
   * <pre>
   * Bidirectional. The client reports the carbon dioxide (CO2) concentration continuously. 
   * The server responds continuously with a statistics of the CO2 concentration
   * </pre>
   */
  public static final class CO2MonitorStub extends io.grpc.stub.AbstractStub<CO2MonitorStub> {
    private CO2MonitorStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CO2MonitorStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CO2MonitorStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CO2MonitorStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<grpc.generated.climateaction.common.CO2Concentration> reportCO2(
        io.grpc.stub.StreamObserver<grpc.generated.climateaction.monitor.CO2Stats> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getReportCO2Method(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   * Bidirectional. The client reports the carbon dioxide (CO2) concentration continuously. 
   * The server responds continuously with a statistics of the CO2 concentration
   * </pre>
   */
  public static final class CO2MonitorBlockingStub extends io.grpc.stub.AbstractStub<CO2MonitorBlockingStub> {
    private CO2MonitorBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CO2MonitorBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CO2MonitorBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CO2MonitorBlockingStub(channel, callOptions);
    }
  }

  /**
   * <pre>
   * Bidirectional. The client reports the carbon dioxide (CO2) concentration continuously. 
   * The server responds continuously with a statistics of the CO2 concentration
   * </pre>
   */
  public static final class CO2MonitorFutureStub extends io.grpc.stub.AbstractStub<CO2MonitorFutureStub> {
    private CO2MonitorFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CO2MonitorFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CO2MonitorFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CO2MonitorFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_REPORT_CO2 = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CO2MonitorImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CO2MonitorImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REPORT_CO2:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.reportCO2(
              (io.grpc.stub.StreamObserver<grpc.generated.climateaction.monitor.CO2Stats>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class CO2MonitorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CO2MonitorBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.generated.climateaction.monitor.CO2MonitorServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CO2Monitor");
    }
  }

  private static final class CO2MonitorFileDescriptorSupplier
      extends CO2MonitorBaseDescriptorSupplier {
    CO2MonitorFileDescriptorSupplier() {}
  }

  private static final class CO2MonitorMethodDescriptorSupplier
      extends CO2MonitorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CO2MonitorMethodDescriptorSupplier(String methodName) {
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
      synchronized (CO2MonitorGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CO2MonitorFileDescriptorSupplier())
              .addMethod(getReportCO2Method())
              .build();
        }
      }
    }
    return result;
  }
}
