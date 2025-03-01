package com.demo.pipobooking.pipobooking;

import com.lgkk.spada.model.community.post.PostDetails;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoTest {

    @Test
    public void testVideoViewActivity() {
        PostDetails postDetails = mock(PostDetails.class);

        when(postDetails.getUser().getName()).thenReturn("Long Nguyễn");

        when(postDetails.getVideo().getUrl()).thenReturn("https://storage.sieuloinhuan.net/assets/uploads/1542875920929-y2mate.com---15_second_countdown_timer_with_buzzer_xqevieqtpsm_360p.mp4");

    }
}
