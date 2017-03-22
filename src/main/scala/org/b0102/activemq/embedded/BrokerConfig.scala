package org.b0102.activemq.embedded

import org.springframework.context.annotation.Configuration
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import java.net.InetAddress
import java.net.URI
import org.apache.activemq.broker.BrokerService
import org.apache.activemq.broker.jmx.ManagementContext
import org.apache.activemq.broker.TransportConnector
import org.apache.activemq.store.kahadb.KahaDBPersistenceAdapter
import java.io.File
import org.slf4j.LoggerFactory

@Configuration
private[embedded] class BrokerConfig 
{
  private val logger = LoggerFactory.getLogger(classOf[BrokerConfig])
  
  private val TCP_URI_PREFIX = "nio://0.0.0.0:"
  private val AMPQ_URI_PREFIX = "amqp://0.0.0.0:"
  
  @Value("${org.b0102.activemq.embedded.system.override:false}")
  private var systemOverride:Boolean = _ 

  @Value("${org.b0102.activemq.embedded.broker.tcp.port:61616}")
  private var brokerTcpPort:Int = _
  
  @Value("${org.b0102.activemq.embedded.broker.amqp.port:5672}")
  private var brokerAmpqPort:Int = _ 

  @Value("${org.b0102.activemq.embedded.jmx.port:1099}")
  private var jmxPort:Int = _ 
  
  @Value("${org.b0102.activemq.embedded.directory}")
  private var directory:String = _
  
  @Bean
  private[embedded] def brokerService():BrokerService =
  {
    val mc = new ManagementContext()
    mc.setCreateConnector(true)
    mc.setConnectorPort(jmxPort)
		
		val bs = new BrokerService()
    bs.addConnector({
      val tc = new TransportConnector()
  		tc.setName("nio")
  		tc.setUri(new URI(s"${TCP_URI_PREFIX}${brokerTcpPort}"))
  		tc
    })
    
    bs.addConnector({
      val tc = new TransportConnector()
  		tc.setName("amqp")
  		
  		tc.setUri(new URI(s"${AMPQ_URI_PREFIX}${brokerAmpqPort}"))
  		tc
    })
    bs.setUseJmx(true)
    bs.setStartAsync(true)
    bs.setManagementContext(mc)
    
    val kpa = new KahaDBPersistenceAdapter()
    val dir = new File(directory)
    
    dir.mkdirs()
    kpa.setDirectory(dir)
    bs.setPersistenceAdapter(kpa)
    
    return bs
  }
  
  

  
}