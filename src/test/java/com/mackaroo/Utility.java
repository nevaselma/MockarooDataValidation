package com.mackaroo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utility {

	public static List<String> readFromFile(int columnNumber) {
		List<String> columnData = new ArrayList<>();

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("C:\\Users\\hurig\\Downloads\\MOCK_DATA.csv"));
			String line = "";
			// line = br.readLine();

			while (((line = br.readLine()) != null)) {

				String[] cell = line.split(",");
				columnData.add(cell[columnNumber]);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return columnData;
	}

	public static String longestCityName(List<String> list) {

		Collections.sort(list);
		int largestString = list.get(0).length();
		int index = 0;

		for (int i = 1; i < list.size(); i++) {

			if (list.get(i).length() > largestString) {
				largestString = list.get(i).length();
				index = i;
			}
		}

		return list.get(index);
	}

	public static String shortestCityName(List<String> list) {

		Collections.sort(list);
		int shortestString = list.get(0).length();
		int index = 0;

		for (int i = 1; i < list.size(); i++) {

			if (list.get(i).length() < shortestString) {
				shortestString = list.get(i).length();
				index = i;
			}
		}

		return list.get(index);
	}

	public static int uniqueEntries(List<String> ls) {
		
		List<String> tempList = new ArrayList<>();

		for (String each : ls) {
			if (!tempList.contains(each)) {
				tempList.add(each);

			}

		}
		return tempList.size();

	}

}