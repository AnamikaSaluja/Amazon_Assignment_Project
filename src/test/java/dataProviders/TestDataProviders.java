package dataProviders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import com.google.gson.Gson;

public class TestDataProviders {

	final static Logger log = Logger.getLogger(TestDataProviders.class);

	@DataProvider(name = "Negative Login Data")
	public Object[][] negativeData() throws Exception {
		log.info("Into Dataprovider - Negative Login Data");
		Gson gson = new Gson();
		Object[][] obj = null;
		String credentialsFile = System.getProperty("user.dir") + "/src/test/resources/credentials.json";
		log.info("Reading JSON from a file");
		BufferedReader br = new BufferedReader(new FileReader(credentialsFile));
		// convert the json string back to object
		JsonData jsonData = gson.fromJson(br, JsonData.class);
		List<InValidCredential> list = jsonData.getInValidCredentials();
		obj = new Object[list.size()][2];
		int i = 0;
		for (InValidCredential inValidCredential : list) {
			obj[i][0] = inValidCredential.getUserName();
			obj[i][1] = inValidCredential.getPassword();
			i++;
		}

		System.out.println(obj.toString());
		return obj;
	}

}
