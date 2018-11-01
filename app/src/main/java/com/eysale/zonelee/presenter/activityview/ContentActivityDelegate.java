package com.eysale.zonelee.presenter.activityview;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.eysale.zonelee.R;
import com.eysale.zonelee.app.Adapter.ContentViewPageAdapter;
import com.eysale.zonelee.app.fragments.PageUtil;
import com.eysale.zonelee.app.ui.ContentActivityToolbar;
import com.eysale.zonelee.util.LogPrinter;
import com.kymjs.frame.view.AppDelegate;

public class ContentActivityDelegate extends AppDelegate implements ViewPager.OnPageChangeListener {

    private ViewPager contentViewPager;
    private ContentViewPageAdapter pagerAdapter;
    private Resources mRes;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_content;
    }

    @Override
    public Toolbar getToolbar() {
        return get(R.id.toolbar);
    }

    @Override
    public void initWidget() {
        contentViewPager = get(R.id.contentPager);
        pagerAdapter = new ContentViewPageAdapter(((AppCompatActivity)getActivity()).getSupportFragmentManager(), PageUtil.getAllPages());
        contentViewPager.setAdapter(pagerAdapter);
        contentViewPager.setCurrentItem(PageUtil.INDEX_FOUND);
        mRes = getActivity().getResources();
        addOnPageChangeListener(this);
    }

    public void addOnPageChangeListener(ViewPager.OnPageChangeListener vpcl) {
        contentViewPager.addOnPageChangeListener(vpcl);
    }

    public void changePageIndex(int index) {
        contentViewPager.setCurrentItem(index);
    }

    public ContentActivityToolbar getContentActivityToolbar() {
        return ContentActivityToolbar.getToolBar(getToolbar());
    }

    private void changeButtonSelectedStatus(int page) {
        resetButtonSelectedStatus();
        switch (page) {
            case PageUtil.INDEX_FOUND:
                setButtonSelectedStatus((Button) get(R.id.btn_content_found), R.mipmap.ic_found_select, true);
                break;

            case PageUtil.INDEX_RANKING:
                setButtonSelectedStatus((Button) get(R.id.btn_content_ranking), R.mipmap.ic_ranking_selected, true);
                break;

            case PageUtil.INDEX_FOLLOW:
                setButtonSelectedStatus((Button) get(R.id.btn_content_follow), R.mipmap.ic_follow_select, true);
                break;

            case PageUtil.INDEX_FRESH:
                setButtonSelectedStatus((Button) get(R.id.btn_content_fresh), R.mipmap.ic_fresh_selected, true);
                break;

            case PageUtil.INDEX_USER:
                setButtonSelectedStatus((Button) get(R.id.btn_content_user), R.mipmap.ic_user_selected, true);
                break;
        }
    }

    private void resetButtonSelectedStatus() {
        setButtonSelectedStatus((Button) get(R.id.btn_content_found), R.mipmap.ic_found_deselect, false);
        setButtonSelectedStatus((Button) get(R.id.btn_content_ranking), R.mipmap.ic_ranking_deselected, false);
        setButtonSelectedStatus((Button) get(R.id.btn_content_follow), R.mipmap.ic_follow_deselected, false);
        setButtonSelectedStatus((Button) get(R.id.btn_content_fresh), R.mipmap.ic_fresh_deselected, false);
        setButtonSelectedStatus((Button) get(R.id.btn_content_user), R.mipmap.ic_user_deselected, false);
    }

    private void setButtonSelectedStatus(Button btn, int res, boolean selected) {
        Drawable d = mRes.getDrawable(res);
        d.setBounds(0, 0, d.getMinimumWidth(), d.getMinimumHeight());
        btn.setCompoundDrawables(null, d, null, null);
        if(selected)
            btn.setTextColor(mRes.getColor(R.color.button_selected));
        else
            btn.setTextColor(mRes.getColor(R.color.button_deselected));
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        LogPrinter.i("wyt", "onPageSelected : " + i);
        changeButtonSelectedStatus(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
