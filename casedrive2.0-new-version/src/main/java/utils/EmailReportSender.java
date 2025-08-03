package utils;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import lombok.extern.java.Log;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

@Log
public class EmailReportSender {

    private EmailReportSender() {
    }

    public static void sendReport(String... toEmails) {
        final String fromEmail = "lezdoqatesters@gmail.com";
        final String password = "htcdxzsufhytnkcf";
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");


        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromEmail));
            msg.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(String.join(",", toEmails))
            );
            msg.setSubject("Automation Test Report");

            Multipart multipart = buildMultipartWithAttachments();
            msg.setContent(multipart);

            Transport.send(msg);
            System.out.println("Report sent successfully to: " + String.join(", ", toEmails));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Multipart buildMultipartWithAttachments() throws MessagingException, IOException {
        Multipart multipart = new MimeMultipart();

        String dateTime = java.time.LocalDateTime.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String environmentUrl = System.getProperty("env.url");
        if (environmentUrl == null || environmentUrl.isEmpty()) {
            environmentUrl = System.getenv("env.url");
        }

        if (environmentUrl == null || environmentUrl.isEmpty()) {
            environmentUrl = "N/A";
        }

        String htmlContent =
                "<!DOCTYPE html>" +
                        "<html>" +
                        "<body style=\"font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;\">" +
                        "<table width=\"100%\" style=\"max-width: 600px; margin: auto; background-color: white; border-radius: 10px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); color: black;\">" +
                        "  <tr>" +
                        "    <td style=\"padding: 20px; text-align: center; background-color: #0038FF; color: white; border-top-left-radius: 10px; border-top-right-radius: 10px;\">" +
                        "      <h2>Automation Test Report</h2>" +
                        "    </td>" +
                        "  </tr>" +
                        "  <tr>" +
                        "    <td style=\"padding: 20px; background-color: white; color: black;\">" +
                        "      <p><strong>Hello Team,</strong></p>" +
                        "      <p>The latest automation test execution has been completed. Please find the attached report for detailed results.</p>" +
                        "      <table style=\"width: 100%; margin-top: 20px; border-collapse: collapse;\">" +
                        "        <tr>" +
                        "          <td style=\"padding: 10px; background-color: #f0f0f0;\">Project</td>" +
                        "          <td style=\"padding: 10px;\">CaseDrive</td>" +
                        "        </tr>" +
                        "        <tr>" +
                        "          <td style=\"padding: 10px; background-color: #f0f0f0;\">Execution Time</td>" +
                        "          <td style=\"padding: 10px;\">" + dateTime + "</td>" +
                        "        </tr>" +
                        "        <tr>" +
                        "          <td style=\"padding: 10px; background-color: #f0f0f0;\">Environment</td>" +
                        "          <td style=\"padding: 10px;\"><a href=\"" + environmentUrl + "\">" + environmentUrl + "</a></td>" +
                        "        </tr>" +
                        "      </table>" +
                        "      <p style=\"margin-top: 20px;\">Please reach out in case of any queries or issues.</p>" +
                        "      <p>Regards,<br><strong>Automation Team</strong></p>" +
                        "    </td>" +
                        "  </tr>" +
                        "  <tr>" +
                        "    <td style=\"padding: 10px; text-align: center; font-size: 12px; color: #888;\">" +
                        "      This is an automated message. Please do not reply." +
                        "    </td>" +
                        "  </tr>" +
                        "</table>" +
                        "</body>" +
                        "</html>";

        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        File logFile = new File("logs/automation-" + today + ".log.0");

        System.out.println("Resolved Log Path: " + logFile.getAbsolutePath());

        if (logFile.exists()) {
            MimeBodyPart logPart = new MimeBodyPart();
            logPart.attachFile(logFile);
            logPart.setFileName("automation-log-" + today + ".log");
            multipart.addBodyPart(logPart);
        } else {
            System.out.println("Log file NOT found: " + logFile.getAbsolutePath());
        }

        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setContent(htmlContent, "text/html");
        multipart.addBodyPart(textPart);

        File htmlReport = new File("test-output/SparkReport/Spark.html");
        if (htmlReport.exists()) {
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.attachFile(htmlReport);
            multipart.addBodyPart(htmlPart);
        }

        File pdfReport = new File("test-output/PdfReport/ExtentPdf.pdf");
        if (pdfReport.exists()) {
            MimeBodyPart pdfPart = new MimeBodyPart();
            pdfPart.attachFile(pdfReport);
            multipart.addBodyPart(pdfPart);
        }

        return multipart;
    }
}