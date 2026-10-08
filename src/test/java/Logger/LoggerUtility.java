package Logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.io.*;

public class LoggerUtility {

    private static final String suiteLogsPath = "target/logs/suite/";
    private static final String regressionLogsPath = "target/logs/";

    private static final Logger logger = LogManager.getLogger();

    //synchronized - asteapta pana testul anterior este gata
    public static synchronized void startTestCase(String testName) {
        ThreadContext.put("threadName", testName);
        logger.info("===== Execution started: " + testName + "===== ");
    }

    public static synchronized void endTestCase(String testName) {
        ThreadContext.put("threadName", testName);
        logger.info("===== Execution ended: " + testName + "===== ");
    }

    public static synchronized void infoTestCase(String messageName) {
        logger.info(Thread.currentThread().getName() + " " + getCallInfo() + " " + messageName);
    }

    public static synchronized void errorLog(String message) {
        logger.error(Thread.currentThread().getName() + " " + getCallInfo() + " " + message);
    }

    public static synchronized String getCallInfo() {
        String className = Thread.currentThread().getStackTrace()[3].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();

        return className + " : " + methodName + " => ";
    }

    public static void mergeFilesIntoOne() {
        File dir = new File(suiteLogsPath);
        String[] fileNames = dir.list();
        // create object of PrintWriter for output file
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(regressionLogsPath + "regressionLogs.log");
            for (String fileName : fileNames) {
                // create instance of file from Name of
                // the file stored in string Array
                File f = new File(dir, fileName);

                // create object of BufferedReader
                BufferedReader br = new BufferedReader(new FileReader(f));
                pw.println("Contents of file " + fileName);

                // Read from current file
                String line = br.readLine();
                while (line != null) {

                    // write to the output file
                    pw.println(line);
                    line = br.readLine();
                }
                pw.flush();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
