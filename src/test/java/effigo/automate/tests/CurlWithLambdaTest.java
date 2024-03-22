package effigo.automate.tests;

import static org.testng.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import effigo.automate.common.utils.CurlCommandBuilder;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;

@Slf4j
public class CurlWithLambdaTest {
	private String usernameString;
	private String passwordString;
	private CurlCommandBuilder curlCommandBuilder;

	@BeforeTest
	public void setup() {
		this.usernameString = RandomString.make(8);
		this.passwordString = RandomString.make(8) + "@A1";
		this.curlCommandBuilder = new CurlCommandBuilder();
	}

	@Test
	public void testBasicApiCall() throws IOException, InterruptedException {
		String url = "\"https://demoqa.com/Account/v1/User\"";
		Gson gson = new Gson();
		Map<String, String> dataMap = new LinkedHashMap<>();
		dataMap.put("userName", this.usernameString);
		dataMap.put("password", this.passwordString);
		String data = gson.toJson(dataMap);
		String requestType = "POST";
		String[] commandString = curlCommandBuilder.curlCommandBuilder(url, data, requestType);

		ProcessBuilder processBuilder = new ProcessBuilder(commandString);
		processBuilder.directory(new File("C:\\Users\\Rohit\\eclipse-workspace\\automate\\test-output\\api-calls"));
		Process process = processBuilder.start();
		InputStream inputStream = process.getInputStream();

		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

		String responseString = reader.readLine();
		log.info(responseString);
		assertEquals(true, responseString.contains("userID"));
	}
}
