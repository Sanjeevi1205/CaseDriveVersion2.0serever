package runner;

import io.cucumber.core.cli.Main;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CommonRunnerClass {
        public static void main(String[] args) {
                // First delete any existing report files
                deleteExistingReports();

                // Get all feature files with their tags
                List<String[]> featureFilesWithTags = getFeatureFilesWithTags();

                if (featureFilesWithTags.isEmpty()) {
                        throw new RuntimeException("No feature files found in execution_order.txt");
                }

                // Build the complete Cucumber command line
                List<String> cucumberOptions = buildCucumberOptions(featureFilesWithTags);

                // Run Cucumber once with all features
                System.out.println("Starting Cucumber with all features");
                int exitCode = Main.run(cucumberOptions.toArray(new String[0]),
                        Thread.currentThread().getContextClassLoader());

                System.out.println("All features executed. Exit code: " + exitCode);
        }

        private static void deleteExistingReports() {
                try {
                        Files.deleteIfExists(Paths.get("test-output/ExtentReport.html"));
                        Files.deleteIfExists(Paths.get("test-output/SparkReport.html"));
                } catch (Exception e) {
                        System.out.println("Could not delete existing reports: " + e.getMessage());
                }
        }

        private static List<String> buildCucumberOptions(List<String[]> featureFilesWithTags) {
                List<String> options = new ArrayList<>();
                // Add common options
                options.add("--glue");
                options.add("stepdefinition");

                // Add all feature files
                for (String[] featureWithTag : featureFilesWithTags) {
                        options.add(featureWithTag[0]);
                }

                // Add all tags (combine them with OR logic)
                StringBuilder tagsBuilder = new StringBuilder();
                for (String[] featureWithTag : featureFilesWithTags) {
                        if (!featureWithTag[1].isEmpty()) {
                                if (tagsBuilder.length() > 0) {
                                        tagsBuilder.append(" or ");
                                }
                                tagsBuilder.append(featureWithTag[1]);
                        }
                }

                if (tagsBuilder.length() > 0) {
                        options.add("--tags");
                        options.add(tagsBuilder.toString());
                }

                // Reporting configuration - ensures single report
                options.add("--plugin");
                options.add("com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:test-output/ExtentReport.html");

                options.add("--plugin");
                options.add("pretty");

                options.add("--plugin");
                options.add("html:test-output/SparkReport.html");
                return options;
        }

        private static List<String[]> getFeatureFilesWithTags() {
                List<String[]> featureFiles = new ArrayList<>();
                try {
                        String filePath = "src/test/resources/execution_order.txt";
                        System.out.println("Checking file existence: " + Paths.get(filePath).toAbsolutePath());

                        if (!Files.exists(Paths.get(filePath))) {
                                throw new RuntimeException("execution_order.txt not found!");
                        }

                        List<String> lines = Files.readAllLines(Paths.get(filePath));
                        System.out.println("Read lines from execution_order.txt: " + lines);

                        for (String line : lines) {
                                if (!line.trim().isEmpty()) {
                                        String[] parts = line.split(" ");
                                        String featureFilePath = "src/test/resources/FeatureFiles/" + parts[0];

                                        if (!Files.exists(Paths.get(featureFilePath))) {
                                                throw new RuntimeException("Feature file not found: " + featureFilePath);
                                        }

                                        String tag = (parts.length > 1) ? parts[1].trim() : "";
                                        featureFiles.add(new String[]{featureFilePath, tag});
                                }
                        }
                } catch (Exception e) {
                        throw new RuntimeException("Error reading execution_order.txt", e);
                }
                return featureFiles;
        }
}