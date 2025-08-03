package mailtrap;

import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.gson.JsonElement;
import lombok.extern.java.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Log
public class MailTrapAPI {
    private static final String API_TOKEN = "36f70c148c88e4f0038dbe89482e2c2c";  //Just change the Token to change the environment //Testing
    private static final String INBOX_ID = "3321197";  //Just change the ID to change the environment
    private static final String BASE_URL = "https://mailtrap.io/api/accounts/1694562/inboxes/";
    private static final int MAX_RETRIES = 3;
    private static final long DELAY_SECONDS = 8;

    public static String fetchOTPWithRetry(String uniqueIdentifier) throws Exception {
        int retries = MAX_RETRIES;

        while (retries > 0) {
            try {
                System.out.println("Attempting to fetch OTP...");
                return fetchOTP(uniqueIdentifier);
            } catch (RuntimeException e) {
                System.out.println("Failed to fetch OTP. Retrying in " + DELAY_SECONDS + " seconds...");
                retries--;
                if (retries == 0) {
                    throw new RuntimeException("Failed to fetch OTP after " + MAX_RETRIES + " retries.", e);
                }
                Thread.sleep(DELAY_SECONDS * 2000);
            }
        }
        throw new RuntimeException("Unexpected error in retry logic.");
    }


    private static String fetchOTP(String uniqueIdentifier) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        String endpoint = BASE_URL + INBOX_ID + "/messages";
        HttpGet request = new HttpGet(endpoint);
        request.setHeader("Authorization", "Bearer " + API_TOKEN);
        CloseableHttpResponse response = client.execute(request);
        System.out.println("Fetching messages from inbox...");
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed to fetch messages from Mailtrap");
        }

        String jsonResponse = EntityUtils.toString(response.getEntity());
        System.out.println("Response JSON: " + jsonResponse);
        JsonArray messages = JsonParser.parseString(jsonResponse).getAsJsonArray();
        if (messages.size() == 0) {
            throw new RuntimeException("No messages found in the inbox");
        }

        for (int i = 0; i < messages.size(); i++) {
            JsonObject message = messages.get(i).getAsJsonObject();
            String messageId = message.get("id").getAsString();
            String subject = message.get("subject").getAsString();
            System.out.println("Processing message with ID: " + messageId + ", Subject: " + subject);
            if (subject.contains("Password Reset Request")) {
                return fetchEmailBody(client, messageId);
            }
        }
        throw new RuntimeException("No message found with the unique identifier: " + uniqueIdentifier);
    }

    private static String fetchEmailBody(CloseableHttpClient client, String messageId) throws Exception {
        String bodyEndpoint = BASE_URL + INBOX_ID + "/messages/" + messageId + "/body.html";
        HttpGet bodyRequest = new HttpGet(bodyEndpoint);
        bodyRequest.setHeader("Authorization", "Bearer " + API_TOKEN);
        HttpResponse bodyResponse = client.execute(bodyRequest);

        System.out.println("Fetching email body...");
        if (bodyResponse.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed to fetch email content");
        }

        String emailBody = EntityUtils.toString(bodyResponse.getEntity());
        System.out.println("Email Body Fetched:\n" + emailBody);

        Pattern otpPattern = Pattern.compile("<strong>(\\d{6})</strong>");
        Matcher matcher = otpPattern.matcher(emailBody);
        if (matcher.find()) {
            String otp = matcher.group(1);
            System.out.println("OTP Found: " + otp);
            return otp;
        }
        throw new RuntimeException("OTP not found in email body");
    }

    public static void main(String[] args) {
        try {
            String uniqueIdentifier = "Test_Run_" + System.currentTimeMillis();
            System.out.println("Unique Identifier: " + uniqueIdentifier);
            String otp = fetchOTPWithRetry(uniqueIdentifier);
            System.out.println("OTP retrieved: " + otp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Member Signup
    public static String fetchSignupLink(String uniqueIdentifier) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        String subjectToFind = "CaseDrive Signup Invitation";
        int maxRetries = 10; // Number of attempts to check
        int retryDelayMs = 5000; // 5 seconds delay between retries

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            log.info("Attempt " + attempt + " to find email with subject: " + subjectToFind);

            // Wait before retrying
            Thread.sleep(retryDelayMs);

            // Attempt to get messageId for the subject
            String messageId = getMessageIdForSubject(client, subjectToFind);
            if (messageId != null) {
                log.info("Message found with ID: " + messageId);
                // Extract the signup link once the message is found
                return extractSignupLink(client, messageId);
            }
        }

        throw new RuntimeException("Failed to find email with subject: " + subjectToFind + " after " + maxRetries + " attempts");
    }

    // This method should contain your logic to fetch the messages and find messageId by subject
    private static String getMessageIdForSubject(CloseableHttpClient client, String subjectToFind) throws Exception {
        String endpoint = BASE_URL + INBOX_ID + "/messages";
        HttpGet request = new HttpGet(endpoint);
        request.setHeader("Authorization", "Bearer " + API_TOKEN);
        CloseableHttpResponse response = client.execute(request);
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed to fetch messages from Mailtrap");
        }

        String jsonResponse = EntityUtils.toString(response.getEntity());
        JsonArray messages = JsonParser.parseString(jsonResponse).getAsJsonArray();
        if (messages.size() == 0) {
            return null; // No messages found yet
        }

        for (int i = 0; i < messages.size(); i++) {
            JsonObject message = messages.get(i).getAsJsonObject();
            String messageId = message.get("id").getAsString();
            String subject = message.get("subject").getAsString();

            if (subject.equalsIgnoreCase(subjectToFind)) {
                return messageId; // Found the message
            }
        }

        return null; // Subject not found yet
    }

    private static String extractSignupLink(CloseableHttpClient client, String messageId) throws Exception {
        String bodyEndpoint = BASE_URL + INBOX_ID + "/messages/" + messageId + "/body.html";
        HttpGet bodyRequest = new HttpGet(bodyEndpoint);
        bodyRequest.setHeader("Authorization", "Bearer " + API_TOKEN);
        HttpResponse bodyResponse = client.execute(bodyRequest);

        if (bodyResponse.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed to fetch email content");
        }

        String emailBody = EntityUtils.toString(bodyResponse.getEntity());

        Pattern urlPattern = Pattern.compile("href=\"(https?://[^\"]+/register[^\"]*)\"");
        Matcher matcher = urlPattern.matcher(emailBody);
        if (matcher.find()) {
            String signupUrl = matcher.group(1);
            log.info("Signup URL Found: " + signupUrl);
            return signupUrl;
        }

        throw new RuntimeException("Signup URL not found in email body");
    }

    public static void signUpInvitation(String[] args) {
        try {
            String uniqueIdentifier = "Test_Run_" + System.currentTimeMillis();
            log.info("Unique Identifier: " + uniqueIdentifier);
            String signupUrl = fetchSignupLink(uniqueIdentifier);
            log.info("Signup URL retrieved: " + signupUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // NewCaseMailToAdmin
    public static void verifyLatestEmailSubjectAndBCC(String expectedSubjectPart) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        int maxRetries = 4;
        long pollingInterval = 3000;
        boolean subjectMatched = false;

        for (int attempt = 0; attempt < maxRetries && !subjectMatched; attempt++) {
            log.info("Waiting 10 seconds before fetching emails...");
            Thread.sleep(10000);
            Instant thresholdTime = Instant.now();

            JsonArray messages = fetchLatestMessages(client);
            if (messages == null || messages.isEmpty()) {
                log.warning("No messages found. Retrying...");
                Thread.sleep(pollingInterval);
                continue;
            }
            subjectMatched = processMessages(messages, expectedSubjectPart, thresholdTime, client);
            if (!subjectMatched) {
                log.info("Subject not matched yet. Retrying... (" + (attempt + 1) + "/" + maxRetries + ")");
                Thread.sleep(pollingInterval);
            }
        }
        if (!subjectMatched) {
            throw new RuntimeException("Email containing subject '" + expectedSubjectPart + "' not found after retries.");
        }
    }


    private static JsonArray fetchLatestMessages(CloseableHttpClient client) throws IOException {
        String endpoint = BASE_URL + INBOX_ID + "/messages?limit=5&sort=date:desc";
        HttpGet request = new HttpGet(endpoint);
        request.setHeader("Authorization", "Bearer " + API_TOKEN);

        HttpResponse response = client.execute(request);
        if (response.getStatusLine().getStatusCode() != 200) {
            log.severe("Failed to fetch messages. Status: " + response.getStatusLine().getStatusCode());
            return null;
        }

        String jsonResponse = EntityUtils.toString(response.getEntity());
        if (jsonResponse.isEmpty()) return null;

        return JsonParser.parseString(jsonResponse).getAsJsonArray();
    }

    private static boolean processMessages(JsonArray messages, String expectedSubjectPart, Instant thresholdTime, CloseableHttpClient client) throws Exception {
        for (JsonElement elem : messages) {
            JsonObject msg = elem.getAsJsonObject();

            if (!isRecentEmail(msg, thresholdTime)) continue;

            String subject = msg.has("subject") ? msg.get("subject").getAsString() : "";
            if (subject.toLowerCase().contains(expectedSubjectPart.toLowerCase())) {
                String id = msg.get("id").getAsString();
                log.info("Found matching subject: " + subject);

                fetchAndLogEmailDetails(client, id);
                fetchAndLogEmailContent(client, id);

                return true; // subject matched, exit early
            }
        }
        return false;
    }

    private static boolean isRecentEmail(JsonObject message, Instant thresholdTime) {
        if (!message.has("sent_at")) {
            log.warning("Message missing 'sent_at'");
            return false;
        }
        try {
            Instant sentAt = Instant.parse(message.get("sent_at").getAsString());
            if (!sentAt.isAfter(thresholdTime)) {
                log.info("Skipping email sent at " + sentAt + " (before threshold " + thresholdTime + ")");
                return false;
            }
            return true;
        } catch (DateTimeParseException e) {
            log.warning("Invalid sent_at format");
            return false;
        }
    }

    private static void fetchAndLogEmailDetails(CloseableHttpClient client, String messageId) throws IOException {
        String detailEndpoint = BASE_URL + INBOX_ID + "/messages/" + messageId;
        HttpGet detailRequest = new HttpGet(detailEndpoint);
        detailRequest.setHeader("Authorization", "Bearer " + API_TOKEN);

        HttpResponse detailResponse = client.execute(detailRequest);
        if (detailResponse.getStatusLine().getStatusCode() != 200) {
            log.warning("Failed to fetch email details for ID: " + messageId);
            return;
        }

        String detailJson = EntityUtils.toString(detailResponse.getEntity());
        JsonObject detail = JsonParser.parseString(detailJson).getAsJsonObject();

        log.info("Subject: " + detail.get("subject").getAsString());
        log.info("Sent At: " + detail.get("sent_at").getAsString());
        log.info("From Email: " + detail.get("from_email").getAsString());

        if (detail.has("smtp_information")) {
            JsonObject smtpData = detail.getAsJsonObject("smtp_information").getAsJsonObject("data");
            if (smtpData.has("mail_from_addr"))
                log.info("SMTP From: " + smtpData.get("mail_from_addr").getAsString());

            if (smtpData.has("rcpt_to_addrs")) {
                log.info("Recipients:");
                for (JsonElement rcpt : smtpData.getAsJsonArray("rcpt_to_addrs")) {
                    log.info(" → " + rcpt.getAsString());
                }
            }
        }
    }

    private static void fetchAndLogEmailContent(CloseableHttpClient client, String messageId) throws IOException {
        String bodyEndpoint = BASE_URL + INBOX_ID + "/messages/" + messageId + "/body.html";
        HttpGet bodyRequest = new HttpGet(bodyEndpoint);
        bodyRequest.setHeader("Authorization", "Bearer " + API_TOKEN);

        HttpResponse bodyResponse = client.execute(bodyRequest);
        if (bodyResponse.getStatusLine().getStatusCode() != 200) {
            log.warning("Failed to fetch email content for ID: " + messageId);
            return;
        }

        String emailBody = EntityUtils.toString(bodyResponse.getEntity());
        Pattern pattern = Pattern.compile("<div class=\"content-text\">(.*?)</div>", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(emailBody);

        if (matcher.find()) {
            String contentText = matcher.group(1).replaceAll("<[^>]*>", "").trim();
            log.info("Extracted Content:\n" + contentText);
        } else {
            log.warning("Email content <div class=\"content-text\"> not found.");
        }
    }



    //Secondary mail otp
    public static String fetchSecondaryMailOTP(String uniqueIdentifier) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        String endpoint = BASE_URL + INBOX_ID + "/messages";
        HttpGet request = new HttpGet(endpoint);
        request.setHeader("Authorization", "Bearer " + API_TOKEN);
        CloseableHttpResponse response = client.execute(request);
        System.out.println("Fetching messages from inbox...");
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed to fetch messages from Mailtrap");
        }


        String jsonResponse = EntityUtils.toString(response.getEntity());
        System.out.println("Response JSON: " + jsonResponse);
        JsonArray messages = JsonParser.parseString(jsonResponse).getAsJsonArray();
        if (messages.size() == 0) {
            throw new RuntimeException("No messages found in the inbox");
        }

        for (int i = 0; i < messages.size(); i++) {
            JsonObject message = messages.get(i).getAsJsonObject();
            String messageId = message.get("id").getAsString();
            String subject = message.get("subject").getAsString();
            System.out.println("Processing message with ID: " + messageId + ", Subject: " + subject);
            if (subject.contains("CaseDrive Secondary Email Verification")) {
                return fetchEmailBody(client, messageId);
            }
        }

        throw new RuntimeException("No message found with the unique identifier: " + uniqueIdentifier);
    }


    //MFA OTP
    public static String fetchMFAOTP(String uniqueIdentifier) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        String endpoint = BASE_URL + INBOX_ID + "/messages";
        HttpGet request = new HttpGet(endpoint);
        request.setHeader("Authorization", "Bearer " + API_TOKEN);
        CloseableHttpResponse response = client.execute(request);
        System.out.println("Fetching messages from inbox...");
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed to fetch messages from Mailtrap");
        }


        String jsonResponse = EntityUtils.toString(response.getEntity());
        System.out.println("Response JSON: " + jsonResponse);
        JsonArray messages = JsonParser.parseString(jsonResponse).getAsJsonArray();
        if (messages.size() == 0) {
            throw new RuntimeException("No messages found in the inbox");
        }

        for (int i = 0; i < messages.size(); i++) {
            JsonObject message = messages.get(i).getAsJsonObject();
            String messageId = message.get("id").getAsString();
            String subject = message.get("subject").getAsString();
            System.out.println("Processing message with ID: " + messageId + ", Subject: " + subject);
            if (subject.contains("CaseDrive MFA Verification Code")) {
                return fetchEmailBody(client, messageId);
            }
        }

        throw new RuntimeException("No message found with the unique identifier: " + uniqueIdentifier);
    }


    public static Map<String, String> getEmployeeCredentialsFromEmail() throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        String endpoint = BASE_URL + INBOX_ID + "/messages";
        HttpGet request = new HttpGet(endpoint);
        request.setHeader("Authorization", "Bearer " + API_TOKEN);
        CloseableHttpResponse response = client.execute(request);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed to fetch messages from Mailtrap");
        }

        String jsonResponse = EntityUtils.toString(response.getEntity());
        JsonArray messages = JsonParser.parseString(jsonResponse).getAsJsonArray();

        for (JsonElement element : messages) {
            JsonObject message = element.getAsJsonObject();
            String subject = message.get("subject").getAsString();
            if (subject.toLowerCase().contains("login details")) {
                String messageId = message.get("id").getAsString();
                return extractCredentials(client, messageId);
            }}

        throw new RuntimeException("No email found with subject: CaseDrive Login Details");
    }

    private static Map<String, String> extractCredentials(CloseableHttpClient client, String messageId) throws Exception {
        String bodyEndpoint = BASE_URL + INBOX_ID + "/messages/" + messageId + "/body.html";
        HttpGet bodyRequest = new HttpGet(bodyEndpoint);
        bodyRequest.setHeader("Authorization", "Bearer " + API_TOKEN);
        HttpResponse bodyResponse = client.execute(bodyRequest);

        if (bodyResponse.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed to fetch email content");
        }

        String emailBody = EntityUtils.toString(bodyResponse.getEntity());
        Map<String, String> credentials = new HashMap<>();

        log.info("----- EMAIL BODY START -----");
        log.info(emailBody);
        log.info("----- EMAIL BODY END -------");

        Pattern emailPattern = Pattern.compile("<p>\\s*Email:\\s*([^<\\s]+)\\s*</p>", Pattern.CASE_INSENSITIVE);
        Matcher emailMatcher = emailPattern.matcher(emailBody);
        if (emailMatcher.find()) {
            credentials.put("email", emailMatcher.group(1).trim());
        }

        Pattern passwordPattern = Pattern.compile("<p>\\s*Password:\\s*(\\S+)\\s*</p>", Pattern.CASE_INSENSITIVE);
        Matcher passwordMatcher = passwordPattern.matcher(emailBody);
        if (passwordMatcher.find()) {
            credentials.put("password", passwordMatcher.group(1).trim());
        }

        Pattern linkPattern = Pattern.compile("<a[^>]*href\\s*=\\s*\"(https?://[^\"]*(testing|staging|dev)[^\"]*)\"", Pattern.CASE_INSENSITIVE);
        Matcher linkMatcher = linkPattern.matcher(emailBody);
        if (linkMatcher.find()) {
            credentials.put("link", linkMatcher.group(1).trim());
        }
        if (!credentials.containsKey("email") || !credentials.containsKey("password") || !credentials.containsKey("link")) {
            throw new RuntimeException("Failed to extract email, password, or link from email content.");
        }
        return credentials;
    }

    //ResendOTP(Forgot Password)
    public static String fetchresendOTP(String uniqueIdentifier) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        String endpoint = BASE_URL + INBOX_ID + "/messages";
        HttpGet request = new HttpGet(endpoint);
        request.setHeader("Authorization", "Bearer " + API_TOKEN);
        CloseableHttpResponse response = client.execute(request);
        System.out.println("Fetching messages from inbox...");
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed to fetch messages from Mailtrap");
        }

        String jsonResponse = EntityUtils.toString(response.getEntity());
        System.out.println("Response JSON: " + jsonResponse);
        JsonArray messages = JsonParser.parseString(jsonResponse).getAsJsonArray();
        if (messages.size() == 0) {
            throw new RuntimeException("No messages found in the inbox");
        }

        for (int i = 0; i < messages.size(); i++) {
            JsonObject message = messages.get(i).getAsJsonObject();
            String messageId = message.get("id").getAsString();
            String subject = message.get("subject").getAsString();
            System.out.println("Processing message with ID: " + messageId + ", Subject: " + subject);
            if (subject.contains("Password Reset Request")) {
                return fetchEmailBody(client, messageId);
            }
        }
        throw new RuntimeException("No message found with the unique identifier: " + uniqueIdentifier);
    }

    //ResendOTP(Signup)
    public static String fetchResendOTP(String uniqueIdentifier) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        String endpoint = BASE_URL + INBOX_ID + "/messages";
        HttpGet request = new HttpGet(endpoint);
        request.setHeader("Authorization", "Bearer " + API_TOKEN);
        CloseableHttpResponse response = client.execute(request);
        System.out.println("Fetching messages from inbox...");
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed to fetch messages from Mailtrap");
        }

        String jsonResponse = EntityUtils.toString(response.getEntity());
        System.out.println("Response JSON: " + jsonResponse);
        JsonArray messages = JsonParser.parseString(jsonResponse).getAsJsonArray();
        if (messages.size() == 0) {
            throw new RuntimeException("No messages found in the inbox");
        }

        for (int i = 0; i < messages.size(); i++) {
            JsonObject message = messages.get(i).getAsJsonObject();
            String messageId = message.get("id").getAsString();
            String subject = message.get("subject").getAsString();
            System.out.println("Processing message with ID: " + messageId + ", Subject: " + subject);
            if (subject.contains("CaseDrive Registration OTP")) {
                return fetchResendEmailBody(client, messageId);
            }
        }
        throw new RuntimeException("No message found with the unique identifier: " + uniqueIdentifier);
    }

    private static String fetchResendEmailBody(CloseableHttpClient client, String messageId) throws Exception {
        String bodyEndpoint = BASE_URL + INBOX_ID + "/messages/" + messageId + "/body.html";
        HttpGet bodyRequest = new HttpGet(bodyEndpoint);
        bodyRequest.setHeader("Authorization", "Bearer " + API_TOKEN);
        HttpResponse bodyResponse = client.execute(bodyRequest);

        System.out.println("Fetching email body...");
        if (bodyResponse.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed to fetch email content");
        }

        String emailBody = EntityUtils.toString(bodyResponse.getEntity());
        System.out.println("Email Body Fetched:\n" + emailBody);

        // Match a 6-digit number inside any <span> tag
        Pattern otpPattern = Pattern.compile("<span[^>]*>(\\d{6})</span>");
        Matcher matcher = otpPattern.matcher(emailBody);
        if (matcher.find()) {
            String otp = matcher.group(1);
            System.out.println("OTP Found: " + otp);
            return otp;
        }
        throw new RuntimeException("OTP not found in email body");
    }
}