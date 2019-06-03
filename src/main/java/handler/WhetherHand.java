package handler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

@Component
public class WhetherHand {

	public String getwhether(int citycode) throws ParseException
	{
		String minTempTime="";
		  String time="";
		String output="";
		String obj="";
		JSONObject jobj = null;
		System.out.println(citycode);
		//Method body
		//In the method body you should make a request to the openweather server with an api key which you can get by registering in the website. You can achieve this with Unirest library (it's the easiest way)
		
	 try {
		 
		// URL url=new URL("http://api.openweathermap.org/data/2.5/forecast/hourly?zip="+citycode+",us&appid=7a500e1d9460b369bffce00762e12ddd");
			URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?zip="+citycode+"&appid=d2855228e3404e2bd7a316a4d012fcd9");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				return "City not found";
				
				/*throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());*/
			}

			Scanner sc = new Scanner(url.openStream());

			while(sc.hasNext())

			{

			output+=sc.nextLine();

			}

			
			System.out.println(output);

			sc.close();
			conn.disconnect();
			
			 JSONParser parse = new JSONParser();
			  jobj = (JSONObject)parse.parse(output); 
			  JSONArray arr = (JSONArray)jobj.get("list");
			//  JSONArray arr1=(JSONArray)jobj.get("city");
			  int i=arr.size();
			  double min=500;
			 
			  LocalDate today = LocalDate.now( ZoneId.of( "America/Montreal" ) ) ;
				 // Date today=new Date();
				  String td=today.toString();
				
			  
			  int cnt=0,index=0;
			  while(cnt<8)
			  {
				     
				 
				JSONObject jsonobj_1 = (JSONObject)arr.get(index);
				  
				   JSONObject obje=(JSONObject)jsonobj_1.get("main");
				   time=jsonobj_1.get("dt_txt").toString();
				   
				   if(time.contains(td))
				   {
					   index++;
					   
				   }
				   
				   else
				   {   
					   String s=obje.get("temp_min").toString();
						  double d=Double.parseDouble(s);
						  System.out.println(s+" " +time);
						  
						  if(d<min)
						  {		  
							  min=d;
							  minTempTime=time;
							  
						  }
						  cnt++;
						  index++;
						   
				   }
				    
			  }
			  
			 
			  System.out.println(minTempTime);
			  System.out.println(td);
			   

		  }  catch (IOException e) {

			e.printStackTrace();

		  }
	return "Coolest tempreture hours are onwords "+minTempTime+" for next 3 hours";

		
	}
}
