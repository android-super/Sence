package com.sence.base;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sence.R;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseMainFragment extends Fragment {
    public abstract void onRefresh();
}
