package argoko

import org.apache.commons.logging.LogFactory
import org.zeromq.ZMQ

object HwClient {
  val log = LogFactory.getLog( HwClient.getClass.getName)

  def main(args : Array[String]) : Unit = {
    val context = ZMQ.context(1)
    //  Socket to talk to server
    System.out.println("Connecting to hello world server…")
    val requester = context.socket(ZMQ.REQ)
    requester.connect("tcp://localhost:5555")
    for { i <- 0 to 10 } {
      val request = "Hello"
      System.out.println("Sending Hello " + i)
      requester.send(request.getBytes(), 0)
      val reply = requester.recv(0)
      log.info("Received " + new String(reply) + " " + i)
    }
    requester.close();
    context.term();
  }
}