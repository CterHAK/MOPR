package vn.iotstar.baitap6_retrofit2.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import vn.iotstar.baitap6_retrofit2.model.Category;

public interface APIService {
    @GET("categories.php")
    Call<List<Category>> getAllCategory();
}
