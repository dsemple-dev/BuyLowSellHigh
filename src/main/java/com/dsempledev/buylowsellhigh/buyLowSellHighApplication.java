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

    private static final Logger logger = LogManager.getLogger(buyLowSellHighApplication.class);
    private final String FILE1 = "src/main/resources/ChallengeSampleDataSet1.txt";
    private final String FILE2 = "src/main/resources/ChallengeSampleDataSet2.txt";

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

            process(FILE1);
            process(FILE2);

            //TO DO
            //add debug log messages
            //configure logging to a file
            //deal with git conflicts with target
            //Make processor a factory method?
            //consider and annotate SOLID "this is the S..."

            //logging test
            //logger.debug("Debugging log");
            //logger.info("Info log");
            //logger.warn("Hey, This is a warning!");
            //logger.error("Oops! We have an Error. OK");
            //logger.fatal("Damn! Fatal error. Please fix me.");

        };
    }

    private void process(String fName){
        logger.debug("PROCESSING: "+fName);
        pricesFileHandler fReader = new pricesFileHandler();
        ArrayList<Float> thePrices = fReader.readWithStreamTokenizer(fName);
        logger.debug("Prices read in from file.");
        buyLowSellHighProcessor shareSet = new buyLowSellHighProcessor(thePrices);
        logger.debug("Shares processed and best trade identified.");
        logger.info(shareSet.getTheBestTrade());
    }
}
