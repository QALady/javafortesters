package com.javafortesters.tests;

import com.javafortesters.utils.ApplicationManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import java.util.Random;

/**
 * Created by QA_Lady on 1/16/2015.
 */
public class TestBase {
    public static ApplicationManager appManager;


    @BeforeTest
    public void setUp() throws Exception {
        String configFile = System.getProperty("configFile", "application.properties");
        Properties properties = new Properties();
        properties.load(new FileReader(new File("application.properties")));
        appManager = new ApplicationManager(properties);
    }

//    @BeforeMethod
//    public void checkAppManager() {
//        if (appManager == null) {
//            appManager = new ApplicationManager();
//        }
//    }


    @AfterTest
    public void tearDown() throws Exception {
        appManager.quit();

    }

    public static String generateRandomString(String text) {
        Random rnd = new Random();
        if (rnd.nextInt(8) == 0) {
            return "";
        } else {
            return text + rnd.nextInt();
        }
    }
}
