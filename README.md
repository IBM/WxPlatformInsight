# WxPlatformInsight

This project provides Prometheus metrics on IS assets.

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
