package dl.projects.propertydeal.bootstrap;

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


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        getCityDeals(City.Jerusalem);
        getCityDeals(City.TLV);
    }

    private void getCityDeals(City city) {
        try
        {
           boolean isLastPage = false;
           int page = 1;
           while (!isLastPage){
               isLastPage =  crawler.LoadPostReq(page, city);
               page++;
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}