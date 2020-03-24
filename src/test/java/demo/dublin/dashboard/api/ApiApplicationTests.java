package demo.dublin.dashboard.api;

import demo.dublin.dashboard.functions.ControllerFunctions;
import demo.dublin.dashboard.functions.HouseFunctions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class ApiApplicationTests {

	@Autowired
	private ControllerFunctions hf;

	@Test
	public void contextLoads() {
	}

	@Test
	public void doesTheAddressHaveAPostcode() {
		List<Integer> myList = new ArrayList<>(Arrays.asList(2,2,3,4,100000));
		int[] myArray = {2,2,3,4,100000};
		assertThat(hf.calculateMedian(myList), is(3));
	}

	@Test
	public void functionalRegex() {
		String test01 = "42 Merton Drive, Ranelagh, Dublin 6, Dublin 6";
		String test02 = "8 Saint Finian\\\\'s Grove, Lucan";
		HouseFunctions houseFunctions = new HouseFunctions();

		assertThat(houseFunctions.toLowerCase.apply(test01), is("42 merton drive, ranelagh, dublin 6, dublin 6"));
		assertThat(houseFunctions.toLowerCase.andThen(houseFunctions.removePostcode).apply(test01), is("42 merton drive, ranelagh"));
		assertThat(
				houseFunctions
						.toLowerCase
						.andThen(houseFunctions.removePostcode)
						.andThen(houseFunctions.removeCharacters)
						.apply(test02), is("8 saint finians grove, lucan"));
	}

}
