package argoko

import org.apache.commons.logging.LogFactory
import org.zeromq.ZMQ;

object HwServer {
  val log = LogFactory.getLog(HwServer.getClass)
  def main(args : Array[String]) : Unit = {
    val context = ZMQ.context(1);

    //  Socket to talk to clients
    val responder = context.socket(ZMQ.REP);
    responder.bind("tcp://*:5555");

    while (!Thread.currentThread().isInterrupted()) {
      // Wait for next request from the client
      val request = responder.recv(0);
      log.info("Received Hello");
      // Do some 'work'
      Thread.sleep(1000)
      // Send reply back to client
      val reply = "World";
      responder.send(reply.getBytes(), 0);
    }
    responder.close();
    context.term();
  }
}