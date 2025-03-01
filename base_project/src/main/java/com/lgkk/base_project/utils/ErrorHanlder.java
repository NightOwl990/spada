package com.lgkk.base_project.utils;//package com.lgkk.hoangnam.homemee.utils;
//
//import com.google.gson.Gson;
//import com.google.gson.TypeAdapter;
//
//import java.io.IOException;
//import java.util.concurrent.TimeoutException;
//
//import okhttp3.ResponseBody;
//import retrofit2.adapter.rxjava.HttpException;
//
///**
// * Created by KAD on 2/16/2017.
// */
//
//public class ErrorHanlder {
//    public static String getErrorMessage(Throwable error) {
//        String errMessage;
//        if (error instanceof HttpException) {
//            ResponseBody body = ((HttpException) error).response().errorBody();
//            Gson gson = new Gson();
//            TypeAdapter<ErrorParser> adapter = gson.getAdapter(ErrorParser.class);
//            try {
//                ErrorParser errorParser = adapter.fromJson(body.string());
//
//                Logs.e("KAD", "Error:" + errorParser.getErrorMessage());
//                errMessage = errorParser.getErrorMessage();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//                errMessage = "There is no data";
//            }
//        } else if (error instanceof TimeoutException) {
//            errMessage = "Connect to sever take too long, please try again";
//        } else {
//            errMessage = "No internet connection";
//        }
//        return errMessage;
//    }
//}
