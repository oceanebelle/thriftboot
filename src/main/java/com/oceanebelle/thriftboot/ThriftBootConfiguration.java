package com.oceanebelle.thriftboot;

import javax.servlet.Servlet;

import com.oceanebelle.thriftboot.echo.TEchoService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ThriftBootConfiguration {
    @Bean
    public TProtocolFactory tProtocolFactory() {
        //We will use binary protocol, but it's possible to use JSON and few others as well
        return new TBinaryProtocol.Factory();
        //No change my mind, change JSON
        //return new TJSONProtocol.Factory();
    }

    @Bean
    public ServletRegistrationBean registerEchoServlet(TProtocolFactory protocolFactory, EchoServiceHandler handler) {
        // Exposes on context /echo
        log.info("Setting up the servlet");
        TServlet servlet = new TServlet(new TEchoService.Processor<EchoServiceHandler>(handler), protocolFactory);
        return new ServletRegistrationBean(servlet, "/echo");
    }
}