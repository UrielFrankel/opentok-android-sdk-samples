package com.tokbox.sample.basicvoipcall;

import android.net.Uri;
import android.os.Build;
import android.telecom.Connection;
import android.telecom.ConnectionRequest;
import android.telecom.ConnectionService;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.util.Log;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.M)
public class OTConnectionService extends ConnectionService {
    public OTConnectionService() {
        super();
    }

    @Override
    public Connection onCreateIncomingConnection(PhoneAccountHandle connectionManagerPhoneAccount, ConnectionRequest request) {
        super.onCreateIncomingConnection(connectionManagerPhoneAccount, request);
        Log.i("CallConnectionService", "onCreateIncomingConnection");
        VoIPConnection conn = new VoIPConnection(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            conn.setConnectionProperties(Connection.PROPERTY_SELF_MANAGED);
        }
        conn.setCallerDisplayName("Incoming Test call", TelecomManager.PRESENTATION_ALLOWED);
        conn.setAddress(Uri.parse("tel:" + "+00000000000"), TelecomManager.PRESENTATION_ALLOWED);
        conn.setInitializing();
        conn.setActive();
        return conn;
    }

    @Override
    public void onCreateIncomingConnectionFailed(PhoneAccountHandle connectionManagerPhoneAccount, ConnectionRequest request) {
        super.onCreateIncomingConnectionFailed(connectionManagerPhoneAccount, request);
    }

    @Override
    public void onCreateOutgoingConnectionFailed(PhoneAccountHandle connectionManagerPhoneAccount, ConnectionRequest request) {
        super.onCreateOutgoingConnectionFailed(connectionManagerPhoneAccount, request);
    }

    @Override
    public Connection onCreateOutgoingConnection(PhoneAccountHandle connectionManagerPhoneAccount, ConnectionRequest request) {
        super.onCreateOutgoingConnection(connectionManagerPhoneAccount, request);
        Log.i("CallConnectionService", "onCreateOutgoingConnection");
        VoIPConnection conn = new VoIPConnection(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            conn.setConnectionProperties(Connection.PROPERTY_SELF_MANAGED);
        }
        conn.setCallerDisplayName("Test Call", TelecomManager.PRESENTATION_ALLOWED);
        conn.setAddress(Uri.parse("tel:" + "+00000000000"), TelecomManager.PRESENTATION_ALLOWED);
        conn.setInitializing();
        conn.setActive();
        conn.setAudioModeIsVoip(true);
        return conn;
    }
}
