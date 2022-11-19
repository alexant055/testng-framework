package com.automation.helpers.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentReportManager {
    public static ExtentReports ExtentReport() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(
                new File(System.getProperty("user.dir") + "/target/reports/extent.html"));

        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        ExtentReports reports = new ExtentReports();
        reports.attachReporter(reporter);
        reports.setSystemInfo("Tester", "Alexander, Antony");

        return reports;
    }
}
