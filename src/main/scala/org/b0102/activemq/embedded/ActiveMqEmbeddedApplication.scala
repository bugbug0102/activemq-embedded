package org.b0102.activemq.embedded

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration
import org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateAutoConfiguration
import org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration
import org.springframework.context.annotation.ComponentScan

@Configuration
@EnableAutoConfiguration(exclude=Array(
		classOf[GroovyTemplateAutoConfiguration], 
		classOf[GsonAutoConfiguration], 
		classOf[WebSocketAutoConfiguration],
		classOf[JmxAutoConfiguration],
		classOf[ActiveMQAutoConfiguration],
		classOf[JacksonAutoConfiguration]
		))
@ComponentScan
private[embedded] class ActiveMqEmbeddedApplication 
{
  
}