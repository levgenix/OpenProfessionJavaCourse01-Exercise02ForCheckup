/*
 * Created by Evgeny V. Lobach on 07.10.18 22:15
 * Copyright (c) 2018 | www.itstudio.app | All rights reserved.
 * Last modified 07.10.18 21:54
 */

package app.itstudio.exercise02forcheckup;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;


public class SearchFragment extends Fragment {

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        /*Bundle args = new Bundle();
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final SearchHelper helper = SearchHelper.getInstance(Objects.requireNonNull(getActivity()));

        Button btn = Objects.requireNonNull(getActivity()).findViewById(R.id.searchButton);
        btn.setText(btn.getText().toString() + " " + helper.getName());
        final EditText et = getActivity().findViewById(R.id.searchString);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(helper.getUri(et.getText().toString()));
                startActivity(intent);
            }
        });
    }
}
