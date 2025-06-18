# WxPlatformInsight

This project provides Prometheus metrics on IS assets.

## Sample Grafana Dashboard

A sample Grafana dashboard for metrics which are provided by `WxPlatformInsight` and `WxPlatformInsight4PE` are [here](./resources/dashboards/webMethods.json) 

## Related Work

* [WxPrometheus](https://github.com/IBM/WxPrometheus)
* [WxPlatformMonitoring](https://github.com/IBM/WxPlatformMonitoring)

## Dependencies 

Following dependent packages must be also installed ...

* [WxPrometheus](https://github.com/IBM/WxPrometheus)
* [WxPlatformMonitoring](https://github.com/IBM/WxPlatformMonitoring)

## Version History

### v1.0.0

Initial release.

### v2.0.0

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

### v2.0.1

Add label `threadid` in metric `sag_is_thread_cpu_usage` to avoid warning message `Error on ingesting samples with different value but same timestamp` in Prometheus server.

### v2.0.2

Watt property `watt.wx.platformMonitoring.thread.top.enabled` added. Switch to disable or enable the evaluation and list of the top (50) threads. The default is `true`.

### v2.2

Same version as `v2.0.2`. Switch version numbering from 3 digits `2.0.2` to 2 digits `2.2`.

### v2.3

Following changes are done in monitoring of running services ...

* `sag_is_services_running` ignores services in `Wx` packages.
* `sag_is_service_running` provides the name of running service as label

### v2.4

Performance and runtime metrics for IS (flow-) services added. For retrieving these metrics the internal IS monitor must be enabled.

## Disclaimer

### IBM Public Repository Disclosure

All content in these repositories including code has been provided by IBM under the associated open source software license and IBM is under no obligation to provide enhancements, updates, or support. IBM developers produced this code as an open source project (not as an IBM product), and IBM makes no assertions as to the level of quality nor security, and will not be maintaining this code going forward.