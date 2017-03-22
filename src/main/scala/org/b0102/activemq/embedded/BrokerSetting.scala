package org.b0102.activemq.embedded

import java.net.URI

private[embedded] abstract class BrokerSetting(val name:String, val uri:URI) 
{
  val protocol:String
  override def toString():String = 
  {
    return s"""
    Name: ${name}\n
    URI: ${uri}
    """ 
  }
}