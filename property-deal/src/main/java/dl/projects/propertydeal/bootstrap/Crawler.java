package dl.projects.propertydeal.bootstrap;

import com.google.gson.Gson;

import dl.projects.propertydeal.model.City;
import dl.projects.propertydeal.model.Deal;
import dl.projects.propertydeal.model.DealPage;
import dl.projects.propertydeal.repositories.DealRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class Crawler {

    static Log logger = LogFactory.getLog(Startup.class.getName());

    private final DealRepository dealRepository;

    public Crawler(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }

    public   boolean LoadPostReq(int page, City city) throws IOException {

        URL url = new URL("https://www.nadlan.gov.il/Nadlan.REST/Main/GetAssestAndDeals");
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        String content =  GetJsonContent(page ,city.getCode());
        String jsonInputString =content;
       byte[] input = convertToBytes(con, jsonInputString);
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            String jsonValue = response.toString();
            //FileUtils.writeStringToFile(new File("E:\\temp\\"+page+".json"),jsonValue ,"utf-8");
            DealPage data = new Gson().fromJson(jsonValue, DealPage.class);
//            if(data == null){
//            //TO DO throw Exception()
//            }

            boolean reachedLastYear =  data.getAllResults().stream().anyMatch(x-> x.getDealDate().endsWith("18"));
            return data.isLastPage() ||  reachedLastYear;
        }
    }


    private  byte[]  convertToBytes(HttpURLConnection con, String jsonInputString) throws IOException {
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
            return input;
        }
    }

    private  String GetJsonContent(int page ,int objectId)
    {

        return "{'MoreAssestsType':0,'FillterRoomNum':0,'GridDisplayType':0,'ResultLable':'תל אביב','ResultType':1,'ObjectID':'"+objectId +
                "','ObjectIDType':'number','ObjectKey':'SETL_CODE','DescLayerID':'SETL_MID_POINT','Alert':null,'X':220182.15,'Y':632031.77,'Gush':null,"+
                "'Parcel':null,'showLotParcel':false,'showLotAddress':false,'OriginalSearchString':'תל אביב ','MutipuleResults':false,'ResultsOptions':null,'CurrentLavel':2,"+
                "'Navs':[{'text':'מחוז תל אביב','url':null,'order':1}],'QueryMapParams':{'QueryToRun':null,'QueryObjectID':'" + objectId +
                 "','QueryObjectType':'number','QueryObjectKey':'SETL_CODE','QueryDescLayerID':'KSHTANN_SETL_AREA','SpacialWhereClause':null},'isHistorical':false," +
                 "'PageNo':" +page +",'OrderByFilled':null,'OrderByDescending':true,'Distance':0}";
}

public void updateCityProperty(){

        try {

          //  Deal d = dealRepository.findById("5958931500").get();
            Iterable<Deal> deals  = dealRepository.findAll();
            for(Deal d: deals){
                d.setFilteredStreetName( filterHebrewLetter(d.getStreetName()));
                d.setFilteredCityName( filterHebrewLetter(d.getCityName()));

            }
            dealRepository.saveAll(deals);
           //String sName =  filterHebrewLetter("הריטב\"א");
          // System.out.println(sName);
     //  setDealDate(d);
/*
            for(Deal d: deals){
//                //setAdressInDetails(d);
//            // //   seFloorDigit(d);
               setDealDate(d);
                deals.in
//
            }
*/
        //    dealRepository.save(d);
          //  dealRepository.saveAll(deals);

        }
        catch (Exception e){
            logger.error("updateCityProperty failed: " + e.getLocalizedMessage() );
            e.printStackTrace();
        }


}

    private void setDealDate(Deal d) throws ParseException {
        d.setDate(new SimpleDateFormat("dd.MM.yyyy").parse(d.getDealDate())) ;
    }

    private void setAdressInDetails(Deal d) {
        if(!StringUtils.isEmpty( d.getFullAddress())) {
            String[] cs = d.getFullAddress().split(",");
            String cName = cs[1].trim();
            d.setCityName(cName);
            setCityCode(d, cName);
            List<String> arrAdress = new ArrayList<String>(Arrays. asList(d.getDisplayAdress().split(" ")));
            if(!arrAdress.isEmpty() )
            {
                int lstIndex = arrAdress.size() -1;
                d.setStreetNo(arrAdress.get(lstIndex));
                arrAdress.remove(lstIndex);
                String streetName = String.join(" ", arrAdress);
                d.setStreetName(streetName);
            }
        }
    }

    private Deal seFloorDigit(Deal d){
        String fNum = d.getFloorNo();

        if( !StringUtils.isEmpty(fNum ) ){



            fNum= fNum.replace("מרתף","0").
                    replace("גלריה","0").
                     replace("קרקע","0").
                    replace("ראשונה","1").
                    replace("שניה","2").
                    replace("שלישית","3").
                    replace("רביעית","4").
                    replace("חמישית","5").
                    replace("שישית","6").
                    replace("שביעית","7").
                    replace("שמינית","8").
                    replace("תשיעית","9").
                    replace("עשירית","10").
                    replace("אחת עשרה","11").
                    replace("שתיים עשרה","12").
                    replace("שלוש עשרה","13").
                    replace("ארבע עשרה","14").
                    replace("חמש עשרה","15").
                    replace("שש עשרה","16").
                    replace("שבע עשרה","17").
                    replace("שמונה עשרה","18").
                    replace("תשע עשרה","19").
                    replace("עשרים ואחת","21").
                    replace("עשרים ושתים","22").
                    replace("עשרים ושלוש","23").
                    replace("עשרים וארבע","24").
                    replace("עשרים וחמש","25").
                    replace("עשרים ושש","26").
                    replace("עשרים ושבע","27").
                    replace("עשרים ושמונה","28").
                    replace("עשרים ותשע","29").
                    replace("עשרים","20").
                    replace("שלושים ואחת","31").
                    replace("שלושים ושתים","32").
                    replace("שלושים ושלוש","33").
                    replace("שלושים וארבע","34").
                    replace("שלושים וחמש","35").
                    replace("שלושים ושש","36").
                    replace("שלושים שבע","37").
                    replace("שלושים שמונה","38").
                    replace("שלושים ותשע","39").
                    replace("שלושים","30").
                    replace("ארבעים","40");
                    d.setFloor( getFloors(fNum));
        }


        return  d;
    }

    private void setCityCode(Deal d, String cName) {
        if (cName.equals("תל אביב -יפו")){
            d.setCityCode(City.TLV);
        }
        if (cName.equals("ירושלים")){
            d.setCityCode(City.Jerusalem);
        }
        if (cName.equals("פתח תקווה")){
            d.setCityCode(City.PetaTikva);
        }
        if (cName.equals("אשדוד")){
            d.setCityCode(City.Ashdod);
        }
        if (cName.equals("ראשון לציון")){
            d.setCityCode(City.Rishon);
        }
    }

    private List<Integer> getFloors(String str){
        List<Integer> numList = new ArrayList<Integer>();
        String number = "";
        for(int i = 0 ; i < str.toCharArray().length; i++ ){

          if(Character.isDigit(str.charAt(i) )) {
              number += str.charAt(i);
          }
          else {
              if(!StringUtils.isEmpty(number)   )
              {
                  numList.add(Integer.parseInt(number));
                  number = "";

              }

          }

       }
        if(!StringUtils.isEmpty(number)   )
        {
            numList.add(Integer.parseInt(number));
}
       return numList;
    }

    private String filterHebrewLetter(String str){
        if(StringUtils.isEmpty(str))
            return str;
        String hebText = "";
        for (int i = 0; i < str.toCharArray().length; i++) {

            if((int)str.charAt(i)  >= 1488 && (int)str.charAt(i)<= 1514 ){
                hebText+=str.charAt(i);
            }
        }
        return hebText;
    }
}
