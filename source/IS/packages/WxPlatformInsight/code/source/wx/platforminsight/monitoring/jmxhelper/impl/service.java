package wx.platforminsight.monitoring.jmxhelper.impl;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.PlatformManagedObject;
import com.softwareag.util.IDataMap;
// --- <<IS-END-IMPORTS>> ---

public final class service

{
	// ---( internal utility methods )---

	final static service _instance = new service();

	static service _newInstance() { return new service(); }

	static service _cast(Object o) { return (service)o; }

	// ---( server methods )---




	public static final void getMemoryMXBean (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getMemoryMXBean)>> ---
		// @sigtype java 3.5
		// [i] field:0:required unit {"GB","MB","KB"}
		// [o] record:0:required heapSize
		// [o] - object:0:required initialSize
		// [o] - object:0:required maxSize
		// [o] - object:0:required usedSize
		// [o] - object:0:required freeSize
		// [o] record:0:required offheapSize
		// [o] - object:0:required initialSize
		// [o] - object:0:required usedSize
		IDataMap pipelineMap = new IDataMap(pipeline);
		
		MemoryMXBean memory = ManagementFactory.getMemoryMXBean();
		MemoryUsage heapMemory = memory.getHeapMemoryUsage();
		MemoryUsage offHeapMemory = memory.getNonHeapMemoryUsage();
		int objectsPendingFinalization = memory.getObjectPendingFinalizationCount();
		
		String unit = pipelineMap.getAsString("unit", "GB").toUpperCase();
		
		long heapInitialSizeInBytes = heapMemory.getInit();
		long heapMaxSizeInBytes = heapMemory.getMax();
		long heapUsedSizeInBytes = heapMemory.getUsed();
		
		long offheapInitialSizeInBytes = offHeapMemory.getInit();
		long offheapMaxSizeInBytes = offHeapMemory.getMax();
		long offheapUsedSizeInBytes = offHeapMemory.getUsed();		
		
		double factor = 1;
		
		if ("GB".equals(unit)){
			factor = Math.pow(1024, 3);
		}
		else if ("MB".equals(unit)){
			factor = Math.pow(1024, 2);
		}
		else if ("KB".equals(unit)){
			factor = Math.pow(1024, 1);
		}
		
		IDataMap heapSize = new IDataMap();
		heapSize.put("initialSize", heapInitialSizeInBytes / factor);
		heapSize.put("maxSize", heapMaxSizeInBytes / factor);
		heapSize.put("usedSize", heapUsedSizeInBytes / factor);
		heapSize.put("freeSize", (heapMaxSizeInBytes - heapUsedSizeInBytes) / factor);
		
		IDataMap offheapSize = new IDataMap();
		offheapSize.put("initialSize", offheapInitialSizeInBytes / factor);
		offheapSize.put("usedSize", offheapUsedSizeInBytes / factor);		
		
		pipelineMap.put("heapSize", heapSize);
		pipelineMap.put("offheapSize", offheapSize);
		
		
		
			
		// --- <<IS-END>> ---

                
	}
}

