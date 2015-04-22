package retrofit.cardekho;


import com.cardekho.model.DataModel;
import com.cardekho.utils.GlobalVariables;

import retrofit.http.GET;

public interface RestService
{	
	@GET(GlobalVariables.URL_GET_DATA)
    void getCityList(MyCallback<DataModel> callback);
}
