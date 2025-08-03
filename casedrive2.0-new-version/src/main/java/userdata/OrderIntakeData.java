package userdata;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderIntakeData {
    @Getter
    @RequiredArgsConstructor
    public enum OrderType {
        //Case wise Hourly Custom link
        CASE_DATA(
                "",
                "N/A",
                generateDynamicFirstName(),
                "Test",
                "AUTO",
                "src/test/resources/Test File Upload/250MB-Corrupt-Testfile.Org.zip",
                "src/test/resources/Test File Upload/500MB-CZIPtestfile.org.zip",
                "src/test/resources/Test File Upload/300MB-Corrupt-Testfile.Org.zip",
                "https://drive.google.com/drive/folders/1nNLQiB5kSkmk81c5vFfv9qRMT7KeuCyE",  // upload link for CUSTOM_LINK_CASE_DATA
                "https://drive.google.com/drive/folders/14i3bJc0wtvLgqngOxQ9b85gCgpiRcuBR",
                "src/test/resources/Test File Upload/SampleDOCFile_200kb.doc",
                "src/test/resources/Test File Upload/SampleGIFImage_40kbmb.gif",
                "src/test/resources/Test File Upload/SamplePDFFile_5mb.pdf",
                "src/test/resources/Test File Upload/SamplePNGImage_100kbmb.png",
                "src/test/resources/Test File Upload/SamplePPTFile_500kb.ppt",
                "src/test/resources/Test File Upload/SamplePSDFile_1.9mbmb.psd",
                "src/test/resources/Test File Upload/SampleTextFile_10kb.txt",
                "123456789"
        );


        private final String casenumber;
        private final String caseOverview;
        private final String confirmFirstname;
        private final String confirmlastname;
        private final String confirmCaseID;
        private final String filePath1;
        private final String filePath2;
        private final String filePath3;
        private final String uploadLink;
        private final String downloadLink;
        private final String docType;
        private final String gifType;
        private final String pdfType;
        private final String pngType;
        private final String pptType;
        private final String psdType;
        private final String textFile;
        private final String socialSecurityNumber;


        private static String generateDynamicFirstName() {
            String[] firstNames = {"John", "Jane", "Michael", "Emily", "Chris", "Sarah", "David", "Anna"};
            Random random = new Random();
            return firstNames[random.nextInt(firstNames.length)];
        }

        public String getConfirmlastname() {
            return generateDynamicConfirmLastname(confirmlastname);
        }

        public String getConfirmCaseID() {
            return generateDynamicConfirmCaseID(confirmCaseID);
        }

        private static String generateDynamicConfirmLastname(String baseConfirmLastname) {
            String currentTime = new SimpleDateFormat("MMddHHmmss").format(new Date());
            return baseConfirmLastname + "_" + currentTime;
        }

        private static String generateDynamicConfirmCaseID(String baseConfirmCaseID) {
            String dynamicTime = new SimpleDateFormat("HHmmss").format(new Date());
            return baseConfirmCaseID + "_" + dynamicTime;
        }

        public String resolveRelativeFilePath(String relativeFilePath) {
            if (relativeFilePath == null) {
                return null;
            }
            String projectRoot = System.getProperty("user.dir");
            return Paths.get(projectRoot, relativeFilePath).toAbsolutePath().toString();
        }
    }
}