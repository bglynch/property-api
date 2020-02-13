package demo.dublin.dashboard.api;

import demo.dublin.dashboard.functions.HouseFunctions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void doesTheAddressHaveAPostcode() {
		List<Integer> myList = new ArrayList<>(Arrays.asList(2,2,3,4,100000));
		int[] myArray = {2,2,3,4,100000};

		HouseFunctions houseFunctions = new HouseFunctions();
		assertThat(houseFunctions.calculateMedian(myList), is(3));
	}

}
