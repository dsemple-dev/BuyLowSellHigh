package com.dsempledev.buylowsellhigh;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class buyLowSellHighApplication {

    private final String FILE1 = "src/main/resources/ChallengeSampleDataSet1.txt";
    private final String FILE2 = "src/main/resources/ChallengeSampleDataSet2.txt";
    private static final Logger logger = LogManager.getLogger(buyLowSellHighApplication.class);

    public static void main(String[] args)
    {
        SpringApplication.run(buyLowSellHighApplication.class, args);
    }

        /*
        S - Single-responsiblity Principle.
        O - Open-closed Principle.
        L - Liskov Substitution Principle.
        I - Interface Segregation Principle.
        D - Dependency Inversion Principle.*/

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            pricesFileHandler fReader = new pricesFileHandler();

            //logging test
            logger.debug("Debugging log");
            logger.info("Info log");
            logger.warn("Hey, This is a warning!");
            logger.error("Oops! We have an Error. OK");
            logger.fatal("Damn! Fatal error. Please fix me.");

            //TO DO
            //add logging
            //set logging to info for prod
            //add debug log messages
            //configure logging to a file
            //pull repeated sections for each set below into a method
            //consider and annotate SOLID "this is the S..."

            logger.info("SET 1");
            ArrayList<Float> thePrices = fReader.readWithStreamTokenizer(FILE1);
            buyLowSellHighProcessor set1 = new buyLowSellHighProcessor(thePrices);
            //set1.matriceTest();
            logger.info(set1.getTheBestTrade());

            logger.info("SET 2");
            thePrices = fReader.readWithStreamTokenizer(FILE2);
            buyLowSellHighProcessor set2 = new buyLowSellHighProcessor(thePrices);
            //set2.matriceTest();
            logger.info(set2.getTheBestTrade());
        };
    }
}
