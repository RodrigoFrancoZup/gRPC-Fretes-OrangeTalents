package br.com.edu

import io.grpc.stub.StreamObserver
import org.slf4j.LoggerFactory
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class FreteServiceEndpoint : FretesServiceGrpc.FretesServiceImplBase() {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun calculaFrete(request: FreteRequest?, responseObserver: StreamObserver<FreteResponse>?) {

        var frete = 0.0
        var cep: String? = request!!.cep

        if(!request.hasField(FreteRequest.getDescriptor().findFieldByName("cep"))){
            cep = "Indefinido"
        }

        if(cep != "Indefinido"){
            frete =  Random.nextDouble(from = 0.0, until =  1000.0)
        }

        val response = FreteResponse.newBuilder()
            .setCep(cep)
            .setValor(frete)
            .build()

        //Log
        logger.info("O frete será: $frete")

        responseObserver?.onNext(response)
        responseObserver?.onCompleted()

    }
}