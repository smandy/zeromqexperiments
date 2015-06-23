package argoko

import org.apache.logging.log4j.LogManager
import org.zeromq.ZMQ

object HwClient {
  val log = LogManager.getLogger( HwClient.getClass.getName)

  def main(args : Array[String]) : Unit = {
    val context = ZMQ.context(1)
    //  Socket to talk to server
    log.info("Connecting to hello world server…")
    val requester = context.socket(ZMQ.REQ)
    requester.connect("tcp://localhost:5555")
    for { i <- 0 to 10 } {
      val request = "Hello"
      log.info("Sending Hello " + i)
      requester.send(request.getBytes(), 0)
      val reply = requester.recv(0)
      log.info("Received " + new String(reply) + " " + i)
    }
    requester.close();
    context.term();
  }
}