package com.javafortesters.tests;

import com.javafortesters.utils.ApplicationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static Logger LOG = LoggerFactory.getLogger(TestBase.class.getName());
    private static Logger BROWSER_LOG = LoggerFactory.getLogger("Browser");

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
