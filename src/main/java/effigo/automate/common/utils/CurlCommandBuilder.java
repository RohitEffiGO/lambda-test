package effigo.automate.common.utils;

public class CurlCommandBuilder {
	public String[] curlCommandBuilder(String url, String data, String req) {
		return new String[] { "curl", "-X", req, url, "-H", "\"accept: application/json\"", "-H",
				"\"Content-Type: application/json\"", "-d", data.replace("\"", "\\\"") };
	}
}
