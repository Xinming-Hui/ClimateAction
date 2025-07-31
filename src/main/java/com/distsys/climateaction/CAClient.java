package com.distsys.climateaction;

import grpc.generated.climateaction.alarm.AlarmGrpc;
import grpc.generated.climateaction.alarm.AlarmGrpc.AlarmBlockingStub;
import grpc.generated.climateaction.common.CO2Concentration;
import grpc.generated.climateaction.common.ResponseMessage;
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
        client.testOTAServer();
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
}
