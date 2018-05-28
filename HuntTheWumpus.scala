import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import scala.language.postfixOps

class HuntTheWumpus extends Actor {

  def receive = {
    case "Greet" =>
      println("##### WELCOME TO \"Hunt the Wumpus\" WORLD #####")
      println("##### Name yourselves #####")

    case "Speleologist" => println(self.path.name + ": I am Speleologist")
    case "Navigator" => println(self.path.name + ": I am Navigator")
    case "greet Speleologist" => println(self.path.name + ": Nice to see you here! - Speleologist")
    case "greet Navigator" => println(self.path.name + ": Well - Navigator")

    case "move to room 1 at level 1" => println(self.path.name + ": I feel a cold wind blowing from a nearby cavern.")
    case "move to room 2 at level 1" => println(self.path.name + ": I smell something terrible nearby.")
    case "move to room 3 at level 1" => println(self.path.name + ": I hear a rustling.")
    case "move to room 1 at level 2" => println("The bats whisk you away! to room 1 at level 1")
    case "move to room 2 at level 2" => println("The wumpus ate you up!\nGAME OVER")
    case "move to room 3 at level 2" => println("You fall into a bottomless pit!\nGAME OVER")
    case "shoot to room 2 at level 2" => println("You win! You've killed Wumpus! ")
  }
}

object Main extends App {
  val system = ActorSystem("WumpusWorld")
  // create actors
  val gameMaster = system.actorOf(Props[HuntTheWumpus], name = "gameMaster")
  val speleologistActor = system.actorOf(Props[HuntTheWumpus], name = "speleologist")
  val navigatorActor = system.actorOf(Props[HuntTheWumpus], name = "navigator")

  gameMaster ! "Greet"
  Thread.sleep(10)
  speleologistActor ! "Speleologist"
  Thread.sleep(10)
  gameMaster ! "greet Speleologist"
  Thread.sleep(10)
  navigatorActor ! "Navigator"
  Thread.sleep(10)
  gameMaster ! "greet Navigator"
  Thread.sleep(10)
  navigatorActor ! "move to room 1 at level 1"
  Thread.sleep(10)
  navigatorActor ! "move to room 2 at level 1"
  Thread.sleep(10)
  navigatorActor ! "shoot to room 2 at level 2"
  system.terminate()
}




