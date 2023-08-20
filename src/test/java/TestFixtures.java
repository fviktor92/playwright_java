import com.microsoft.playwright.Page;
import common.exceptions.MissingSystemPropertyException;
import org.junit.jupiter.api.*;
import ui.extensions.ReportingExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import ui.BrowserFactory;

import java.io.File;

@ExtendWith(ReportingExtension.class)
class TestFixtures {
    Page page;

    @BeforeAll
    static void beforeAll() {
        String allureResultsPath = "allure-results";

        // Create a File object for the folder
        File allureResultsFolder = new File(allureResultsPath);

        // Check if the folder exists
        if (allureResultsFolder.exists()) {
            // If it exists, delete it
            deleteFolder(allureResultsFolder);
        }
    }

    @AfterAll
    static void afterAll() {
        BrowserFactory.closePage();
        BrowserFactory.closeBrowserContext();
        BrowserFactory.closeBrowser();
        BrowserFactory.closePlaywright();
    }

    @BeforeEach
    void beforeEach() throws MissingSystemPropertyException {
        page = BrowserFactory.getPage(null);
    }


    private static void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteFolder(file);
                } else {
                    file.delete();
                }
            }
        }
        folder.delete();
    }
}
