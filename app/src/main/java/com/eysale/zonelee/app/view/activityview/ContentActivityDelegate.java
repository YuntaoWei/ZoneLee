package com.eysale.zonelee.app.view.activityview;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.eysale.zonelee.R;
import com.eysale.zonelee.app.Adapter.ContentViewPageAdapter;
import com.eysale.zonelee.app.fragments.PageUtil;
import com.eysale.zonelee.app.ui.ContentActivityToolbar;
import com.kymjs.frame.view.AppDelegate;

public class ContentActivityDelegate extends AppDelegate {

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

    public void changeButtonSelectedStatus(int id) {
        resetButtonSelectedStatus();
        switch (id) {
            case R.id.btn_content_found:
                setButtonSelectedStatus((Button) get(R.id.btn_content_found), R.mipmap.ic_found_select, true);
                break;

            case R.id.btn_content_follow:
                setButtonSelectedStatus((Button) get(R.id.btn_content_ranking), R.mipmap.ic_ranking_selected, true);
                break;

            case R.id.btn_content_ranking:
                setButtonSelectedStatus((Button) get(R.id.btn_content_follow), R.mipmap.ic_follow_select, true);
                break;

            case R.id.btn_content_fresh:
                setButtonSelectedStatus((Button) get(R.id.btn_content_fresh), R.mipmap.ic_fresh_selected, true);
                break;

            case R.id.btn_content_user:
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

}
