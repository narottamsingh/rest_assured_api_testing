package com.bce.api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports reports;
    public ExtentTest test;

    public String reportName;

    public void onStart(ITestContext testContext) {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        reportName = "Test-report-" + timeStamp;

        sparkReporter = new ExtentSparkReporter(".//reports//" + reportName);

        sparkReporter.config().setDocumentTitle("RestAssuredTestAutomationReport");
        sparkReporter.config().setReportName("EmployeeTest");
        sparkReporter.config().setTheme(Theme.STANDARD);

        reports = new ExtentReports();
        reports.attachReporter(sparkReporter);
        reports.setSystemInfo("Application", "Employee Test");
        reports.setSystemInfo("Operating System", System.getProperty("os.name"));
        reports.setSystemInfo("User Name", System.getProperty("user.name"));
        reports.setSystemInfo("Environment", "QA");
        reports.setSystemInfo("User", "Narottam Singh");

    }

    public void onTestSuccess(ITestResult result) {
        test = reports.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.PASS, "Test Passed");
    }


    public void onTestFailure(ITestResult result) {
        test = reports.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.FAIL, "Test Failed");
        test.log(Status.FAIL, result.getThrowable().getMessage());
    }

    public void onTestSkipped(ITestResult result) {
        test = reports.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.SKIP, "Test Skipped");
        test.log(Status.SKIP, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext context) {
        reports.flush();
    }
}
