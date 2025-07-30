package grpc.generated.climateaction.ota;

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
 * Server Streaming - Listen for OTA upgrade, the device reports its own firmware version number, and then continuously monitors whether there is a new version that needs to be upgraded.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: ota.proto")
public final class OTAGrpc {

  private OTAGrpc() {}

  public static final String SERVICE_NAME = "climateaction.OTA";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.generated.climateaction.ota.DeviceInfo,
      grpc.generated.climateaction.ota.FirmwareUpgrade> getListenForOTAUpgradeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListenForOTAUpgrade",
      requestType = grpc.generated.climateaction.ota.DeviceInfo.class,
      responseType = grpc.generated.climateaction.ota.FirmwareUpgrade.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.generated.climateaction.ota.DeviceInfo,
      grpc.generated.climateaction.ota.FirmwareUpgrade> getListenForOTAUpgradeMethod() {
    io.grpc.MethodDescriptor<grpc.generated.climateaction.ota.DeviceInfo, grpc.generated.climateaction.ota.FirmwareUpgrade> getListenForOTAUpgradeMethod;
    if ((getListenForOTAUpgradeMethod = OTAGrpc.getListenForOTAUpgradeMethod) == null) {
      synchronized (OTAGrpc.class) {
        if ((getListenForOTAUpgradeMethod = OTAGrpc.getListenForOTAUpgradeMethod) == null) {
          OTAGrpc.getListenForOTAUpgradeMethod = getListenForOTAUpgradeMethod = 
              io.grpc.MethodDescriptor.<grpc.generated.climateaction.ota.DeviceInfo, grpc.generated.climateaction.ota.FirmwareUpgrade>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "climateaction.OTA", "ListenForOTAUpgrade"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.generated.climateaction.ota.DeviceInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.generated.climateaction.ota.FirmwareUpgrade.getDefaultInstance()))
                  .setSchemaDescriptor(new OTAMethodDescriptorSupplier("ListenForOTAUpgrade"))
                  .build();
          }
        }
     }
     return getListenForOTAUpgradeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static OTAStub newStub(io.grpc.Channel channel) {
    return new OTAStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static OTABlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new OTABlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static OTAFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new OTAFutureStub(channel);
  }

  /**
   * <pre>
   * Server Streaming - Listen for OTA upgrade, the device reports its own firmware version number, and then continuously monitors whether there is a new version that needs to be upgraded.
   * </pre>
   */
  public static abstract class OTAImplBase implements io.grpc.BindableService {

    /**
     */
    public void listenForOTAUpgrade(grpc.generated.climateaction.ota.DeviceInfo request,
        io.grpc.stub.StreamObserver<grpc.generated.climateaction.ota.FirmwareUpgrade> responseObserver) {
      asyncUnimplementedUnaryCall(getListenForOTAUpgradeMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getListenForOTAUpgradeMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                grpc.generated.climateaction.ota.DeviceInfo,
                grpc.generated.climateaction.ota.FirmwareUpgrade>(
                  this, METHODID_LISTEN_FOR_OTAUPGRADE)))
          .build();
    }
  }

  /**
   * <pre>
   * Server Streaming - Listen for OTA upgrade, the device reports its own firmware version number, and then continuously monitors whether there is a new version that needs to be upgraded.
   * </pre>
   */
  public static final class OTAStub extends io.grpc.stub.AbstractStub<OTAStub> {
    private OTAStub(io.grpc.Channel channel) {
      super(channel);
    }

    private OTAStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OTAStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new OTAStub(channel, callOptions);
    }

    /**
     */
    public void listenForOTAUpgrade(grpc.generated.climateaction.ota.DeviceInfo request,
        io.grpc.stub.StreamObserver<grpc.generated.climateaction.ota.FirmwareUpgrade> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getListenForOTAUpgradeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Server Streaming - Listen for OTA upgrade, the device reports its own firmware version number, and then continuously monitors whether there is a new version that needs to be upgraded.
   * </pre>
   */
  public static final class OTABlockingStub extends io.grpc.stub.AbstractStub<OTABlockingStub> {
    private OTABlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private OTABlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OTABlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new OTABlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<grpc.generated.climateaction.ota.FirmwareUpgrade> listenForOTAUpgrade(
        grpc.generated.climateaction.ota.DeviceInfo request) {
      return blockingServerStreamingCall(
          getChannel(), getListenForOTAUpgradeMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Server Streaming - Listen for OTA upgrade, the device reports its own firmware version number, and then continuously monitors whether there is a new version that needs to be upgraded.
   * </pre>
   */
  public static final class OTAFutureStub extends io.grpc.stub.AbstractStub<OTAFutureStub> {
    private OTAFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private OTAFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OTAFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new OTAFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_LISTEN_FOR_OTAUPGRADE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final OTAImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(OTAImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LISTEN_FOR_OTAUPGRADE:
          serviceImpl.listenForOTAUpgrade((grpc.generated.climateaction.ota.DeviceInfo) request,
              (io.grpc.stub.StreamObserver<grpc.generated.climateaction.ota.FirmwareUpgrade>) responseObserver);
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

  private static abstract class OTABaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    OTABaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.generated.climateaction.ota.OTAServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("OTA");
    }
  }

  private static final class OTAFileDescriptorSupplier
      extends OTABaseDescriptorSupplier {
    OTAFileDescriptorSupplier() {}
  }

  private static final class OTAMethodDescriptorSupplier
      extends OTABaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    OTAMethodDescriptorSupplier(String methodName) {
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
      synchronized (OTAGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new OTAFileDescriptorSupplier())
              .addMethod(getListenForOTAUpgradeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
