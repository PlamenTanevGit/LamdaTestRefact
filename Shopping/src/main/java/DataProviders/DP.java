package DataProviders;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class DP {

	@DataProvider(name = "productInputs")
	public Object[][] getData(Method m) {
		
		if (m.getName().equals("t2_SearchField_verifyAutoSuggestResults")) {
			return new Object[][] { { "HTC Touch HD", 5 }, { "Palm Treo Pro", 2 },

			};
		}
		else if (m.getName().equals("t3_search_ProductInTheSearchFieldAndVerifyResults")) {
			return new Object[][] { { "Palm Treo Pro", "$337.99",2 }, { "HTC Touch HD", "$146.00",8 },

			};
		}
		else if (m.getName().equals("t1")) {
			return new Object[][] { { "HTC Touch HD" },

			};
		}
		
		else if (m.getName().equals("t1_shoppingCartVerificationAndRemoveProducts")) {
			return new Object[][] { { "HTC Touch HD","Product 1","1","$146.00","$146.00" },

			};
		}		

		return new Object[][] { { "HTC Touch HD", 5 }, };
	}

	@DataProvider(name = "getDataFromCSV")
	public static Object[][] getDataFromCSV () throws Exception {
		return Utils.CSVReaderFile.getCSVData(System.getProperty("user.dir") + "\\testData\\sampleCSVdata.csv", false);
	}
	
	

}
