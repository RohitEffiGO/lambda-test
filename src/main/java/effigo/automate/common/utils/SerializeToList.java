package effigo.automate.common.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SerializeToList {
	private SerializeToList() {

	}

	public static List<String> convertToList(String input) {
		return Arrays.stream(input.split(",")).map(String::trim).collect(Collectors.toList());
	}
}
