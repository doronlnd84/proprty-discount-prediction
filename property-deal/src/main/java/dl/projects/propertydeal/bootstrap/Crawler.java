package dl.projects.propertydeal.bootstrap;

import com.google.gson.Gson;

import dl.projects.propertydeal.model.City;
import dl.projects.propertydeal.model.DealPage;
import dl.projects.propertydeal.repositories.DealRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class Crawler {

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
            dealRepository.saveAll(data.getAllResults());
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
}
