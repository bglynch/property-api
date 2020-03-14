package demo.dublin.dashboard.functions;

import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@NoArgsConstructor
public class HouseFunctions {

//  public static Integer calculateMedian(List<Integer> list) {
//    Collections.sort(list);
//    Integer[] numArray = list.toArray(new Integer[list.size()]);
//    double median;
//    if (list.size() % 2 == 0)
//      median = ((double) numArray[numArray.length / 2] + (double) numArray[numArray.length / 2 - 1]) / 2;
//    else
//      median = (double) numArray[numArray.length / 2];
//
//    return (int) median;
//  }

  public Function<String, String> toLowerCase = String::toLowerCase;
  public Function<String, String> removePostcode = a -> a.replaceAll("([, ]+dublin \\d+)+", "");
  public Function<String, String> removeDuplicateArea = a -> a.replaceAll("[, ]*\\b(\\w+)\\b, \\1$", "\1");
  public Function<String, String> removeCharacters = a -> a.replaceAll("(\\\\{2,}|'|\\([\\w ]+\\))", "");

  public static String ExtractStreet(String address, String locality) {
    System.out.println(address.split(",").length);
    address = address.replaceAll("\\s{2,}", " ");
    address = address.split("[, ]+" + locality + "(?! (Road|Avenue))")[0];
    address = address.replaceAll("[, ]+$", "");
    if (address.contains(",")) {
      address = address.split(",")[address.split(",").length - 1].trim();    // get last item in address
    }
    address = address.replaceAll("'", "");
    return ExtractStreet(address);
  }

  static String ExtractStreet(String address) {
    address = address.replaceAll("^\\d{1,3}(-\\d{1,3})? ", ""); // remove number from start of string
    address = address.replaceAll("Apartments", "").trim();      // remove number from start of string
    address = address.replaceAll("\\\\", "").trim();            // remove number from start of string
    address = address.replaceAll("(\\(.+\\))", "").trim();      // remove number from start of string
    return address;
  }

}
