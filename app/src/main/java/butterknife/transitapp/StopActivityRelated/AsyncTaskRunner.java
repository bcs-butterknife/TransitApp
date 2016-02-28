package butterknife.transitapp.StopActivityRelated;

import android.os.AsyncTask;

/**
 * Created by Trevor on 2/28/2016.
 */
public class AsyncTaskRunner extends AsyncTask<Void, Void, String> {

    private String stopNumber;

    public AsyncTaskRunner(String stopNumber) {
        this.stopNumber = stopNumber;
    }

    protected String doInBackground(Void... urls) {
        Feed.execute(stopNumber);
        return stopNumber;
    }

    /*
     * (non-Javadoc)
     *
     * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
     */
    @Override
    protected void onPostExecute(String result) {
        // execution of result of Long time consuming operation
    }

    /*
     * (non-Javadoc)
     *
     * @see android.os.AsyncTask#onPreExecute()
     */
    @Override
    protected void onPreExecute() {
        // Things to be done before execution of long running operation. For
        // example showing ProgessDialog
    }


}
