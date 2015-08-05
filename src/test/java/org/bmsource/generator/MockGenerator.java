package org.bmsource.generator;

import java.util.Random;
import java.util.logging.Logger;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import org.bmsource.interceptor.Loggable;

@Alternative
@ThirteenDigits
public class MockGenerator implements NumberGenerator
{
    @Inject
    private Logger logger;

    @Override
    @Loggable
    public String generateNumber()
    {
        String mock = "MOCK-" + Math.abs(new Random().nextInt());
        logger.info("Generated Mock : " + mock);
        return mock;
    }
}