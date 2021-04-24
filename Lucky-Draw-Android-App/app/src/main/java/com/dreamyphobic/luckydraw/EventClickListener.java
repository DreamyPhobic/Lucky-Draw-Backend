package com.dreamyphobic.luckydraw;

/*
  Created by Harsh Gupta on 24/04/2021.
 */

import android.view.View;

import com.dreamyphobic.luckydraw.models.EventModel;

public interface EventClickListener {
    void itemClicked(View view, EventModel position);
}