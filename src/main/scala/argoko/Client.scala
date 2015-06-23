package argoko

import org.apache.commons.logging.LogFactory
import org.zeromq.ZMQ

object Client {
  val log = LogFactory.getLog(Client.getClass.getName)
}

class Client {

 def run() : Unit = {
   val context = ZMQ.context(1);
   val socket = context.socket(ZMQ.REQ);
   socket.connect("tcp://127.0.0.1:5555");
   val result = new String(socket.recv(0));
   socket.close();
   context.term();
 }


}
