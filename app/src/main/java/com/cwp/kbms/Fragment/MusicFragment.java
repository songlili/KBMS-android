package com.cwp.kbms.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.cwp.kbms.Adapter.FileListAdapter;
import com.cwp.kbms.Http.HttpClient;
import com.cwp.kbms.R;
import com.cwp.kbms.Util.HintUtil;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MusicFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MusicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MusicFragment extends Fragment implements AdapterView.OnItemClickListener {

    private final static String url =
            "http://192.168.173.1:8080/kb/user/category?userID=9";

    private FileListAdapter adapter;
    private ListView listView;
    private TextView tv;
    private ArrayList<String> list;
    private String jsonResult;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MusicFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MusicFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MusicFragment newInstance(String param1, String param2) {
        MusicFragment fragment = new MusicFragment();
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

        String[] strs = {"高等数学", "线性代数", "离散数学", "概率论",
                "数据结构", "解析几何", "算法导论", "数据库系统", "操作系统", "计算机网络"};
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (String s : strs) {
                list.add(s);
            }
        }

        adapter = new FileListAdapter(list, getActivity(), R.drawable.ic_menu_music);


    }

    Runnable runable = new Runnable() {
        @Override
        public void run() {
            //// TODO: 2016/4/29 网络操作
            jsonResult = HttpClient.getStringFromUrl(url);
            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("value", "result");
            msg.setData(data);
            handler.sendMessage(msg);
        }
    };



    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Bundle data = msg.getData();
            String val = data.getString("value");
            Log.i("mylog", "请求结果为--->" + val);

            //// TODO: 2016/4/29 界面操作
            tv.setText(jsonResult);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music, container, false);

        listView = (ListView) view.findViewById(R.id.audio_list);
//        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        tv = (TextView) view.findViewById(R.id.test);
        new Thread(runable).start();


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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        for (int i = 0; i < list.size(); i++) {
            if (i == position) {
                HintUtil.alert(getActivity(), "you tapped " + list.get(position) + " in " + position);
            }
        }
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
