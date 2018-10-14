/*
 * Created by Evgeny V. Lobach on 13.10.18 22:21
 * Copyright (c) 2018 | www.itstudio.app | All rights reserved.
 * Last modified 13.10.18 22:21
 */

package app.itstudio.exercise02forcheckup;

import android.net.Uri;

public interface SearchSystem {
    public Uri getUri(String searchString);
    public String getName();
}
