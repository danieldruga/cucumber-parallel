package com.ddruga.test.selenium.context;

import static org.junit.jupiter.api.Assertions.fail;

import org.openqa.selenium.WebDriverException;
import org.opentest4j.AssertionFailedError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Execution {
    private static final Logger LOGGER = LoggerFactory.getLogger(Execution.class);
    private static final int MAX_ATTEMPTS = 5;

    @FunctionalInterface
    public interface FragileExecutor {
        void execute();
    }

    @FunctionalInterface
    public interface FragileFetcher<T> {
        T fetch();
    }

    public static void retry(FragileExecutor method) {
        int attempts = 0;
        boolean succeeeded = false;

        while (!succeeeded && attempts < MAX_ATTEMPTS) {
            attempts++;
            try {
                method.execute();
                succeeeded = true;
            } catch (WebDriverException e) {
                if (attempts == MAX_ATTEMPTS) {
                    LOGGER.error("Retried {} times", attempts);
                    fail(e);
                }
            }
        }
    }

    public static <T> T retryForResult(FragileFetcher<T> getMethod) {
        int attempts = 0;

        while (attempts < MAX_ATTEMPTS) {
            attempts++;
            try {
                return getMethod.fetch();
            } catch (WebDriverException e) {
                if (attempts == MAX_ATTEMPTS) {
                    LOGGER.error("Retried {} times", attempts);
                    fail(e);
                }
            }
        }
        // this line will never execute (added to pass the compilation)
        return getMethod.fetch();
    }

    public static void retryForAssert(FragileExecutor method) {
        int attempts = 0;
        boolean succeeeded = false;

        while (!succeeeded && attempts < MAX_ATTEMPTS) {
            attempts++;
            try {
                method.execute();
                succeeeded = true;
            } catch (WebDriverException | AssertionFailedError e) {
                if (attempts == MAX_ATTEMPTS) {
                    LOGGER.error("Retried for assert {} times", attempts);
                    fail(e);
                }
            }
        }
    }
}