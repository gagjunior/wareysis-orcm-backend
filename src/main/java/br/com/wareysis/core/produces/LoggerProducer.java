package br.com.wareysis.core.produces;

import org.jboss.logging.Logger;

import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Singleton;

@Singleton
public class LoggerProducer {

    @Produces
    public Logger produceLogger(InjectionPoint injectionPoint) {

        var clazz = injectionPoint.getMember().getDeclaringClass();
        return Logger.getLogger(clazz);
    }
}

