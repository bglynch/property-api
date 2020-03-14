package demo.dublin.dashboard.functions;

import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@NoArgsConstructor
public class HouseFunctions {

  public Function<String, String> toLowerCase = String::toLowerCase;
  public Function<String, String> removePostcode = a -> a.replaceAll("([, ]+dublin \\d+)+", "");
  public Function<String, String> removeDuplicateArea = a -> a.replaceAll("[, ]*\\b(\\w+)\\b, \\1$", "\1");
  public Function<String, String> removeCharacters = a -> a.replaceAll("(\\\\{2,}|'|\\([\\w ]+\\))", "");


}
