package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentList extends Fragment implements View.OnClickListener{

    protected static final String FL_TAG = "FL_TAG";


    @Override
    public void onClick(View v) {

        int position = rep.add();
        mAdapter.notifyItemInserted(position);
    }


    private Adapter mAdapter;
    protected IListener mListener;
    protected Button button;
    protected ItemRep rep = new ItemRep();;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (requireActivity() instanceof IListener) {
            mListener = (IListener) requireActivity();
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_fragment, container, false);
        button = (Button) v.findViewById(R.id.button);
        button.setOnClickListener(this);
        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final RecyclerView recycler = view.findViewById(R.id.recycler);

        GridLayoutManager mLayout = new GridLayoutManager(getActivity(),
                getResources().getInteger(R.integer.cols), LinearLayoutManager.VERTICAL, false);
        if (savedInstanceState != null) {
            int amount = savedInstanceState.getInt(FL_TAG);
            rep = new ItemRep(amount);
        }
        mAdapter = new Adapter(rep.list(), new ClickChecker());
        recycler.setAdapter(mAdapter);
        recycler.setLayoutManager(mLayout);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(FL_TAG, rep.size());
        Log.d("HERE", "СОХРАНИЛ СОСТОЯНИЕ ЛИСТА В КЛАССЕ ЛИСТА");
    }

    class ClickChecker implements IListener{
       
        @Override
        public void onClicked(Item item) {
            if (mListener != null) {
                mListener.onClicked(item);
            }
        }
    }
}
