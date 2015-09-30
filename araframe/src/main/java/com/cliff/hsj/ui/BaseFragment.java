package com.cliff.hsj.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cliff.hsj.utils.LogUtils;


/**
 * A placeholder fragment containing a simple view.
 */
public class BaseFragment extends Fragment {

    /**
     * 日志的标记
     */
    protected final String TAG = getClass().getSimpleName();

    @Override
    public void onAttach(Activity activity) {
        LogUtils.i(TAG, "-----onAttach-----");
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i(TAG, "-----onCreate-----");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogUtils.i(TAG, "-----onCreateView-----");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        LogUtils.i(TAG, "-----onActivityCreated-----");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        LogUtils.i(TAG, "-----onStart-----");
        super.onStart();
    }

    @Override
    public void onResume() {
        LogUtils.i(TAG, "-----onResume-----");
        super.onResume();
    }

    @Override
    public void onPause() {
        LogUtils.i(TAG, "-----onPause-----");
        super.onPause();
    }

    @Override
    public void onStop() {
        LogUtils.i(TAG, "-----onStop-----");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        LogUtils.i(TAG, "-----onDestroyView-----");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        LogUtils.i(TAG, "-----onDestroy-----");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        LogUtils.i(TAG, "-----onDetach-----");
        super.onDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        LogUtils.i(TAG, "-----onSaveInstanceState-----");
        super.onSaveInstanceState(outState);
    }

}