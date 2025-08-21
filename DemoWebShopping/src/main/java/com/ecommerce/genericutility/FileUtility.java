package com.ecommerce.genericutility;

import java.util.Random;

public class FileUtility {

	public int getRandomNumber() {
		Random random = new Random();
		int generateNo = random.nextInt(5000);
		return generateNo;
	}

}
