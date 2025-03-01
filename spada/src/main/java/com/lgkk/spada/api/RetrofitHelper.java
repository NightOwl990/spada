package com.lgkk.spada.api;


import com.lgkk.spada.R;
import com.lgkk.spada.model.AddPostResult;
import com.lgkk.spada.model.BannerDetail;
import com.lgkk.spada.model.Doctor;
import com.lgkk.spada.model.FilterTypeDetail;
import com.lgkk.spada.model.FilterTypeSecond;
import com.lgkk.spada.model.FlashCategory;
import com.lgkk.spada.model.HistoryBook;
import com.lgkk.spada.model.HospitalDetail;
import com.lgkk.spada.model.Message;
import com.lgkk.spada.model.NotificationUnSeenNumber;
import com.lgkk.spada.model.RankingDetail;
import com.lgkk.spada.model.RecommendCity;
import com.lgkk.spada.model.UpdateVersionBean;
import com.lgkk.spada.model.UploadDetails;
import com.lgkk.spada.model.User;
import com.lgkk.spada.model.UserSetting;
import com.lgkk.spada.model._getdata.DataMainObject;
import com.lgkk.spada.model.account.Splash;
import com.lgkk.spada.model.community.PostNews;
import com.lgkk.spada.model.community.group.GroupByTag;
import com.lgkk.spada.model.community.group.GroupDetails;
import com.lgkk.spada.model.community.group.JoinGroupDetails;
import com.lgkk.spada.model.community.group.UnJoinGroupDetails;
import com.lgkk.spada.model.community.post.CategoryPost;
import com.lgkk.spada.model.community.post.MyPost;
import com.lgkk.spada.model.community.post.PostDetails;
import com.lgkk.spada.model.community.post.PostService;
import com.lgkk.spada.model.community.post.PostServiceMostView;
import com.lgkk.spada.model.community.post.postdetails.CommentDetails;
import com.lgkk.spada.model.community.post.postdetails.ReplyDetails;
import com.lgkk.spada.model.community.post.postdetails.UserPostDetails;
import com.lgkk.spada.model.home.LifeSciences;
import com.lgkk.spada.model.home.NotificationBean;
import com.lgkk.spada.model.home.NotificationSeen;
import com.lgkk.spada.model.home.nutrition.Nutrition;
import com.lgkk.spada.model.home.nutrition.NutritionCatAndRecommend;
import com.lgkk.spada.model.service.FirstServiceCategory;
import com.lgkk.spada.model.service.MainServiceCategory;
import com.lgkk.spada.model.service.SecondServiceCategory;
import com.lgkk.spada.model.service.ServiceCategory;
import com.lgkk.spada.model.service.ServiceDetails;
import com.lgkk.spada.model.service.ServiceUtility;
import com.lgkk.spada.model.service.UpComingServiceCategory;
import com.lgkk.spada.model.shop.AddressInfo;
import com.lgkk.spada.model.shop.Cart;
import com.lgkk.spada.model.shop.Category;
import com.lgkk.spada.model.shop.Order;
import com.lgkk.spada.model.shop.Product;
import com.lgkk.spada.model.shop.Rating;
import com.lgkk.spada.model.shop.RatingScore;
import com.lgkk.spada.model.shop.ShopBanner;
import com.lgkk.spada.model.shop.SubCategory;
import com.lgkk.spada.model.user.Diary;
import com.lgkk.spada.model.user.DiaryContent;
import com.lgkk.spada.model.user.MyBabyDetail;
import com.lgkk.spada.model.user.Token;
import com.lgkk.spada.model.user.UserBaby;
import com.lgkk.spada.model.user.UserInfo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RetrofitHelper {
    private final BiboService biboService;
    private final GetDataService getDataService;
    private final StorageService storageService;

    @Inject
    public RetrofitHelper(BiboService biboService, GetDataService getDataService, StorageService storageService) {
        this.biboService = biboService;
        this.getDataService = getDataService;
        this.storageService = storageService;
    }

    //    Shop -------------
    public Observable<List<FlashCategory>> getFlashCategoryList(String accessToken) {
        List<FlashCategory> flashList = new ArrayList<>();
        flashList.add(new FlashCategory("1", "Flash Deal", "flash", "Giơi hạn thời gian", "Chỉ từ 99k", R.drawable.hot_girl_1, R.drawable.icon_fs_time, 5 * 60 * 60 * 1000));
        flashList.add(new FlashCategory("1", "Lập nhóm Shopping", "group", "Thỏa thích mua sắm", "Giảm tới 11%", R.drawable.hot_girl_4, R.drawable.icon_fs_group, 5 * 60 * 60 * 1000));
        flashList.add(new FlashCategory("1", "Sản phẩm bán chạy", "hotsale", "Hàng đẹp giá đúng", "Số lượng có hạn", R.drawable.hot_girl_1, R.drawable.icon_fs_heart, 5 * 60 * 60 * 1000));
        flashList.add(new FlashCategory("1", "Ngày đặc biệt", "special", "Chỉ bán trong dịp lễ", "Giới hạn", R.drawable.hot_girl_2, R.drawable.icon_fs_hotdeal, 5 * 60 * 60 * 1000));
        return Observable.just(flashList);
    }


    public Observable<List<RankingDetail>> getRankingDetailList(String accessToken) {
        List<RankingDetail> rankingDetailList = new ArrayList<>();
        rankingDetailList.add(new RankingDetail("1", "Lượt like", "http://uteen.vn/uploads/108077331d354713b867edfdb02b83c9/2016/11/03/1478134317_2.jpg",
                "HanHee","Ngọc Trinh","Sơn Tùng", R.drawable.post_like_white, "Like"));
        rankingDetailList.add(new RankingDetail("2", "Lượt share", "https://i.a4vn.com/2019/2/28/bat-ngo-voi-do-giau-sang-cua-hot-girl-tai-tieng-ca-mau-thuy-vi-t-00e766.jpeg",
                "Long Nguyễn","Ngọc Trinh","Sơn Tùng", R.drawable.icon_share_white, "Share"));
        rankingDetailList.add(new RankingDetail("3", "Lượt đến thăm", "https://anh.24h.com.vn/upload/3-2016/images/2016-07-07/1467878459-146787406963062-38.jpg",
                "Long Nguyễn","Ngọc Trinh","Sơn Tùng", R.drawable.diagnose_view_cnt_bg, "Visit"));
        rankingDetailList.add(new RankingDetail("1", "Lượt like", "http://uteen.vn/uploads/108077331d354713b867edfdb02b83c9/2016/11/03/1478134317_2.jpg",
                "HanHee","Ngọc Trinh","Sơn Tùng", R.drawable.post_like_white, "Like"));
        rankingDetailList.add(new RankingDetail("2", "Lượt share", "https://i.a4vn.com/2019/2/28/bat-ngo-voi-do-giau-sang-cua-hot-girl-tai-tieng-ca-mau-thuy-vi-t-00e766.jpeg",
                "Long Nguyễn","Ngọc Trinh","Sơn Tùng", R.drawable.icon_share_white, "Share"));


        return Observable.just(rankingDetailList);
    }


    public Observable<List<BannerDetail>> getShopServiceBannerList(String accessToken, int start, int limit) {
        List<BannerDetail> biboServiceUtilityList = new ArrayList<>();
        biboServiceUtilityList.add(new BannerDetail("1", "https://3.bp.blogspot.com/-NbEOD2hxeNY/WuNfaJ7D-SI/AAAAAAAARzY/9B6tEcxHOPQa9DISBxtaXx0jOQhVG2BIgCLcBGAs/s1600/banner-design-banner-thiet-ke-banner-dien-may.jpg"));
        biboServiceUtilityList.add(new BannerDetail("2", "http://cdn-gd-v1.webbnc.net/useruploads/userfiles//513640/images/khuyen-mai-tranh-amia-ha-nam-nhan-dip-khai-truong(1).jpg"));
        biboServiceUtilityList.add(new BannerDetail("3", "https://www.khatoco.com/Portals/0/2016/KM.png"));
        return Observable.just(biboServiceUtilityList);
    }

    public Observable<List<ServiceCategory>> getShopCategoryServiceList(String accessToken, int start, int limit) {
        List<ServiceCategory> biboServiceCategoryList = new ArrayList<>();
        biboServiceCategoryList.add(new ServiceCategory("1", "Chống lão hóa", R.drawable.ic_home_service_chonglaohoa));
        biboServiceCategoryList.add(new ServiceCategory("2", "Chăm sóc da", R.drawable.ic_home_service_chamsocda));
        biboServiceCategoryList.add(new ServiceCategory("3", "Chăm sóc tóc", R.drawable.ic_home_service_chamsoctoc));
        biboServiceCategoryList.add(new ServiceCategory("4", "Chỉnh hình môi", R.drawable.ic_home_service_dinhinhmoi));
        biboServiceCategoryList.add(new ServiceCategory("5", "Dưỡng trắng da", R.drawable.ic_home_service_lamtrang));
        biboServiceCategoryList.add(new ServiceCategory("6", "Trang điểm", R.drawable.ic_home_service_trangdiem));
        biboServiceCategoryList.add(new ServiceCategory("7", "Sửa khuyết điểm", R.drawable.ic_home_service_chuakhuyetdiem));
        biboServiceCategoryList.add(new ServiceCategory("8", "Triệt lông", R.drawable.ic_home_service_trietlong));
        biboServiceCategoryList.add(new ServiceCategory("9", "Cằm V-Line", R.drawable.icon_service_mat_v_line));
        biboServiceCategoryList.add(new ServiceCategory("10", "Chỉnh hình", R.drawable.ic_home_service_chinhhinh));
        return Observable.just(biboServiceCategoryList);
    }

    public Observable<List<RecommendCity>> getShopRecommendCityList(String accessToken, int start, int limit) {
        List<RecommendCity> recommendList = new ArrayList<>();
        recommendList.add(new RecommendCity("1", "https://downtownpainesville.org/wp-content/uploads/2018/07/ha-noi-co-bao-nhieu-quan8.jpg"
                ,"Sức mạnh công nghệ", "Hà Nội", 123, 456));
        recommendList.add(new RecommendCity("2", "https://cdn.tuoitre.vn/thumb_w/586/2019/1/9/toan-canh-q1-1547005523042936218014.jpg"
                ,"Xu hướng", "Hồ Chí Minh", 123, 456));
        recommendList.add(new RecommendCity("3", "https://travel.com.vn/images/destination/Large/dg_160519_du-lich-da-nang.jpg"
                ,"Đô thị phát triển", "Đà nẵng", 123, 456));
        return Observable.just(recommendList);
    }



    //Get Data --------
    public Observable<DataMainObject> getData(String mode, String myCategory, String deviceId) {
        return getDataService.getData(mode, myCategory, deviceId);
    }

    public Observable<UploadDetails> uploadImageVideo(String accessToken, MultipartBody.Part file) {
        return storageService.uploadImageVideo(accessToken, file);
    }

    // Tracking User ----------------
    public Observable<Integer> postTracking(String accessToken, String userScreen, long userTime, String userType, String userOs) {
        return biboService.postTracking(accessToken, userScreen, userTime, userType, userOs);
    }

    public Observable<Integer> postTrackingV2(String accessToken, String userScreen, long startTime, long endTime, String userOs) {
        return biboService.postTrackingV2(accessToken, userScreen, startTime, endTime, userOs);
    }



    //    Diary ---------------------
    public Observable<Diary> getDiaryByWeekAndDay(String accessToken, int week, int day) {
        return biboService.getDiaryByWeekAndDay(accessToken, week, day);
    }

    public Observable<List<Diary>> getListDiary(String accessToken, int skip, int limit) {

        List<Diary> diaryList = new ArrayList<>();
        List<DiaryContent> diaryContents = new ArrayList<>();
        diaryContents.add(new DiaryContent("1"));
        diaryContents.add(new DiaryContent("1"));
        for (int i = 0; i < 30; i++) {
            diaryList.add(new Diary(diaryContents));
        }

        List<Diary> resultDiaryList = new ArrayList<>();
        for (int i = skip; i < diaryList.size(); i++) {
            resultDiaryList.add(diaryList.get(i));
            if (resultDiaryList.size() == limit) break;
        }
        return Observable.just(resultDiaryList);
//        return biboService.getListDiary(accessToken, skip, limit);
    }

    public Observable<Message> postDiaryByWeekAndDay(String accessToken, MultipartBody.Part image, RequestBody title, RequestBody week, RequestBody day) {
        return biboService.postDiaryByWeekAndDay(accessToken, image, title, week, day);
    }

    public Observable<Message> postDiary(String accessToken, MultipartBody.Part image, RequestBody title) {
        return biboService.postDiary(accessToken, image, title);
    }

    public Observable<Message> deleteDiary(String accessToken, int photoId) {
        return biboService.deleteDiary(accessToken, photoId);
    }

    //    Rating Product ---------------
    public Observable<Message> rateProduct(String accessToken, String productId, int score, String comment) {
        return biboService.rateProduct(accessToken, productId, score, comment);
    }

    public Observable<List<Rating>> getListRatingByScore(String accessToken, String productId, int score, int skip, int limit) {
        return biboService.getListRatingByScore(accessToken, productId, score, skip, limit);
    }

    public Observable<List<Rating>> getListRating(String accessToken, String productId, int skip, int limit) {
        return biboService.getListRating(accessToken, productId, skip, limit);
    }

    public Observable<RatingScore> getListRatingScoreDetail(String accessToken, String productId) {
        return biboService.getListRatingScoreDetail(accessToken, productId);
    }

    // Shop Product ------------------
    public Observable<Message> likeProductById(String accessToken, String productId) {
        return biboService.likeProductById(accessToken, productId);
    }

    public Observable<Category> getCategoryDetailById(String accessToken, String categoryId) {
        return biboService.getCategoryDetailById(accessToken, categoryId);
    }

    public Observable<SubCategory> getSubCategoryDetailById(String accessToken, String subCategoryId) {
        return biboService.getSubCategoryDetailById(accessToken, subCategoryId);
    }


    public Observable<List<Category>> getListCategory(String accessToken) {
        return biboService.getListCategory(accessToken);
    }

    public Observable<List<ShopBanner>> getShopHomeBanner(String accessToken, int skip, int limit) {
        return biboService.getShopHomeBanner(accessToken, skip, limit);
    }

    public Observable<List<Product>> getListProductNewest(String accessToken, int skip, int limit) {
        return biboService.getListProductNewest(accessToken, skip, limit);
    }

    public Observable<List<SubCategory>> getListSubCategory(String accessToken, String categoryId, int skip, int limit) {
        return biboService.getListSubCategory(accessToken, categoryId, skip, limit);
    }

    public Observable<Product> getProduct(String accessToken, String id) {
        return biboService.getProduct(accessToken, id);
    }

    public Observable<List<Product>> getListProductRecommend(String accessToken, String categoryId, int skip, int limit) {
        return biboService.getListProductRecommend(accessToken, categoryId, skip, limit);
    }

    public Observable<List<Product>> getListProductByCategory(String accessToken, String categoryId, int skip, int limit) {
        return biboService.getListProductByCategory(accessToken, categoryId, skip, limit);
    }

    public Observable<List<Product>> getListProductBySubCategory(String accessToken, int skip, int limit) {
        return biboService.getListProductBySubCategory(accessToken, skip, limit);
    }

    public Observable<List<Product>> getListProductBySubCategory(String accessToken, String subCategoryId, int skip, int limit) {
        return biboService.getListProductBySubCategory(accessToken, subCategoryId, skip, limit);
    }

    public Observable<List<Product>> getThreeSuggestProduct(String accessToken, int size) {
        return biboService.getThreeSuggestProduct(accessToken, size);
    }

    public Observable<List<Product>> searchProductByName(String accessToken, String query, int skip, int limit) {
        return biboService.searchProductByName(accessToken, query, skip, limit);
    }

    public Observable<List<Product>> searchListProductByName(String accessToken, String query, int skip, int limit) {
        return biboService.searchListProductByName(accessToken, query, skip, limit);
    }

    public Observable<MyPost> getListProductByLikeAndComment(String accessToken, String groupId, String sortBy, int skip, int limit) {
        return biboService.getListProductByLikeAndComment(accessToken, groupId, sortBy, skip, limit);
    }


    //    Login -------------------
    public Observable<User> loginFacebook(String accessToken, String deviceToken, String os, int version) {
        return biboService.loginFacebook(accessToken, deviceToken, os, version);
    }

    public Observable<User> loginGoogle(String accessToken, String deviceToken, String os, int version) {
        return biboService.loginGoogle(accessToken, deviceToken, os, version);
    }

    public Observable<User> loginZalo(String accessToken, String deviceToken, String os, int version) {
        return biboService.loginZalo(accessToken, deviceToken, os, version);
    }


    //    Cart -----------------
    public Observable<Message> addProductToCart(String accessToken, String productId, int quantity, boolean isCheck) {
        return biboService.addProductToCart(accessToken, productId, quantity, isCheck);
    }

    public Observable<Message> removeAllProductOnCart(String accessToken) {
        return biboService.removeAllProductOnCart(accessToken);
    }

    public Observable<Message> removeOneProductOnCartByCartId(String accessToken, String cartId) {
        return biboService.removeOneProductOnCartByCartId(accessToken, cartId);
    }

    public Observable<List<Cart>> getListCartProduct(String accessToken) {
        return biboService.getListCartProduct(accessToken);
    }

    public Observable<Message> orderOneProduct(String accessToken, String productId, int quantity,
                                               String fullName, String phoneNumber, String email, String address) {
        return biboService.orderOneProduct(accessToken, productId, quantity, fullName, phoneNumber, email, address);
    }

    public Observable<Message> orderAll(String accessToken, String fullName, String phoneNumber, String email, String address) {
        return biboService.orderAll(accessToken, fullName, phoneNumber, email, address);
    }

    public Observable<Message> orderSelect(String accessToken, List<String> cartListId, String fullName, String phoneNumber, String email, String address) {
        return biboService.orderSelect(accessToken, cartListId, fullName, phoneNumber, email, address);
    }

    public Observable<List<Order>> getOrderList(String accessToken, int skip, int limit) {
        return biboService.getOrderList(accessToken, skip, limit);
    }

    public Observable<Message> removeOrderId(String accessToken, String orderId, String reason) {
        return biboService.removeOrderId(accessToken, orderId, reason);
    }

//    User Address Shop ------------------

    public Observable<List<AddressInfo>> getListAddressInfo(String accessToken) {
        return biboService.getListAddressInfo(accessToken);
    }

    public Observable<Message> addAddressInfo(String accessToken, String fullName, String phoneNumber, String email, String address) {
        return biboService.addAddressInfo(accessToken, fullName, phoneNumber, email, address);
    }

    public Observable<Message> removeAddressInfo(String accessToken, String addressid) {
        return biboService.removeAddressInfo(accessToken, addressid);
    }

    public Observable<AddressInfo> getAddressInfoById(String accessToken, String addressid) {
        return biboService.getAddressInfoById(accessToken, addressid);
    }

    public Observable<Message> updateAddressInfo(String accessToken, String addressId, String fullName, String phoneNumber, String email, String address) {
        return biboService.updateAddressInfo(accessToken, addressId, fullName, phoneNumber, email, address);
    }

    //    User ----------
    public Observable<Message> updateDataUser(String accessToken, int weekNumber, String phone, String dateBirth) {
        return biboService.updateDataUser(accessToken, weekNumber, phone, dateBirth);
    }


    public Observable<Message> changeAvatar(String accessToken, MultipartBody.Part avatar) {
        return biboService.changeAvatar(accessToken, avatar);
    }

    public Observable<User> getUserInfoById(String accessToken, String userId) {
        return biboService.getUserInfoById(accessToken, userId);
    }

    public Observable<User> getInfoUserMom(String accessToken) {
        return biboService.getInfoUserMom(accessToken);
    }

    public Observable<List<ServiceCategory>> getUserServiceList(String accessToken) {
        List<ServiceCategory> userSettingList = new ArrayList<>();
        userSettingList.add(new ServiceCategory("Khóa biểu", R.drawable.home_ic_record));
//        userSettingList.add(new ServiceCategory("Thông báo", R.drawable.me_icon_remind));
        userSettingList.add(new ServiceCategory("Nhật ký", R.drawable.me_icon_diary));
        userSettingList.add(new ServiceCategory("Sưu tập", R.drawable.me_icon_collect));
        return Observable.just(userSettingList);
    }

    public Observable<List<UserSetting>> getUserSettingList(String accessToken) {
        List<UserSetting> userSettingList = new ArrayList<>();
//        userSettingList.add(new UserSetting("Khóa biểu",R.drawable.home_ic_record));
        userSettingList.add(new UserSetting("Đơn hàng", R.drawable.me_icon_dingdan));
//        userSettingList.add(new UserSetting("Thông báo", R.drawable.me_icon_remind));
//        userSettingList.add(new UserSetting("Nhật ký", R.drawable.me_icon_diary));
//        userSettingList.add(new UserSetting("Chú ý",R.drawable.me_icon_care));
//        userSettingList.add(new UserSetting("Thảo luận",R.drawable.me_icon_topic));
//        userSettingList.add(new UserSetting("Sưu tập",R.drawable.me_icon_collect));
        userSettingList.add(new UserSetting("Phản hồi", R.drawable.me_icon_feedback));
        userSettingList.add(new UserSetting("Đăng xuất", R.drawable.logout_menu_icn));
//        userSettingList.add(new UserSetting("Cài đặt",R.drawable.me_icon_set));
        return Observable.just(userSettingList);
    }


    public Observable<List<UserSetting>> getOrderSettingList(String accessToken) {
        List<UserSetting> userSettingList = new ArrayList<>();
        userSettingList.add(new UserSetting("1", "Chờ thanh toán", R.drawable.home_pending_payment));
        userSettingList.add(new UserSetting("2", "Chờ vận chuyển", R.drawable.home_useing));
        userSettingList.add(new UserSetting("3", "Chờ giao hàng", R.drawable.home_my_order_evaluated));
        userSettingList.add(new UserSetting("4", "Đánh giá", R.drawable.home_my_order_write_diary));
        userSettingList.add(new UserSetting("5", "Hoàn tiền", R.drawable.home_my_order_refund));
        return Observable.just(userSettingList);
    }

    public Observable<List<UserSetting>> getMainSettingList(String accessToken) {
        List<UserSetting> userSettingList = new ArrayList<>();
        userSettingList.add(new UserSetting("1", "Giỏ hàng", R.drawable.home_giohang));
        userSettingList.add(new UserSetting("2", "Sưu tập", R.drawable.home_suutap));
        userSettingList.add(new UserSetting("3", "Vừa xem", R.drawable.home_vuaxem));
        userSettingList.add(new UserSetting("4", "Mã giảm giá", R.drawable.home_magiamgia));
        userSettingList.add(new UserSetting("5", "Ví", R.drawable.home_vidientu));
        userSettingList.add(new UserSetting("6", "Hỗ trợ", R.drawable.home_hotro));
        userSettingList.add(new UserSetting("7", "Chứng nhận", R.drawable.home_chungnhan));
        userSettingList.add(new UserSetting("8", "Mời bạn", R.drawable.home_moiban));
        return Observable.just(userSettingList);
    }


    public Observable<List<ServiceDetails>> getRecommendServiceList(String accessToken) {
        List<ServiceDetails> serviceList = new ArrayList<>();
        serviceList.add(new ServiceDetails("1"));
        serviceList.add(new ServiceDetails("2"));
        serviceList.add(new ServiceDetails("3"));
        serviceList.add(new ServiceDetails("4"));
        serviceList.add(new ServiceDetails("5"));
        return Observable.just(serviceList);
    }

    public Observable<Message> updateMom(String accessToken, int weekNumber, String phone, String dateBirth, String name) {
        return biboService.updateMom(accessToken, weekNumber, phone, dateBirth, name);
    }

    public Observable<UserInfo> getUserInfo(String accessToken) {
        return biboService.getUserInfo(accessToken);
    }


    public Observable<Message> updateBaby(String accessToken, String name, String gender, int week) {
        return biboService.updateBaby(accessToken, name, gender, week);
    }

    public Observable<Message> changeAvatarBaby(String accessToken, MultipartBody.Part avatar) {
        return biboService.changeAvatarBaby(accessToken, avatar);
    }

    public Observable<UserBaby> getInfoBaby(String accessToken) {
        return biboService.getInfoBaby(accessToken);
    }

    //    Update Version -----------
    public Observable<List<UpdateVersionBean>> getUpdateVersionList(String accessToken) {
        return biboService.getUpdateVersionList(accessToken);
    }

//    Splash---------------

    public Observable<Message> sendEmail(String email) {
        return biboService.sendEmail(email);
    }

    public Observable<Splash> getInfoUser(String accessToken) {
        return biboService.getInfoUser(accessToken);
    }

    //    Post -------------------

    public Observable<List<CommentDetails>> getListCommentByPostId(String accessToken, String postId, int skip, int limit,
                                                                   int skipReply, int limitReply) {
        return biboService.getListCommentByPostId(accessToken, postId, skip, limit, skipReply, limitReply);
    }


    public Observable<CommentDetails> getListReplyByCommentId(String accessToken, String commentId, int skip, int limit) {
        return biboService.getListReplyByCommentId(accessToken, commentId, skip, limit);
    }


    public Observable<ArrayList<MyPost>> getListMyPost(String accessToken) {
        return biboService.getListMyPost(accessToken);
    }


    public Observable<ArrayList<UserPostDetails>> getAllUserLikedPost(String accessToken, String _id) {
        return biboService.getAllUserLikedPost(accessToken, _id);
    }

    public Observable<AddPostResult> createPostv2(String accessToken, String title, String content, List<String> images
            , String videoUrl, Boolean anonymous, String group, String type, Integer background, String color, String link) {
        return biboService.createPostv2(accessToken, title, content, images,
                videoUrl, anonymous, group, type, background, color, link);
    }

    public Observable<List<CategoryPost>> getListCategoriesPost(String accessToken) {
        return biboService.getListCategoriesPost(accessToken);
    }

    public Observable<MyPost> getallPost(String accessToken, String category, int time, int skip, int limit) {
        return biboService.getallPost(accessToken, category, time, skip, limit);
    }

    public Observable<MyPost> getListPostIncludeAds(String accessToken, int skip, int limit) {
        return biboService.getListPostIncludeAds(accessToken, skip, limit);
    }


    public Observable<MyPost> searchListPostByName(String accessToken, String query, int skip, int limit) {
        return biboService.searchListPostByName(accessToken, query, skip, limit);
    }

    public Observable<MyPost> searchPost(String accessToken, String query) {
        return biboService.searchPost(accessToken, query);
    }

    public Observable<Message> createPost(String accessToken, RequestBody title, RequestBody content,
                                          List<MultipartBody.Part> images, RequestBody anonymous, RequestBody group, RequestBody type
            , List<MultipartBody.Part> videos, RequestBody background, RequestBody color, RequestBody link) {
        return biboService.createPost(accessToken, title, content, images, anonymous, group, type, videos, background, color, link);
    }

    public Observable<Message> removePost(String accessToken, String postId) {
        return biboService.removePost(accessToken, postId);
    }

    public Observable<Message> removeComment(String accessToken, String commentId) {
        return biboService.removeComment(accessToken, commentId);
    }

    public Observable<Message> removeReply(String accessToken, String replyId) {
        return biboService.removeReply(accessToken, replyId);
    }

    public Observable<MyPost> getListMyPostByComment(String accessToken, String groupId, int comment, int skip, int limit) {
        return biboService.getListMyPostByComment(accessToken, groupId, comment, skip, limit);
    }

    public Observable<MyPost> getListMyPostByLike(String accessToken, String groupId, int like, int skip, int limit) {
        return biboService.getListMyPostByLike(accessToken, groupId, like, skip, limit);
    }

    public Observable<MyPost> getPostByGroupId(String accessToken, String groupId, long skip, int limit) {
        return biboService.getPostByGroupId(accessToken, groupId, skip, limit);
    }


    //group -------------------------
    public Observable<List<JoinGroupDetails>> getJoinGroup(String accessToken) {
        return biboService.getJoinGroup(accessToken);
    }

    public Observable<List<UnJoinGroupDetails>> getUnjoinGroup(String accessToken) {
        return biboService.getUnjoinGroup(accessToken);
    }

    public Observable<Message> joinAndUnjoinGroup(String accessToken, String groupId) {
        return biboService.joinAndUnjoinGroup(accessToken, groupId);
    }

    public Observable<Message> joinMultiGroup(String accessToken, List<String> groupIdList) {
        return biboService.joinMultiGroup(accessToken, groupIdList);
    }


    public Observable<List<GroupByTag>> getListGroupByTag(String accessToken) {
        return biboService.getListGroupByTag(accessToken);
    }

    public Observable<GroupDetails> getGroupDetailsById(String accessToken, String groupId) {
        return biboService.getGroupDetailsById(accessToken, groupId);
    }

    public Observable<List<User>> getUserGroup(String accessToken, String groupId, long skip, int limit, String sortType) {
        return biboService.getUserGroup(accessToken, groupId, skip, limit, sortType);
    }

    public Observable<List<JoinGroupDetails>> getListGroupByUserId(String accessToken, String userId, long skip, int limit) {
        return biboService.getListGroupByUserId(accessToken, userId, skip, limit);
    }

    public Observable<CommentDetails> createCommentv2(String accessToken, String id, String content, String fileUrl) {
        return biboService.createCommentv2(accessToken, id, content, fileUrl);
    }

    public Observable<ReplyDetails> createReplyv2(String accessToken, String commentId, String content, String image) {
        return biboService.createReplyv2(accessToken, commentId, content, image);
    }

    public Observable<Message> likePost(String accessToken, String quizId) {
        return biboService.likePost(accessToken, quizId);
    }

    public Observable<Message> savePost(String accessToken, String quizId) {
        return biboService.savePost(accessToken, quizId);
    }

    public Observable<Message> likeCommentPost(String accessToken, String commentId) {
        return biboService.likeCommentPost(accessToken, commentId);
    }

    public Observable<Message> likeReplyPost(String accessToken, String replyCommentId) {
        return biboService.likeReplyPost(accessToken, replyCommentId);
    }

    public Observable<Message> reportPost(String accessToken, String quizId, String reason) {
        return biboService.reportPost(accessToken, quizId, reason);
    }

    public Observable<Message> pinQuiz(String accessToken, String quizId) {
        return biboService.pinQuiz(accessToken, quizId);
    }

    public Observable<Message> unPinQuiz(String accessToken, String quizId) {
        return biboService.unPinQuiz(accessToken, quizId);
    }

    public Observable<PostDetails> getDetailPost(String accessToken, String _id) {
        return biboService.getDetailPost(accessToken, _id);
    }

    public Observable<ReplyDetails> getReplyDetails(String accessToken, String _id) {
        return biboService.getReplyDetails(accessToken, _id);
    }

    public Observable<PostDetails> getPostHome(String accessToken) {
        return biboService.getPostHome(accessToken);
    }

    public Observable<PostDetails> getPostPin(String accessToken) {
        return biboService.getPostPin(accessToken);
    }

    //Category Service Fragment
    public Observable<List<ServiceCategory>> getCategory(String accessToken) {
        return biboService.getCategory(accessToken);
    }

    public Observable<List<HistoryBook>> getHistory(String accessToken) {
        return biboService.getHistory(accessToken);
    }

    public Observable<List<BannerDetail>> getBanner(String accessToken) {
        return biboService.getBanner(accessToken);
    }

    public Observable<Message> CancelService(String accessToken, String bookId) {
        return biboService.CancelService(accessToken, bookId);
    }

//    Home -----------

    public Observable<ArrayList<PostNews>> getHomeNews(String accessToken, int week, int limit) {
        return biboService.getNewsHome(accessToken, week, limit);
    }

    public Observable<ArrayList<PostNews>> getHomeNewsLastest(String accessToken, int week, int limit, int skip) {
        return biboService.getHomeNewsLastest(accessToken, week, limit, skip);
    }


    public Observable<Token> refreshToken(String accessToken, String deviceToken) {
        return biboService.refresh(accessToken, deviceToken);
    }

    public Observable<MyBabyDetail> getDetaiWeek(String accessToken, int week) {
        return biboService.getDetaiWeek(accessToken, week);
    }

    public Observable<List<LifeSciences>> getLifeSciences(String accessToken, int week) {
        return biboService.getLifeSciences(accessToken, week);
    }

    public Observable<List<ServiceCategory>> getListHomeService(String accessToken) {
        List<ServiceCategory> biboServiceCategoryList = new ArrayList<>();
        biboServiceCategoryList.add(new ServiceCategory(Constants.NUTRITION_HOME, R.drawable.photo_icon_things_chifushi, true));
        biboServiceCategoryList.add(new ServiceCategory(Constants.MUSIC_HOME, R.drawable.ic_tool_music, true));
//        biboServiceCategoryList.add(new ServiceCategory(Constants.BMI_HOME, R.drawable.photo_icon_things_huodong));
        biboServiceCategoryList.add(new ServiceCategory(Constants.NAMED_HOME, R.drawable.diary_btn_latenight));
        biboServiceCategoryList.add(new ServiceCategory(Constants.HANDBOOK_HOME, R.drawable.diary_btn_condom));
//        biboServiceCategoryList.add(new ServiceCategory(Constants.HEIGHT_HOME, R.drawable.diary_btn_check));

        biboServiceCategoryList.add(new ServiceCategory(Constants.MORE_HOME, R.drawable.ic_service_more));
        return Observable.just(biboServiceCategoryList);
    }

    public Observable<List<ServiceCategory>> getListHomeServiceRelease(String accessToken) {
        List<ServiceCategory> biboServiceCategoryList = new ArrayList<>();
        biboServiceCategoryList.add(new ServiceCategory(Constants.NUTRITION_LIST, R.drawable.photo_icon_things_chifushi, true));
        biboServiceCategoryList.add(new ServiceCategory(Constants.MUSIC_LIST, R.drawable.ic_tool_music, true));
        biboServiceCategoryList.add(new ServiceCategory(Constants.NAMED_LIST, R.drawable.diary_btn_latenight));
        biboServiceCategoryList.add(new ServiceCategory(Constants.HEIGHT_LIST, R.drawable.diary_btn_check));
        biboServiceCategoryList.add(new ServiceCategory(Constants.BMI_LIST, R.drawable.photo_icon_things_huodong));
        biboServiceCategoryList.add(new ServiceCategory(Constants.HANDBOOK_LIST, R.drawable.diary_btn_condom));

        return Observable.just(biboServiceCategoryList);
    }

    public Observable<List<UpComingServiceCategory>> getListHomeServiceUpComing(String accessToken) {
        List<UpComingServiceCategory> categoryServiceList = new ArrayList<>();
        categoryServiceList.add(new UpComingServiceCategory(Constants.BEAUTY_LIST, R.drawable.diary_btn_makeup));
        categoryServiceList.add(new UpComingServiceCategory(Constants.DOCTOR_LIST, R.drawable.tools_img_doctor));
//        categoryServiceList.add(new UpComingServiceCategory(Constants.NUTRITION_LIST, R.drawable.ic_biboService_thuc_pham_dinh_duong));
        categoryServiceList.add(new UpComingServiceCategory(Constants.COOKING_LIST, R.drawable.photo_icon_things_zijichifan));
        categoryServiceList.add(new UpComingServiceCategory(Constants.PREGNANT_LIST, R.drawable.diary_btn_sex));
        return Observable.just(categoryServiceList);
    }

    // Nutrition ---------------------------
    public Observable<NutritionCatAndRecommend> getListNutritionRecommendAndCategories(String accessToken, int skip, int limit) {
        return biboService.getListNutritionRecommendAndCategories(accessToken, skip, limit);
    }

    public Observable<List<Nutrition>> getListNutritionByCategoryId(String accessToken, String categoryId, int skip, int limit) {
        return biboService.getListNutritionByCategoryId(accessToken, categoryId, skip, limit);
    }

    public Observable<Nutrition> getNutritionDetailById(String accessToken, String nutritionId) {
        return biboService.getNutritionDetailById(accessToken, nutritionId);
    }

    //    Service -----------------------

    public Observable<List<Category>> getListSuggestTab(String accessToken, int start, int limit) {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("1", "Đề xuất"));
        categoryList.add(new Category("2", "Mũi"));
        categoryList.add(new Category("3", "Tóc"));
        categoryList.add(new Category("4", "Răng"));
        return Observable.just(categoryList);
    }

    public Observable<List<PostService>> getListPostServiceByCategoryId(String accessToken, String categoryId, int start, int limit) {
        List<PostService> postServiceList = new ArrayList<>();
        List<String> image1 = new ArrayList<>();
        List<String> image2 = new ArrayList<>();
        image1.add("http://file.vforum.vn/hinh/2018/02/top-nhung-hinh-anh-gai-dep-gai-xinh-nhat-hien-nay-17.png");
        image2.add("https://znews-photo.zadn.vn/w480/Uploaded/qfssu/2018_11_13/17152066509140841245905.jpg");
        image2.add("https://image2.baonghean.vn/w607/Uploaded/2019/pmzsogazsnzm/2018_03_11/204430-1.jpg");
        postServiceList.add(new PostService("1", "ảnh", "Da cứng sẽ trở nên mềm mại hơn với công nghệ 3 phẫu thuật 7 phục hồi, chức năng như một Bác sĩ phẫu thuật chỉnh hình", image1, false, 235));
        postServiceList.add(new PostService("2", "ba", "Hành trình từ đàn ông hóa tiên nữ của Hương Giang", image2, true, 12797));
        postServiceList.add(new PostService("4", "44", "Hành trình từ đàn ông hóa tiên nữ của Hương Giang", image2, true, 12797));
        postServiceList.add(new PostService("3", "13", "Da cứng sẽ trở nên mềm mại hơn với công nghệ 3 phẫu thuật 7 phục hồi, chức năng như một Bác sĩ phẫu thuật chỉnh hình", image1, false, 235));
        return Observable.just(postServiceList);
    }

    public Observable<List<ServiceCategory>> getCategoryServiceList(String accessToken, int start, int limit) {
        List<ServiceCategory> biboServiceCategoryList = new ArrayList<>();
        biboServiceCategoryList.add(new ServiceCategory("1", "Chống lão hóa", R.drawable.ic_home_service_chonglaohoa));
        biboServiceCategoryList.add(new ServiceCategory("2", "Chăm sóc da", R.drawable.ic_home_service_chamsocda));
        biboServiceCategoryList.add(new ServiceCategory("3", "Chăm sóc tóc", R.drawable.ic_home_service_chamsoctoc));
        biboServiceCategoryList.add(new ServiceCategory("4", "Chỉnh hình môi", R.drawable.ic_home_service_dinhinhmoi));
        biboServiceCategoryList.add(new ServiceCategory("5", "Dưỡng trắng da", R.drawable.ic_home_service_lamtrang));
        biboServiceCategoryList.add(new ServiceCategory("6", "Trang điểm", R.drawable.ic_home_service_trangdiem));
        biboServiceCategoryList.add(new ServiceCategory("7", "Sửa khuyết điểm", R.drawable.ic_home_service_chuakhuyetdiem));
        biboServiceCategoryList.add(new ServiceCategory("8", "Triệt lông", R.drawable.ic_home_service_trietlong));
        biboServiceCategoryList.add(new ServiceCategory("9", "Cằm V-Line", R.drawable.icon_service_mat_v_line));
        biboServiceCategoryList.add(new ServiceCategory("10", "Chỉnh hình", R.drawable.ic_home_service_chinhhinh));
        return Observable.just(biboServiceCategoryList);
    }

    public Observable<List<ServiceUtility>> getListUtilityService(String accessToken, int start, int limit) {
        List<ServiceUtility> biboServiceUtilityList = new ArrayList<>();
        biboServiceUtilityList.add(new ServiceUtility("1", "Bách khoa toàn thư", "Cẩm nang làm đẹp", R.drawable.ic_home_wiki));
        biboServiceUtilityList.add(new ServiceUtility("2", "Nhật ký", "Nhật ký làm đẹp", R.drawable.ic_home_diary));
        biboServiceUtilityList.add(new ServiceUtility("3", "Bác sĩ", "Chuyên gia tư vấn", R.drawable.ic_home_doctor));
        biboServiceUtilityList.add(new ServiceUtility("4", "Spa", "Địa điểm spa", R.drawable.ic_home_hospital));
        biboServiceUtilityList.add(new ServiceUtility("5", "Góc nhìn cuộc sống", "Live trực tiếp", R.drawable.ic_home_live));
        biboServiceUtilityList.add(new ServiceUtility("6", "Tư vấn", "Dịch vụ 1v1", R.drawable.ic_home_beautiful));

        return Observable.just(biboServiceUtilityList);
    }

    public Observable<List<BannerDetail>> getServiceBannerList(String accessToken, int start, int limit) {
        List<BannerDetail> biboServiceUtilityList = new ArrayList<>();
        biboServiceUtilityList.add(new BannerDetail("1", "https://3.bp.blogspot.com/-NbEOD2hxeNY/WuNfaJ7D-SI/AAAAAAAARzY/9B6tEcxHOPQa9DISBxtaXx0jOQhVG2BIgCLcBGAs/s1600/banner-design-banner-thiet-ke-banner-dien-may.jpg"));
        biboServiceUtilityList.add(new BannerDetail("2", "http://cdn-gd-v1.webbnc.net/useruploads/userfiles//513640/images/khuyen-mai-tranh-amia-ha-nam-nhan-dip-khai-truong(1).jpg"));
        biboServiceUtilityList.add(new BannerDetail("3", "https://www.khatoco.com/Portals/0/2016/KM.png"));
        return Observable.just(biboServiceUtilityList);
    }

    public Observable<List<BannerDetail>> getServiceBannerByServiceId(String accessToken, String biboServiceId, int start, int limit) {
        List<BannerDetail> biboServiceUtilityList = new ArrayList<>();
        biboServiceUtilityList.add(new BannerDetail("1", "https://3.bp.blogspot.com/-NbEOD2hxeNY/WuNfaJ7D-SI/AAAAAAAARzY/9B6tEcxHOPQa9DISBxtaXx0jOQhVG2BIgCLcBGAs/s1600/banner-design-banner-thiet-ke-banner-dien-may.jpg"));
        biboServiceUtilityList.add(new BannerDetail("2", "http://cdn-gd-v1.webbnc.net/useruploads/userfiles//513640/images/khuyen-mai-tranh-amia-ha-nam-nhan-dip-khai-truong(1).jpg"));
        biboServiceUtilityList.add(new BannerDetail("3", "https://www.khatoco.com/Portals/0/2016/KM.png"));
        return Observable.just(biboServiceUtilityList);
    }

    public Observable<List<FirstServiceCategory>> getFirstListServiceByServId(String accessToken, String biboServiceId, int start, int limit) {
        List<FirstServiceCategory> biboServiceUtilityList = new ArrayList<>();
        biboServiceUtilityList.add(new FirstServiceCategory("1", "Message bụng"));
        biboServiceUtilityList.add(new FirstServiceCategory("2", "Message chân"));
        biboServiceUtilityList.add(new FirstServiceCategory("3", "Message mặt"));
        biboServiceUtilityList.add(new FirstServiceCategory("1", "Message bụng"));
        biboServiceUtilityList.add(new FirstServiceCategory("2", "Message chân"));
        biboServiceUtilityList.add(new FirstServiceCategory("3", "Message mặt"));
        return Observable.just(biboServiceUtilityList);
    }

    public Observable<List<SecondServiceCategory>> getSecondListServiceByServId(String accessToken, String biboServiceId, int start, int limit) {
        List<SecondServiceCategory> biboServiceUtilityList = new ArrayList<>();
        biboServiceUtilityList.add(new SecondServiceCategory("1", "Message bụng"));
        biboServiceUtilityList.add(new SecondServiceCategory("2", "Message chân"));
        biboServiceUtilityList.add(new SecondServiceCategory("3", "Message mặt"));
        return Observable.just(biboServiceUtilityList);
    }

    public Observable<List<MainServiceCategory>> getMainListServiceByServId(String accessToken, String biboServiceId, int start, int limit) {
        List<MainServiceCategory> biboServiceUtilityList = new ArrayList<>();
        biboServiceUtilityList.add(new MainServiceCategory("1", "Nhật ký", R.drawable.icon_second_dairy));
        biboServiceUtilityList.add(new MainServiceCategory("2", "Bác sĩ", R.drawable.icon_second_doctor));
        biboServiceUtilityList.add(new MainServiceCategory("3", "Bệnh viện", R.drawable.icon_second_hospital));
        biboServiceUtilityList.add(new MainServiceCategory("4", "Câu hỏi", R.drawable.icon_second_question));
        biboServiceUtilityList.add(new MainServiceCategory("5", "Chủ đề", R.drawable.icon_topic));
        return Observable.just(biboServiceUtilityList);
    }

    public Observable<List<PostServiceMostView>> getMostViewPostListByServId(String accessToken, String biboServiceId, int start, int limit) {
        List<PostServiceMostView> biboServiceUtilityList = new ArrayList<>();
        User user = new User("https://2sao.vietnamnetjsc.vn/images/2017/07/31/06/54/hot-girl-xu-nghe-5.jpg", "Mỹ nữ phương đông");
        List<String> images = new ArrayList<>();
        images.add("https://uphinhnhanh.com/images/2019/02/05/before_1.jpg");
        images.add("https://uphinhnhanh.com/images/2019/02/05/after_1.jpg");
        biboServiceUtilityList.add(new PostServiceMostView("1", "Làm đẹp tại nhà", "Giá sốc như giật điện", images, 23432, user));
        biboServiceUtilityList.add(new PostServiceMostView("2", "Làm đẹp tại phố", "Giá sốc như giật điện", images, 21432, user));
        biboServiceUtilityList.add(new PostServiceMostView("3", "Làm đẹp tại spa", "Giá sốc như giật điện", images, 2332, user));
        biboServiceUtilityList.add(new PostServiceMostView("4", "Làm đẹp tại vỉa hè", "Giá sốc như giật điện", images, 6432, user));
        return Observable.just(biboServiceUtilityList);
    }

    public Observable<List<ServiceDetails>> getServiceDetailByCategoryId(String accessToken, String categoryId, int start, int limit) {
        List<ServiceDetails> serviceList = new ArrayList<>();
        serviceList.add(new ServiceDetails("1"));
        serviceList.add(new ServiceDetails("2"));
        serviceList.add(new ServiceDetails("3"));
        serviceList.add(new ServiceDetails("4"));
        serviceList.add(new ServiceDetails("5"));
        return Observable.just(serviceList);
    }

    public Observable<ServiceDetails> getServiceDetailById(String accessToken, String biboServiceId) {
        ServiceDetails biboServiceDetails = new ServiceDetails("1");
        return Observable.just(biboServiceDetails);
    }

//    Filter --------

    public Observable<List<FilterTypeDetail>> getMultiTypeFilterList(String accessToken, String targetId) {
        List<FilterTypeDetail> filterTypeDetailList = new ArrayList<>();
        filterTypeDetailList.add(new FilterTypeDetail("1", "Bệnh viên tư", "machine"));
        filterTypeDetailList.add(new FilterTypeDetail("2", "Bệnh viên công", "machine"));
        filterTypeDetailList.add(new FilterTypeDetail("3", "Thương hiệu lớn", "machine"));
        filterTypeDetailList.add(new FilterTypeDetail("4", "Cấu trúc máy làm đẹp", "machine"));
        filterTypeDetailList.add(new FilterTypeDetail("5", "Máy nha khoa", "machine"));
        filterTypeDetailList.add(new FilterTypeDetail("1", "Thẻ đen độc quyền", "benefit"));
        filterTypeDetailList.add(new FilterTypeDetail("2", "Giới hạn thời gian", "benefit"));
        filterTypeDetailList.add(new FilterTypeDetail("3", "Nhóm", "benefit"));
        filterTypeDetailList.add(new FilterTypeDetail("4", "gói hội viên vàng", "benefit"));
        filterTypeDetailList.add(new FilterTypeDetail("5", "Phúc lợi đỏ", "benefit"));
        filterTypeDetailList.add(new FilterTypeDetail("1", "Hỗ trợ bảo hiểm", "support"));
        filterTypeDetailList.add(new FilterTypeDetail("2", "Thời hạn hỗ trợ", "support"));
        filterTypeDetailList.add(new FilterTypeDetail("2", "Có trường hợp", "reality"));
        filterTypeDetailList.add(new FilterTypeDetail("2", "Apple", "trademark"));
        filterTypeDetailList.add(new FilterTypeDetail("2", "Samsung", "trademark"));
        filterTypeDetailList.add(new FilterTypeDetail("2", "Oppo", "trademark"));
        filterTypeDetailList.add(new FilterTypeDetail("2", "Hàng đẹp", "comment"));
        filterTypeDetailList.add(new FilterTypeDetail("2", "Uy tín", "comment"));
        filterTypeDetailList.add(new FilterTypeDetail("2", "Nhiều người dùng", "comment"));
        filterTypeDetailList.add(new FilterTypeDetail("2", "Chất lượng tuyệt vời", "comment"));
        filterTypeDetailList.add(new FilterTypeDetail("2", "0.5ml", "rule"));
        filterTypeDetailList.add(new FilterTypeDetail("2", "0.75ml", "rule"));
        filterTypeDetailList.add(new FilterTypeDetail("2", "0.8ml", "rule"));
        filterTypeDetailList.add(new FilterTypeDetail("2", "1ml", "rule"));
        filterTypeDetailList.add(new FilterTypeDetail("2", "1.25ml", "rule"));
        filterTypeDetailList.add(new FilterTypeDetail("2", "1.5ml", "rule"));
        filterTypeDetailList.add(new FilterTypeDetail("2", "Hà Nội", "location"));
        filterTypeDetailList.add(new FilterTypeDetail("2", "Hồ Chí Minh", "location"));
        filterTypeDetailList.add(new FilterTypeDetail("2", "Đà Nẵng", "location"));
        return Observable.just(filterTypeDetailList);
    }

    public Observable<List<FilterTypeDetail>> getProductFilterList(String accessToken, String targetId) {
        List<FilterTypeDetail> filterTypeDetailList = new ArrayList<>();
        filterTypeDetailList.add(new FilterTypeDetail("2", "Doanh thu cao nhất", "product"));
        filterTypeDetailList.add(new FilterTypeDetail("2", "Lượt đặt cao nhất", "product"));
        filterTypeDetailList.add(new FilterTypeDetail("2", "Đánh giá cao nhất", "product"));
        filterTypeDetailList.add(new FilterTypeDetail("2", "Gần tôi nhất", "product"));
        filterTypeDetailList.add(new FilterTypeDetail("2", "Mới nhất", "product"));
        filterTypeDetailList.add(new FilterTypeDetail("2", "Giá cao nhất", "product"));
        filterTypeDetailList.add(new FilterTypeDetail("2", "Giá thấp nhất", "product"));

        return Observable.just(filterTypeDetailList);
    }

    public Observable<List<FilterTypeDetail>> getCityFilterList(String accessToken, String targetId) {
        List<FilterTypeDetail> filterTypeDetailList = new ArrayList<>();
        List<FilterTypeSecond> filterTypeSecondList = new ArrayList<>();
        List<FilterTypeSecond> filterTypeSecondList2 = new ArrayList<>();
        List<FilterTypeSecond> filterTypeSecondList3 = new ArrayList<>();
        List<FilterTypeSecond> filterTypeSecondList4 = new ArrayList<>();
        filterTypeSecondList.add(new FilterTypeSecond("1", "Hoàng Mai", "district"));
        filterTypeSecondList.add(new FilterTypeSecond("1", "Cầu Giấy", "district"));
        filterTypeSecondList.add(new FilterTypeSecond("1", "Hoàn Kiếm", "district"));

        filterTypeSecondList2.add(new FilterTypeSecond("1", "Quận 1", "district"));
        filterTypeSecondList2.add(new FilterTypeSecond("1", "Quận 2", "district"));
        filterTypeSecondList2.add(new FilterTypeSecond("1", "Quận 3", "district"));

        filterTypeSecondList3.add(new FilterTypeSecond("1", "Hoàng Mai 2", "district"));
        filterTypeSecondList3.add(new FilterTypeSecond("1", "Cầu Giấy 2", "district"));
        filterTypeSecondList3.add(new FilterTypeSecond("1", "Hoàn Kiếm 2", "district"));


        filterTypeSecondList4.add(new FilterTypeSecond("1", "Quận 4", "district"));
        filterTypeSecondList4.add(new FilterTypeSecond("1", "Quận 5", "district"));
        filterTypeSecondList4.add(new FilterTypeSecond("1", "Quận 6", "district"));

//        filterTypeDetailList.add(new FilterTypeDetail("1", "Đã lưu", "city", null));
//        filterTypeDetailList.add(new FilterTypeDetail("1", "Tất cả", "city", null));
        filterTypeDetailList.add(new FilterTypeDetail("1", "Hà Nội", "city", filterTypeSecondList));
        filterTypeDetailList.add(new FilterTypeDetail("2", "Hồ Chí Minh", "city", filterTypeSecondList2));
        filterTypeDetailList.add(new FilterTypeDetail("3", "Đà Nẵng", "city", filterTypeSecondList3));
        filterTypeDetailList.add(new FilterTypeDetail("4", "Huế", "city", filterTypeSecondList4));
        return Observable.just(filterTypeDetailList);
    }

    public Observable<List<FilterTypeDetail>> getPlaceFilterList(String accessToken, String targetId) {
        List<FilterTypeDetail> filterTypeDetailList = new ArrayList<>();
        filterTypeDetailList.add(new FilterTypeDetail("2", "Bách Khoa", "place"));
        filterTypeDetailList.add(new FilterTypeDetail("2", "KTQD", "place"));
        filterTypeDetailList.add(new FilterTypeDetail("2", "Quán ăn", "place"));
        filterTypeDetailList.add(new FilterTypeDetail("2", "Hàng nước", "place"));


        return Observable.just(filterTypeDetailList);
    }

//    Doctor ----------

    public Observable<Doctor> getDoctorInfo(String accessToken, String doctorId) {
        Doctor doctor = new Doctor();

        return Observable.just(doctor);
    }
//    NotificationBean -------------

    public Observable<List<NotificationBean>> getNotificationList(String accessToken, int skip, int limit) {
        List<NotificationBean> notificationBeanList = new ArrayList<>();
        notificationBeanList.add(new NotificationBean("1"));
        notificationBeanList.add(new NotificationBean("1"));
        notificationBeanList.add(new NotificationBean("1"));
        notificationBeanList.add(new NotificationBean("1"));
        notificationBeanList.add(new NotificationBean("1"));
        notificationBeanList.add(new NotificationBean("1"));
        notificationBeanList.add(new NotificationBean("1"));
        return Observable.just(notificationBeanList);
    }

    public Observable<NotificationUnSeenNumber> getNotificationUnseenNumber(String accessToken) {
        return biboService.getNotificationUnseenNumber(accessToken);
    }

    public Observable<NotificationSeen> seenNotificationId(String accessToken, String _id) {
        return biboService.seenNotificationId(accessToken, _id);
    }

    public Observable<NotificationSeen> seenNotificationAll(String accessToken) {
        return biboService.seenNotificationAll(accessToken);
    }

    public Observable<Message> removeNotificationId(String accessToken, String _id) {
        return biboService.removeNotificationId(accessToken, _id);
    }

    public Observable<Message> removeNotificationAll(String accessToken) {
        return biboService.removeNotificationAll(accessToken);
    }

    public Observable<Splash> getSplashData(String accessToken) {
        Splash splash = new Splash();
        return Observable.just(splash);
    }

    public Observable<List<Doctor>> getDoctorListByServId(String accessToken, String serviceId, int start, int limit) {
        List<Doctor> doctorList = new ArrayList<>();
        doctorList.add(new Doctor());
        doctorList.add(new Doctor());
        doctorList.add(new Doctor());
        doctorList.add(new Doctor());
        return  Observable.just(doctorList);
    }

    public Observable<List<Product>> getHotFlipperProductList(String accessToken, int start, int limit) {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("1", "https://www.sapo.vn/blog/wp-content/uploads/2017/03/kinh-doanh-my-pham-1.png"
                , "Mỹ phẩm 1", 1000000, 970000.0, 5.2));
        productList.add(new Product("2", "https://www.dulichvietnam.com.vn/kinh-nghiem/wp-content/uploads/2018/12/di-han-quoc-nen-mua-my-pham-gi.jpg"
                , "Mỹ phẩm 2", 4000000, 2970000.0, 3.2));
        productList.add(new Product("3", "https://www.sapo.vn/blog/wp-content/uploads/2017/03/kinh-doanh-my-pham-1.png"
                , "Mỹ phẩm 3", 1000000, 970000.0,4.5));
        productList.add(new Product("4", "https://www.dulichvietnam.com.vn/kinh-nghiem/wp-content/uploads/2018/12/di-han-quoc-nen-mua-my-pham-gi.jpg"
                , "Mỹ phẩm 4", 1000000, 970000.0,1.3));
        return Observable.just(productList);
    }

    public Observable<List<Product>> getFlashSaleByHourList(String accessToken, long startTime) {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());
        productList.add(new Product());
        productList.add(new Product());
        productList.add(new Product());
        productList.add(new Product());
        return Observable.just(productList);
    }

    public Observable<List<HospitalDetail>> getHospitalDetailByCategoryId(String accessToken, String categoryId, int start, int limit) {
        List<HospitalDetail> hospitalList = new ArrayList<>();
        hospitalList.add(new HospitalDetail());
        hospitalList.add(new HospitalDetail());
        hospitalList.add(new HospitalDetail());
        hospitalList.add(new HospitalDetail());
        hospitalList.add(new HospitalDetail());
        hospitalList.add(new HospitalDetail());
        return Observable.just(hospitalList);
    }

//    Cart ---------------
    public Observable<List<Cart>> getListCartService(String token) {
        List<Cart> cartList = new ArrayList<>();
        cartList.add(new Cart());
        cartList.add(new Cart());
        cartList.add(new Cart());
        return Observable.just(cartList);
    }
}
