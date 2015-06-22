package ch.swissonid.design_lib_sample.fragments;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import ch.swissonid.design_lib_sample.R;
import ch.swissonid.design_lib_sample.adapters.RVArrayAdapter;
import ch.swissonid.design_lib_sample.util.DatabaseClass;
import ch.swissonid.design_lib_sample.util.JSONParser;
import ch.swissonid.design_lib_sample.util.ParvamObj;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StandardAppBarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StandardAppBarFragment extends BaseFragment {

    private static final int AMOUNT_OF_DATA = 50;

    private static String TAG = "StandardAppBarFragment";

    private DatabaseClass databaseClass;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment StandardAppBarFragment.
     */
    public static StandardAppBarFragment newInstance() {
        return new StandardAppBarFragment();
    }

    public StandardAppBarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        databaseClass = new DatabaseClass(getActivity());
        //addParvamData();
        new doEnrollProcess().execute();
        setList();
    }

    private void addParvamData() {
        String[] stringsera = getActivity().getResources().getStringArray(R.array.parvam_array);

        for (int i=0;i < stringsera.length;i++){
            databaseClass.addParvams(new ParvamObj(stringsera[i],"Chapter1",1988));
        }

        //databaseClass.addParvams(new ParvamObj("??? ?????? ","Chapter1",1988));
        //databaseClass.addParvams(new ParvamObj("??? ?????? ","Chapter2",1989));
        //databaseClass.addParvams(new ParvamObj("??? ?????? ??? ?????? ","Chapter3",1990));
    }

    private void setList() {
        RecyclerView recyclerView = ButterKnife.findById(getActivity(),R.id.simpleList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        RVArrayAdapter arrayAdapter = new RVArrayAdapter(getActivity(),getData());
        recyclerView.setAdapter(arrayAdapter);
    }

    @NonNull
    private ArrayList<ParvamObj> getData() {

        ArrayList<ParvamObj> parvamsList =databaseClass.getAllParvams();

        return parvamsList;
    }

    @Override
    protected int getTitle() {
        return R.string.standard_app_bar_menu_title;
    }

    @Override
    public boolean hasCustomToolbar() {
        return true;
    }

    @Override
    protected int getLayout() {
        return R.layout.standard_app_bar_fragment;
    }

    ProgressDialog prgDialog;

    class doEnrollProcess extends AsyncTask<String, String, Boolean> {

        // Show Progress bar
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Shows Progress Bar Dialog and then call doInBackground method
            // showDialog(progress_bar_type);
            // prgDialog = new ProgressDialog(getApplicationContext());
            prgDialog = new ProgressDialog(getActivity());
            prgDialog.setTitle("Loading");
            prgDialog.setMessage("Please wait...");
            prgDialog.setCancelable(false);
            prgDialog.setIndeterminate(true);
            prgDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... f_url) {
            String jsonResponseString = "";
            String jsonRequestString = "";

            try {

                JSONParser jsonParser = new JSONParser();

                JSONObject jsonResponseObject =
                        jsonParser.getJSONFromUrl("http://www.activetechvizag.com/parvams.txt");

                if (jsonResponseObject.getBoolean("success")) {

                    JSONArray jsonArray = jsonResponseObject.getJSONArray("parvams");
                    for (int i=0;i<jsonArray.length();i++){
                        String s = jsonArray.getJSONObject(i).getString("name");
                        Log.d(TAG,"---->>> "+s);
                        databaseClass.addParvams(new ParvamObj(s, "Chapter1", 1988));
                    }
                    return true;

                } else {

                    return false;
                }

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        /*
         * protected void onProgressUpdate(String... progress) { // Set progress
         * percentage //prgDialog.setProgress(Integer.parseInt(progress[0])); }
         */
        @Override
        protected void onPostExecute(Boolean isenrolledsuccess) {

            try {
                if (isenrolledsuccess) {

                    Toast.makeText(getActivity(),
                            "Enrolled Successfully...", Toast.LENGTH_SHORT)
                            .show();

                } else {
                    Toast.makeText(getActivity(), "some error",
                            Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                prgDialog.dismiss();
            }

            prgDialog.dismiss();

        }
    }


}
