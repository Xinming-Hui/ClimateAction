package com.distsys.climateaction;

import grpc.generated.climateaction.ota.DeviceInfo;
import grpc.generated.climateaction.ota.FirmwareUpgrade;
import grpc.generated.climateaction.ota.OTAGrpc.OTAImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;

/**
 *
 * @author xinminghui
 */
public class OTAServer extends OTAImplBase {

    public static void main(String[] args) {
        OTAServer otaServer = new OTAServer();

        int port = 50052;

        try {
            Server server = ServerBuilder.forPort(port)
                    .addService(otaServer)
                    .build()
                    .start();
            System.out.println("Server started, listening on " + port);
            // The server registers itself so that clients can discover it
            ServiceRegistration sg = ServiceRegistration.getInstance();
            sg.registerService("_grpc._tcp.local.", "ota", port, "OTA server will check whether there is a new version based on the device's version");
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
    public void listenForOTAUpgrade(DeviceInfo request, StreamObserver<FirmwareUpgrade> responseObserver) {
        System.out.println("received listenForOTAUpgrade request");
        String id = request.getId();
        FirmwareUpgrade response = FirmwareUpgrade.newBuilder()
                .setId(id)
                .setUpgrade(false)
                .build();
        FirmwareUpgrade upgrade = FirmwareUpgrade.newBuilder()
                .setId(id)
                .setUpgrade(true)
                .setVersion("99.99.99")
                .setDownloadUrl("https://ota.climateaction.com/999999")
                .setSha256Hash("315f5bdb76d078c43b8ac0064e4a0164612b1fce77c869345bfc94c75894edd3")
                .build();
        for (int i = 0; i < 3; i++) {
            if (i == 2) {
                responseObserver.onNext(upgrade);
            } else {
                responseObserver.onNext(response);
            }
        }
        responseObserver.onCompleted();
    }
}
