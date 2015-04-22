package retrofit.cardekho;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.cardekho.R;
import com.cardekho.base.MyApplication;

import retrofit.RestAdapter;

public class BaseActivity extends FragmentActivity {

    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        this.context = this;
    }

    public RestAdapter getRestAdaptor() {
        return ((MyApplication) getApplicationContext()).getRestAdapter();
    }
}
