package com.example.thewitcher3bestiary.ui.Vampires;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.thewitcher3bestiary.R;
import com.example.thewitcher3bestiary.databinding.FragmentVampiresBinding;
import com.example.thewitcher3bestiary.model.Adapter;
import com.example.thewitcher3bestiary.model.ObjectBeast;
import com.example.thewitcher3bestiary.model.Response;
import com.example.thewitcher3bestiary.model.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VampiresFragment extends Fragment {

    private FragmentVampiresBinding binding;
    private Adapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_necrophages, container, false);
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
                    // Filtrar los objetos para que solo se muestren aquellos con tipo "Beast"
                    List<ObjectBeast> beasts = response.body().getBests();
                    List<ObjectBeast> filteredBeasts = new ArrayList<>();
                    for (ObjectBeast beast : beasts) {
                        if ("Vampires".equals(beast.getType())) {
                            filteredBeasts.add(beast);
                        }
                    }
                    adapter.addAll(filteredBeasts);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}