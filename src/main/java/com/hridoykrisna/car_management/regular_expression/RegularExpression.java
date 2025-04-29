package com.hridoykrisna.car_management.regular_expression;
//
//import opennlp.tools.namefind.NameFinderME;
//import opennlp.tools.namefind.TokenNameFinderModel;
//import opennlp.tools.tokenize.SimpleTokenizer;
//import opennlp.tools.util.Span;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpression {
    public LocalDate extractDate(String prompt) {
        Pattern pattern = Pattern.compile("at\\s+(\\d{2}-\\d{2}-\\d{4})");
        Matcher matcher = pattern.matcher(prompt);
        if (matcher.find()) {
            LocalDate result = LocalDate.parse(matcher.group(1), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            System.out.println("Date: " + result);
            return result;
        }
        return null;
    }

    public String extractTime(String promt) {
        // Define the regular expression for 12-hour format
        String timePattern = "(1[0-2]|0?[1-9]):([0-5][0-9]) ?([AaPp][Mm])";

        // Compile the pattern
        Pattern pattern = Pattern.compile(timePattern);
        Matcher matcher = pattern.matcher(promt);

        // Find and display all matches
        while (matcher.find()) {
            String time = matcher.group(); // Full match
            String hour = matcher.group(1); // Hour part
            String minute = matcher.group(2); // Minute part
            String period = matcher.group(3); // AM/PM part

            System.out.println("Found time: " + time);
            System.out.println("Hour: " + hour);
            System.out.println("Minute: " + minute);
            System.out.println("Period: " + period);

            return time;
        }
        return null;
    }

    public String extractStartLocation(String prompt) {
        // Example regex to extract "from Office"
        Pattern pattern = Pattern.compile("from\\s+(\\w+)");
        Matcher matcher = pattern.matcher(prompt);
        if (matcher.find()) {
            System.out.println("Start Location: " + matcher.group(1));
            return matcher.group(1);  // This returns "Office"
        }
        return "Office"; // default if not found
    }

    public String extractDestination(String prompt) {
        // Example regex to extract destination "to XYZ"
        Pattern pattern = Pattern.compile("to\\s+(\\w+)");
        Matcher matcher = pattern.matcher(prompt);
        if (matcher.find()) {
            System.out.println("End Location: " + matcher.group(1));
            return matcher.group(1);  // This returns "XYZ"
        }
        return null;
    }
}
