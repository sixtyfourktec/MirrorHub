/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Christian Pötzsch
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package de.sixtyfourktec.mirrorhub.views;

import de.sixtyfourktec.mirrorhub.R;
import de.sixtyfourktec.mirrorhub.data.TimeToWork;
import de.sixtyfourktec.mirrorhub.modules.ModuleCallback;
import de.sixtyfourktec.mirrorhub.modules.TimeToWorkModule;
import de.sixtyfourktec.mirrorhub.views.AnimationSwitcher;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TimeToWorkView extends AnimationSwitcher<TimeToWorkModule> {

    private class TimeToWorkViewItem extends RelativeLayout {
        public TimeToWorkViewItem(Context context, TimeToWork ttw) {
            super(context);
            setPadding(5, 0, 5, 0);

            inflate(context, R.layout.timetoworkviewitem, this);

            ImageView icon = (ImageView)findViewById(R.id.icon);
            icon.setImageLevel(1);
            TextView time = (TextView)findViewById(R.id.time);
            time.setText(ttw.duration);
        }
    }

    public class mcb extends ModuleCallback<TimeToWork> {
        public void onData(TimeToWork res) {
            TimeToWorkViewItem view = new TimeToWorkViewItem(getContext(), res);
            TimeToWorkView.this.exchangeView(view);
        }
        public void onNoData() {
            TimeToWorkView.this.resetView();
        }
    };

    public TimeToWorkView(Context context) {
        super(context);
        init();
    }

    public TimeToWorkView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        module = TimeToWorkModule.getInstance();
        module.addCallback(new mcb());
    }
}

/* ex: set tabstop=4 shiftwidth=4 expandtab: */
