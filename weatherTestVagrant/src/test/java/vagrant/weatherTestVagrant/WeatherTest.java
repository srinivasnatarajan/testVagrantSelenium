package vagrant.weatherTestVagrant;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import vagrant.commons.apiCommons;

public class WeatherTest extends base{

	//Assuming the deviation allowed as 5
	public int deviationallowed = 5;
	
	@Test 
	public void compareTheWheatherReportAPIandWebsite() throws IOException{ 
		ArrayList<String>	 tempValues = new ArrayList<String>(); 
		List<WebElement> TemparatureDetails = Weather.getDetailsFromWebsite(driver); 
		for ( WebElement we:TemparatureDetails) { 
			tempValues.add(we.getText()); 
		}
		System.out.println(tempValues);
		float farhenitetemp = apiCommons.getWeatherDetails("imperial", "Bengaluru");
		System.out.println(farhenitetemp);
		float celciustemp = apiCommons.getWeatherDetails("metric", "Bengaluru");
		System.out.println(celciustemp);
		boolean temp = apiCommons.getWeatherDiff(tempValues, farhenitetemp, celciustemp, deviationallowed);

		try{
			if(!temp) {
				String msg = "Deviation constraint crossed";
				temparatureDifferenceOutsidetheexpectedRangeException ex = new temparatureDifferenceOutsidetheexpectedRangeException(msg);
				throw ex;
			}
		}catch(temparatureDifferenceOutsidetheexpectedRangeException e) {
			e.printStackTrace();
		}
		assertEquals(temp, true);
	}

}
