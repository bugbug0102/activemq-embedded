package org.b0102.activemq.embedded

import org.springframework.boot.SpringApplication
import org.springframework.context.ApplicationContext
import org.slf4j.LoggerFactory
import org.springframework.boot.builder.SpringApplicationBuilder

object Main 
{
  private val logger = LoggerFactory.getLogger(this.getClass)
  def main(args:Array[String]):Unit = 
  {
    val ctx = new SpringApplicationBuilder(classOf[ActiveMqEmbeddedApplication]).web(false).run(args:_*)
  }
}