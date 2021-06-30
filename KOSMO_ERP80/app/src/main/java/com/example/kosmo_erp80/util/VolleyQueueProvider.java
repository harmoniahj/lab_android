package com.example.kosmo_erp80.util;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.Map;
<<<<<<< HEAD

public final class VolleyQueueProvider {
    private VolleyQueueProvider() {
    }

=======
public final class VolleyQueueProvider {
    private VolleyQueueProvider() {
    }
>>>>>>> d968ed16f89bcdbf2db9385a3f3d1c4e2ff84af1
    public static Context app = null;
    private static class LazyHolder {
        private static final RequestQueue requestQueue = Volley.newRequestQueue(app);
    }
<<<<<<< HEAD

=======
>>>>>>> d968ed16f89bcdbf2db9385a3f3d1c4e2ff84af1
    public static RequestQueue initRequestQueue(Context context) {
        if (context != null) {
            app = context;
//            Log.e("app: ", app.toString());
//            Log.e("LazyHolder.requestQueue: ", LazyHolder.requestQueue.toString());
            return LazyHolder.requestQueue;
        }
        return null;
    }
    public static RequestQueue openQueue() {
        if (app != null) {
            return LazyHolder.requestQueue;
        }
        return null;
    }
    public static void openQueue(Request request) {
        if (app != null) {
            openQueue().add(request);
        } else {
            Log.e("[error] openQueue", "app == null");
        }
    }
    public static void callbackVolley(@NonNull final VolleyCallback volleyCallBack, String path, final Map<String, String> pMap) {
        Object result;
<<<<<<< HEAD
        final String url = "http://172.30.1.36:8000/" + path + ".jsp";
=======
        final String url = "http://192.168.0.244:7000/" + path;
>>>>>>> d968ed16f89bcdbf2db9385a3f3d1c4e2ff84af1
//        final String url = "http://172.30.1.18:8000/android/androidOracleConnection.jsp";
        Log.e("url: ", url);
        try {
            RequestFuture<Object> future = RequestFuture.newFuture();
            StringRequest request = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i(this.getClass().getName(),"sname:"+response);
                            volleyCallBack.onResponse(response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            volleyCallBack.onErrorResponse(error);
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return pMap;
                }
            };
            request.setShouldCache(false);
            openQueue(request);
        } catch (Exception e) {
            Log.e("error", e.toString());
        }
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> d968ed16f89bcdbf2db9385a3f3d1c4e2ff84af1
