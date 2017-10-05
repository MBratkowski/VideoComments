package io.thecapitals.videocomments.feature.main.view;

import android.content.Context;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

/**
 * Created for project VideoComments on 05/10/2017.
 */

public class Test {
    public void thing(Context context, String dummy) {

        SimpleExoPlayerView view = new SimpleExoPlayerView(context);
        SimpleExoPlayer exoPlayer = ExoPlayerFactory.newSimpleInstance(context, null);
        view.setPlayer(exoPlayer);
    }
}
