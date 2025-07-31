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
import grpc.generated.climateaction.ota.OTAGrpc.OTABlockingStub;
import grpc.generated.climateaction.ota.OTAGrpc.OTAStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author xinminghui
 */

public class CAClient {
    
    public static void main(String[] args) throws InterruptedException  {
        CAClient client = new CAClient();
//        client.testAlarmServer();
//        client.testOTAServer();
//        client.testDataBatchSyncServer();
        client.testCO2MonitorServer();
    }
    
    public void testAlarmServer() throws InterruptedException {
        String host = "localhost";
        int port = 50051;
        ManagedChannel channel = ManagedChannelBuilder.
                forAddress(host, port)
                .usePlaintext()
                .build();
       AlarmBlockingStub blockingStub = AlarmGrpc.newBlockingStub(channel);
        try {
            
            CO2Concentration request = CO2Concentration.newBuilder()
                    .setId("123")
                    .setTimestamp(System.currentTimeMillis())
                    .setConcentration(99999)
                    .build();

            ResponseMessage response = blockingStub.alarm(request);
            System.out.println("Alarm reuslt: " + response.getResult() + ", message: " +response.getMessage());
        } catch (StatusRuntimeException e) {
            System.out.println("StatusRuntimeException");
            e.printStackTrace();
        } finally {
            //shutdown channel
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        }
    }
    
    public void testOTAServer() throws InterruptedException {
        String host = "localhost";
        int port = 50052;
        ManagedChannel channel = ManagedChannelBuilder.
                forAddress(host, port)
                .usePlaintext()
                .build();
       OTAStub stub = OTAGrpc.newStub(channel);
        try {
            
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
            stub.listenForOTAUpgrade(request, responseObserver);
            Thread.sleep(10000);
         
        } catch (StatusRuntimeException e) {
            System.out.println("StatusRuntimeException");
            e.printStackTrace();
        } finally {
            //shutdown channel
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        }
    }
    
    
    public void testDataBatchSyncServer() throws InterruptedException {
        String host = "localhost";
        int port = 50053;
        ManagedChannel channel = ManagedChannelBuilder.
                forAddress(host, port)
                .usePlaintext()
                .build();
       DataBatchSyncStub stub = DataBatchSyncGrpc.newStub(channel);
       
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
       
       Thread.sleep(1000);
       
       CO2Concentration concentration1 = CO2Concentration.newBuilder()
               .setId("123")
               .setTimestamp(System.currentTimeMillis())
               .setConcentration(876)
               .build();
       requestObserver.onNext(concentration1);
       Thread.sleep(1000);
       
       CO2Concentration concentration2 = CO2Concentration.newBuilder()
               .setId("456")
               .setTimestamp(System.currentTimeMillis())
               .setConcentration(952)
               .build();
       requestObserver.onNext(concentration2);
       Thread.sleep(1000);
       
       CO2Concentration concentration3 = CO2Concentration.newBuilder()
               .setId("7689")
               .setTimestamp(System.currentTimeMillis())
               .setConcentration(1001)
               .build();
       requestObserver.onNext(concentration3);
       Thread.sleep(1000);
       
       CO2Concentration concentration4 = CO2Concentration.newBuilder()
               .setId("2967")
               .setTimestamp(System.currentTimeMillis())
               .setConcentration(999)
               .build();
       requestObserver.onNext(concentration4);
       Thread.sleep(1000);
       
       requestObserver.onCompleted();
       Thread.sleep(1000);
    }
    
    public void testCO2MonitorServer() throws InterruptedException {
        String host = "localhost";
        int port = 50054;
        ManagedChannel channel = ManagedChannelBuilder.
                forAddress(host, port)
                .usePlaintext()
                .build();
       CO2MonitorStub stub = CO2MonitorGrpc.newStub(channel);
       
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
        
    
       StreamObserver<CO2Concentration> requestObserver = stub.reportCO2(responseObserver);
       
       Thread.sleep(1000);
       
       CO2Concentration concentration1 = CO2Concentration.newBuilder()
               .setId("123")
               .setTimestamp(System.currentTimeMillis())
               .setConcentration(876)
               .build();
       requestObserver.onNext(concentration1);
       Thread.sleep(1000);
       
       CO2Concentration concentration2 = CO2Concentration.newBuilder()
               .setId("123")
               .setTimestamp(System.currentTimeMillis())
               .setConcentration(952)
               .build();
       requestObserver.onNext(concentration2);
       Thread.sleep(1000);
       
       CO2Concentration concentration3 = CO2Concentration.newBuilder()
               .setId("123")
               .setTimestamp(System.currentTimeMillis())
               .setConcentration(1001)
               .build();
       requestObserver.onNext(concentration3);
       Thread.sleep(1000);
       
       CO2Concentration concentration4 = CO2Concentration.newBuilder()
               .setId("123")
               .setTimestamp(System.currentTimeMillis())
               .setConcentration(999)
               .build();
       requestObserver.onNext(concentration4);
       
       Thread.sleep(1000);
       
       requestObserver.onCompleted();
       Thread.sleep(1000);
    }
}
