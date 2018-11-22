package com.eysale.zonelee.app.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.eysale.zonelee.R;
import com.eysale.zonelee.app.PublishActivity;
import com.eysale.zonelee.presenter.fragmentview.UserFragmentDelegate;
import com.eysale.zonelee.util.TagUtil;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

public class UserFragment extends BaseFragment<UserFragmentDelegate> implements View.OnClickListener {

    public UserFragment() {
    }

    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private UserFragmentDelegate getDelegate() {
        return (UserFragmentDelegate) viewDelegate;
    }

    @Override
    protected void bindEvenListener() {
        getDelegate().setOnClickListener(this, R.id.btn_publish);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onFragmentVisibleChange(boolean visible) {

    }

    @Override
    protected Class getDelegateClass() {
        return UserFragmentDelegate.class;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {

        int publishTag = -1;
        switch (v.getId()) {

            case R.id.btn_publish:
                showPublishDialog();
                return;

            case R.id.btn_tag_movie:
                publishTag = TagUtil.BASE_TAG_MOVIE;
                break;

            case R.id.btn_tag_music:
                publishTag = TagUtil.BASE_TAG_MUSIC;
                break;

            case R.id.btn_tag_novel:
                publishTag = TagUtil.BASE_TAG_NOVEL;
                break;
        }
        Intent intent = new Intent(this.getContext(), PublishActivity.class);
        intent.putExtra(TagUtil.PUBLISH_TAG, publishTag);
        this.getContext().startActivity(intent);
        dialogBuilder.dismiss();
    }

    private View customView = null;
    private NiftyDialogBuilder dialogBuilder;
    private void showPublishDialog() {
        if(dialogBuilder == null) {
            dialogBuilder = NiftyDialogBuilder.getInstance(this.getContext());
            dialogBuilder.withMessage(null)
                    .withTitle(null)
                    .withDialogColor("#FFFFFAFA");
        }

        if(customView == null) {
            customView = LayoutInflater.from(this.getContext()).inflate(R.layout.publish_tag_layout, null);
            customView.findViewById(R.id.btn_tag_movie).setOnClickListener(this);
            customView.findViewById(R.id.btn_tag_music).setOnClickListener(this);
            customView.findViewById(R.id.btn_tag_novel).setOnClickListener(this);
            dialogBuilder.setCustomView(customView, this.getContext());
        }

        dialogBuilder.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        customView = null;
    }
}
