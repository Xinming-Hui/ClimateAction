package com.distsys.climateaction;

import grpc.generated.climateaction.batch.DataBatchSyncGrpc.DataBatchSyncImplBase;
import grpc.generated.climateaction.common.CO2Concentration;
import grpc.generated.climateaction.common.ResponseMessage;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author xinminghui
 */
public class DataBatchSyncServer extends DataBatchSyncImplBase {
    
    public static void main(String[] args) {
        DataBatchSyncServer batchServer = new DataBatchSyncServer();

        int port = 50053;

        try {
            Server server = ServerBuilder.forPort(port)
                    .addService(batchServer)
                    .intercept(new CAAuth())
                    .build()
                    .start();
            System.out.println("Server started, listening on " + port);
            // The server registers itself so that clients can discover it
            ServiceRegistration sg = ServiceRegistration.getInstance();
            sg.registerService("_grpc._tcp.local.", "databatchsync", port, "DataBatchSync server allows batch uploading of data for efficiency");
            server.awaitTermination();

        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();

        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
            e.printStackTrace();
        }
    }
    
    @Override
    public StreamObserver<CO2Concentration> dataBatchSync(StreamObserver<ResponseMessage> responseObserver) {
        return new StreamObserver<CO2Concentration>() {
            ArrayList<CO2Concentration> list = new ArrayList<>();
            
            @Override
            public void onNext(CO2Concentration v) {
                System.out.println("DataBatchSync received data: " + v.toString());
                list.add(v);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("dataBatchSync error: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Received all DataBatchSync messages");
                ResponseMessage response = ResponseMessage.newBuilder()
                        .setResult(true)
                        .setMessage("Received " + list.size() + "messages")
                        .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
            
        };
    }
}
