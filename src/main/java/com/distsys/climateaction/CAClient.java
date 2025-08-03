package com.distsys.climateaction;

import grpc.generated.climateaction.alarm.AlarmGrpc;
import grpc.generated.climateaction.alarm.AlarmGrpc.AlarmBlockingStub;
import grpc.generated.climateaction.batch.DataBatchSyncGrpc;
import grpc.generated.climateaction.batch.DataBatchSyncGrpc.DataBatchSyncStub;
import grpc.generated.climateaction.common.CO2Concentration;
import grpc.generated.climateaction.common.ResponseMessage;
import grpc.generated.climateaction.monitor.CO2MonitorGrpc;
import grpc.generated.climateaction.monitor.CO2MonitorGrpc.CO2MonitorStub;
import grpc.generated.climateaction.monitor.CO2Stats;
import grpc.generated.climateaction.ota.DeviceInfo;
import grpc.generated.climateaction.ota.FirmwareUpgrade;
import grpc.generated.climateaction.ota.OTAGrpc;
import grpc.generated.climateaction.ota.OTAGrpc.OTAStub;
import io.grpc.Channel;
import io.grpc.ClientInterceptors;
import io.grpc.Context;
import io.grpc.Context.CancellableContext;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.Status.Code;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.MetadataUtils;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

/**
 *
 * @author xinminghui
 */
/* Naming - Discovery
* CAClient implements ServiceListener interface,
* automatically discover services and call corresponding requests
*/
public class CAClient implements ServiceListener {

    private AlarmBlockingStub alarmBlockingStub;
    private OTAStub otaStub;
    private DataBatchSyncStub batchStub;
    private CO2MonitorStub monitorStub;
    private LogCallback logCallback;

    public CAClient() {
        alarmBlockingStub = null;
        otaStub = null;
        batchStub = null;
        monitorStub = null;
    }

    @Override
    public void serviceAdded(ServiceEvent event) {
        ServiceInfo info = event.getInfo();
        if (info.hasData()) {
            System.out.println("has data " + info.getHostAddresses().toString());
        }
        System.out.println("Service added: " + event.getInfo());
    }

    @Override
    public void serviceRemoved(ServiceEvent event) {
        System.out.println("Service removed: " + event.getInfo());
    }

    @Override
    public void serviceResolved(ServiceEvent event) {
        System.out.println("Service resolved: " + event.getInfo());
        logCallback.logEvent("Service resolved: " + event.getInfo());
        ServiceInfo info = event.getInfo();
        String host = info.getHostAddresses()[0];
        int port = info.getPort();
        String serviceName = info.getName();
        System.out.println("####service " + serviceName + " resolved at: " + host + ":" + port);
        logCallback.logEvent("####service " + serviceName + " resolved at: " + host + ":" + port);
        ManagedChannel channel = ManagedChannelBuilder.
                forAddress(host, port)
                .usePlaintext()
                .build();
        Metadata metaData = CAMetaData.buildTokenMetadata();
        Channel interceptedChannel = ClientInterceptors.intercept(channel,
                MetadataUtils.newAttachHeadersInterceptor(metaData));
        if (serviceName.equals("alarm")) {
            alarmBlockingStub = AlarmGrpc.newBlockingStub(interceptedChannel);
        }

        if (serviceName.equals("co2monitor")) {
            monitorStub = CO2MonitorGrpc.newStub(interceptedChannel);
        }

        if (serviceName.equals("ota")) {
            otaStub = OTAGrpc.newStub(interceptedChannel);
        }

        if (serviceName.equals("databatchsync")) {
            batchStub = DataBatchSyncGrpc.newStub(interceptedChannel);
        }
    }
    
    public void setLogCallback(LogCallback logCallback) {
        this.logCallback = logCallback;
    }
    
    public void alarm(String id, float concentration) {
        CO2Concentration request = CO2Concentration.newBuilder()
                    .setId(id)
                    .setTimestamp(System.currentTimeMillis())
                    .setConcentration(concentration)
                    .build();
            // reqeust with timeout
            ResponseMessage response = alarmBlockingStub.withDeadlineAfter(2, TimeUnit.SECONDS).alarm(request);
            System.out.println("Alarm reuslt: " + response.getResult() + ", message: " + response.getMessage());
            logCallback.logEvent("Alarm reuslt: " + response.getResult() + ", message: " + response.getMessage());
    }
    
    public void otaUpgrade(String id, String version) {
        DeviceInfo request = DeviceInfo.newBuilder()
                .setId(id)
                .setVersion(version)
                .build();
        StreamObserver<FirmwareUpgrade> responseObserver = new StreamObserver<FirmwareUpgrade>() {
            @Override
            public void onNext(FirmwareUpgrade v) {
                System.out.println("Received OTA response, result: " + v.getUpgrade());
                logCallback.logEvent("Received OTA response, result: " + v.getUpgrade());
                System.out.println("Detail response: " + v.toString());
                logCallback.logEvent("Detail response: " + v.toString());
            }

            @Override
            public void onError(Throwable t) {
                Status status = Status.fromThrowable(t);
                System.out.println("gRPC error with status: " + status);
                logCallback.logEvent("gRPC error with status: " + status);
                System.out.println("Status code: " + status.getCode());
                logCallback.logEvent("Status code: " + status.getCode());
                System.out.println("Description: " + status.getDescription());
                logCallback.logEvent("Description: " + status.getDescription());
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("OTA onCompleted");
                logCallback.logEvent("OTA is Completed");
            }

        };
        System.out.println("start listenForOTAUpgrade");
        otaStub.withDeadlineAfter(2, TimeUnit.SECONDS).listenForOTAUpgrade(request, responseObserver);
        
    }
    
    public void dataBatchUpload(String id, int start, int end) {
        StreamObserver<ResponseMessage> responseObserver = new StreamObserver<ResponseMessage>() {
            @Override
            public void onNext(ResponseMessage v) {
                System.out.println(v.toString());
                logCallback.logEvent(v.toString());
            }
            
            @Override
            public void onError(Throwable t) {
                Status status = Status.fromThrowable(t);
                System.out.println("gRPC error with status: " + status);
                logCallback.logEvent("gRPC error with status: " + status);
                System.out.println("Status code: " + status.getCode());
                logCallback.logEvent("Status code: " + status.getCode());
                System.out.println("Description: " + status.getDescription());
                logCallback.logEvent("Description: " + status.getDescription());
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("Data batch sync is completed");
                logCallback.logEvent("Data batch sync is completed");
            }

        };

        StreamObserver<CO2Concentration> requestObserver = batchStub.withDeadlineAfter(20, TimeUnit.SECONDS).dataBatchSync(responseObserver);

        for (int i = 0; i < 4; i++) {
            float co2 = (float) (start + Math.random() * (end - start) + 1);
            CO2Concentration concentration = CO2Concentration.newBuilder()
                    .setId(id)
                    .setTimestamp(System.currentTimeMillis())
                    .setConcentration(co2)
                    .build();
            requestObserver.onNext(concentration);
            logCallback.logEvent(concentration.toString());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.print("sleep exception: " + e.getMessage());
            }
        }
        requestObserver.onCompleted();
    }
    
    public void co2Monitor(String id, int start, int end) {
        StreamObserver<CO2Stats> responseObserver = new StreamObserver<CO2Stats>() {
            @Override
            public void onNext(CO2Stats v) {
                System.out.println("Current CO2 concentration statistics: ");
                logCallback.logEvent("Current CO2 concentration statistics: ");
                System.out.println(v.toString());
                logCallback.logEvent(v.toString());
            }

            @Override
            public void onError(Throwable t) {
                Status status = Status.fromThrowable(t);
                System.out.println("gRPC error with status: " + status);
                logCallback.logEvent("gRPC error with status: " + status);
                System.out.println("Status code: " + status.getCode());
                logCallback.logEvent("Status code: " + status.getCode());
                System.out.println("Description: " + status.getDescription());
                logCallback.logEvent("Description: " + status.getDescription());
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("CO2 concentration report is completed");
                logCallback.logEvent("CO2 concentration report is completed");
            }

        };

        StreamObserver<CO2Concentration> requestObserver = monitorStub.withDeadlineAfter(20, TimeUnit.SECONDS).reportCO2(responseObserver);
        for (int i = 0; i < 4; i++) {
            float tmp = (float) (start + Math.random() * (end - start) + 1);
            CO2Concentration concentration = CO2Concentration.newBuilder()
                    .setId(id)
                    .setTimestamp(System.currentTimeMillis())
                    .setConcentration(tmp)
                    .build();
            requestObserver.onNext(concentration);
            logCallback.logEvent(concentration.toString());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.print("sleep exception: " + e.getMessage());
            }
        }
        requestObserver.onCompleted();
    }

    public static void main(String[] args) throws InterruptedException {
//        CAClient client = new CAClient();
//        client.testAlarmServer();
//        client.testOTAServer();
//        client.testDataBatchSyncServer();
//        client.testCancelDataBatchSyncServer();
//        client.testCO2MonitorServer();
//        try {
//            // Create a JmDNS instance
//            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
//            System.out.println("Client: InetAddress.getLocalHost():" + InetAddress.getLocalHost());
//            // Add a service listener that listens for all services on local host
//            jmdns.addServiceListener("_grpc._tcp.local.", client);
//
//            while (true) {
//                Thread.sleep(2000);
//            }
//
//        } catch (UnknownHostException e) {
//            System.out.println(e.getMessage());
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
    }

    public void testAlarmServer() {
        String host = "localhost";
        int port = 50051;
        ManagedChannel channel = ManagedChannelBuilder.
                forAddress(host, port)
                .usePlaintext()
                .build();
        Metadata metaData = CAMetaData.buildTokenMetadata();
        Channel interceptedChannel = ClientInterceptors.intercept(channel,
                MetadataUtils.newAttachHeadersInterceptor(metaData));
        AlarmBlockingStub blockingStub = AlarmGrpc.newBlockingStub(interceptedChannel);
        try {

            CO2Concentration request = CO2Concentration.newBuilder()
                    .setId("123")
                    .setTimestamp(System.currentTimeMillis())
                    .setConcentration(99999)
                    .build();
            // timeout reqeust
            ResponseMessage response = blockingStub.withDeadlineAfter(2, TimeUnit.SECONDS).alarm(request);
            System.out.println("Alarm reuslt: " + response.getResult() + ", message: " + response.getMessage());
        } catch (StatusRuntimeException e) {
            Status status = e.getStatus();
            Code code = status.getCode();
            String description = status.getDescription();
            System.out.println("!!! Error code: " + code);
            System.out.println("!!! Error message: " + description);
            e.printStackTrace();
        } finally {
            try {
                //shutdown channel
                channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                System.out.println("Alarm client channel shutdown exception: " + e.getMessage());
            }
        }
    }

    public void testOTAServer() {
        String host = "localhost";
        int port = 50052;
        ManagedChannel channel = ManagedChannelBuilder.
                forAddress(host, port)
                .usePlaintext()
                .build();
        Metadata metaData = CAMetaData.buildTokenMetadata();
        Channel interceptedChannel = ClientInterceptors.intercept(channel,
                MetadataUtils.newAttachHeadersInterceptor(metaData));
        OTAStub stub = OTAGrpc.newStub(interceptedChannel);

        DeviceInfo request = DeviceInfo.newBuilder()
                .setId("123")
                .setVersion("1.0.1")
                .build();
        StreamObserver<FirmwareUpgrade> responseObserver = new StreamObserver<FirmwareUpgrade>() {
            @Override
            public void onNext(FirmwareUpgrade v) {
                System.out.println("Received OTA response: " + v.toString());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("OTA error: " + t.getLocalizedMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("OTA onCompleted");
            }

        };
        System.out.println("start listenForOTAUpgrade");
        stub.withDeadlineAfter(2, TimeUnit.SECONDS).listenForOTAUpgrade(request, responseObserver);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("sleep exception: " + e.getMessage());
        }

        try {
            //shutdown channel
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("OTA client channel shutdown exception: " + e.getMessage());
        }

    }

    public void testDataBatchSyncServer() {
        String host = "localhost";
        int port = 50053;
        ManagedChannel channel = ManagedChannelBuilder.
                forAddress(host, port)
                .usePlaintext()
                .build();
        Metadata metaData = CAMetaData.buildTokenMetadata();
        Channel interceptedChannel = ClientInterceptors.intercept(channel,
                MetadataUtils.newAttachHeadersInterceptor(metaData));
        DataBatchSyncStub stub = DataBatchSyncGrpc.newStub(interceptedChannel);
        StreamObserver<ResponseMessage> responseObserver = new StreamObserver<ResponseMessage>() {
            @Override
            public void onNext(ResponseMessage v) {
                System.out.println(v.toString());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("DataBatchSync response error: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Data batch sync is completed");
            }

        };

        StreamObserver<CO2Concentration> requestObserver = stub.withDeadlineAfter(20, TimeUnit.SECONDS).dataBatchSync(responseObserver);

        for (int i = 0; i < 4; i++) {
            float co2 = (float) (500 + Math.random() * 1000);
            CO2Concentration concentration = CO2Concentration.newBuilder()
                    .setId("123")
                    .setTimestamp(System.currentTimeMillis())
                    .setConcentration(co2)
                    .build();
            requestObserver.onNext(concentration);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.print("sleep exception: " + e.getMessage());
            }
        }
        requestObserver.onCompleted();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.print("sleep exception: " + e.getMessage());
        }
    }

    public void testCancelDataBatchSyncServer() {
        String host = "localhost";
        int port = 50053;
        ManagedChannel channel = ManagedChannelBuilder.
                forAddress(host, port)
                .usePlaintext()
                .build();
        Metadata metaData = CAMetaData.buildTokenMetadata();
        Channel interceptedChannel = ClientInterceptors.intercept(channel,
                MetadataUtils.newAttachHeadersInterceptor(metaData));
        DataBatchSyncStub stub = DataBatchSyncGrpc.newStub(interceptedChannel);

        CancellableContext cancellableCtx = Context.current().withCancellation();
        cancellableCtx.run(() -> {
            StreamObserver<ResponseMessage> responseObserver = new StreamObserver<ResponseMessage>() {
                @Override
                public void onNext(ResponseMessage v) {
                    System.out.println(v.toString());
                }

                @Override
                public void onError(Throwable t) {
                    System.out.println("DataBatchSync response error: " + t.getMessage());
                }

                @Override
                public void onCompleted() {
                    System.out.println("Data batch sync is completed");
                }

            };

            StreamObserver<CO2Concentration> requestObserver = stub.dataBatchSync(responseObserver);

            for (int i = 0; i < 2; i++) {
                float co2 = (float) (500 + Math.random() * 1000);
                CO2Concentration concentration = CO2Concentration.newBuilder()
                        .setId("123")
                        .setTimestamp(System.currentTimeMillis())
                        .setConcentration(co2)
                        .build();
                requestObserver.onNext(concentration);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.print("sleep exception: " + e.getMessage());
                }
            }
            System.out.println("!!! Cancelling data batch sync stream manually...");

            // cancel manually
            cancellableCtx.cancel(null);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.print("sleep exception: " + e.getMessage());
            }
        });
    }

    public void testCO2MonitorServer() {
        String host = "localhost";
        int port = 50054;
        ManagedChannel channel = ManagedChannelBuilder.
                forAddress(host, port)
                .usePlaintext()
                .build();
        Metadata metaData = CAMetaData.buildTokenMetadata();
        Channel interceptedChannel = ClientInterceptors.intercept(channel,
                MetadataUtils.newAttachHeadersInterceptor(metaData));
        CO2MonitorStub stub = CO2MonitorGrpc.newStub(interceptedChannel);

        StreamObserver<CO2Stats> responseObserver = new StreamObserver<CO2Stats>() {
            @Override
            public void onNext(CO2Stats v) {
                System.out.println("Current CO2 concentration statistics: ");
                System.out.println(v.toString());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("CO2Monitor response error: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("CO2 concentration report is completed");
            }

        };

        StreamObserver<CO2Concentration> requestObserver = stub.withDeadlineAfter(20, TimeUnit.SECONDS).reportCO2(responseObserver);
        for (int i = 0; i < 4; i++) {
            float tmp = (float) (500 * Math.random() * 1000);
            CO2Concentration concentration = CO2Concentration.newBuilder()
                    .setId("123")
                    .setTimestamp(System.currentTimeMillis())
                    .setConcentration(tmp)
                    .build();
            requestObserver.onNext(concentration);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.print("sleep exception: " + e.getMessage());
            }
        }
        requestObserver.onCompleted();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.print("sleep exception: " + e.getMessage());
        }
    }
}
