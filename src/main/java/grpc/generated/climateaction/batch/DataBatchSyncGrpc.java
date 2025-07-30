package grpc.generated.climateaction.batch;

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
    comments = "Source: data_batch_sync.proto")
public final class DataBatchSyncGrpc {

  private DataBatchSyncGrpc() {}

  public static final String SERVICE_NAME = "climateaction.DataBatchSync";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.generated.climateaction.common.CO2Concentration,
      grpc.generated.climateaction.common.ConfirmationMessage> getDataBatchSyncMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DataBatchSync",
      requestType = grpc.generated.climateaction.common.CO2Concentration.class,
      responseType = grpc.generated.climateaction.common.ConfirmationMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.generated.climateaction.common.CO2Concentration,
      grpc.generated.climateaction.common.ConfirmationMessage> getDataBatchSyncMethod() {
    io.grpc.MethodDescriptor<grpc.generated.climateaction.common.CO2Concentration, grpc.generated.climateaction.common.ConfirmationMessage> getDataBatchSyncMethod;
    if ((getDataBatchSyncMethod = DataBatchSyncGrpc.getDataBatchSyncMethod) == null) {
      synchronized (DataBatchSyncGrpc.class) {
        if ((getDataBatchSyncMethod = DataBatchSyncGrpc.getDataBatchSyncMethod) == null) {
          DataBatchSyncGrpc.getDataBatchSyncMethod = getDataBatchSyncMethod = 
              io.grpc.MethodDescriptor.<grpc.generated.climateaction.common.CO2Concentration, grpc.generated.climateaction.common.ConfirmationMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "climateaction.DataBatchSync", "DataBatchSync"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.generated.climateaction.common.CO2Concentration.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.generated.climateaction.common.ConfirmationMessage.getDefaultInstance()))
                  .setSchemaDescriptor(new DataBatchSyncMethodDescriptorSupplier("DataBatchSync"))
                  .build();
          }
        }
     }
     return getDataBatchSyncMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DataBatchSyncStub newStub(io.grpc.Channel channel) {
    return new DataBatchSyncStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DataBatchSyncBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new DataBatchSyncBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DataBatchSyncFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new DataBatchSyncFutureStub(channel);
  }

  /**
   */
  public static abstract class DataBatchSyncImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Client streaming - Batch upload of data accumulated 
     * Server confirms 
     * </pre>
     */
    public io.grpc.stub.StreamObserver<grpc.generated.climateaction.common.CO2Concentration> dataBatchSync(
        io.grpc.stub.StreamObserver<grpc.generated.climateaction.common.ConfirmationMessage> responseObserver) {
      return asyncUnimplementedStreamingCall(getDataBatchSyncMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getDataBatchSyncMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                grpc.generated.climateaction.common.CO2Concentration,
                grpc.generated.climateaction.common.ConfirmationMessage>(
                  this, METHODID_DATA_BATCH_SYNC)))
          .build();
    }
  }

  /**
   */
  public static final class DataBatchSyncStub extends io.grpc.stub.AbstractStub<DataBatchSyncStub> {
    private DataBatchSyncStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DataBatchSyncStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DataBatchSyncStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DataBatchSyncStub(channel, callOptions);
    }

    /**
     * <pre>
     * Client streaming - Batch upload of data accumulated 
     * Server confirms 
     * </pre>
     */
    public io.grpc.stub.StreamObserver<grpc.generated.climateaction.common.CO2Concentration> dataBatchSync(
        io.grpc.stub.StreamObserver<grpc.generated.climateaction.common.ConfirmationMessage> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getDataBatchSyncMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class DataBatchSyncBlockingStub extends io.grpc.stub.AbstractStub<DataBatchSyncBlockingStub> {
    private DataBatchSyncBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DataBatchSyncBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DataBatchSyncBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DataBatchSyncBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class DataBatchSyncFutureStub extends io.grpc.stub.AbstractStub<DataBatchSyncFutureStub> {
    private DataBatchSyncFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DataBatchSyncFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DataBatchSyncFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DataBatchSyncFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_DATA_BATCH_SYNC = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DataBatchSyncImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(DataBatchSyncImplBase serviceImpl, int methodId) {
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
        case METHODID_DATA_BATCH_SYNC:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.dataBatchSync(
              (io.grpc.stub.StreamObserver<grpc.generated.climateaction.common.ConfirmationMessage>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class DataBatchSyncBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DataBatchSyncBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.generated.climateaction.batch.DataBatchSyncServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DataBatchSync");
    }
  }

  private static final class DataBatchSyncFileDescriptorSupplier
      extends DataBatchSyncBaseDescriptorSupplier {
    DataBatchSyncFileDescriptorSupplier() {}
  }

  private static final class DataBatchSyncMethodDescriptorSupplier
      extends DataBatchSyncBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    DataBatchSyncMethodDescriptorSupplier(String methodName) {
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
      synchronized (DataBatchSyncGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DataBatchSyncFileDescriptorSupplier())
              .addMethod(getDataBatchSyncMethod())
              .build();
        }
      }
    }
    return result;
  }
}
