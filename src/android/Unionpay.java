package com.santinowu.cordova.unionpay;

import android.util.Log;
import android.content.Intent;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import com.unionpay.UPPayAssistEx;
import com.unionpay.uppay.PayActivity;

public class Unionpay extends CordovaPlugin {

    public final static String LOG_TAG = "com.santinowu.cordova.unionpay";
    public final static String UNIONPAY_MODE_KEY = "UnionpayMode";
    public final static String UNIONPAY_TRANSACTION_NUMBER_KEY = "tn";

    public final static String RESULT_SUCCESS         = "success";
    public final static String RESULT_FAIL            = "fail";
    public final static String RESULT_CANCEL          = "cancel";
    public final static String RESULT_SUCCESS_MESSAGE = "支付成功";
    public final static String RESULT_FAIL_MESSAGE    = "支付失败";
    public final static String RESULT_CANCEL_MESSAGE  = "用户取消支付";

    public CallbackContext unionpayCallbackContext;

    /**
     * Payment mode, defaults to "00"
     *   * "00" => Official
     *   * "01" => Test
     */
    public static String mMode = "00";

    @Override
    public void pluginInitialize() {
        super.pluginInitialize();

        initMode();
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        Log.d(LOG_TAG, String.format("Invoking action \"%s\".", action));

        unionpayCallbackContext = callbackContext;
        cordova.setActivityResultCallback(this);

        if ("pay".equals(action)) {
            try {
                String tn = args.getString(0);

                Log.d(LOG_TAG, String.format("Start payment with transaction number \"%s\" and mode \"%s\".", tn, mMode));

                UPPayAssistEx.startPayByJAR(cordova.getActivity(), PayActivity.class, null, null, tn, mMode);
            } catch (Exception e) {
                callbackContext.error(e.getMessage());
            }

            return true;
        }

        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (null == intent) {
            unionpayCallbackContext.error("Fail to get result with intent");

            return;
        }

        String result = intent.getExtras().getString("pay_result");

        if (RESULT_SUCCESS.equals(result)) {
            unionpayCallbackContext.success(RESULT_SUCCESS_MESSAGE);
        } else if (RESULT_FAIL.equals(result)) {
            unionpayCallbackContext.error(RESULT_FAIL_MESSAGE);
        } else if (RESULT_CANCEL.equals(result)) {
            unionpayCallbackContext.error(RESULT_CANCEL_MESSAGE);
        }
    }

    private void initMode() {
        mMode = preferences.getString(UNIONPAY_MODE_KEY, "00");

        Log.d(LOG_TAG, String.format("Initialized payment mode as %s.", mMode));
    }

}
