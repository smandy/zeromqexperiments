package argoko

import org.apache.commons.logging.LogFactory
import org.zeromq.ZMQ

object Client {
  val log = LogFactory.getLog(Client.getClass.getName)

  def main(args: Array[String]): Unit = {
    new Client().run()
  }
}

class Client {
  import argoko.Client._

  def run(): Unit = {
    val context = ZMQ.context(1);
    val socket = context.socket(ZMQ.REQ);
    socket.connect("tcp://127.0.0.1:5555");
    socket.send(Array[Byte]())
    val result = new String(socket.recv(0));

    log.info(s"Bing got $result")

    socket.close();
    context.term();
  }
}
