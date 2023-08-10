/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author colemiller
 */
java.
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
