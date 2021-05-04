package vagrant.commons;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class apiCommons{

	public static float getWeatherDetails(String metric,String city) throws IOException {
		float tempvalue = 0;
		try {
			OkHttpClient client = new OkHttpClient().newBuilder()
					.build();
			Request request = new Request.Builder()
					.url("http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=7fe67bf08c80ded756e598d6f8fedaea&units="+metric)
					.method("GET", null)
					.build();
			okhttp3.Response response;

			response = client.newCall(request).execute();

			String jsonResonse =  response.body().string();
			JSONObject jj = new JSONObject(jsonResonse);
			JSONObject tempu = (JSONObject) jj.get("main");
			tempvalue = Float.parseFloat(tempu.get("temp").toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tempvalue;
	}
	
	
	public static boolean getWeatherDiff(ArrayList<String>	tempValues,float farhenit,float celcius,float diffValue) {
		boolean Fflag = true;
		boolean Cflag = true;
		for(int i=0;i<tempValues.size();i++) {
			String value = tempValues.get(i);
			if(value.contains("℉")) {
				value = value.replace("℉", "");
				float TempValue = Float.parseFloat(value);
				float diffValue1 = farhenit>TempValue?(farhenit-TempValue):(TempValue-farhenit);
				System.out.println("Farhenit difference,"+diffValue1);
				if(!(diffValue1 < diffValue)) {
					Fflag = false;
				}
			}else {
				value = value.replace("℃", "");
				float TempValue = Float.parseFloat(value);
				float diffValue1 = celcius>TempValue?(celcius-TempValue):(TempValue-celcius);
				if(!(diffValue1 < diffValue)) {
					Cflag = false;
				}
				System.out.println("Celcius difference,"+diffValue1);
			}
		}
		boolean retFlag = (Cflag && Fflag);
		return retFlag;
		
	}
}
