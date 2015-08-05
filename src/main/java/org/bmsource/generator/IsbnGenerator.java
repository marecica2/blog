package org.bmsource.generator;

import java.util.Random;
import java.util.logging.Logger;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.bmsource.interceptor.Loggable;

@Default
@ThirteenDigits
public class IsbnGenerator implements NumberGenerator
{
    @Inject
    private Logger logger;

    @Loggable
    @Override
    public String generateNumber()
    {
        String isbn = "13-84356-" + Math.abs(new Random().nextInt());
        logger.info("Generated ISBN : " + isbn);
        return isbn;
    }

}
