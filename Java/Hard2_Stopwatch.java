import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;
import java.util.Scanner;

/**
 * Derek Thomas
 *
 * This is a stopwatch program, the user can start and stop the stopwatch as well as get lap times.
 * It will also record the times to a .log file.
 */

public class Hard2_Stopwatch {
  boolean running = false;
  Timestamp start = null;
  int lapCounter = 1;
  double previousLap = 0;

  public static void main(String[] args) throws IOException {
    Scanner input = new Scanner(System.in);
    Hard2_Stopwatch sw = new Hard2_Stopwatch();
    while (true) {
      System.out.println("(s)tart/(s)top, (l)ap time, and (q)uit.");
      String cmd = input.nextLine();
      switch (cmd.toUpperCase()) {
        case "S":
          sw.start_stop();
          break;
        case "L":
          sw.lap();
          break;
        case "Q":
          sw.quit();
          break;
        default:
          System.out.println("The only valid inputs are 's' 'l' or 'q'");
          break;
      }
    }
  }

  public int start_stop() throws IOException {
    if (running) {
      double diff = (double) this.get_time_diff();
      diff = diff / 1000;
      running = false;
      String string = String.format("Clock Stopped. Time is: %1$.3fs\n", diff);
      Files.write(Paths.get("./stopwatch.log"), string.getBytes(), StandardOpenOption.APPEND);
      System.out.println("Clock Stopped. Time is: " + diff + "s");
      lapCounter = 1;
      return 0;
    }
    this.start = new Timestamp(System.currentTimeMillis());
    running = true;
    Files.write(Paths.get("./stopwatch.log"), "Clock Started.\n".getBytes(), StandardOpenOption.CREATE);
    System.out.println("Clock Started.");
    return 0;
  }

  public int lap() throws IOException {
    if (!running) {
      System.out.println("You need to start the clock before you can get lap times.");
      return 0; 
    }
    double diff = (double) this.get_time_diff();
    diff = diff / 1000;
    String string = String.format(
        "Lap %1$d time is: %2$.3fs + %3$.3fs\n", 
        lapCounter, 
        diff, 
        (diff - previousLap));
    Files.write(Paths.get("./stopwatch.log"), string.getBytes(), StandardOpenOption.APPEND);
    System.out.println(string);
    lapCounter++;
    previousLap = diff;
    return 0;
  }

  public void quit() throws IOException {
    if (running) {
      this.start_stop();
    }
    System.exit(0);
  }

  public long get_time_diff() {
    Timestamp now = new Timestamp(System.currentTimeMillis());
    return (now.getTime() - this.start.getTime());
  }
}
