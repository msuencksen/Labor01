package com.example.mazine.labor01;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "httpReq";
    private static final String ACTION_BAZ = "com.example.mazine.labor01.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.example.mazine.labor01.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.example.mazine.labor01.extra.PARAM2";

    public MyIntentService() {
        super("MyIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if (intent != null) {
            final String action = intent.getAction();

            Log.d(MainActivity.TAG,"on HandleIntent, action=" + (action == null ? "NULL" : action));

            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }

            // release wakelock
            MyReceiver.completeWakefulIntent(intent);

        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo

        String str =    HttpReq.getResponse("http://www.surfbude.de/spots/fuerteventura-sotavento.html");

        float windSpeed = extractFloat(str, "(?s)no-repeat;\"></div>.{0,4}?(\\d{1,2}.{0,4}?)kn");

        Log.d(MainActivity.TAG, "windspeed: "+windSpeed);




    }

    // liefert die erste Gruppe des ersten RegEx-Matches
    //   in source in float gewandelt zurueck
    // kein match: returnvalue = -1.0
    //

    private float extractFloat(String source, String regex) {

        Log.d(MainActivity.TAG, "match()");

        float retVal = -1.0f;

        try {

            Matcher matcher = Pattern.compile(  regex ).matcher( source );

            if ( matcher.find() ) {
                // evaluate only the first match         #
                Log.d(MainActivity.TAG, "extractFloat(): found:  " + matcher.group()  + "  !!!" + matcher.group(1) + "!!!  an Position: "
                        + matcher.start() + ", " + matcher.end());
                String found = matcher.group(1);

                    retVal = Float.parseFloat(found);

            }
        } catch (Exception e) {
            Log.d(MainActivity.TAG, "extractFloat(): Exception during format conversion: "
                    + e.toString() );
        }

    return retVal; }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
