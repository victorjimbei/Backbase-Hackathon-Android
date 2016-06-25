package com.vjimbei.backbase_hackathon_android.ui.fragment;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.data.Value;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.fitness.result.DataSourcesResult;
import com.vjimbei.backbase_hackathon_android.BuildConfig;
import com.vjimbei.backbase_hackathon_android.Mvp.TaskDetailsMvp;
import com.vjimbei.backbase_hackathon_android.PhoneUnlockedReceiver;
import com.vjimbei.backbase_hackathon_android.R;
import com.vjimbei.backbase_hackathon_android.entity.MilestoneUnitTypeEnum;
import com.vjimbei.backbase_hackathon_android.entity.Task;
import com.vjimbei.backbase_hackathon_android.entity.TaskStatistics;
import com.vjimbei.backbase_hackathon_android.entity.TaskStatusEnum;
import com.vjimbei.backbase_hackathon_android.presenter.TaskDetailsPresenter;
import com.vjimbei.backbase_hackathon_android.ui.utils.ApplicationPreferences;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskDetailsFragment extends Fragment implements EditTaskFragment.OnTaskUpdated, TaskDetailsMvp.View {

    private static final String ARGS_TASK = "args.task.details";
    private static final int DIALOG_FRAGMENT = 112;
    public static final String TAG = "BasicSensorsApi";
    private PhoneUnlockedReceiver receiver;
    private ApplicationPreferences applicationPreferences;

    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;

    private TextView title;
    private TextView status;
    private TextView description;
    private TextView milestone;
    private TextView revenue;
    private TextView progressVlue;
    private ProgressBar progressBar;
    private Button edit;
    private Button startStopBtn;

    private Task task;
    private GoogleApiClient mClient = null;
    private OnDataPointListener mListener;
    private TaskDetailsMvp.Presenter presenter;
    private CombinedChart mChart;
    protected String[] mMonths = new String[]{
            "20/6", "21/6", "22/6", "23/6", "24/6", "25/6"
    };
    private final int itemcount = 6;

    public static TaskDetailsFragment newInstance(Task task) {
        TaskDetailsFragment fragment = new TaskDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGS_TASK, task);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        task = getArguments().getParcelable(ARGS_TASK);
        presenter = new TaskDetailsPresenter(getContext(), this);

        if (!checkPermissions()) {
            requestPermissions();
        }
        applicationPreferences = new ApplicationPreferences(getActivity());
        receiver = new PhoneUnlockedReceiver();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_details, container, false);
        title = (TextView) view.findViewById(R.id.title);
        status = (TextView) view.findViewById(R.id.status);
        milestone = (TextView) view.findViewById(R.id.milestone);
        revenue = (TextView) view.findViewById(R.id.revenue);
        description = (TextView) view.findViewById(R.id.description);
        progressVlue = (TextView) view.findViewById(R.id.progress_value);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        edit = (Button) view.findViewById(R.id.btn_edit);
        startStopBtn = (Button) view.findViewById(R.id.btn_start_stop);

        mChart = (CombinedChart) view.findViewById(R.id.chart1);
        mChart.setDescription("");
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);

        // draw bars behind lines
        mChart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.BUBBLE, CombinedChart.DrawOrder.CANDLE, CombinedChart.DrawOrder.LINE, CombinedChart.DrawOrder.SCATTER
        });
        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinValue(0f); // this replaces setStartAtZero(true)

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinValue(0f); // this replaces setStartAtZero(true)

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);

        CombinedData data = new CombinedData(mMonths);

        data.setData(generateLineData());
        data.setData(generateBarData());

        mChart.setData(data);
        mChart.invalidate();
        return view;
    }


    private LineData generateLineData() {

        LineData d = new LineData();

        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int index = 0; index < itemcount; index++)
            entries.add(new Entry(getRandom(task.getMilestoneLimit() - 3, +3), index));

        LineDataSet set = new LineDataSet(entries, "Milestone Set");
        set.setColor(Color.rgb(255, 64, 129));
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.rgb(255, 64, 129));
        set.setCircleRadius(5f);
        set.setFillColor(Color.rgb(255, 64, 129));
        set.setDrawCubic(true);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.rgb(255, 64, 129));

        set.setAxisDependency(YAxis.AxisDependency.LEFT);

        d.addDataSet(set);

        return d;
    }

    private BarData generateBarData() {

        BarData d = new BarData();

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        for (int index = 0; index < itemcount; index++)
            entries.add(new BarEntry(getRandom(task.getMilestoneLimit() - 5, +7), index));

        BarDataSet set = new BarDataSet(entries, "Achieved");
        set.setColor(Color.rgb(63, 81, 181));
        set.setValueTextColor(Color.rgb(63, 81, 181));
        set.setValueTextSize(10f);
        d.addDataSet(set);

        set.setAxisDependency(YAxis.AxisDependency.LEFT);

        return d;
    }

    private float getRandom(float range, float startsfrom) {
        return (float) (Math.random() * range) + startsfrom;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        updateUI(task);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditTaskFragment newFragment = EditTaskFragment.newInstance(task);
                newFragment.setTargetFragment(TaskDetailsFragment.this, DIALOG_FRAGMENT);
                newFragment.show(getFragmentManager(), "dialog");
            }
        });

        startStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (task.getMilestoneUnits().equalsIgnoreCase(MilestoneUnitTypeEnum.STEPS.name())) {
                    if (task.getStatus().equalsIgnoreCase(TaskStatusEnum.STARTED.name())) {
                        task.setStatus(TaskStatusEnum.NOTSTARTED.name());
                        startStopBtn.setText(getString(R.string.start_label));
//                        unregisterFitnessDataListener();
                    } else {
                        task.setStatus(TaskStatusEnum.STARTED.name());
                        startStopBtn.setText(getString(R.string.stop_label));
                        buildFitnessClient();
                    }
                    presenter.updateTask(task);
                } else if (task.getMilestoneUnits().equalsIgnoreCase(MilestoneUnitTypeEnum.UNLOCKS.name())) {
//                    getActivity().registerReceiver(receiver, new IntentFilter("android.intent.action.USER_PRESENT"));
                } else if (task.getMilestoneUnits().equalsIgnoreCase(MilestoneUnitTypeEnum.MINUTES.name())) {

                }

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (task.getStatus().equals(TaskStatusEnum.STARTED.name())) {
//            createAndSendNewTaskStatistik(String.valueOf(applicationPreferences.getUnlockCount()));
        }
    }

    @Override
    public void onPause() {
        super.onPause();
//        getActivity().unregisterReceiver(receiver);
    }

    @Override
    public void refreshTask(Task task) {
        updateUI(task);
    }

    private void updateUI(Task task) {
        if (task != null) {
            startStopBtn.setText(task.getStatus().equalsIgnoreCase(TaskStatusEnum.STARTED.name()) ? getString(R
                    .string.stop_label) : getString(R.string.start_label));
            title.setText(task.getTitle());
            status.setText(task.getStatus());
            description.setText(task.getDescription());

            revenue.setText(String.format(getContext().getString(R.string.format_revenue), task.getRevenue()));
            milestone.setText(String.format(getContext().getString(R.string.format_milestone), task
                    .getMilestoneLimit(), task.getMilestoneUnits()));
            progressVlue.setText(String.format(getContext().getString(R.string.format_milestone), task
                    .getCurrentMilestoneValue(), task.getMilestoneUnits()));
            progressBar.setMax((int) task.getMilestoneLimit());
            progressBar.setProgress((int) task.getCurrentMilestoneValue());
            presenter.updateTask(task);
        }
    }

    private void buildFitnessClient() {
        if (mClient == null && checkPermissions()) {
            mClient = new GoogleApiClient.Builder(getContext())
                    .addApi(Fitness.SENSORS_API)
                    .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ_WRITE))
                    .addConnectionCallbacks(
                            new GoogleApiClient.ConnectionCallbacks() {
                                @Override
                                public void onConnected(Bundle bundle) {
                                    Log.i(TAG, "Connected!!!");
                                    findFitnessDataSources();
                                }

                                @Override
                                public void onConnectionSuspended(int i) {
                                    if (i == GoogleApiClient.ConnectionCallbacks.CAUSE_NETWORK_LOST) {
                                        Log.i(TAG, "Connection lost.  Cause: Network Lost.");
                                    } else if (i
                                            == GoogleApiClient.ConnectionCallbacks.CAUSE_SERVICE_DISCONNECTED) {
                                        Log.i(TAG,
                                                "Connection lost.  Reason: Service Disconnected");
                                    }
                                }
                            }
                    )
                    .enableAutoManage(getActivity(), 0, new GoogleApiClient.OnConnectionFailedListener() {
                        @Override
                        public void onConnectionFailed(ConnectionResult result) {
                            Log.i(TAG, "Google Play services connection failed. Cause: " +
                                    result.toString());
                            Toast.makeText(getContext(), "Exception while connecting to Google Play services: " +
                                    result.getErrorMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .build();
        }
    }

    private void findFitnessDataSources() {
        Fitness.SensorsApi.findDataSources(mClient, new DataSourcesRequest.Builder()
                .setDataTypes(DataType.TYPE_STEP_COUNT_CUMULATIVE)
                .setDataSourceTypes(DataSource.TYPE_RAW)
                .build())
                .setResultCallback(new ResultCallback<DataSourcesResult>() {
                    @Override
                    public void onResult(DataSourcesResult dataSourcesResult) {
                        Log.i(TAG, "Result: " + dataSourcesResult.getStatus().toString());
                        for (DataSource dataSource : dataSourcesResult.getDataSources()) {
                            Log.i(TAG, "Data source found: " + dataSource.toString());
                            Log.i(TAG, "Data Source type: " + dataSource.getDataType().getName());

                            if (dataSource.getDataType().equals(DataType.TYPE_STEP_COUNT_CUMULATIVE)
                                    && mListener == null) {
                                Log.i(TAG, "Data source for LOCATION_SAMPLE found!  Registering.");
                                registerFitnessDataListener(dataSource,
                                        DataType.TYPE_STEP_COUNT_CUMULATIVE);
                            }
                        }
                    }
                });
    }

    private void registerFitnessDataListener(DataSource dataSource, DataType dataType) {
        mListener = new OnDataPointListener() {
            @Override
            public void onDataPoint(DataPoint dataPoint) {
                for (Field field : dataPoint.getDataType().getFields()) {
                    final Value val = dataPoint.getValue(field);
                    Log.i(TAG, "Detected DataPoint field: " + field.getName());
                    Log.i(TAG, "Detected DataPoint value: " + val);

                    TaskStatistics statistics = new TaskStatistics();
                    task.setCurrentMilestoneValue(val.asInt());
                    statistics.setMilestoneValue(task.getCurrentMilestoneValue());
                    statistics.setMilestoneLimit(task.getMilestoneLimit());
                    statistics.setTaskId(task.getId());
                    statistics.setDate(System.currentTimeMillis());
                    statistics.setTask(task);
                    statistics.setId(System.currentTimeMillis());
                    presenter.sendData(statistics);

                    createAndSendNewTaskStatistik(val.asString());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), val + "", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        };


        Fitness.SensorsApi.add(
                mClient,
                new SensorRequest.Builder()
                        .setDataSource(dataSource) // Optional but recommended for custom data sets.
                        .setDataType(dataType) // Can't be omitted.
                        .setSamplingRate(10, TimeUnit.SECONDS)
                        .build(),
                mListener)
                .setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        if (status.isSuccess()) {
                            Log.i(TAG, "Listener registered!");
                        } else {
                            Log.i(TAG, "Listener not registered.");
                        }
                    }
                });
    }

    private void createAndSendNewTaskStatistik(String value) {
        TaskStatistics statistics = new TaskStatistics();
        task.setCurrentMilestoneValue(Long.valueOf(value));
        statistics.setMilestoneValue(task.getCurrentMilestoneValue());
        statistics.setMilestoneLimit(task.getMilestoneLimit());
        statistics.setTaskId(task.getId());
        statistics.setDate(System.currentTimeMillis());
        presenter.sendData(statistics);
    }

    private void unregisterFitnessDataListener() {
        if (mListener == null) {
            return;
        }
        Fitness.SensorsApi.remove(
                mClient,
                mListener)
                .setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        if (status.isSuccess()) {
                            Log.i(TAG, "Listener was removed!");
                        } else {
                            Log.i(TAG, "Listener was not removed.");
                        }
                    }
                });
    }

    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                        Manifest.permission.ACCESS_FINE_LOCATION);

        if (shouldProvideRationale) {
            Log.i(TAG, "Displaying permission rationale to provide additional context.");
            Snackbar.make(
                    getActivity().findViewById(R.id.main_activity_view),
                    R.string.permission_rationale,
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.ok, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Request permission
                            ActivityCompat.requestPermissions(getActivity(),
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    REQUEST_PERMISSIONS_REQUEST_CODE);
                        }
                    })
                    .show();
        } else {
            Log.i(TAG, "Requesting permission");
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        Log.i(TAG, "onRequestPermissionResult");
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.length <= 0) {
                Log.i(TAG, "User interaction was cancelled.");
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission was granted.
                buildFitnessClient();
            } else {
                Snackbar.make(
                        getActivity().findViewById(R.id.main_activity_view),
                        R.string.permission_denied_explanation,
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.settings, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // Build intent that displays the App settings screen.
                                Intent intent = new Intent();
                                intent.setAction(
                                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package",
                                        BuildConfig.APPLICATION_ID, null);
                                intent.setData(uri);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        })
                        .show();
            }
        }
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void updateStatisticsUI(final TaskStatistics statistics) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updateUI(statistics.getTask());
            }
        });
    }

}
