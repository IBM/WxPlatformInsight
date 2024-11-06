# WxPlatformInsight

This project provides Prometheus metrics on IS assets.

## Sample Grafana Dashboard

A sample Grafana dashboard for metrics which are provided by `WxPlatformInsight` and `WxPlatformInsight4PE` are [here](./dashboards/webMethods.json) 

## Version History

### 1.0.0 `WxPlatformInsight` Package

Initial release.

### 2.0.0 `WxPlatformInsight` Package

* Testing with wM 10.15
* Testing with WxPlatforMonitoring 2.0.0
* EntireX Adapter support
* Adapter Pool metrics added
* Label `package` added for connections, listeners, notifications and trigger. The label allows you to organize the dashboard business oriented.
* Loop about adapter types. Avoid error if e.g. JDBC Adapter is not installed
* New JDBC Pool metrics `sag_is_jdbcpool_connections_is_in_fail_fast_mode` added
* Replace metric name `sag_thread_...` by `sag_is_thread_...`
* Retrieve CPU usage. New metric `sag_is_process_cpu_seconds_total` added
* IS Server stats metrics `sag_is_service_error_count` and `sag_is_requests_count` added
* Label `package` added on Scheduler Tasks

### 1.0.0 `WxPlatformInsight4PE` Package

This package is optional and provides metrics of process which are running inside Process Engine. To provide the metrics, the monitoring service `pub.monitor.process.model:getModelList` is called. The metrics `sag_pe_processes_completed`, `sag_pe_processes_failed` and `sag_pe_processes_started` can be scrapped.

*Tipp:* See the [webMethods Dashboard](./dashboards/webMethods.json) and use the `rate()` function to get the wanted monitoring signal. 

### 1.1.0 `WxPlatformInsight4PE` Package

Following metrics are added to detect non activities on process instances. The metrics are only printed for started processes:

* `sag_pe_process_non_activity_duration_time`: Time duration since last activity.
* `sag_pe_process_running_duration_time`: Time duraton since process intstance was started.

### 2.0.1 `WxPlatformInsight` Package

Add label `threadid` in metric `sag_is_thread_cpu_usage` to avoid warning message `Error on ingesting samples with different value but same timestamp` in Prometheus server.

### 1.2.0 `WxPlatformInsight4PE` Package

Started and failed processes are printed in `sag_pe_process_non_activity_duration_time` and `sag_pe_process_running_duration_time`. Additional, a label `status` is added. The process instance status is written to this label.

### 2.0.2 `WxPlatformInsight` Package

Watt property `watt.wx.platformMonitoring.thread.top.enabled` added. Switch to disable or enable the evaluation and list of the top (50) threads.
