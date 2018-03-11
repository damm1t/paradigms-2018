import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CalcMD5 {
  /**
   * The entry point of the program.
   *
   * @param args argument: file name
   */
  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Usage: Name input file");
      return;
    }
    MessageDigest md;
    try {
      md = MessageDigest.getInstance("MD5");
      for (String name: Files.readAllLines( Paths.get( args[0] ))) {
        byte[] buf = Files.readAllBytes(Paths.get( name ));
        System.out.println(DatatypeConverter.printHexBinary(md.digest(buf)).toUpperCase());
      }
    } catch (IOException | NoSuchAlgorithmException e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
    catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Usage: command line arguments");
    }
  }
}
