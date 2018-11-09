package com.eysale.zonelee.presenter.fragmentview;

import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.eysale.zonelee.R;
import com.kymjs.frame.view.AppDelegate;

import java.util.ArrayList;
import java.util.List;

import co.lujun.androidtagview.TagContainerLayout;

public class UserFragmentDelegate extends AppDelegate {

    TagContainerLayout mTagContainerLayout;
    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    public void initWidget() {
        mTagContainerLayout = get(R.id.tag_view);
        List<String> tag = new ArrayList<>();
        tag.add("Music");
        tag.add("Movie");
        tag.add("Other");
        mTagContainerLayout.setTags(tag);
    }

    public void onAddTagButtonClick() {
        String tag = ((EditText)get(R.id.et_tag)).getText().toString();
        if(!TextUtils.isEmpty(tag)) {
            List<String> tags = mTagContainerLayout.getTags();
            if(!tags.contains(tag)) {
                mTagContainerLayout.addTag(tag);
                ((EditText)get(R.id.et_tag)).setText("");
            } else {
                Toast.makeText(getActivity().getApplication(), "标签已存在", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
