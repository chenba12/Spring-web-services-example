package com.example.Spring.web.services.example.repo;

import com.example.Spring.web.services.example.generatedXSD.Country;
import com.example.Spring.web.services.example.generatedXSD.Currency;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class CountryRepository {
    private static final Map<String, Country> countries = new HashMap<>();

    @PostConstruct
    public void initData() {
        Country spain = new Country();
        spain.setName("Spain");
        spain.setCapital("Madrid");
        spain.setCurrency(Currency.EUR);
        spain.setPopulation(46704314);

        countries.put(spain.getName(), spain);

        Country poland = new Country();
        poland.setName("Poland");
        poland.setCapital("Warsaw");
        poland.setCurrency(Currency.PLN);
        poland.setPopulation(38186860);

        countries.put(poland.getName(), poland);

        Country uk = new Country();
        uk.setName("United Kingdom");
        uk.setCapital("London");
        uk.setCurrency(Currency.GBP);
        uk.setPopulation(63705000);

        countries.put(uk.getName(), uk);
    }

    public Country findCountry(String name) {
        Assert.notNull(name, "The country's name must not be null");
        return countries.get(name);
    }
    public Country findCountryByCurrency(String currency) {
        Assert.notNull(currency, "The country's currency must not be null");
        String key= getKeyFromValue(currency);
        return countries.get(key);
    }
    public static String getKeyFromValue(String currency) {
        for (String key : countries.keySet()) {
            Currency countryCurrency=countries.get(key).getCurrency();
            if (String.valueOf(countryCurrency).equals(currency)) {
                return key;
            }
        }
        return null;
    }
}