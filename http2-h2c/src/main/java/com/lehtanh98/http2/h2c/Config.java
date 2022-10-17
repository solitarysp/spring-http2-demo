package com.lehtanh98.http2.h2c;

import static io.undertow.UndertowOptions.ENABLE_HTTP2;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.ServerProperties.Undertow;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

  @Configuration
  @ConditionalOnClass(Undertow.class)
  @AutoConfigureBefore(ServletWebServerFactoryAutoConfiguration.class)
  public static class UndertowHttp2Configuration {

    @Bean
    public ServletWebServerFactory servletWebServerFactory() {
      UndertowServletWebServerFactory factory = new UndertowServletWebServerFactory();
      factory.addBuilderCustomizers(builder -> builder.setServerOption(ENABLE_HTTP2, true));
      return factory;
    }

  }
}
