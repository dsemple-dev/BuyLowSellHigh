package com.dsempledev.buylowsellhigh;

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

            //TO DO
            //add logging using log4j2
            //seperate logging by debug, error for prod
            //pull repeated sections for each set below into a method
            //consider and annotate SOLID "this is the S..."

            System.out.println("SET 1");
            ArrayList<Float> thePrices = fReader.readWithStreamTokenizer(FILE1);
            buyLowSellHighProcessor set1 = new buyLowSellHighProcessor(thePrices);
            //set1.matriceTest();
            System.out.println(set1.getTheBestTrade());

            System.out.println("SET 2");
            thePrices = fReader.readWithStreamTokenizer(FILE2);
            buyLowSellHighProcessor set2 = new buyLowSellHighProcessor(thePrices);
            //set2.matriceTest();
            System.out.println(set2.getTheBestTrade());
        };
    }
}
