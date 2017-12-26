object Day20 {

  case class Particle(id: Int, x: Long, y: Long, z: Long, vx: Long, vy: Long, vz: Long, ax: Long, ay: Long, az: Long)

  def parse(input: String): Array[Particle] = {
    val inputLine = """p=<(-?\d+),(-?\d+),(-?\d+)>, v=<(-?\d+),(-?\d+),(-?\d+)>, a=<(-?\d+),(-?\d+),(-?\d+)>""".r
    input.split("\n").zipWithIndex.map {
      case (inputLine(x, y, z, vx, vy, vz, ax, ay, az), idx) =>
        Particle(idx, x.toInt, y.toInt, z.toInt, vx.toInt, vy.toInt, vz.toInt, ax.toInt, ay.toInt, az.toInt)
    }
  }

  def move(p: Particle): Particle = p.copy(vx = p.vx + p.ax, vy = p.vy + p.ay, vz = p.vz + p.az,
                                           x = p.x + p.vx + p.ax, y = p.y + p.vy + p.ay, z = p.z + p.vz + p.az)

  def part1(input: String): Int = parse(input).minBy {
    case Particle(_, x, y, z, vx, vy, vz, ax, ay, az) => (ax.abs + ay.abs + az.abs, vx.abs + vy.abs + vz.abs, x.abs + y.abs + z.abs)
  }.id

  def part2(input: String): Int = {
    var finishedParticles = 0
    var particles = parse(input)
    var byAccel = Array[Particle]()
    do {
      particles = particles.map(move).groupBy {
        case Particle(_, x, y, z, _, _, _, _, _, _) => (x, y, z)
      }.flatMap {
        case (_, particlesAtPosition) if particlesAtPosition.length == 1 => particlesAtPosition
        case _ => Array[Particle]()
      }.toArray

      particles = {
        val byAX = particles.sortBy { case Particle(_, _, _, _, _, _, _, ax, _, _) => ax }
        val byVX = particles.sortBy { case Particle(_, _, _, _, vx, _, _, _, _, _) => vx }
        val byX = particles.sortBy { case Particle(_, x, _, _, _, _, _, _, _, _) => x }
        val newParticles = byAX.zipWithIndex.dropWhile {
          case (p, idx) => byVX(idx).id == p.id && byX(idx).id == p.id && p.vx != 0
        }.map {
          case (p, _) => p
        }
        finishedParticles += particles.length - newParticles.length
        newParticles
      }

      particles = {
        val byAY = particles.sortBy { case Particle(_, _, _, _, _, _, _, _, ay, _) => ay }
        val byVY = particles.sortBy { case Particle(_, _, _, _, _, vy, _, _, _, _) => vy }
        val byY = particles.sortBy { case Particle(_, _, y, _, _, _, _, _, _, _) => y }
        val newParticles = byAY.zipWithIndex.dropWhile {
          case (p, idx) => byVY(idx).id == p.id && byY(idx).id == p.id && p.vy != 0
        }.map {
          case (p, _) => p
        }
        finishedParticles += particles.length - newParticles.length
        newParticles
      }

      particles = {
        val byAZ = particles.sortBy { case Particle(_, _, _, _, _, _, _, _, _, az) => az }
        val byVZ = particles.sortBy { case Particle(_, _, _, _, _, _, vz, _, _, _) => vz }
        val byZ = particles.sortBy { case Particle(_, _, _, z, _, _, _, _, _, _) => z }
        val newParticles = byAZ.zipWithIndex.dropWhile {
          case (p, idx) => byVZ(idx).id == p.id && byZ(idx).id == p.id && p.vz != 0
        }.map {
          case (p, _) => p
        }
        finishedParticles += particles.length - newParticles.length
        newParticles
      }

    } while (!particles.isEmpty)
    finishedParticles
  }

  def solve(): (Int, Int) = {
    val input = DataFolder.openFile("day20.txt").mkString
    (part1(input), part2(input))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }

}
