package com.distsys.climateaction;

import grpc.generated.climateaction.alarm.AlarmGrpc.AlarmImplBase;
import grpc.generated.climateaction.common.CO2Concentration;
import grpc.generated.climateaction.common.ResponseMessage;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;

/**
 *
 * @author xinminghui
 */
/* Unary
* Report the carbon dioxide concentration, 
* and the server will decide whether to alarm.
*/
public class AlarmServer extends AlarmImplBase {

    // CO2 threshold: 5,000 ppm
    private static final float CO2ConcentrationThreshold = 5000;

    public static void main(String[] args) {
        AlarmServer alarmServer = new AlarmServer();

        int port = 50051;

        try {
            Server server = ServerBuilder.forPort(port)
                    .addService(alarmServer)
                    .intercept(new CAAuth())
                    .build()
                    .start();
            System.out.println("Server started, listening on " + port);
            // The server registers itself so that clients can discover it
            ServiceRegistration sg = ServiceRegistration.getInstance();
            sg.registerService("_grpc._tcp.local.", "alarm", port, "Alarm server will remotely control to turn on alarm device");
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
    // When the concentration exceeds the threshold, trigger alarm
    public void alarm(CO2Concentration request, StreamObserver<ResponseMessage> responseObserver) {
        System.out.println("receiving alarm request");

        boolean result;
        String message;
        float concentration = request.getConcentration();

        if (concentration > CO2ConcentrationThreshold) {
            result = true;
            message = "Alarm immediately, the CO2 concentration is: "
                    + concentration + " greater than the threshold: " + CO2ConcentrationThreshold;
        } else {
            result = false;
            message = "Don't alarm, the CO2 concentration is: "
                    + concentration + " less than or equal the threshold: " + CO2ConcentrationThreshold;
        }
        ResponseMessage response = ResponseMessage.newBuilder()
                .setResult(result)
                .setMessage(message)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
