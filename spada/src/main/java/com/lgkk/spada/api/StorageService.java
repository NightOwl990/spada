package com.lgkk.spada.api;

import com.lgkk.spada.model.UploadDetails;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface StorageService {

    @Multipart
    @POST("v1/storage/single")
    Observable<UploadDetails> uploadImageVideo(@Header("Authorization") String accessToken, @Part MultipartBody.Part file);

    //    Download Url -------
    @Streaming
    @GET
    Observable<Response<ResponseBody>> downloadFileByUrlRx(@Url String fileUrl);


}
