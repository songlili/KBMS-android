package com.cwp.kbms.Fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cwp.kbms.Adapter.FileListAdapter;
import com.cwp.kbms.R;
import com.cwp.kbms.UI.MainActivity;
import com.cwp.kbms.Util.HintUtil;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener {

    private Context context;
    private ListView homeLV;
    private FileListAdapter adapter;
    private ArrayList<String> list;

    //Fragments
    private Fragment docFragment;
    private Fragment musicFragment;
    private Fragment picFragment;
    private Fragment videoFragment;
    private Fragment homeFragment;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2, Context context) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getActivity();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        docFragment = MainActivity.docFragment;
        musicFragment = MainActivity.musicFragment;
        picFragment = MainActivity.picFragment;
        videoFragment = MainActivity.videoFragment;
        homeFragment = MainActivity.homeFragment;

        list = new ArrayList<>();
        list.add("Picture");
        list.add("Video");
        list.add("Documents");
        list.add("Music");
        list.add("Home");

        adapter = new FileListAdapter(list, context);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        homeLV = (ListView) view.findViewById(R.id.home_list);
        homeLV.setAdapter(adapter);
        homeLV.setOnItemClickListener(this);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * 对列表项事件的处理
     *
     * @param parent   父视图
     * @param view     本视图
     * @param position 位置
     * @param id       列表项id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        MainActivity mainActivity = (MainActivity) getActivity();
        FragmentTransaction action = mainActivity.getFragmentManager().beginTransaction();

        switch (position) {
            case 0:
                action.replace(R.id.fragment_container, picFragment);
                HintUtil.alert(context, "Tap to get in Picture Fragment.");
                break;
            case 1:
                action.replace(R.id.fragment_container, videoFragment);
                HintUtil.alert(context, "Tap to get in Video Fragment.");
                break;
            case 2:
                action.replace(R.id.fragment_container, docFragment);
                HintUtil.alert(context, "Tap to get in Document Fragment.");
                break;
            case 3:
                action.replace(R.id.fragment_container, musicFragment);
                HintUtil.alert(context, "Tap to get in Music Fragment.");
                break;
            case 4:
                if (!homeFragment.isAdded())
                    action.replace(R.id.fragment_container, homeFragment);
                HintUtil.alert(context, "Tap to get in Common File Fragment.");
                break;
            default:
                break;
        }
        action.commit();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
