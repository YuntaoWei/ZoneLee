package com.eysale.zonelee.app;

import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestFutureTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.eysale.zonelee.R;
import com.eysale.zonelee.app.widget.ViewHookUtils;
import com.eysale.zonelee.presenter.activityview.PublishActivityDelegate;
import com.eysale.zonelee.util.BasicUtil;
import com.eysale.zonelee.util.TagUtil;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.kymjs.frame.presenter.ActivityPresenter;

public class PublishActivity extends ActivityPresenter<PublishActivityDelegate> implements View.OnClickListener {

    private Uri tmpUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBaseTag();
    }

    private void initBaseTag() {
        int baseTag = getIntent().getIntExtra(TagUtil.PUBLISH_TAG, -1);
        if (baseTag == -1) {
            Snackbar.make(viewDelegate.getToolbar(), "发布功能异常：tag -1", Snackbar.LENGTH_LONG).show();
            finish();
            return;
        }

        viewDelegate.setBaseTag(TagUtil.getBaseTagString(baseTag));
    }

    @Override
    protected void bindEvenListener() {
        viewDelegate.setOnClickListener(this, R.id.btn_get_photo, R.id.btn_take_photo);
    }

    @Override
    protected Class getDelegateClass() {
        return PublishActivityDelegate.class;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                dialogBuilder.dismiss();
                break;

            case R.id.button2:
                if (etTag != null) {
                    String tags = etTag.getText().toString();
                    if (!TextUtils.isEmpty(tags)) {
                        String[] tag = tags.split("，");
                        if (tag == null) {
                            viewDelegate.addTag(tags);
                        } else {
                            for (String t : tag
                                    ) {
                                viewDelegate.addTag(t);
                            }
                        }
                    }
                    dialogBuilder.dismiss();
                }
                break;

            case R.id.btn_get_photo:
                Intent getIntent = new Intent(Intent.ACTION_PICK);
                getIntent.setType("image/*");
                startActivityForResult(getIntent, 0x123);
                break;

            case R.id.btn_take_photo:
                Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                tmpUri = BasicUtil.getTmpPictureUri(this);
                if (tmpUri != null) {
                    takeIntent.putExtra(MediaStore.EXTRA_OUTPUT, tmpUri);
                }
                takeIntent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
                startActivityForResult(takeIntent, 0x124);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 0x123) {
            tmpUri = data.getData();
            if (null != tmpUri)
                viewDelegate.insertBitmap(tmpUri, ContentUris.parseId(tmpUri));
        } else if (requestCode == 0x124) {
            if (null != tmpUri)
                viewDelegate.insertBitmap(tmpUri, System.currentTimeMillis() / 1000);
            Log.i("ttt", "got picture from camera : " + tmpUri);
        }
    }

    private NiftyDialogBuilder dialogBuilder;
    private EditText etTag;

    public void showAddTagDialog() {
        if (dialogBuilder == null) {
            dialogBuilder = NiftyDialogBuilder.getInstance(this);
            dialogBuilder.withMessage(null)
                    .withTitle("添加标签")
                    .withTitleColor(Color.rgb(0x3A, 0x3A, 0x3A))
                    .withDialogColor("#FFFFFAFA");
        }

        ViewHookUtils.hookDialogTitleColor(dialogBuilder, Color.argb(0xFF, 0xFF, 0xFA, 0xFA));

        if (etTag == null) {
            etTag = new EditText(this);
            etTag.setHint("输入标签，多个用逗号隔开");
            etTag.setMaxLines(1);
        }

        dialogBuilder.setCustomView(etTag, this)
                .withButton1Text("取消")
                .setButton1Click(this)
                .setButton2Click(this)
                .withButton2Text("确定");
        dialogBuilder.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) etTag.getLayoutParams();
                lp.leftMargin = 30;
                lp.rightMargin = 30;
                etTag.setLayoutParams(lp);
            }
        });
        dialogBuilder.show();
    }

    @Override
    protected void onDestroy() {
        viewDelegate.onActivityDestory();
        super.onDestroy();
    }
}
