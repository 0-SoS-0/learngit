package com.example.hp.oralpractice.ui.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by HP on 2017/10/26.
 */

public class BaseFragment extends Fragment implements FragmentBackListener {
    @Override
    public boolean onBackPressed() {
        return false;
    }
}
