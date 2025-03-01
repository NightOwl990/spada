package com.lgkk.spada.api;

import com.lgkk.spada.model.AddPostResult;
import com.lgkk.spada.model.BannerDetail;
import com.lgkk.spada.model.HistoryBook;
import com.lgkk.spada.model.Message;
import com.lgkk.spada.model.NotificationUnSeenNumber;
import com.lgkk.spada.model.Search;
import com.lgkk.spada.model.UpdateVersionBean;
import com.lgkk.spada.model.User;
import com.lgkk.spada.model.account.Splash;
import com.lgkk.spada.model.community.CategoriesNews;
import com.lgkk.spada.model.community.PostNews;
import com.lgkk.spada.model.community.group.GroupByTag;
import com.lgkk.spada.model.community.group.GroupDetails;
import com.lgkk.spada.model.community.group.JoinGroupDetails;
import com.lgkk.spada.model.community.group.UnJoinGroupDetails;
import com.lgkk.spada.model.community.post.CategoryPost;
import com.lgkk.spada.model.community.post.MyPost;
import com.lgkk.spada.model.community.post.PostDetails;
import com.lgkk.spada.model.community.post.postdetails.CommentDetails;
import com.lgkk.spada.model.community.post.postdetails.ReplyDetails;
import com.lgkk.spada.model.community.post.postdetails.UserPostDetails;
import com.lgkk.spada.model.community.quiz.Quiz;
import com.lgkk.spada.model.community.quiz.QuizCategories;
import com.lgkk.spada.model.home.LifeSciences;
import com.lgkk.spada.model.home.NotificationBean;
import com.lgkk.spada.model.home.NotificationSeen;
import com.lgkk.spada.model.home.nutrition.Nutrition;
import com.lgkk.spada.model.home.nutrition.NutritionCatAndRecommend;
import com.lgkk.spada.model.service.ServiceCategory;
import com.lgkk.spada.model.service.ServiceDetails;
import com.lgkk.spada.model.shop.AddressInfo;
import com.lgkk.spada.model.shop.Cart;
import com.lgkk.spada.model.shop.Category;
import com.lgkk.spada.model.shop.Order;
import com.lgkk.spada.model.shop.Product;
import com.lgkk.spada.model.shop.Rating;
import com.lgkk.spada.model.shop.RatingScore;
import com.lgkk.spada.model.shop.Shop;
import com.lgkk.spada.model.shop.ShopBanner;
import com.lgkk.spada.model.shop.SubCategory;
import com.lgkk.spada.model.user.Diary;
import com.lgkk.spada.model.user.MyBabyDetail;
import com.lgkk.spada.model.user.Token;
import com.lgkk.spada.model.user.UserBaby;
import com.lgkk.spada.model.user.UserInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;


public interface BiboService {
    //    Music ---------

    @DELETE("v2/mp3/playlist")
    Observable<Message> removeSongPlayList(@Header("Authorization") String accessToken, @Query("songId") String songId);

    @DELETE("v2/mp3/playlist/all")
    Observable<Message> removeAllPlayList(@Header("Authorization") String accessToken);


    // TrackingUser ----------------
    @FormUrlEncoded
    @POST("v2/trackingUser/write")
    Observable<Integer> postTracking(@Header("Authorization") String accessToken,
                                     @Field("userScreen") String userScreen,
                                     @Field("userTime") long userTime,
                                     @Field("userType") String userType,
                                     @Field("userOs") String userOs);

    @FormUrlEncoded
    @POST("v2/tracking/write2")
    Observable<Integer> postTrackingV2(@Header("Authorization") String accessToken,
                                       @Field("userScreen") String userScreen,
                                       @Field("startTime") long userTime,
                                       @Field("endTime") long userType,
                                       @Field("userOs") String userOs);

    //    Rate Product ---------
    @FormUrlEncoded
    @POST("v2/product/rate")
    Observable<Message> rateProduct(@Header("Authorization") String accessToken,
                                    @Field("product") String productId,
                                    @Field("score") int score,
                                    @Field("comment") String comment);

//    API HERE -------------------------

    @GET("v2/product/rate_list")
    Observable<List<Rating>> getListRatingByScore(@Header("Authorization") String accessToken,
                                                  @Query("productId") String productId,
                                                  @Query("score") int score,
                                                  @Query("skip") int skip,
                                                  @Query("limit") int limit);

    @GET("v2/product/rate_list")
    Observable<List<Rating>> getListRating(@Header("Authorization") String accessToken,
                                           @Query("productId") String productId,
                                           @Query("skip") int skip,
                                           @Query("limit") int limit);

    @GET("v2/product/rate_detail")
    Observable<RatingScore> getListRatingScoreDetail(@Header("Authorization") String accessToken, @Query("productId") String productId);

    @DELETE("v2/product/remove_rate")
    Observable<RatingScore> removeRatingById(@Header("Authorization") String accessToken, @Query("rateId") String rateId);

    // Shop Product ---------
    @FormUrlEncoded
    @POST("v2/product/like")
    Observable<Message> likeProductById(@Header("Authorization") String accessToken, @Field("productId") String productId);

    @GET("v2/product/sub_categories")
    Observable<List<SubCategory>> getListSubCategory(
            @Header("Authorization") String accessToken,
            @Query("category") String categoryId, @Query("skip") int skip, @Query("limit") int limit
    );

    @GET("v2/product/categories")
    Observable<List<Category>> getListCategory(@Header("Authorization") String accessToken);

    @GET("v2/product/banners")
    Observable<List<ShopBanner>> getShopHomeBanner(@Header("Authorization") String accessToken, @Query("skip") int skip, @Query("limit") int limit);

    @GET("v2/product")
    Observable<List<Product>> getListProductByCategory(@Header("Authorization") String accessToken, @Query("category") String categoryId, @Query("skip") int skip, @Query("limit") int limit);

    @GET("v2/product")
    Observable<List<Product>> getListProductBySubCategory(@Header("Authorization") String accessToken, @Query("skip") int skip, @Query("limit") int limit);

    @GET("v2/product/new")
    Observable<List<Product>> getListProductNewest(@Header("Authorization") String accessToken, @Query("skip") int skip, @Query("limit") int limit);

    @GET("v2/product")
    Observable<List<Product>> getListProductBySubCategory(@Header("Authorization") String accessToken, @Query("subCategory") String subCategoryId, @Query("skip") int skip, @Query("limit") int limit);

    @GET("v2/product/recommends")
    Observable<List<Product>> getListProductRecommend(@Header("Authorization") String accessToken, @Query("categoryId") String categoryId, @Query("skip") int skip, @Query("limit") int limit);

    @GET("v2/product/detail")
    Observable<Product> getProduct(@Header("Authorization") String accessToken, @Query("_id") String id);

    @GET("v2/product/suggests")
    Observable<List<Product>> getThreeSuggestProduct(@Header("Authorization") String accessToken, @Query("size") int size);

    @GET("v2/product/search")
    Observable<List<Product>> searchProductByName(@Header("Authorization") String accessToken, @Query("q") String query, @Query("skip") int skip, @Query("limit") int limit);

    @GET("v2/product/categories/detail")
    Observable<Category> getCategoryDetailById(@Header("Authorization") String accessToken, @Query("_id") String categoryId);

    @GET("v2/product/sub_categories/detail")
    Observable<SubCategory> getSubCategoryDetailById(@Header("Authorization") String accessToken, @Query("_id") String subCategoryId);

    @GET("v2/product/user_info")
    Observable<List<AddressInfo>> getListAddressInfo(@Header("Authorization") String accessToken);


    //    User Product -----------

    @FormUrlEncoded
    @POST("v2/product/user_info")
    Observable<Message> addAddressInfo(@Header("Authorization") String accessToken,
                                       @Field("fullName") String fullName,
                                       @Field("phoneNumber") String phoneNumber,
                                       @Field("email") String email,
                                       @Field("address") String address
    );

    @DELETE("v2/product/user_info")
    Observable<Message> removeAddressInfo(@Header("Authorization") String accessToken, @Query("_id") String addressid);

    @GET("v2/product/user_info/detail")
    Observable<AddressInfo> getAddressInfoById(@Header("Authorization") String accessToken, @Query("_id") String addressId);

    @FormUrlEncoded
    @PUT("v2/product/user_info")
    Observable<Message> updateAddressInfo(@Header("Authorization") String accessToken, @Field("_id") String addressId,
                                          @Field("fullName") String fullName,
                                          @Field("phoneNumber") String phoneNumber,
                                          @Field("email") String email,
                                          @Field("address") String address
    );

    @GET("v2/product/search")
    Observable<List<Product>> searchListProductByName(@Header("Authorization") String accessToken, @Query("q") String query, @Query("skip") int skip, @Query("limit") int limit);

    //    Cart --------------------
    @FormUrlEncoded
    @POST("v2/product/carts")
    Observable<Message> addProductToCart(@Header("Authorization") String accessToken, @Field("product") String productId,
                                         @Field("quantity") int quantity, @Field("isCheck") boolean isCheck);

    @DELETE("v2/product/carts/all")
    Observable<Message> removeAllProductOnCart(@Header("Authorization") String accessToken);

    @DELETE("v2/product/carts/one")
    Observable<Message> removeOneProductOnCartByCartId(@Header("Authorization") String accessToken, @Query("cartId") String cartId);

    @GET("v2/product/carts")
    Observable<List<Cart>> getListCartProduct(@Header("Authorization") String accessToken);

    @FormUrlEncoded
    @POST("v2/product/orders/all")
    Observable<Message> orderAll(@Header("Authorization") String accessToken,
                                 @Field("fullName") String fullName,
                                 @Field("phoneNumber") String phoneNumber,
                                 @Field("email") String email,
                                 @Field("address") String address);

    @FormUrlEncoded
    @POST("v2/product/orders/select")
    Observable<Message> orderSelect(@Header("Authorization") String accessToken,
                                    @Field("cartId") List<String> cartListId,
                                    @Field("fullName") String fullName,
                                    @Field("phoneNumber") String phoneNumber,
                                    @Field("email") String email,
                                    @Field("address") String address);

    @FormUrlEncoded
    @POST("v2/product/orders/one")
    Observable<Message> orderOneProduct(@Header("Authorization") String accessToken,
                                        @Field("productId") String productId,
                                        @Field("quantity") int quantity,
                                        @Field("fullName") String fullName,
                                        @Field("phoneNumber") String phoneNumber,
                                        @Field("email") String email,
                                        @Field("address") String address);

    @FormUrlEncoded
    @POST("v2/product/orders/all")
    Observable<Message> orderOne(@Header("Authorization") String accessToken,
                                 @Field("cartId") String cartId,
                                 @Field("fullName") String fullName,
                                 @Field("phoneNumber") String phoneNumber,
                                 @Field("email") String email,
                                 @Field("address") String address);

    @GET("v2/product/orders")
    Observable<List<Order>> getOrderList(@Header("Authorization") String accessToken, @Query("skip") int skip, @Query("limit") int limit);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "v2/product/orders", hasBody = true)
    Observable<Message> removeOrderId(@Header("Authorization") String accessToken, @Field("orderId") String orderId, @Field("cancelReason") String reason);

    @GET("user/get-info")
    Observable<User> getUserInfoById(@Header("Authorization") String accessToken, @Query("_id") String _id);

//    User -------------

    @GET("user/get-info")
    Observable<User> getInfoUserMom(@Header("Authorization") String accessToken);


    @FormUrlEncoded
    @POST("user/send-code-verify-email")
    Observable<Message> sendEmail(@Field("email") String email);

    @FormUrlEncoded
    @POST("user/refresh")
    Observable<Token> refresh(@Header("Authorization") String accessToken, @Field("deviceToken") String deviceToken);

    @FormUrlEncoded
    @POST("user/login-with-provider/facebook")
    Observable<User> loginFacebook(@Field("access_token") String accessToken, @Field("deviceToken") String deviceToken, @Field("os") String os, @Field("version") int version);

    @FormUrlEncoded
    @POST("user/login-with-provider/google2")
    Observable<User> loginGoogle(@Field("access_token") String accessToken, @Field("deviceToken") String deviceToken, @Field("os") String os, @Field("version") int version);

    @FormUrlEncoded
    @POST("user/login-with-provider/zalo")
    Observable<User> loginZalo(@Field("oauthCode") String accessToken, @Field("deviceToken") String deviceToken, @Field("os") String os, @Field("version") int version);

    @FormUrlEncoded
    @POST("user/send-code-forgot-pwd")
    Observable<Message> forgotPass(@Field("email") String mail);

    @FormUrlEncoded
    @POST("user/set-pwd-after-confirm")
    Observable<Message> pushNewPassword(@Field("email") String mail, @Field("code") int code, @Field("newPwd") String password, @Field("confirmPwd") String confirmPwd);

    @GET("clinic/search-by-criteria")
    Observable<ArrayList<Search>> search(@Header("Authorization") String accessToken, @Query("value") String query);


    //get all lịch pk theo ngày
    @GET("schedule/find-all-schedule-clinic")
    Observable<ArrayList<String>> getAllDateSchedule(@Query("clinic_id") String clinic_id);

    @GET("v2/news/list")
    Observable<ArrayList<PostNews>> getNewsHome(@Header("Authorization") String accessToken, @Query("week") int i, @Query("limit") int limit);

    @GET("v2/news/latest")
    Observable<ArrayList<PostNews>> getHomeNewsLastest(@Header("Authorization") String accessToken, @Query("week") int week, @Query("limit") int limit, @Query("skip") int skip);

    //home
//    @GET("h/ns/get-news/v2")
//    Observable<ArrayList<PostNews>> getNewsHome(@Header("Authorization") String accessToken, @Query("week") int week);

    @FormUrlEncoded
    @PUT("h/ca/edit-card")
    Observable<Message> updateCardViewHome(@Header("Authorization") String accessToken, @Query("field") String field, @Field("value") boolean phone);

    @FormUrlEncoded
    @POST("h/ht/update")
    Observable<Message> addHealth(@Header("Authorization") String accessToken,
                                  @Field("weight") float weight,
                                  @Field("heartBeat") float heartBeat,
                                  @Field("bellyLength") float bellyLength,
                                  @Field("bloodPressureMin") float bloodPressure,
                                  @Field("bloodPressureMax") float bloodPressureMax
            , @Field("week") int week
            , @Field("day") int day);

    @FormUrlEncoded
    @POST("h/ht/update")
    Observable<Message> addHealthWeight(@Header("Authorization") String accessToken,
                                        @Field("weight") float weight
            , @Field("week") int week
            , @Field("day") int day);

    @FormUrlEncoded
    @POST("h/ht/update")
    Observable<Message> addHealthHeartBeat(@Header("Authorization") String accessToken,
                                           @Field("heartBeat") float heartBeat
            , @Field("week") int week
            , @Field("day") int day);

    @FormUrlEncoded
    @POST("h/ht/update")
    Observable<Message> addHealthBellyLength(@Header("Authorization") String accessToken,

                                             @Field("bellyLength") float bellyLength
            , @Field("week") int week
            , @Field("day") int day);

    @FormUrlEncoded
    @POST("h/ht/update")
    Observable<Message> addHealthBloodPressure(@Header("Authorization") String accessToken,
                                               @Field("bloodPressureMin") float bloodPressure,
                                               @Field("bloodPressureMax") float bloodPressureMax
            , @Field("week") int week
            , @Field("day") int day);

    @FormUrlEncoded
    @PUT("h/ht/f-edit")
    Observable<Message> editWeightChart(
            @Header("Authorization") String accessToken,
            @Query("_id") String id,
            @Query("fields") String fields,
            @Field("value") int value);

    @FormUrlEncoded
    @POST("h/t/kt/add")
    Observable<Message> addKick(@Header("Authorization") String accessToken, @Field("long") int longTime, @Field("kickCount") int kickCount);

    @DELETE("h/t/kt/remove")
    Observable<Message> deleteKick(@Header("Authorization") String accessToken, @Query("_id") String id);

    @GET("tags/suggest-tags")
    Observable<ArrayList<String>> suggest(@Query("k") String query);

    @GET("tags/get-hot-tags")
    Observable<ArrayList<String>> getHotTag();

    @FormUrlEncoded
    @POST("tags/update-tag")
    Observable<ResponseBody> updateTag(@Field("name") String name);

    @GET("v2/notification")
    Observable<List<NotificationBean>> getNotificationList(@Header("Authorization") String accessToken, @Query("skip") int skip, @Query("limit") int limit);

    // notification ------------------

    @GET("v2/notification/not_seen_number")
    Observable<NotificationUnSeenNumber> getNotificationUnseenNumber(@Header("Authorization") String accessToken);

    @FormUrlEncoded
    @POST("v2/notification/seen")
    Observable<NotificationSeen> seenNotificationId(@Header("Authorization") String accessToken, @Field("_id") String _id);

    @POST("v2/notification/seen_all")
    Observable<NotificationSeen> seenNotificationAll(@Header("Authorization") String accessToken);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "v2/notification", hasBody = true)
    Observable<Message> removeNotificationId(@Header("Authorization") String accessToken, @Field("_id") String _id);

    @FormUrlEncoded
    @DELETE("v2/notification/all")
    Observable<Message> removeNotificationAll(@Header("Authorization") String accessToken);

    @GET("categories")
    Observable<ArrayList<CategoriesNews>> getAllCategory();


    //news ------------------------------------

    @GET("posts")
    Observable<ArrayList<PostNews>> getAllNews(@Query("categories") String id);

    @GET("h/ns/get-news")
    Observable<ArrayList<PostNews>> getNewsByWeek(@Header("Authorization") String accessToken, @Query("week") String week, @Query("cateId") String cate_id);


    //

    //    bookmark news
    @GET("h/ns/get-bookmark")
    Observable<ArrayList<PostNews>> getBookmarkNews(@Header("Authorization") String accessToken);

    @FormUrlEncoded
    @POST("h/ns/bookmark")
    Observable<Message> addBookmarkNews(@Header("Authorization") String accessToken, @Field("newsId") String newsId);

    @DELETE("h/ns/un-bookmark")
    Observable<Message> deleteBookmarkNews(@Header("Authorization") String accessToken, @Query("newsId") String newsId);

    //cấu hình
    @FormUrlEncoded
    @PUT("user/update-profile")
    Observable<Message> updateDataUser(@Header("Authorization") String accessToken, @Field("weekNumber") int weekNumber, @Field("phone") String phone, @Field("dateBirth") String dateBirth);

    @FormUrlEncoded
    @PUT("user/update-profile")
    Observable<Message> updateMom(@Header("Authorization") String accessToken, @Field("weekNumber") int weekNumber, @Field("phone") String phone, @Field("dateBirth") String dateBirth, @Field("name") String name);

    @FormUrlEncoded
    @PUT("h/bb/update-info")
    Observable<Message> updateBaby(@Header("Authorization") String accessToken, @Field("name") String name, @Field("gender") String gender, @Field("week") int week);

    @GET("user/get-info")
    Observable<UserInfo> getInfoUserById(@Header("Authorization") String accessToken, @Query("_id") String _id);

    @GET("user/get-info")
    Observable<UserInfo> getUserInfo(@Header("Authorization") String accessToken);

    @GET("h/bb/get-info")
    Observable<UserBaby> getInfoBaby(@Header("Authorization") String accessToken);

    @Multipart
    @PUT("user/update-avatar")
    Observable<Message> changeAvatar(@Header("Authorization") String accessToken, @Part MultipartBody.Part avatar);

    @Multipart
    @PUT("user/update_status")
    Observable<Message> changeUserStatus(@Header("Authorization") String accessToken, @Part MultipartBody.Part statusTitle);

    @Multipart
    @PUT("h/bb/change-avatar")
    Observable<Message> changeAvatarBaby(@Header("Authorization") String accessToken, @Part MultipartBody.Part avatar);

    // Post -----------------------------------------
    @GET("h/quiz/get-my-quiz")
    Observable<ArrayList<MyPost>> getListMyPost(@Header("Authorization") String accessToken);

    @GET("v2/quiz/like-list")
    Observable<ArrayList<UserPostDetails>> getAllUserLikedPost(@Header("Authorization") String accessToken, @Query("_id") String _id);

    @GET("v2/quiz/category/list")
    Observable<List<CategoryPost>> getListCategoriesPost(@Header("Authorization") String accessToken);

    @GET("v2/quiz/list")
    Observable<MyPost> getallPost(@Header("Authorization") String accessToken
            , @Query("category") String category
            , @Query("time") int time
            , @Query("skip") int skip
            , @Query("limit") int limit
    );

    @GET("v2/quiz/list_include_ads")
    Observable<MyPost> getListPostIncludeAds(@Header("Authorization") String accessToken
            , @Query("skip") int skip
            , @Query("limit") int limit
    );


    @GET("v2/quiz/search")
    Observable<MyPost> searchListPostByName(@Header("Authorization") String accessToken
            , @Query("q") String query, @Query("skip") int skip, @Query("limit") int limit);

    @GET("v2/quiz/search")
    Observable<MyPost> searchPost(@Header("Authorization") String accessToken
            , @Query("q") String query);

    @DELETE("v2/quiz/remove")
    Observable<Message> removePost(@Header("Authorization") String accessToken, @Query("_id") String postId);

    @DELETE("v2/quiz/remove-comment")
    Observable<Message> removeComment(@Header("Authorization") String accessToken, @Query("commentId") String commentId);

    @DELETE("v2/quiz/remove-reply")
    Observable<Message> removeReply(@Header("Authorization") String accessToken, @Query("replyCommentId") String replyId);

    @GET("v2/quiz/list")
    Observable<MyPost> getListMyPostByComment(@Header("Authorization") String accessToken, @Query("group") String groupId, @Query("comment") int comment, @Query("skip") int skip, @Query("limit") int limit);

    @GET("v2/quiz/list")
    Observable<MyPost> getListMyPostByLike(@Header("Authorization") String accessToken, @Query("group") String groupId, @Query("like") int like, @Query("skip") int skip, @Query("limit") int limit);

    @GET("v2/quiz/list")
    Observable<MyPost> getPostByGroupId(@Header("Authorization") String accessToken
            , @Query("group") String groupId
            , @Query("skip") long skip
            , @Query("limit") int limit
    );

    @GET("v2/quiz/features")
    Observable<MyPost> getListProductByLikeAndComment(@Header("Authorization") String accessToken, @Query("group") String groupId, @Query("sortBy") String sortBy, @Query("skip") int skip, @Query("limit") int limit);

    @GET("v2/quiz/comment")
    Observable<List<CommentDetails>> getListCommentByPostId(@Header("Authorization") String accessToken, @Query("questionId") String postId, @Query("skip") int skip, @Query("limit") int limit, @Query("skipReply") int skipReply, @Query("limitReply") int limitReply);

    @GET("v2/quiz/reply-list")
    Observable<CommentDetails> getListReplyByCommentId(@Header("Authorization") String accessToken, @Query("commentId") String commentId, @Query("skip") int skip, @Query("limit") int limit);

    @Multipart
    @POST("v2/quiz/create")
    Observable<Message> createPost(
            @Header("Authorization") String accessToken
            , @Part("title") RequestBody title
            , @Part("content") RequestBody content
            , @Part List<MultipartBody.Part> images
            , @Part("anonymous") RequestBody anonymous
            , @Part("group") RequestBody group
            , @Part("type") RequestBody type
            , @Part List<MultipartBody.Part> videos
            , @Part("background") RequestBody background
            , @Part("color") RequestBody color
            , @Part("link") RequestBody link
    );

    @FormUrlEncoded
    @POST("v2/quiz/create")
    Observable<AddPostResult> createPostv2(
            @Header("Authorization") String accessToken
            , @Field("title") String title
            , @Field("content") String content
            , @Field("images") List<String> images
            , @Field("videoUrl") String videoUrl
            , @Field("anonymous") Boolean anonymous
            , @Field("group") String group
            , @Field("type") String type
            , @Field("background") Integer background
            , @Field("color") String color
            , @Field("link") String link
    );

    @FormUrlEncoded
    @POST("v2/quiz/comment")
    Observable<CommentDetails> createCommentv2(
            @Header("Authorization") String accessToken,
            @Field("_id") String id
            , @Field("content") String content
            , @Field("image") String fileUrl
    );

    @FormUrlEncoded
    @POST("v2/quiz/reply-comment")
    Observable<ReplyDetails> createReplyv2(
            @Header("Authorization") String accessToken
            , @Field("commentId") String commentId
            , @Field("content") String content
            , @Field("image") String image
    );

    @FormUrlEncoded
    @POST("v2/quiz/like")
    Observable<Message> likePost(@Header("Authorization") String accessToken, @Field("_id") String quizId);

    @FormUrlEncoded
    @POST("v2/quiz/save")
    Observable<Message> savePost(@Header("Authorization") String accessToken, @Field("_id") String quizId);

    @FormUrlEncoded
    @POST("v2/quiz/like-comment")
    Observable<Message> likeCommentPost(@Header("Authorization") String accessToken, @Field("commentId") String commentId);

    @FormUrlEncoded
    @POST("v2/quiz/like-reply")
    Observable<Message> likeReplyPost(@Header("Authorization") String accessToken, @Field("replyCommentId") String replyCommentId);

    @FormUrlEncoded
    @POST("v2/quiz/report")
    Observable<Message> reportPost(@Header("Authorization") String accessToken, @Field("_id") String quizId, @Field("reason") String reason);

    @FormUrlEncoded
    @POST("v2/quiz/pin")
    Observable<Message> pinQuiz(@Header("Authorization") String accessToken, @Field("_id") String quizId);

    @FormUrlEncoded
    @POST("v2/quiz/unpin")
    Observable<Message> unPinQuiz(@Header("Authorization") String accessToken, @Field("_id") String quizId);

    @GET("v2/quiz/detail")
    Observable<PostDetails> getDetailPost(@Header("Authorization") String accessToken, @Query("_id") String _id);

    @GET("v2/quiz/reply-list")
    Observable<ReplyDetails> getReplyDetails(@Header("Authorization") String accessToken, @Query("commentId") String _id);

    @GET("v2/quiz/random")
    Observable<PostDetails> getPostHome(@Header("Authorization") String accessToken);

    @GET("h/quiz/pin/get")
    Observable<PostDetails> getPostPin(@Header("Authorization") String accessToken);

    //Group
    @GET("v2/quiz/group/list")
    Observable<List<JoinGroupDetails>> getJoinGroup(@Header("Authorization") String accessToken);

    @GET("v2/quiz/group/not-join")
    Observable<List<UnJoinGroupDetails>> getUnjoinGroup(@Header("Authorization") String accessToken);

    @FormUrlEncoded
    @POST("v2/quiz/group/join")
    Observable<Message> joinAndUnjoinGroup(@Header("Authorization") String accessToken, @Field("group") String groupId);

    @FormUrlEncoded
    @POST("v2/quiz/group/join_multiple")
    Observable<Message> joinMultiGroup(@Header("Authorization") String accessToken, @Field("group") List<String> groupIdList);

    @GET("v2/quiz/tag/list")
    Observable<List<GroupByTag>> getListGroupByTag(@Header("Authorization") String accessToken);

    @GET("v2/quiz/group/detail")
    Observable<GroupDetails> getGroupDetailsById(@Header("Authorization") String accessToken, @Query("_id") String groupId);

    @GET("v2/quiz/group/users")
    Observable<List<User>> getUserGroup(@Header("Authorization") String accessToken,
                                        @Query("group") String groupId
            , @Query("skip") long skip
            , @Query("limit") int limit, @Query("sortBy") String sortType);

    @GET("v2/quiz/group/list")
    Observable<List<JoinGroupDetails>> getListGroupByUserId(@Header("Authorization") String accessToken,
                                                            @Query("user") String userId, @Query("skip") long skip, @Query("limit") int limit);

    //rate ---------------
    @FormUrlEncoded
    @POST("rating/rating-doctor")
    Observable<Message> rate(@Header("Authorization") String accessToken, @Field("time_id") String time_id, @Field("score") int password, @Field("content") String content);

    @GET("user/get-info-home")
    Observable<Splash> getInfoUser(@Header("Authorization") String accessToken);

    // diary --------------------
    @GET("h/dr/get-photo")
    Observable<Diary> getDiaryByWeekAndDay(@Header("Authorization") String accessToken, @Query("week") int week, @Query("day") int day);

    @GET("h/dr/get-all-photo")
    Observable<List<Diary>> getListDiary(@Header("Authorization") String accessToken, @Query("skip") int skip, @Query("limit") int limit);

    @Multipart
    @POST("h/dr/post-photo-by-week")
    Observable<Message> postDiaryByWeekAndDay(@Header("Authorization") String accessToken, @Part MultipartBody.Part image,
                                              @Part("title") RequestBody title,
                                              @Part("week") RequestBody week,
                                              @Part("day") RequestBody day);

    @Multipart
    @POST("h/dr/post-photo")
    Observable<Message> postDiary(@Header("Authorization") String accessToken, @Part MultipartBody.Part image,
                                  @Part("title") RequestBody title);

    @DELETE("h/dr/remove-photo")
    Observable<Message> deleteDiary(@Header("Authorization") String accessToken, @Query("photoId") int photoId);

    //    quiz ------------------
    @GET("h/t/mcquiz/get-mcquiz")
    Observable<ArrayList<Quiz>> getQuizByCategories(@Header("Authorization") String accessToken, @Query("cate") String cateId);

    @GET("h/ct/get-list")
    Observable<ArrayList<QuizCategories>> getCategoriesQuiz(@Header("Authorization") String accessToken);

    @GET("h/post/get-detail-by-week")
    Observable<MyBabyDetail> getDetaiWeek(@Header("Authorization") String accessToken, @Query("week") int week);

    @GET("h/post/get-info-by-week")
    Observable<List<LifeSciences>> getLifeSciences(@Header("Authorization") String accessToken, @Query("week") int week);

    //    Nutrition ----------------------
    @GET("v2/nutrition/categories")
    Observable<NutritionCatAndRecommend> getListNutritionRecommendAndCategories(@Header("Authorization") String accessToken, @Query("skip") int skip, @Query("limit") int limit);

    @GET("v2/nutrition/list")
    Observable<List<Nutrition>> getListNutritionByCategoryId(@Header("Authorization") String accessToken, @Query("category") String categoryId, @Query("skip") int skip, @Query("limit") int limit);

    @GET("v2/nutrition/detail")
    Observable<Nutrition> getNutritionDetailById(@Header("Authorization") String accessToken, @Query("_id") String nutritionId);

    //service --------------------
    @GET("serv/category/list")
    Observable<List<ServiceCategory>> getCategory(@Header("Authorization") String accessToken);

    @GET("serv/get-shop")
    Observable<List<Shop>> getProvider(@Header("Authorization") String accessToken, @Query("category") String category);

    @GET("serv/list")
    Observable<List<ServiceDetails>> getDetail(@Header("Authorization") String accessToken, @Query("category") String category, @Query("shop") String shop);

    @FormUrlEncoded
    @POST("serv/book")
    Observable<Message> bookService(@Header("Authorization") String accessToken,
                                    @Field("serv") String serv,
                                    @Field("shop") String shop,
                                    @Field("name") String name,
                                    @Field("phone") String phone,
                                    @Field("address") String address,
                                    @Field("time") Date time
    );

    @GET("serv/history")
    Observable<List<HistoryBook>> getHistory(@Header("Authorization") String accessToken);

    @FormUrlEncoded
    @POST("serv/rate-shop")
    Observable<Message> Rate(@Header("Authorization") String accessToken,
                             @Field("shop") String shop,
                             @Field("score") int score

    );

    @GET("serv/banner/list")
    Observable<List<BannerDetail>> getBanner(@Header("Authorization") String accessToken);

    @FormUrlEncoded
    @POST("serv/cancel")
    Observable<Message> CancelService(@Header("Authorization") String accessToken,
                                      @Field("bookId") String bookId);

    //  Update Version --------
    @GET("version/android")
    Observable<List<UpdateVersionBean>> getUpdateVersionList(@Header("Authorization") String accessToken);

}
