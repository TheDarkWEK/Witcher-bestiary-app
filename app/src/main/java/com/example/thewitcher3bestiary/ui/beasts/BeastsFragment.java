package com.example.thewitcher3bestiary.ui.beasts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.thewitcher3bestiary.R;
import com.example.thewitcher3bestiary.databinding.FragmentBeastsBinding;
import com.example.thewitcher3bestiary.model.Adapter;
import com.example.thewitcher3bestiary.model.Response;
import com.example.thewitcher3bestiary.model.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BeastsFragment extends Fragment {

    private FragmentBeastsBinding binding;
    private Adapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_beasts, container, false);
        ListView listView = root.findViewById(R.id.listView);
        adapter = new Adapter(getContext(), new ArrayList<>());
        listView.setAdapter(adapter);

        fetchData();

        return root;
    }

    private void fetchData() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/TheDarkWEK/Witcher-bestiary-app/main/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Service service = retrofit.create(Service.class);

        Call<Response> call = service.getBeasts();
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.addAll(response.body().getBests());
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
