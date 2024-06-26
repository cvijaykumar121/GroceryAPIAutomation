package com.api.Utilities;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ExtentManager {
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static synchronized ExtentTest getTest() {
        return extentTest.get();
    }

    public static synchronized void setTest(ExtentTest test) {
        extentTest.set(test);
    }

    public static synchronized void log(Status status, String message) {
        ExtentTest test = getTest();
        if (test != null) {
            test.log(status, message);
        } else {
            throw new NullPointerException("ExtentTest instance is null. Ensure that the test has been properly started.");
        }
    }

    public static synchronized void logPass(String message) {
        ExtentTest extentTest = getTest();
        if (extentTest != null) {
            extentTest.pass(message);
        } else {
            throw new NullPointerException("ExtentTest instance is null. Ensure that the test has been properly started.");
        }
    }

    public static synchronized void logFail(String message) {
        getTest().fail(message);
    }

    public static synchronized void logInfo(String message) {
        getTest().info(message);
    }

    public static synchronized void logSkip(String message) {
        getTest().skip(message);
    }
}
