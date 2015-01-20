package com.javafortesters.tests;

import com.javafortesters.utils.ApplicationManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

/**
 * Created by QA_Lady on 1/16/2015.
 */
public class TestBase {
    public static ApplicationManager appManager;


    @BeforeTest
    public void setUp() throws Exception {
        appManager = new ApplicationManager();

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

}
