package com.distsys.climateaction;

import grpc.generated.climateaction.common.CO2Concentration;
import grpc.generated.climateaction.monitor.CO2MonitorGrpc.CO2MonitorImplBase;
import grpc.generated.climateaction.monitor.CO2Stats;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author xinminghui
 */
public class CO2MonitorServer extends CO2MonitorImplBase {

    public static void main(String[] args) {
        CO2MonitorServer monitorServer = new CO2MonitorServer();

        int port = 50054;

        try {
            Server server = ServerBuilder.forPort(port)
                    .addService(monitorServer)
                    .build()
                    .start();
            System.out.println("Server started, listening on " + port);
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
    public StreamObserver<CO2Concentration> reportCO2(StreamObserver<CO2Stats> responseObserver) {
        return new StreamObserver<CO2Concentration>() {
            ArrayList<CO2Concentration> list = new ArrayList<>();
            
            @Override
            public void onNext(CO2Concentration v) {
                list.add(v);
                CO2Stats response = calculateData(list);
                responseObserver.onNext(response);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("reportCO2 error: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("CO2Monitor request is completed");
                responseObserver.onCompleted();
            }
            
            private CO2Stats calculateData(ArrayList<CO2Concentration> list) {
                String id = "";
                float min = 0, max = 0, avg = 0, sum = 0;               
                if (!list.isEmpty()) {
                    id = list.get(0).getId();
                    min = list.get(0).getConcentration();
                    max = min;
                    sum = min;
                    for (int i = 1; i < list.size(); i++) {
                        float concentration = list.get(i).getConcentration();
                        if (concentration < min) {
                            min = concentration;
                        }
                        if (concentration > max) {
                            max = concentration;
                        }
                        sum += concentration;
                    }
                    avg = sum / list.size();
                }
                return CO2Stats.newBuilder()
                        .setId(id)
                        .setMin(min)
                        .setMax(max)
                        .setAvg(avg)
                        .build();
                
            }
        };
    }

}
