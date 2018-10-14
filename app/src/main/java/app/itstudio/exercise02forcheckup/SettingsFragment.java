/*
 * Created by Evgeny V. Lobach on 07.10.18 21:42
 * Copyright (c) 2018 | www.itstudio.app | All rights reserved.
 * Last modified 07.10.18 21:42
 */

package app.itstudio.exercise02forcheckup;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Objects;


public class SettingsFragment extends Fragment {

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        /*Bundle args = new Bundle();
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RadioButton radio = null;

        switch (SearchHelper.getInstance(Objects.requireNonNull(getActivity())).getName()) {
            case SearchHelper.GoogleSearchHelper.NAME:
                radio = Objects.requireNonNull(getActivity()).findViewById(R.id.googleSearch);
                break;
            case SearchHelper.YandexSearchHelper.NAME:
                radio = Objects.requireNonNull(getActivity()).findViewById(R.id.yandexSearch);
                break;
            case SearchHelper.BingSearchHelper.NAME:
                radio = Objects.requireNonNull(getActivity()).findViewById(R.id.bingSearch);
                break;
        }
        if (null != radio) {
            radio.setChecked(true);
        }

        RadioGroup buttons = Objects.requireNonNull(getActivity()).findViewById(R.id.searchSystems);

        buttons.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.googleSearch:
                        Toast.makeText(getContext(), getString(R.string.set_to) + " " + SearchHelper.GoogleSearchHelper.NAME, Toast.LENGTH_SHORT).show();
                        SearchHelper.getInstance(getActivity()).setSearchSystem(SearchHelper.GoogleSearchHelper.NAME);
                        break;
                    case R.id.yandexSearch:
                        Toast.makeText(getContext(), getString(R.string.set_to) + " " + SearchHelper.YandexSearchHelper.NAME, Toast.LENGTH_SHORT).show();
                        SearchHelper.getInstance(getActivity()).setSearchSystem(SearchHelper.YandexSearchHelper.NAME);
                        break;
                    case R.id.bingSearch:
                        Toast.makeText(getContext(), getString(R.string.set_to) + " " + SearchHelper.BingSearchHelper.NAME, Toast.LENGTH_SHORT).show();
                        SearchHelper.getInstance(getActivity()).setSearchSystem(SearchHelper.BingSearchHelper.NAME);
                        break;
                }
            }
        });
    }
}
