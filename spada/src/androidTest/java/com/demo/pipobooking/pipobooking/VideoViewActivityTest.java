//package com.demo.pipobooking.pipobooking;
//
//import android.content.Intent;
//
//import com.lgkk.spada.model.community.post.PostDetails;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import androidx.test.filters.LargeTest;
//import androidx.test.rule.ActivityTestRule;
//import androidx.test.runner.AndroidJUnit4;
//
//@RunWith(AndroidJUnit4.class)
//@LargeTest
//public class VideoViewActivityTest {
//
//    @Rule
//    public ActivityTestRule<VideoViewActivity> activityRule
//            = new ActivityTestRule(VideoViewActivity.class, true, false);   // launchActivity. False to customize the intent
//
//
//    @Test
//    public void intent() {
//        Intent intent = new Intent();
//        PostDetails postDetails = new PostDetails();
//        postDetails.getUser().setName("Long Nguyễn");
//        postDetails.getUser().setAvatar("http://2sao.vietnamnetjsc.vn/images/2017/09/03/06/48/hot-girl-3.jpg");
//        postDetails.getVideo().setUrl("https://storage.sieuloinhuan.net/assets/uploads/1542875920929-y2mate.com---15_second_countdown_timer_with_buzzer_xqevieqtpsm_360p.mp4");
//        intent.putExtra("User", postDetails);
//
//        activityRule.launchActivity(intent);
//
//
//        // Continue with your test
//    }
//}
