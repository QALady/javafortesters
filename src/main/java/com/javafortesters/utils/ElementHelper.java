package com.javafortesters.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

/**
 * Created by QA_Lady on 1/17/2015.
 */
public class ElementHelper extends HelperBase {
    public ElementHelper(ApplicationManager manager) {
        super(manager);
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
