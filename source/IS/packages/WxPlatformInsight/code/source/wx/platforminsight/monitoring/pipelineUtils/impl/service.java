package wx.platforminsight.monitoring.pipelineUtils.impl;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.softwareag.util.IDataMap;
// --- <<IS-END-IMPORTS>> ---

public final class service

{
	// ---( internal utility methods )---

	final static service _instance = new service();

	static service _newInstance() { return new service(); }

	static service _cast(Object o) { return (service)o; }

	// ---( server methods )---




	public static final void getMetricFromPipeline (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getMetricFromPipeline)>> ---
		// @sigtype java 3.5
		// [i] field:0:required metricName
		// [o] recref:0:required metric wx.prometheus.pub.documents:metric
		IDataMap plMap = new IDataMap(pipeline);
		plMap.put("metric", plMap.get(plMap.get("metricName")));
		// --- <<IS-END>> ---

                
	}
}

