package com.example.babybuy.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.babybuy.R;
import com.example.babybuy.adapters.HomeAdapter;
import com.example.babybuy.adapters.PopularAdapters;
import com.example.babybuy.adapters.RecommendedAdapter;
import com.example.babybuy.models.HomeCategory;
import com.example.babybuy.models.PopularModel;
import com.example.babybuy.models.RecommendedModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeFragment extends Fragment {

    ScrollView scrollView;
    ProgressBar progressBar;

    RecyclerView popularRec,homeCatRec,recommendedRec;
    FirebaseFirestore db;

    List<PopularModel>popularModelList;
    PopularAdapters popularAdapters;

    List<HomeCategory> categoryList;
    HomeAdapter homeAdapter;

    List<RecommendedModel>recommendedModelList;
    RecommendedAdapter recommendedAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home,container,false);
        db = FirebaseFirestore.getInstance();

        popularRec = root.findViewById(R.id.pop_rec);
        homeCatRec = root.findViewById(R.id.explore_rec);
        recommendedRec = root.findViewById(R.id.recommended_rec);
        scrollView = root.findViewById(R.id.scroll_view);
        progressBar =  root.findViewById(R.id.progressbar);

        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);


        popularRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        popularModelList = new ArrayList<>();
        popularAdapters = new PopularAdapters(getActivity(),popularModelList);
        popularRec.setAdapter(popularAdapters);


        db.collection("PopularProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PopularModel popularModel = document.toObject(PopularModel.class);
                                popularModelList.add(popularModel);
                                popularAdapters.notifyDataSetChanged();

                                progressBar.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error "+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });




//        homeCatRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
//        categoryList = new ArrayList<>();
//        homeAdapter = new HomeAdapter(getActivity(),categoryList);
//        homeCatRec.setAdapter(homeAdapter);
//
//
//        db.collection("HomeCategory")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//
//                               HomeCategory homeCategory = document.toObject(HomeCategory.class);
//                                categoryList.add(homeCategory);
//                                homeAdapter.notifyDataSetChanged();
//                            }
//                        } else {
//                            Toast.makeText(getActivity(), "Error "+task.getException(), Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                });


        recommendedRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        recommendedModelList = new ArrayList<>();
        recommendedAdapter = new RecommendedAdapter(getActivity(),recommendedModelList);
        recommendedRec.setAdapter(recommendedAdapter);


        db.collection("Recommended")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<RecommendedModel> recommendedModelList = new ArrayList<>();

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                RecommendedModel recommendedModel = document.toObject(RecommendedModel.class);
                                recommendedModelList.add(recommendedModel);
                            }

                            // Check if there are any documents in the collection
                            if (!recommendedModelList.isEmpty()) {
                                // Generate a random index to select a document
                                int randomIndex = new Random().nextInt(recommendedModelList.size());

                                // Get the randomly selected document
                                RecommendedModel randomRecommendedModel = recommendedModelList.get(randomIndex);

                                // Now you can use 'randomRecommendedModel' as the randomly selected document
                                // Do whatever you need to do with it

                                // If you want to notify your adapter or perform any other actions with the random document, you can do it here
                            } else {
                                Toast.makeText(getActivity(), "No documents found in the collection", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });




//        ImageSlider imageSlider = root.findViewById(R.id.image_slider);
//        List<SlideModel> slideModels = new ArrayList<>();


//        slideModels.add(new SlideModel(R.drawable.banner4,"", ScaleTypes.CENTER_CROP));
//        slideModels.add(new SlideModel(R.drawable.banner5,"", ScaleTypes.CENTER_CROP));
//        slideModels.add(new SlideModel(R.drawable.banner3,"", ScaleTypes.CENTER_CROP));
//
//        imageSlider.setImageList(slideModels);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}