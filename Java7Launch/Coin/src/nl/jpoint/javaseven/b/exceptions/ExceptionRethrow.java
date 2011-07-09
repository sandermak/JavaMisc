package nl.jpoint.javaseven.b.exceptions;

import java.io.IOException;
import java.sql.SQLException;

public class ExceptionRethrow {

    public static void main(String[] args) throws Exception {
        ExceptionRethrow c = new ExceptionRethrow();
        c.test(true);
    }

    void test(boolean foo) throws IOException, SQLException {
        try {
            if (foo) {
                throw new IOException();
            } else {
                throw new SQLException();
            }
        } catch (final Exception e) {
            // Just logging here, not handling the exceptions.
            e.printStackTrace();
            throw e;
        }
    }
}
