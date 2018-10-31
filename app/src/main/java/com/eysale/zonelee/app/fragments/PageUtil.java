package com.eysale.zonelee.app.fragments;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.WeakHashMap;

public class PageUtil {

    public static final int INDEX_FOUND = 0;
    public static final int INDEX_RANKING = 1;
    public static final int INDEX_FOLLOW = 2;
    public static final int INDEX_FRESH = 3;
    public static final int INDEX_USER = 4;

    public static List<Fragment> getAllPages() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(createFoundFragment());
        fragments.add(createRankingFragment());
        fragments.add(createFollowFragment());
        fragments.add(createFreshFragment());
        fragments.add(createUserFragment());
        return fragments;
    }

    private static Fragment createFoundFragment() {
        return FoundFragment.newInstance("", "");
    }

    private static Fragment createRankingFragment() {
        return RankingFragment.newInstance("", "");
    }

    private static Fragment createFollowFragment() {
        return FollowFragment.newInstance("", "");
    }

    private static Fragment createFreshFragment() {
        return FreshFragment.newInstance("", "");
    }

    private static Fragment createUserFragment() {
        return UserFragment.newInstance("", "");
    }


}
