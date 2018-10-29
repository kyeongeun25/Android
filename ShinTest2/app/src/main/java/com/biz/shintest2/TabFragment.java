package com.biz.shintest2;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TabFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TabFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TabFragment newInstance(String param1, String param2) {
        TabFragment fragment = new TabFragment();
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

    // Tablayout에 포함될 SubFragment들을 생성, 관리 해주는 곳
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bcs, container, false);
        TabLayout tabLayout_bcs = view.findViewById(R.id.tab_menu_bcs);
        ViewPager vp_bcs = view.findViewById(R.id.vp_bcs);
        vp_bcs.setAdapter(new TabPageAdapter(getFragmentManager()));
        tabLayout_bcs.setupWithViewPager(vp_bcs);
        return view ;
    }

    private class TabPageAdapter extends FragmentStatePagerAdapter {

        public TabPageAdapter(FragmentManager fm) {
            super(fm);
        }

        // TabLayout 각 제목을 설정
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0 : return "KBS";
                case 1 : return "MBC";
                case 2 : return "SBS";
                case 3 : return "tvN";
                case 4 : return "JTBC";
                case 5 : return "EBS";

            }
            return super.getPageTitle(position);
        }

        // 화면에 표시할 Fragment를 설정
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0 : return TabPage1Fragment.newInstance("KBS","첫번째 페이지");
                case 1 : return TabPage1Fragment.newInstance("MBC","두번째 페이지");
                case 2 : return TabPage1Fragment.newInstance("SBS","세번째 페이지");
                case 3 : return TabPage1Fragment.newInstance("tvN","네번째 페이지");
                case 4 : return TabPage1Fragment.newInstance("JTBC","다섯번째 페이지");
                case 5 : return TabPage1Fragment.newInstance("EBS","여섯번째 페이지");

            }
            return null;
        }

        @Override
        public int getCount() {
            return 6;
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);//       if (context instanceof OnFragmentInteractionListener) {
//           mListener = (OnFragmentInteractionListener) context;
//        } else {
//           throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
    //    }
  //  }

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
