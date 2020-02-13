package demo.dublin.dashboard.functions;

import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@NoArgsConstructor
public class HouseFunctions {

  public Integer calculateMedian(List<Integer> list) {
    Collections.sort(list);
    Integer[] numArray = list.toArray(new Integer[list.size()]);
    double median;
    if (list.size() % 2 == 0)
      median = ((double)numArray[numArray.length/2] + (double)numArray[numArray.length/2 - 1])/2;
    else
      median = (double) numArray[numArray.length/2];

    return (int) median;
  }


}
