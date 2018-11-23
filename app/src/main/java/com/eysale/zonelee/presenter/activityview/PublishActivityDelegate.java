package com.eysale.zonelee.presenter.activityview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.eysale.zonelee.R;
import com.eysale.zonelee.app.PublishActivity;
import com.eysale.zonelee.app.ui.BaseActivityToolbar;
import com.eysale.zonelee.app.widget.MyEditText;
import com.eysale.zonelee.util.BasicUtil;
import com.eysale.zonelee.util.LogPrinter;
import com.kymjs.frame.view.AppDelegate;

import java.util.List;

import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;

public class PublishActivityDelegate extends AppDelegate implements BaseActivityToolbar.OnToolbarButtonClickListener {

    private Point screenSize;
    TagContainerLayout tagView;
    MyEditText etContent;
    BaseActivityToolbar baseToolBar;
    RequestManager requestManager;
    int screenWidth;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_publish;
    }

    @Override
    public Toolbar getToolbar() {
        return get(R.id.toolbar);
    }

    @Override
    public void initWidget() {
        tagView = get(R.id.tag_view);
        etContent = get(R.id.et_content);

        baseToolBar = BaseActivityToolbar.getToolBar(getToolbar());
        baseToolBar.setOnButtonClickListener(this);
        baseToolBar.setDisplayOptions(true, true, true);
        setTitle("");
        tagView.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                if(position == 0) {
                    //add tag here.
                    getActivity().showAddTagDialog();
                    return;
                }

                if(position == 1)
                    return;
                tagView.removeTag(position);
            }

            @Override
            public void onTagLongClick(int position, String text) {}

            @Override
            public void onTagCrossClick(int position) {}
        });

        requestManager = Glide.with(getActivity());
        screenWidth = BasicUtil.getScreenWidth(getActivity());
    }

    public void setBaseTag(String baseTag) {
        tagView.addTag("点击添加标签", 0);
        tagView.addTag(baseTag, 1);
    }

    public void addTag(String tag) {
        if(!TextUtils.isEmpty(tag)) {
            List<String> tags = tagView.getTags();
            if(!tags.contains(tag)) {
                tagView.addTag(tag);
            } else {
                Snackbar.make(getToolbar(), "标签已存在", Snackbar.LENGTH_LONG).show();
            }
        }
    }

    public void insertBitmap(Uri u, final long imgName) {
        requestManager.asBitmap().load(u).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                if(resource.getWidth() > screenWidth) {
                    float scale = screenWidth * 1.0f / resource.getWidth() * 1.0f;
                    resource = BasicUtil.scaleBitmap(resource, scale);
                }
                etContent.insertDrawable(resource, "zo-img:" + imgName);
            }
        });
    }

    private void setTitle(String title) {
        baseToolBar.setTitle(title);
    }

    @Override
    public PublishActivity getActivity() {
        return super.getActivity();
    }

    public void onActivityDestory() {
        requestManager.onDestroy();
    }

    @Override
    public void onClick(View v, boolean backBar) {
        if(backBar) {
            getActivity().finish();
        } else {
            //publish content here.
            String s = etContent.getText().toString();
            LogPrinter.i("ttt", "etContent : " + s);
        }
    }
}
