package GUI;

public class Converter {

	public static String converter(double number) {
		String num = Double.toString(number);
		int y = 0;
		String x = "";

		if (number == 0)
			return "    0.00";

		for (int i = 0; i < 10; i++) {
			if (number >= Math.pow(10, i) && number < Math.pow(10, i + 1)) {

				y = i + 2;
			}
		}
		for (int i = 0; i < 10; i++) {
			if (number < -Math.pow(10, i) && number >= -Math.pow(10, i + 1)) {

				y = i + 2;
			}
		}

		for (int i = 0; i <= y; i++) {
			x += num.charAt(i);
		}
		x += "0";
		return x;
	}

	public static double converter2(String x) {
		double sum = 0;
		String ss = "";
		for (int i = 0; i < x.length(); i++) {
			if (String.valueOf(x.charAt(i)).equals(",")) {
				continue;

			}
			ss += String.valueOf(x.charAt(i));
			sum = Double.parseDouble(ss);

		}

		return sum;
	}

}