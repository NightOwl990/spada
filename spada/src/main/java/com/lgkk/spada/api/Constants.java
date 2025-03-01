package com.lgkk.spada.api;

import android.graphics.Color;


/**
 * Created by Hoang Nam on 19/03/2017.
 */

public interface Constants {
    String BASE_API_URL = "http://devts.sieuloinhuan.net";
    String BASE_RETROFIT_API_URL = BASE_API_URL + "/api/";

//    String BASE_API_URL = "http://192.168.1.144:3303";
//    String BASE_RETROFIT_API_URL = BASE_API_URL + "/api/";

    String STORAGE_URL = "https://storage.sieuloinhuan.net/api/";
    String GETDATA_URL = "http://circle.seeyouyima.com/v2/";

    String COMMON_UA_STR = "HomeCandy Android Client";
    String emailFeedback = "admin@bsagroup.vn";

    int REQUEST_WRITE_SETTINGS = 0;
    int REQUEST_ALBUM = 1;
    int REQUEST_CORP = 2;

    int[] tagColors = new int[]{
            Color.parseColor("#90C5F0"),
            Color.parseColor("#91CED5"),
            Color.parseColor("#F88F55"),
            Color.parseColor("#C0AFD0"),
            Color.parseColor("#E78F8F"),
            Color.parseColor("#67CCB7"),
            Color.parseColor("#F6BC7E")
    };

    String CommunityGroupId = "5c66ea74010942769e69ecb4";
    String HomeGiftId = "5c7674886ae75a5e3ed4a626";
    String HomeGiftTitle = "Khuyến mãi cho bạn";

    int MAX_userid_length_visible = 5;

    String NUTRITION_LIST = "Thực phẩm dinh dưỡng";
    String NAMED_LIST = "Đặt tên cho bé";
    String HEIGHT_LIST = "Dự đoán chiều cao";
    String BMI_LIST = "Chỉ số BMI";
    String HANDBOOK_LIST = "Cẩm nang";
    String MUSIC_LIST = "Nghe nhạc";
    String BEAUTY_LIST = "Chăm sóc sắc đẹp";
    String DOCTOR_LIST = "Chuyên gia tư vấn";
    String PREGNANT_LIST = "Đang mang thai";
    String COOKING_LIST = "Món ăn ngon";

    String MUSIC_HOME = "Nghe nhạc";
    String NUTRITION_HOME = "Dinh dưỡng";
    String NAMED_HOME = "Bói tên";
    String HEIGHT_HOME = "Chiều cao";
    String BMI_HOME = "Chỉ số BMI";
    String HANDBOOK_HOME = "Cẩm nang";
    String MORE_HOME = "Xem thêm";

    String url_post = "question";
    String url_main = "/home/main";
    String url_product_detail = "/product/detail";
    String url_category = "/product/category";
    String url_order_list = "/product/orderList";
    String url_handbook = "/home/handBook";
    String url_music = "/home/music";
    String url_nutrition = "/home/nutrition";
    String url_due_date = "/home/dueDate";
    String url_name_egypt = "/home/nameEgypt";
    String url_name_baby = "/home/nameBaby";
    String url_bmi = "/home/bmi";
    String url_multi_choose = "/home/multiChoose";
    String url_height = "/home/height";
    String url_news = "/home/news";
    String url_notification = "/home/notification";
    String url_gift = "/home/gift";
    String url_group_detail = "/community/groupDetail";
    String url_post_detail = "/community/postDetail";


    int MAX_BABY_GROW = 294;
    int MAX_QUANTITY_PRODUCT = 20;
    int VISIBLE_FLOAT_BUTTON_POSITION = 4;
    int START_POSITION_OLD_NOTIFICATION = 5;
    int MAX_NUTRITION_CATEGORY_ITEM = 12;
    int MAX_ITEM_ORDER_LIST = 30;
    int MAX_ITEM_LOAD_BANNER = 4;
    int MAX_ITEM_LOAD_FIRST_TIME = 15;
    int MAX_ITEM_LOAD_REPLY_FIRST_TIME = 3;
    int MAX_ITEM_LOAD_REPLY_SECOND_TIME = 10;
    int MAX_ITEM_LOAD_RATING_FIRST_TIME = 30;
    int MAX_ITEM_LOAD_COMMENT = 10;
    long DAY_MILISECOND = 86400000;
    String ORDER_PENDING = "pending";
    String ORDER_PENDING_TRANSLATE = "Chờ xác nhận";

    String ORDER_ALL = "all";
    String ORDER_ALL_TRANS = "Tất cả";
    String ORDER_APPROVED = "approved";
    String ORDER_APPROVED_TRANS = "Chờ vận chuyển";
    String ORDER_DELIVERING = "delivering";
    String ORDER_DELIVERING_TRANS = "Chờ giao hàng";
    String ORDER_CANCEL = "cancel";
    String ORDER_CANCEL_TRANS = "Đã hủy";
    String ORDER_FAIL = "fail";
    String ORDER_FAIL_TRANS = "Đơn hàng lỗi";
    String ORDER_SUCCESS = "showPost";
    String ORDER_SUCCESS_TRANS = "Đã giao";

    String ORDER_ = "pending";
    int mParallaxEndFadeHeight = 250;
    int mParallaxStartFadeHeight = 150;

    boolean DEBUG_MODE = true;
    //Picture Selector
    int MAX_SELECT_IMAGE_VIDEO = 5;
    int MIN_SELECT_IMAGE_VIDEO = 1;


    boolean PREVIEW_IMAGE = false;
    boolean IS_CAMERA = true;
    boolean ENABLE_CROP = false;
    int GLIDE_OVERRIDE_WIDTH = 160;
    int GLIDE_OVERRIDE_HEIGH = 160;
    //File
    public static final String SUFFIX_TXT = ".txt";
    public static final String SUFFIX_PDF = ".pdf";
    public static final String SUFFIX_EPUB = ".epub";
    public static final String SUFFIX_ZIP = ".zip";
    public static final String SUFFIX_CHM = ".chm";

    public static final int TEXT_TYPE = 0;
    public static final int BACKGROUND_TYPE = 1;
    public static final int IMAGE_TYPE = 2;
    public static final int VIDEO_TYPE = 3;
    public static final int LINK_TYPE = 4;
    public static final int SPONSOR_TYPE = 5;

    public interface ACTION {
        public static String MAIN_ACTION = "com.marothiatechs.customnotification.action.main";
        public static String INIT_ACTION = "com.marothiatechs.customnotification.action.init";
        public static String PREV_ACTION = "com.marothiatechs.customnotification.action.prev";
        public static String PLAY_ACTION = "com.marothiatechs.customnotification.action.play";
        public static String NEXT_ACTION = "com.marothiatechs.customnotification.action.next";
        public static String STARTFOREGROUND_ACTION = "com.marothiatechs.customnotification.action.startforeground";
        public static String STOPFOREGROUND_ACTION = "com.marothiatechs.customnotification.action.stopforeground";
    }

    public interface NOTIFICATION_ID {
        public static int FOREGROUND_SERVICE = 101;
    }

    public static final String NAVIGATE_LIBRARY = "navigate_library";
    public static final String NAVIGATE_QUEUE = "navigate_queue";
    public static final String NAVIGATE_ALBUM = "navigate_album";
    public static final String NAVIGATE_ARTIST = "navigate_artist";

    public static final String NAVIGATE_PLAYLIST_RECENTPLAY = "navigate_playlist_recentplay";
    public static final String NAVIGATE_PLAYLIST_RECENTADD = "navigate_playlist_recentadd";
    public static final String NAVIGATE_PLAYLIST_TOPPLAYED = "navigate_playlist_topplayed";
    public static final String NAVIGATE_ALLSONG = "navigate_all_song";
    public static final String NAVIGATE_PLAYLIST_FAVOURATE = "navigate_playlist_favourate";

    public static final String ALBUM_ID = "album_id";
    public static final String ALBUM_NAME = "album_name";
    public static final String ARTIST_ID = "artist_id";
    public static final String ARTIST_NAME = "artist_name";
    public static final String PLAYLIST_ID = "playlist_id";
    public static final String PLAYLIST_NAME = "playlist_name";
    public static final String PLAYLIST_TYPE = "playlist_type";
    public static final String FIRST_ALBUM_ID = "first_album_id";
    public static final String FOLDER_PATH = "folder_path";

    public static final int PLAYLIST_VIEW_LIST = 1;
    public static final int PLAYLIST_VIEW_GRID = 2;

    public static final int HTTP_CACHE_SIZE = 20 * 1024 * 1024;
    public static final int HTTP_CONNECT_TIMEOUT = 15 * 1000;
    public static final int HTTP_READ_TIMEOUT = 20 * 1000;


}
