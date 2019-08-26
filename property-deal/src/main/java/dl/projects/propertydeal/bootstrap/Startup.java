package dl.projects.propertydeal.bootstrap;

import dl.projects.propertydeal.model.City;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Startup implements ApplicationListener<ContextRefreshedEvent>
{
    @Autowired
    Crawler crawler;

    static Log logger = LogFactory.getLog(Startup.class.getName());


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)  {
        crawler.updateCityProperty();

//        for (City c: City.values()){
//        logger.info("Retrieve deal from city of - " + c.toString());
//            boolean isSucceded = getCityDeals(c);
//            if(!isSucceded)
//                return;
//        }

    }

    private boolean getCityDeals(City city) {
        int tries = 3;
        int page = 1;
        for (int i = 0 ; i<= tries;i++ ) {
            try {
                boolean isLastPage = false;

                while (!isLastPage) {
                    isLastPage = crawler.LoadPostReq(page, city);
                    page++;
                }
                return true;
            } catch (IOException e) {

                e.printStackTrace();
                logger.info("try - "+i+": getCityDeals failed on  - " + city.toString() + " page: " + page + ": " + e.getLocalizedMessage());
            }


        }
        logger.error("getCityDeals failed 3 times"  );
        return  false;

    }
}