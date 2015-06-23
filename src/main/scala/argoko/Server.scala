package argoko

import org.apache.logging.log4j.LogManager
import org.zeromq.ZMQ

object Server {
  val log = LogManager.getLogger(Server.getClass.getName)

  def main(args: Array[String]): Unit = {
    new Server().run()
  }
}

class Server {
  def run() = {
    val context = ZMQ.context(1)
    val socket = context.socket(ZMQ.REP)
    socket.bind("tcp://127.0.0.1:5555")

    while (!Thread.currentThread().isInterrupted()) {
      socket.send(Array[Byte]())
      Thread.sleep(1000)
    }
    socket.close()
    context.term()
  }
}
