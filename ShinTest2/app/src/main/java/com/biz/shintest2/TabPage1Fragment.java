package com.biz.shintest2;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TabPage1Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TabPage1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabPage1Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1; // TAB1
    private String mParam2; // 첫번째 TAB

    private OnFragmentInteractionListener mListener;

    public TabPage1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabPage1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TabPage1Fragment newInstance(String param1, String param2) {
        TabPage1Fragment fragment = new TabPage1Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    TextView textView ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fav, container, false);
        textView = view.findViewById(R.id.tab_bangpage1);

        if (mParam1.equalsIgnoreCase("KBS")){
            textView.setText("KBS화면");
        }

        if (mParam1.equalsIgnoreCase("MBC")){
            textView.setText("MBC화면");
        }

        if (mParam1.equalsIgnoreCase("SBS")){
            textView.setText("SBS화면");
        }

        if (mParam1.equalsIgnoreCase("tvN")){
            textView.setText("tvN화면");
        }
        if (mParam1.equalsIgnoreCase("JTBC")){
            textView.setText("경은이 바보");
        }
        if (mParam1.equalsIgnoreCase("EBS")){
            textView.setText("민수 바보");
        }

        return view ;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    */

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
