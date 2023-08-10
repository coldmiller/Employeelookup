
public class test {
    BufferedReader reader =
                   new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();
    	byte[] bytes = name.getBytes();
  StringBuilder binary = new StringBuilder();
  for (byte b : bytes)
  {
     int val = b;
     for (int i = 0; i < 8; i++)
     {
        binary.append((val & 128) == 0 ? 0 : 1);
        val <<= 1;
     }
     binary.append(' ');
  }
  System.out.println("'" + s + "' to binary: " + binary);
}
